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
(NOTE: problem numbers are left-padded with 0 up to 3 digits)

The problem/test will run in continuous mode.

You can open the file Problem001.scala and add your solution.
```scala
class Problem001 extends EulerSuite {
  euler(problem(1)) {
    TODO // add your solution here - MUST return a Long!!!
  }
}
```
### Alternative implementations
You can add alternative implementations for each problem. To do that you need to pass a alternative name for the problem.
```scala
class Problem001 extends EulerSuite {
  euler(problem(1)) {
    TODO // your first solution goes here
  }
  euler(problem(1), "alternative solution") {
    TODO // your alternative solution goes here
  }
}
```

### Elapsed time
Time elapsed to run each solution is displayed in nanoseconds and milliseconds. Therefore you can compare the efficiency of different solutions.


Problem000.scala contains a demo problem with a solution.
You can use it as implementation example.

Bellow we see the output of Problem000. 
```
[info] Problem000:
[info] - Problem #0
[info]   +
[info]   + Description:
[info]   +   Find the sum of all numbers from 1 to 100
[info]   +   that are NOT multiples of 7
[info]   + ------------------------------------------------------
[info]   +
[info]   + Elapsed time: 12578266ns - 0.012578266s
[info]   + Your solution: 4315
[info]   +
[info] - Problem #0 (alternative impl)
[info]   +
[info]   + Description:
[info]   +   Find the sum of all numbers from 1 to 100
[info]   +   that are NOT multiples of 7
[info]   + ------------------------------------------------------
[info]   +
[info]   + Elapsed time: 7958406ns - 0.007958406s
[info]   + Your solution: 4315
[info]   +
```

###Getting started

When you start working on the given problems, you will soon find out that you need to solve a number of generic problems over and over again. In order to give you a head start, a package object is included in the ```org.bescala.projecteuler``` package that contains a number of useful functions such as a prime number generator, decomposition of a number in prime factors, etc.

We're not forcing you to use these functions: if you don't want to use them, just ignore them and roll your own. If you do, look at the source code, or alternatively, look at the Scaladoc we've generated (doc/index.html).

Note that when you browse the main page, you will only see the object ```Integers```. you can get to the other functions by clicking on package ```org.bescala.projecteuler``` in the left navigation pane.
