package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._
import org.bescala.projecteuler._

class Problem010 extends EulerSuite {

  /**
   * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
   * Find the sum of all the primes below two million
   */
  euler(problem(10)) {
    var sum: Long = 0
    for (i <- 1 to 2000000) {
      if (isPrime(i)) sum += i
    }
    sum
  }
  euler(problem(10), "FoldRight") {
    (1 until 2000000).foldRight(0L) { (l, acc) => if (isPrime(l)) acc + l else acc}
  }
  
  euler(problem(10), "Tail-rec") {
    def sumPrimes(n: Int): Long = {
      def sumPrimes(n:Int, acc: Long) : Long = {
        if(n == 0) acc
        else if (isPrime(n)) sumPrimes(n-1, acc+n)
        else sumPrimes(n-1, acc)
        
      }
      sumPrimes(n,0L)
    } 
    sumPrimes(2000000)
  }
}