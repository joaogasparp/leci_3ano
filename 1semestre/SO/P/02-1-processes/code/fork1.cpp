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

  pfork(); // equivalent to fork(), dealing internally with error situations
  
  printf("After the fork:\n");
  printf("  PID = %d, PPID = %d\n",getpid(), getppid());
  bwRandomDelay(0, 2000);
  printf("  Was I printed by the parent or by the child process? How can I know it?\n"); 
  
  return EXIT_SUCCESS;
}

/* With: bwRandomDelay(0, 100000);
Q: Why is the are the lines of text that appear in the output 
after executing the program diffrent?
A: The function fork() clones the executing process, creating a
replica of it that's why.
Q: Which process is responsible for each line of text that appears
in the output?
A: Before the fork:                   -> Parent
  PID = 26074, PPID = 26067           -> Parent
After the fork:                       -> Parent
  PID = 26074, PPID = 26067           -> Parent
After the fork:                       -> Child
  PID = 26075, PPID = 26074           -> Child
  Was I printed by the parent or by the child process? How can I know it?   -> ??
  Was I printed by the parent or by the child process? How can I know it?   -> ??
*/

/* With: bwRandomDelay(0, 2000);
Q: Which process is responsible for each line of text that appears
in the output?
A: Before the fork:                   -> Parent
  PID = 26154, PPID = 26067           -> Parent
After the fork:                       -> Parent
  PID = 26154, PPID = 26067           -> Parent
  Was I printed by the parent or by the child process? How can I know it?   -> Parent
After the fork:                                                             -> Child
  PID = 26155, PPID = 26154                                                 -> Child
  Was I printed by the parent or by the child process? How can I know it?   -> Child
*/