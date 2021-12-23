// Name: Nicholas Guerrero
// USC NetID: ng55585
// CSCI 455 PA5
// Fall 2021

#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

//Node struct constructor
Node::Node(const string &theKey, int theValue)
{
   key = theKey;
   value = theValue;
   next = NULL;
}

//Node struct constructor with Node*n input
Node::Node(const string &theKey, int theValue, Node *n)
{
   key = theKey;
   value = theValue;
   next = n;
}

//*************************************************************************
// put the function definitions for your list functions below

// checks if the key is in the list
// @returns true if key is in list else false
bool contains(ListType list, std::string key)
{

   for (ListType cur = list; cur != nullptr; cur = cur->next)
      if (cur->key == key)
      {
         return true;
      }
   return false;
}

// checks the size of the  list
// @returns number of elements in the list
int size(ListType list)
{

   int count = 0;
   for (ListType cur = list; cur != nullptr; cur = cur->next)
   {
      count++;
   }
   return count;
}

// if key is in the list, returs an int pointer to the value of the node containing the input key
// if key in not in the list, returns nullptr
// @returns value of key for input key in {key, value} Node
int *listLookup(ListType &list, std::string key)
{

   for (ListType cur = list; cur != nullptr; cur = cur->next)
   {
      if (cur->key == key)
         return &cur->value;
   }
   return nullptr;
}

//if the key is not a duplicate in the list, made a new node and insert it at the end of the list
//@returns true if the operation was successful else false
bool listInsert(ListType &list, std::string key, int value)
{

   if (contains(list, key))
      return false;
   if (list == nullptr)
   {
      list = new Node(key, value);
      return true;
   }
   ListType cur;
   for (cur = list; cur->next != nullptr; cur = cur->next)
   {
      ;
   }
   cur->next = new Node(key, value);
   return true;
}

//prints every element in the list
void listPrint(ListType list)
{

   for (ListType cur = list; cur != nullptr; cur = cur->next)
   {
      cout << cur->key << " " << cur->value << endl;
   }
}

// Removes the node from the list at the specified key in the list
// @returns true if the operation was successful else false
bool listRemove(ListType &list, std::string key)
{

   if (list == nullptr)
   {
      return false;
   }

   if (list->key == key)
   {
      ListType p = list;
      list = p->next;
      delete p;
      return true;
   }

   ListType p = list;
   while (p != nullptr)
   {
      if (p->next != nullptr && p->next->key == key)
         break;
      p = p->next;
   }

   if (p != nullptr)
   {
      ListType q = p->next;
      p->next = q->next;
      delete q;
   }

   return true;
}
