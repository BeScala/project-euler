package org.bescala.projecteuler.problems


import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem005 extends EulerSuite {

  /**
   * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
   * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
   *
   * TIP:
   * This problem can be easily by some observations
   * sequence 1 .. 20 contains the following prime numbers:
   *
   * 2 3 5 7 11 13 17 and 19
   *
   * So, the number should at least have these as factors, the remaining numbers can be written in terms
   * of prime factors as follows:
   *
   * 4  = 2 * 2         // need at least one extra 2
   * 6  = 2 * 3         // already covered
   * 8  = 2 * 2 * 2     // need another extra 2
   * 9  = 3 * 3         // need at least one extra 3
   * 10 = 2 * 5         // already covered
   * 12 = 2 * 2 * 3     // already covered if we cover previous cases
   * 14 = 2 * 7         // already covered
   * 15 = 3 * 5         // already covered
   * 16 = 2 * 2 * 2 * 2 // need another extra 2
   * 18 = 2 * 3 * 3     // already covered if we cover previous cases
   * 20 = 2 * 2 * 5     // already covered if we cover previous cases
   */
  import org.bescala.projecteuler._

  euler(problem(5), "eloots-1") {
    2L * 2 * 2 * 2 * 3 * 3 * 5 * 7 * 11 * 13 * 17 * 19
  }

  euler(problem(5), "eloots-2") {
    // The idea here is to generalize the mechanism proposed in the TIP.
    // First, we 'encode' the expansion of each number into prime factors
    // next, the prime factor encodings are sorted by value (2, 3, 5, 7, ...)
    // and the prime factors with the highest multiplicity are extracted
    // Finally, in order to calculate the product which produces the result,
    // each prime factor/multiplicity paired is decoded
    //
    // I added comment after each step for N = 6 as an example...
    //
    // Note: look in the doc. for the 'encode' method

    import scala.language.postfixOps

    val N = 20

    (Vector(1L to N:_*)
      .flatMap (n => encode(primeFactors(n))) distinct)                // Vector((2,1), (3,1), (2,2), (5,1))
      .groupBy { case (base, mult) => base }                           // Map(2 -> Vector((2,1), (2,2)), 5 -> Vector((5,1)), 3 -> Vector((3,1)))
      .map { case (base, vals) => vals.sortBy(x => -x._2).head }       // Map(2 -> 2, 5 -> 1, 3 -> 1)
      .flatMap { case (base, mult) => Vector.fill(mult)(base.toLong) } // List(2, 2, 5, 3)
      .product                                                         // 60
  }

  euler(problem(5), "eloots-3") {
    // Variation on eloots-2
    // Instead of using 'encode', 'pack' is used. See doc. for details
    //
    // Again, I added comment after each step for N = 6 as an example...

    import scala.language.postfixOps

    val N = 20
    Vector(1L to N:_*)
      .flatMap (n => pack(primeFactors(n)))                            // Vector(Vector(2), Vector(3), Vector(2, 2), Vector(5), Vector(3), Vector(2))
      .groupBy (_.head)                                                // Map(2 -> Vector(Vector(2), Vector(2, 2), Vector(2)), 5 -> Vector(Vector(5)), 3 -> Vector(Vector(3), Vector(3)))
      .flatMap { case (k, vals) => vals.sortBy(- _.length).head }      // List(2, 2, 5, 3)
      .map (_.toLong)                                                  // List(2, 2, 5, 3)
      .product                                                         // 60
  }
  euler(problem(5), "eloots-4") {

  // looking at Michel Verbist's comment on this problem, it turned out that
  // solution eloots-2 could be simplified, leading to this solution...
  val N = 20

  val essentialPF = (Vector(1L to N:_*)
    .flatMap (n => encode(primeFactors(n))) distinct)
    .map {case (pf, mult) => pf }
  (essentialPF foldLeft 1L) { case (acc, n) => acc * n }              // calling product on essentialPF is sufficient in for N=20.
                                                                      // However it will fail if N larger due to Int overflow,
                                                                      // Hence this fold which does the calc. with Long

  }
}