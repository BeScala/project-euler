package org.bescala.projecteuler.problems



import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem004 extends EulerSuite {

 /**
  * A palindromic number reads the same both ways. The largest palindrome made from the product
  * of two 2-digit numbers is 9009 = 91 x 99.
  *
  * Task: Find the largest palindrome made from the product of two 3-digit numbers.
  *
  * Note: The stated problem can be solved for numbers with a larger number of digits.
  *
  * For example, for 5 digit numbers, the largest palindrome is 9966006699 = 99681 * 99979 and
  * this solution can be determined programmatically in under a second.
  *
  * In fact, it is possible to calculate the value of these palindrome for the product of two
  * numbers up-to 8 digits in around 5 seconds.
  *
  * Finding the right strategy for finding a fast solution is not straightforward. Use experiments to
  *find a good strategy. The 'fast' solution doesn't require tons of math...
  *
  */


  euler(problem(4), "eloots-1 - reduce search space") {
    def largestPalindrome(nMax: Long): Long = {
      // General strategy: try to reduce the search space
      // idea is to find the largest number who's square is a palindrome
      // Given this number, we can reduce the range of the second number in the
      // multiplications
      def calcLargestPalindromeFromSquare(number: Long): Long = {
        @annotation.tailrec
        def testlp(n: Long): Long = {
          val n2 = n * n
          if (n2.toString == n2.toString.reverse) {
            println(n); n2
          }
          else testlp(n - 1)
        }

        val lsp = testlp(number)
        println(s"lsp = $lsp")
        lsp
      }
      // find largest palindrome from product of two identical numbers
      val lp_fromSquaredN = calcLargestPalindromeFromSquare(nMax)


      (for {
        n1: Long <- nMax to lp_fromSquaredN / nMax by -1
        n2: Long <- n1 to lp_fromSquaredN / n1 by -1
        np = n1 * n2
        if (np.toString == np.toString.reverse)
      } yield np).sortWith(_ > _).head

    }

    largestPalindrome(999)
  }

  euler(problem(4), "eloots-2 - search by starting from palindromes") {
    def largestPalindrome(N: Int): Long = {

      def primeFactorsWithCutoff(n: Long, cutoff: Long = Long.MaxValue)(implicit primes: Stream[Long]): Vector[Long] = {
        def pf(n: Long, pfactors: Vector[Long], primes: Stream[Long]): Vector[Long] = {
          if (n == 1) pfactors
          else {
            val curPrime = primes.head
            if (curPrime > cutoff) pfactors
            else {
              if (n % curPrime == 0) pf(n / curPrime, pfactors :+ curPrime, primes)
              else pf(n, pfactors, primes.tail)
            }
          }
        }

        if ( n <= 1 ) Vector.empty[Long]
        else pf(n, Vector.empty[Long], primes)
      }

      def specialPalindrome(palindrome: Long, cutoff: Long = Long.MaxValue)(implicit primes: Stream[Long]): Boolean = {

        val pFactors = primeFactorsWithCutoff(palindrome, cutoff)(primes)

        ((1 to pFactors.length) map { n => pFactors.combinations(n).map(_.product) } flatten ) filter (_ <= cutoff) exists( div => palindrome % div == 0 && palindrome / div < cutoff)
      }

      object PrimeFast {
        import scala.collection.mutable.Map

        val fastPrimes : Stream[Long] = {
          2 #:: sieve(3, Map{ 9L -> 6L })
        }

        private def sieve(p: Long, pQ: Map[Long, Long]): Stream[Long] = {
          //println(s"sieve(${p}, ${pQ})")
          p #:: sieve(nextPrime(p + 2, pQ), pQ )
        }
        private def nextCompositeNumber(x:Long, step: Long, pQ: Map[Long, Long]): Unit = {
          //println(s"nextCompositeNumber(${x}, ${step}, ${pQ})")
          pQ.get(x) match {
            case Some(_) =>
              nextCompositeNumber(x + step, step, pQ)
            case None =>
              pQ(x) = step
          }
        }
        private def nextPrime(candidate: Long, pQ: Map[Long, Long]): Long = {
          //println(s"nextPrime(${candidate}, ${pQ})")
          pQ.get(candidate) match {
            case Some(step) =>
              pQ -= candidate
              nextCompositeNumber(candidate + step, step, pQ)
              nextPrime(candidate + 2, pQ)
            case None =>
              pQ(candidate * candidate) = candidate * 2
              candidate
          }
        }
      }

      import PrimeFast._

      implicit val pfs = fastPrimes
      val baseLength = (N.toLong * N).toString.length
      val startPalSeed = (N.toLong * N).toString.take(N.toString.length).mkString.toInt

      val numbers = Iterator.from(startPalSeed, -1)
      val result: Option[Long] = if (baseLength % 2 == 0) {
        (for {
          n <- numbers
          s = n.toString
        } yield (s + s.reverse).toLong).find { n => specialPalindrome(n, N)}
      } else {
        (for {
          n <- numbers
          id <- (9 to 0 by -1).map(_.toString)
          s = n.toString
        } yield (s + id + s.reverse).toLong).find { n => specialPalindrome(n, N)}
      }

      val p = result match {
        case Some(p) => p
        case None => -1L
      }
      p
    }

    largestPalindrome(999)
  }

  euler(problem(4), "eloots-3 - search in more optimal way") {
    def largestPalindrome(n: Long, range: Long =  100, pRange: Long = 0): (Long, Long, Long) = {
      val candidatesNewXPrev = for {
        nNew  <- n - pRange until n - range  by -1
        nPrev <- n          until n - pRange by -1
        posPalindrome = (nNew * nPrev).toString
        if posPalindrome == posPalindrome.reverse
      } yield (posPalindrome.toLong, nNew, nPrev)

      val candidatesNewXNew = for {
        nNew  <- n - pRange until n - range by -1
        nNew2 <- nNew       until n - range by -1
        posPalindrome = (nNew * nNew2).toString
        if posPalindrome == posPalindrome.reverse
      } yield (posPalindrome.toLong, nNew2, nNew)

      val interm = (candidatesNewXPrev ++ candidatesNewXNew).sortBy { case (num, _, _) => -num }

      if (interm.isEmpty) {
        largestPalindrome(n, range + 100, range)
      } else interm.head
    }

    // This version of largestPalindrome generates a 3-element
    // tuple with the palindrome as the first element
    largestPalindrome(999)._1
  }

  euler(problem(4), "eloots-3a - same as eloots-3 but refactored") {
     
    case class PalindromeAndDividers(palindrome: Long, divider1: Long, divider2: Long)

    def largestPalindrome(n: Long, range: Long =  100, pRange: Long = 0): PalindromeAndDividers = {
      val candidatesNewXPrev = for {
        nNew  <- n - pRange until n - range  by -1
        nPrev <- n          until n - pRange by -1
        posPalindrome = (nNew * nPrev).toString
        if posPalindrome == posPalindrome.reverse
      } yield PalindromeAndDividers(posPalindrome.toLong, nNew, nPrev)

      val candidatesNewXNew = for {
        nNew  <- n - pRange until n - range by -1
        nNew2 <- nNew       until n - range by -1
        posPalindrome = (nNew * nNew2).toString
        if posPalindrome == posPalindrome.reverse
      } yield PalindromeAndDividers(posPalindrome.toLong, nNew2, nNew)

      val palindromes = (candidatesNewXPrev ++ candidatesNewXNew).sortBy { case PalindromeAndDividers(num, _, _) => -num }

      if (palindromes.isEmpty) {
        largestPalindrome(n, range + 100, range)
      } else palindromes.head
    }

    largestPalindrome(999).palindrome
  }

}
