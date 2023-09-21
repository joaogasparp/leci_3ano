#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

#include "delays.h"
#include "process.h"

int main(void)
{
  printf("Before the fork:\n");
  printf("  PID = %d, PPID = %d\n", getpid(), getppid());

  pid_t ret = pfork();

  printf("After the fork:\n");
  printf("  PID = %d, PPID = %d\n", getpid(), getppid());
  bwRandomDelay(0, 10000);
  printf("  [ret = %d] Was I printed by the parent or by the child process? How can I know it?\n", ret); 

  return EXIT_SUCCESS;
}

/* Q: Can you now say which process (parent or child) print
each one of the message "Was I printed by the parent or by the
child process? How can I know it?"?
A: The value returned by the fork is diferrent in parent and
child processes, in the parent it's the PID of the child; in the
child it's always 0.
Q: Can you figure out how the return value of the fork may be
used to distinguish between parent and child processes?
A: The return value can be used as a boolean variable so we can
distinguish the code running on child and parent. In general, used
alone, the fork is of little interest. In general, we wnat to run a
different program in the child, exec system call (there are different
versions of exec). Sometimes, we want the parent to wait for the
conclusion of the running in the child, wait system call. In the
fork3.cpp, we're assuming the fork doesn't fail, in case of an error,
it return -1.
*/