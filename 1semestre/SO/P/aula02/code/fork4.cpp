#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#include "delays.h"
#include "process.h"

int main(void)
{
  printf("Before the fork: PID = %d, PPID = %d\n", getpid(), getppid());

  pid_t ret = pfork();
  if (ret == 0)
  {
    execl("./child", "./child", NULL);
    printf("why doesn't this message show up?\n");
    return EXIT_FAILURE;
  }
  else
  {
    printf("I'm the parent: PID = %d, PPID = %d\n", getpid(), getppid());
    usleep(20000);
    // pwait(NULL);
  }

  return EXIT_SUCCESS;
}

/* Q: The message associated to the printf instruction placed
after the execl did not show up. Why? What kind of message should
be placed after the execl?
A: When we call execl to execute another program, it replaces the
current process with the new executabled program that it child.
Therefore, any code after the excel call in the original program
will not be executed. This behaviour is by design.
Q: The first and second arguments of the call to execl are equal.
Why?
A: In the call to execl, the first and second arguments are typically
the same because the first argument represents the path to the executable
file, and the second argument is used to specify the program name. This
is considered good practice to do so for clarity and consistency but it's
not strictly necessary.
Q: Why are the values of PPID different?
A: 
*/
