/*  Name: Nicholas Guerrero
 *  USC NetID: ng55585
 *  CS 455 Fall 2021
 *
 *  See ecListFuncs.h for specification of each function.
 *
 */

#include <string>
#include <cassert>

// for istringstream
#include <sstream>

#include "ecListFuncs.h"

using namespace std;

// *********************************************************
// Node constructors: do not change
Node::Node(int item)
{
   data = item;
   next = NULL;
}

Node::Node(int item, Node *n)
{
   data = item;
   next = n;
}
// *********************************************************

/*
 * creates and returns a linked list from a string of space separated numbers
 */
ListType buildList(const string &listString)
{
   ListType linkedList = NULL;
   ListType ptr;
   string word;
   int data;
   istringstream iss(listString);

   while (iss >> word)
   {
      data = atoi(word.c_str());

      if (linkedList == NULL)
      {
         linkedList = new Node(data);
         ptr = linkedList;
      }
      else
      {
         ptr->next = new Node(data);
         ptr = ptr->next;
      }
   }
   return linkedList;
}

/*
 * converts the list to a string form that has the following format shown by example.
 * the list is unchanged by the function.
 our function must use the exact format shown in the string,
 * e.g. no extra spaces.
 */
string listToString(ListType list)
{
   string listString = "(";

   for (ListType cur = list; cur != nullptr; cur = cur->next)
   {
      if (cur->next == nullptr) // if last node in the list
      {
         listString += to_string(cur->data);
      }
      else
      {
         listString += to_string(cur->data) + " ";
      }
   }
   listString += ")";
   return listString;
}

/*
 * inserts value at location 'index' in the list, where we count locations
 * from 0.
 */
void insertAt(ListType &list, int index, int value)
{
   ListType ptr = list;
   ListType ptr2 = new Node(value);

   if (index == 0)
   {
      ptr2->next = ptr;
      list = ptr2;
   }
   else
   {
      index--;
      while (index > 0)
      {
         ptr = ptr->next;
         index--;
      }
      ptr2->next = ptr->next;
      ptr->next = ptr2;
   }
}

/*
 * Partition the given list into two lists:
 *    * the return value contains all the elements in the list that are
 *         less than the given number in the same order they appeared in list
 *    * the list argument is updated to contain only the elements from the
 *         original list that are greater or equal to the given value in the
 *         same order they appeared in list
 */
ListType partitionBy(ListType &list, int number)
{
   ListType ptr1 = list;
   ListType ptr2;
   ListType newList = NULL;
   bool hasHead = false;
   int iterCount = 0;

   for (ListType cur = list; cur != nullptr; cur = cur->next)
   {
      int data = cur->data;
      if (data < number)
      {
         // Build newList
         if (!hasHead)
         {
            hasHead = true;
            newList = new Node(data);
            ptr2 = newList; // ptr2 now points at the newList
         }
         else
         {
            ptr2->next = new Node(data);
            ptr2 = ptr2->next;
         }

         // Mutate original List
         if (iterCount == 0)
         {
            list = list->next;
         }
         else
         {
            for (int i = 0; i < iterCount - 1; i++)
            {
               ptr1 = ptr1->next;
            }
            ptr1->next = ptr1->next->next; // reattach
         }
      }
      else
      {
         iterCount++; // keep track of how many times ptr1 should iterate when mutating orignal list
      }
   }
   return newList;
}
