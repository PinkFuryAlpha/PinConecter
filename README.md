# PinConecter

A program for connecting pins on a board

The program is still under developement. It is tought to use the Java bytecode for industrial applications. Given an input file as a matrix N by N, you put the pins with the numbers on it and the program should connect the pins (all the 2's togheter etc.). Currently does not work for every test case.

Future fixes to add:

-use minimum cable to connect by backtracking the current used cable;
-using the minimum distance between two points in space to determinate a more efficient priority connection for the pins;
-avoid intersections by iterating by possible solutions (choosing a different order to connect the pins) using permutations;
