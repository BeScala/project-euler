package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem025 extends EulerSuite {

  /**
   * The Fibonacci sequence is defined by the recurrence relation:
   *
   * F(n) = F(n−1) + F(n−2), where F(1) = 1 and F(2) = 1.
   * Hence the first 12 terms will be:
   *
   * F1  = 1
   * F2  = 1
   * F3  = 2
   * F4  = 3
   * F5  = 5
   * F6  = 8
   * F7  = 13
   * F8  = 21
   * F9  = 34
   * F10 = 55
   * F11 = 89
   * F12 = 144
   *
   * The 12th term, F12, is the first term to contain three digits.
   *
   * What is the first term in the Fibonacci sequence to contain 1000 digits?
   */
  euler(problem(25)) {

    def fiboStop(f: BigDecimal => Boolean): Int = {
      def fibonacciAcc(f: BigDecimal => Boolean, n: Int, f_n_1: BigDecimal, f_n: BigDecimal): Int = {
        if (f(f_n)) n
        else fibonacciAcc(f, n + 1, f_n, f_n + f_n_1)
      }
      fibonacciAcc(f, 0, 1, 0)
    }
    
    def check(x: BigDecimal): Boolean = {
      x.toString().length == 1000
    }
    
    fiboStop(check)
  }
}