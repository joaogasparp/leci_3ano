#include <iostream>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <unistd.h>
#include <cstdlib>
#include <stdexcept>
#include <sys/wait.h>

using namespace std;

struct memoria_partilhada {
    int counter;
};

int main() {
    pid_t childPID = fork();
    key_t key = ftok("shmfile",65);
    int shmid = shmget(key,sizeof(struct memoria_partilhada),0666|IPC_CREAT);
    struct memoria_partilhada *memoria_partilhada = (struct memoria_partilhada*) shmat(shmid,(void*)0,0);
    memoria_partilhada->counter = 0;

    if (childPID == 0) {
        int value;

        while (true) {
            printf("VALUE [10 20] = ");
            scanf("%d", &value);
            printf("\n");

            if (value >= 10 && value <= 20) {
                break;
            } else {
                printf("VALUE MUST BE BETWEEN 10 AND 20\n");
            }
        }

        while (memoria_partilhada->counter < value) {
            memoria_partilhada->counter++;
            printf("[%d] = %d\n", getppid(), memoria_partilhada->counter);
        }

        exit(EXIT_SUCCESS);

    } else {
        wait(NULL);
        while (memoria_partilhada->counter > 1) {
            memoria_partilhada->counter--;
            printf("[%d] = %d\n", getppid(), memoria_partilhada->counter);
        }
    }
        
    shmdt(memoria_partilhada);
    shmctl(shmid,IPC_RMID,NULL);
    exit(EXIT_SUCCESS);
    
    return 0;
}
