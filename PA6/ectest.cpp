/*  Name: Nicholas Guerrero
 *  USC NetID: ng55585
 *  CS 455 Fall 2021
 *  Extra credit assignment
 *
 *  ectest.cpp
 *
 *  a non-interactive test program to test the functions described in ecListFuncs.h
 *
 *    to run it use the command:   ectest
 *
 *  Note: this uses separate compilation.  You put your list code ecListFuncs.cpp
 *  Code in this file should call those functions.
 */

#include <iostream>
#include <string>

#include "ecListFuncs.h"

using namespace std;

void testListToString(); // testing listToString ecListFuncs method
void testBuildList();    // testing buildList ecListFuncs method
void testInsertAt();     // testing inserAt ecListFuncs method
void testPartitionBy();  // testing partitionBy ecListFuncs method

// Helper method for testListToString
void testListToStringHelper(ListType linkedList, string input, string expectedOutput);
// Helper method for testBuildList
void testBuildListHelper(string input, string expectedOutput);
// Helper method for testInsert
void testInsertAtHelper(string input, string expectedOutput, int position, int value);
// Helper method for testPartitionBy
void testPartitionByHelper(string input, string builtListOutput, string partitionListOutput, int partitionValue);

int main()
{

   ListType linkedListA = new Node(0);
   ListType linkedListB = new Node(7, linkedListA);

   testListToString();
   testBuildList();
   testInsertAt();
   testPartitionBy();

   return 0;
}

// testing listToString ecListFuncs method
void testListToString()
{
   cout << "=======Testing listToString Method===========" << endl;
   ListType linkedListA = new Node(0);
   cout << "One element list condition" << endl;
   testListToStringHelper(linkedListA, "0", "(0)");

   ListType linkedListB = new Node(3);
   linkedListB->next = new Node(4);
   linkedListB->next->next = new Node(5);
   linkedListB->next->next->next = new Node(6);
   cout << "Mutliple element list condition" << endl;
   testListToStringHelper(linkedListB, "3->4->5->6", "(3 4 5 6)");

   ListType linkedListC = NULL;
   cout << "Empty list condition" << endl;
   testListToStringHelper(linkedListC, "", "()");

   ListType linkedListD = new Node(-3);
   linkedListD->next = new Node(-4);
   linkedListD->next->next = new Node(-5);
   linkedListD->next->next->next = new Node(6);
   cout << "Mutliple negative element list condition" << endl;
   testListToStringHelper(linkedListD, "-3->-4->-5->6", "(-3 -4 -5 6)");
}

// Helper method for testListToString
void testListToStringHelper(ListType linkedList, string input, string expectedOutput)
{
   string listString;
   listString = listToString(linkedList);
   cout << "Input: " + input << endl;
   cout << "Expected: " + expectedOutput + "  |   Results : " + listString << endl;
   if (listString == expectedOutput)
   {
      cout << "PASSED!" << endl;
   }
   else
   {
      cout << "FAILED!" << endl;
   }
}

// testing buildList ecListFuncs method
void testBuildList()
{
   cout << "=======Testing buildList Method===========" << endl;
   cout << "with spaces & mutliple positive integers condition " << endl;
   testBuildListHelper("  1 3 2", "(1 3 2)");

   cout << "negative integer condition " << endl;
   testBuildListHelper("-32", "(-32)");

   cout << "multiple negative integer condition & with spaces" << endl;
   testBuildListHelper("     -32   -64 ", "(-32 -64)");

   cout << "empty condition" << endl;
   testBuildListHelper("", "()");
}

// Helper method for testBuildList
void testBuildListHelper(string input, string expectedOutput)
{
   string listString;
   cout << "Input: " + input << endl;
   ListType buitList = buildList(input);
   listString = listToString(buitList);
   cout << "Expected: " + expectedOutput + " |   Results : " + listString << endl;
   if (listString == expectedOutput)
   {
      cout << "PASSED!" << endl;
   }
   else
   {
      cout << "FAILED!" << endl;
   }
}

// testing insertAt ecListFuncs method
void testInsertAt()
{
   cout << "=======Testing insertAT Method===========" << endl;
   cout << "insert at first position condition " << endl;
   testInsertAtHelper("3 4 5 6", "(13 3 4 5 6)", 0, 13);

   cout << "insert at third position condition " << endl;
   testInsertAtHelper("3 4 5 6", "(3 4 13 5 6)", 2, 13);

   cout << "insert at last position condition " << endl;
   testInsertAtHelper("3 4 5 6", "(3 4 5 6 13)", 4, 13);
}

// Helper method for testInsertAt
void testInsertAtHelper(string input, string expectedOutput, int position, int value)
{
   string listString;
   cout << "Input: " + to_string(position) << endl;
   ListType buitList = buildList(input);
   insertAt(buitList, position, value);
   listString = listToString(buitList);
   cout << "Expected: " + expectedOutput + "  |   Results : " + listString << endl;
   if (listString == expectedOutput)
   {
      cout << "PASSED!" << endl;
   }
   else
   {
      cout << "FAILED!" << endl;
   }
}

// testing partitionBy ecListFuncs method
void testPartitionBy()
{
   cout << "=======Testing partitionBy Method===========" << endl;
   cout << "partition by 5 condition " << endl;
   testPartitionByHelper("7 4 4 3 9", "(7 9)", "(4 4 3)", 5);

   cout << "partition by 4 condition " << endl;
   testPartitionByHelper("7 4 4 3 9", "(7 4 4 9)", "(3)", 4);

   cout << "total partition by 10 condition " << endl;
   testPartitionByHelper("1 2 3 3 2", "()", "(1 2 3 3 2)", 10);

   cout << "No partition by 0 condition " << endl;
   testPartitionByHelper("7 4 2 3 9", "(7 4 2 3 9)", "()", 0);

   cout << "Empty partition by 3 condition " << endl;
   testPartitionByHelper("", "()", "()", 3);
}

// Helper method for testPartitionBy
void testPartitionByHelper(string input, string builtListOutput, string partitionListOutput, int partitionValue)
{
   string listString;
   cout << "Input: " + input << endl;
   ListType builtList = buildList(input);
   ListType partitionList = partitionBy(builtList, partitionValue);
   listString = listToString(builtList);
   cout << "Expected (list'): " + builtListOutput + "  |   Results : " + listString << endl;
   if (listString == builtListOutput)
   {
      cout << "PASSED!" << endl;
   }
   else
   {
      cout << "FAILED!" << endl;
   }
   listString = listToString(partitionList);
   cout << "Expected (return value): " + partitionListOutput + "  |   Results : " + listString << endl;
   if (listString == partitionListOutput)
   {
      cout << "PASSED!" << endl;
   }
   else
   {
      cout << "FAILED!" << endl;
   }
}
