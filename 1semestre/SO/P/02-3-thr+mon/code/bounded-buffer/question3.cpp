#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include "thread.h"

int static count = 0;
int static finished = 0;
static pthread_mutex_t mutex;
pthread_cond_t cond;

void* child_thread1(void* arg)
{

    while (finished == 0)
    {
        mutex_lock(&mutex);
        while (count % 2 == 0)
            cond_wait(&cond, &mutex);
        count--;
        if (count <= 2)
            finished = 1;
        printf("THREAD [1] | PID [%d]: %d\n", getpid(), count);
        cond_broadcast(&cond);
        mutex_unlock(&mutex);
    }

    return NULL;
}

void* child_thread2(void* arg)
{

    while (finished == 0)
    {
        mutex_lock(&mutex);
        while (count % 2 == 1)
            cond_wait(&cond, &mutex);
        count--;
        if (count <= 1)
            finished = 1;
        printf("THREAD [2] | PID [%d]: %d\n", getpid(), count);
        cond_broadcast(&cond);
        mutex_unlock(&mutex);
    }

    return NULL;
}

int main()
{
    int value;
    int n = 0;
    printf("VALUE [10 20] = ");
    scanf("%d", &value);
    while (value < 10 || value > 20) {
        n++;
        printf("(repeat[%d]) VALUE [10 20] = ", n);
        scanf("%d", &value);
    }

    pthread_t thr1, thr2;
    mutex_init(&mutex, NULL);
    cond_init(&cond, NULL);
    count = value;
    thread_create(&thr1, NULL, child_thread1, NULL);
    thread_create(&thr2, NULL, child_thread2, NULL);
    thread_join(thr1, NULL);
    thread_join(thr2, NULL);
    mutex_destroy(&mutex);
    cond_destroy(&cond);

    return 0;
}
