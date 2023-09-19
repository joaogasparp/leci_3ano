#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <alloca.h>

#include "linked-list.h"

using namespace base;

static Node* list = NULL;

/* ******************************************** */

static void printMenu(void)
{
   printf("\n"
         "+===============================================+\n"
         "|            Manipulation functions             |\n"
         "+===============================================+\n"
         "| 0 --- quit                                    |\n"
         "| 1 --- append new student                      |\n"
         "| 2 --- print list                              |\n"
         "| 3 --- remove student, through number          |\n"
         "| 4 --- get student's name, given the number    |\n"
         "| 5 --- sort list by name                       |\n"
         "| 6 --- sort list by number                     |\n"
         "+===============================================+\n");
}

/* ******************************************** */
/* menu handling functions */
/* ******************************************** */

void menuChoiceAppend()
{
   /*uint32_t nmec;
   char name[100];
   printf("student nmec = ");
   scanf("%d", &nmec);

   if(base::exists(list,nmec))
   {
      printf("this student has already been added\n");
   }else
   {
      printf("student name = ");
      scanf("%s", name);
      list = base::append(list,nmec,name);
      printf("student added to the list\n");
   }*/
   list = append(list,1,"a");
   list = append(list,2,"b");
   list = append(list,3,"c");
   list = append(list,4,"d");
   list = append(list,5,"e");
   list = append(list,6,"f");
}

/* ******************************************** */

void menuChoicePrint()
{
   print(list);
}

/* ******************************************** */

void menuChoiceRemove()
{
   uint32_t nmec;
   printf("enter student number to remove: ");
   scanf("%d", &nmec);
   list = base::remove(list,nmec);
   printf("student removed from the list\n");
}
/* ******************************************** */

void menuChoiceSearch()
{
   uint32_t nmec;
   printf("enter student number to search: ");
   scanf("%d", &nmec);
   const char* name = base::search(list,nmec);
   if(name != NULL)
   {
      printf("student number: %d | student name: %s\n", nmec, name);
   }else
   {
      printf("student not found");
   }
}

/* ******************************************** */

void menuChoiceSortByName()
{
}

/* ******************************************** */

void menuChoiceSortByNumber()
{
}

/* ******************************************** */

/* The main function */
int main()
{
   int terminate = 0;
   while (!terminate)
   {
      printMenu();
      printf("Your command: ");
      int cmd;
      int res = scanf("%d", &cmd);
      if (res != 1)
      {
         fprintf(stderr, "Wrong input format (unsigned integer number required)\n");
         scanf("%*s"); // consume and ignore standard input
      }
      else
      {
         printf("\n");
         switch(cmd) 
         {
            case 0:
               terminate = 1;
               break;
            case 1:
               menuChoiceAppend();
               break;
            case 2:
               menuChoicePrint();
               break;
            case 3:
               menuChoiceRemove();
               break;
            case 4:
               menuChoiceSearch();
               break;
            case 5:
               menuChoiceSortByName();
               break;
            case 6:
               menuChoiceSortByNumber();
               break;
            default:
               fprintf(stderr, "Wrong action required (%d)\n", cmd);
         }
      }
   }

   list = base::destroy(list);
   return 0;
}     /* end of main */

