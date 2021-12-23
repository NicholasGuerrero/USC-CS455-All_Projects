# Makefile for CS 455 extra credit assgt Fall 2021
#
#
#     make ectest
#        Makes ectest executable
#


CXXFLAGS = -ggdb -Wall -std=c++11 -g
OBJS = ecListFuncs.o ectest.o


ectest: $(OBJS)
	$(CXX) $(CXXFLAGS) $(OBJS) -o ectest

$(OBJS): ecListFuncs.h