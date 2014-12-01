# Nested Boxes Homework 5 part 1
# Garrison Meyer and Ian Elletson
Each person contributed equally to this project

- The DAG class uses a depth first search to help execute the "find a longest
  path in a DAG" algorithm.
- The longest path algorithm adds 1 to length because the algorithm starts on
  the second node, but needs to account for paths of length one.
- The program reads from standard input and writes to standard output
- The program should be run from the terminal as (look in the directory
  "TEST_HERE" for the jar and test files):
  `java -jar NestBoxes.jar < $TESTFILE` where `$TESTFILE` is the name of the
  file you wish to test. Files test1 - test4 are included. They give correct
  output.
- test3 is of particular interest.
