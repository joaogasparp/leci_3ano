#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <stdint.h>
#include <string.h>
#include <assert.h>

#include "linked-list.h"

namespace base {

   Node* destroy(Node* list)
   {
      Node* current = list;
      while(current != NULL)
      {
         Node* next = current->next;
         free(current->reg.name);
         delete current;
         current = next;
      }
      return NULL;
   }

   Node* append(Node* list, uint32_t nmec, char *name)
   {
      Node* newNode = new Node;
      newNode->reg.nmec = nmec;
      newNode->reg.name = strdup(name);
      newNode->next = NULL;

      if(list == NULL)
      {
         return newNode;
      }

      Node* p = list;
      while(p->next != NULL)
      {
         p = p->next;
      }
      p->next = newNode;
      return list;
   }

   void print(Node* list)
   {
      Node* p = list;
      while(p != NULL)
      {
         printf("student number: %d | stundet name: %s\n", p->reg.nmec, p->reg.name);
         p = p->next;
      }
   }

   int exists(Node* list, uint32_t nmec)
   {
      Node* current = list;
      while(current != NULL)
      {
         if(current->reg.nmec == nmec)
         {
            return 1;
         }
         current = current->next;
      }
      return 0;
   }

   Node* remove(Node* list, uint32_t nmec)
   {
      if (list == NULL)
      {
         return NULL;
      }

      if(list->reg.nmec == nmec)
      {
         Node* newNode = list->next;
         free(list->reg.name);
         delete list;
         return newNode;
      }

      list->next = remove(list->next,nmec);
      return list;
   }

   const char *search(Node* list, uint32_t nmec)
   {
      Node* current = list;
      while (current != NULL)
      {
         if(current->reg.nmec == nmec)
         {
            return current->reg.name;
         }
         current = current->next;
      }
      return NULL;
   }

   Node* sort_by_name(Node* list)
   {
      if(list == NULL){
         return list;
      }

      Node *i = list;

      while(i != NULL){
         Node *j = i->next;

         while(j != NULL){

            if(strcmp(j->reg.name, i->reg.name) < 0){
               Student t = i->reg;
               i->reg = j->reg;
               j->reg = t;
            }
            j = j->next;
         }

         i = i->next;
      }

      return list;
   }

   Node* sort_by_number(Node* list)
   {
      if(list == NULL){
         return list;
      }

      Node *i = list;

      while(i != NULL){
         Node *j = i->next;

         while(j != NULL){
            if(j->reg.nmec < i->reg.nmec){
               Student t = i->reg;
               i->reg = j->reg;
               j->reg = t;
            }
            j = j->next;
         }

         i = i->next;
      }

      return list;
   }
}
