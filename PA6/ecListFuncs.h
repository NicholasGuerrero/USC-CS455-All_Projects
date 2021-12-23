/*  Name: Nicholas Guerrero
 *  USC NetID: ng55585
 *  CS 455 Fall 2021
 */

// ecListFuncs.h
// CSCI 455 Fall 2021, Extra Credit assignment
// You do not need to modify or submit this file.

#ifndef EC_LIST_FUNCS_H
#define EC_LIST_FUNCS_H

#include <string>

//*************************************************************************
// Node type used for lists
struct Node
{
   int data;
   Node *next;

   Node(int item);
   Node(int item, Node *n);
};

typedef Node *ListType;

//*************************************************************************
// Functions you need to write for this assignment:
//   (implementations go in ecListFuncs.cpp)

/*
 * Note: examples of linked lists here are shown as a space separated list of numbers
 * surrounded by parentheses.  This is also the format of the string produced by
 * listToString, below.
 */

/*
 * listToString
 *
 * PRE: list is a well-formed list.
 *
 * converts the list to a string form that has the following format shown by example.
 * the list is unchanged by the function.
 *
 *   string format:
 *
 *   "()"        an empty list
 *   "(3)        a list with one element, 3
 *   "(3 4 5)"   a list with multiple elements: 3 followed by 4 followed by 5
 *
 * Note: to get credit your function must use the exact format shown in the string,
 * e.g. no extra spaces.
 */
std::string listToString(ListType list);

/*
 * buildList
 *
 * PRE: listString only contains numbers (valid integer format) and spaces
 *
 * creates and returns a linked list from a string of space separated numbers
 *
 *
 * Examples:
 *  listString         return value of buildList(listString)
 *
 *    ""               ()
 *    "-32"            (-32)
 *    "     -32   "    (-32)
 *    "1 3 2"          (1 3 2)
 *    "  1 3 2"        (1 3 2)
 *
 * Hint: see C++ library hints on assignment description.
 */
ListType buildList(const std::string &listString);

/*
 * insertAt
 *
 * PRE: well-formed list and index is in the range [0, length of list]
 *
 * inserts value at location 'index' in the list, where we count locations
 * from 0.
 *
 * Examples (list' indicates the value of list after the call):
 *
 *     list            index      value      list'
 *      ()             0          3          (3)
 *      (12)           0          3          (3 12)
 *      (12)           1          3          (12 3)
 *      (7 4 7 7)      2          -10        (7 4 -10 7 7)
 */
void insertAt(ListType &list, int index, int value);

/*
 * partitionBy
 *
 * PRE: list is a well-formed list
 *
 * Partition the given list into two lists:
 *    * the return value contains all the elements in the list that are
 *         less than the given number in the same order they appeared in list
 *    * the list argument is updated to contain only the elements from the
 *         original list that are greater or equal to the given value in the
 *         same order they appeared in list
 *
 *
 * NOTE: this function does not create any nodes, but reuses all of the nodes from
 * the original list.
 *
 * Examples (list' indicates the value of list after the call):
 *
 *  list         partitionBy  return value  list'
 *  (7 4 4 3 9)  5            (4 4 3)       (7 9)
 *  (7 4 4 3 9)  4            (3)           (7 4 4 9)
 *  (7 4 2 3 9)  0            ()            (7 4 2 3 9)
 *  (1 2 3 3 2)  10           (1 2 3 3 2)   ()
 *  ()           3            ()            ()
 *
 */
ListType partitionBy(ListType &list, int number);

//*************************************************************************

#endif
