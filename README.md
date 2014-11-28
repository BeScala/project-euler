[![Gitter](https://badges.gitter.im/Join Chat.svg)](https://gitter.im/bescala?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

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
[info]   + Elapsed time: 13ms
[info]   + Your solution: 4315
[info]   +
[info] - Problem #0 (alternative solution)
[info]   +
[info]   + Description:
[info]   +   Find the sum of all numbers from 1 to 100
[info]   +   that are NOT multiples of 7
[info]   + ------------------------------------------------------
[info]   +
[info]   + Elapsed time: 8ms
[info]   + Your solution: 4315
[info]   +
```

###Getting started

When you start working on the given problems, you will soon find out that you need to solve a number of generic problems over and over again. In order to give you a head start, a package object is included in the ```org.bescala.projecteuler``` package that contains a number of useful functions such as a prime number generator, decomposition of a number in prime factors, etc.

We're not forcing you to use these functions: if you don't want to use them, just ignore them and roll your own. If you do, look at the source code, or alternatively, look at the Scaladoc we've generated (doc/index.html).

Note that when you browse the main page, you will only see the object ```Integers```. you can get to the other functions by clicking on package ```org.bescala.projecteuler``` in the left navigation pane.

This issue just tries to explain how we can proceed in discussing our solutions for project-euler.

## Homework

### First steps
* Clone the repository locally `git clone https://github.com/bescala/project-euler.git`
* Create your own branch `git branch ${github.username}`

### Problem-by-problem solving
The idea is to handle problems one by one, to keep us focused. We can all come up with new, better, brighter solutions and all of us can learn something.
We opened a first issue [Problem 1: Multiples of 3 and 5](https://github.com/bescala/project-euler/issues/5) so we can start submitting, comparing, discussing solutions for it.

* Make your solutions, add, commit and push your branch. (I recommend using IntelliJ IDEA GitHub plugin or SourceTree in stead of these low level git commands but your milage may vary.)
```
git add .
git commit --message 'some message'
git push --set-upstream origin ${github.username}
```
* You can directly make a reference to the issue number in your commit message.
```
git commit --message 'solution for #5 using Scala Sets'
```
* Add a comment to the issue, where you explain a bit about your solution. What technique does it use, mathematical trick, performance characteristics, ...  [Take a look at this example.](https://github.com/bescala/project-euler/issues/5) Of course this is not obligatory, but sharing is nice and doing so can start a discussion or trigger others to think about other solutions. If you haven't used a reference to the issue in the commit message, add in your comment a link to your code in your branch.  You can even link to line numbers in your code like in this [simple](https://github.com/bescala/project-euler/blob/samdebacker/src/test/scala/org/bescala/projecteuler/problems/Problem001.scala#L15) example. 

  * Be gentle. Some of you are very experienced Scala developers, some others are new. Introduce (new) Scala constructs or (new) Functional Programming techniques gradually. Explain them a bit in the comments of the issues when they are introduced for the first time.

  * If your solution is already discussed in the issue by someone else, well that is nice, but avoid reposting the same solution. (You can of course push it to your branch.) We are all happy you found that solution as well, but the idea is that we are looking for (really) different solutions and discuss them.

### For the chatty-(wo)men
Try to keep the comments in an issue focussed. But from time to time you want to discuss something with some other member(s). In that case we recommend that you join us in the BeScala room on Gitter. You can find the link on the top of this README.

### Round-up
Later on we can look to cherry-pick from all the solutions and merge them into a single branch.
