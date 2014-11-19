# Project Euler (in Scala)

This project contains a short selection of problems from Project Euler
(https://projecteuler.net/) to be solved in Scala.

It was build in the context of a hacking session for beginners at the BeScala (Belgian Scala User Group).

The selected problem numbers are:
1, 3, 5, 6, 7, 9, 10, 13, 15, 18, 21, 24 and 25

##Instructions

start sbt

run command 'euler *ProblemXXX' or 'euler-all'
(where XXX is to be replaced by the problem number)

To start, you can run:
sbt "euler *Problem001"

The problem/test will run in continuous mode.

You can open the file Problem001.scala and add your solution.
```scala
class Problem001 extends EulerSuite {
  euler(problem(1)) {
    TODO // add your solution here - MUST return a Long!!!
  }
}
```

Problem000.scala contains a demo problem with a solution.
You can use it as implementation example.