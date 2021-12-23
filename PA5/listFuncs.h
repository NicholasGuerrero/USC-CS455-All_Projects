// Name: Nicholas Guerrero
// USC NetID: ng55585
// CSCI 455 PA5
// Fall 2021

//*************************************************************************
// Node class definition
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to *not* put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H

#include <string>

//Node struct
struct Node
{
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};

typedef Node *ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

// checks if the key is in the list
// @returns true if key is in list else false
bool contains(ListType list, std::string key);

// checks the size of the  list
// @returns number of elements in the list
int size(ListType list);

// if key is in the list, returs an int pointer to the value of the node containing the input key
// if key in not in the lsit, returns nullptr
// @returns value of key for input key in {key, value} Node
int *listLookup(ListType &list, std::string key);

//if the key is not a duplicate in the list, made a new node and insert it at the end of the list
//@returns true if the operation was successful else false
bool listInsert(ListType &list, std::string key, int value);

//prints every element in the list
void listPrint(ListType list);

// Removes the node from the list at the specified key in the list
// @returns true if the operation was successful else false
bool listRemove(ListType &list, std::string key);

// keep the following line at the end of the file
#endif
