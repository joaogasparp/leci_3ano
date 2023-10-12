#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include "thread.h"

int static count = 0;

void* child_thread(void* arg)
{
    int max;
    printf("VALUE [10 20] = ");
    scanf("%d", &max);

    while(count < max)
    {
        printf("[child] %d\n",count);
        usleep(100000);
        count++;
    }

    return NULL;
}

int main()
{
    int N1;
    printf("VALUE [1 9] = ");
    scanf("%d", &N1);
    
    if(N1 >= 1 && N1 <= 9)
    {
        count = N1;
        pthread_t thr;
        pthread_create(&thr, NULL, child_thread, NULL);
        pthread_join(thr, NULL);

        while(count > 0)
        {
            printf("[main] %d\n",count);
            usleep(100000);
            count--;
        }
    }
    else
    {
        printf("VALUE MUST BE BETWEEN 1 AND 9\n");
        exit(EXIT_FAILURE);
    }

    return 0;
}
