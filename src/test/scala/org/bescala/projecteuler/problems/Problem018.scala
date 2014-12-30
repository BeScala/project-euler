package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

import scala.collection.mutable.ArrayBuffer

class Problem018  extends EulerSuite {

  /**
   * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total
   * from top to bottom is 23.
   *
   *    3
   *   7 4
   *  2 4 6
   * 8 5 9 3
   *
   * That is, 3 + 7 + 4 + 9 = 23.
   *
   * Find the maximum total from top to bottom of the triangle below:
   *
   *                             75
   *                           95 64
   *                         17 47 82
   *                       18 35 87 10
   *                     20 04 82 47 65
   *                   19 01 23 75 03 34
   *                 88 02 77 73 07 63 67
   *               99 65 04 28 06 16 70 92
   *             41 41 26 56 83 40 80 70 33
   *           41 48 72 33 47 32 37 16 94 29
   *         53 71 44 65 25 43 91 52 97 51 14
   *       70 11 33 28 77 73 17 78 39 68 17 57
   *     91 71 52 38 17 14 91 43 58 50 27 29 48
   *   63 66 04 68 89 53 67 30 73 16 69 87 40 31
   * 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
   *
   * NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route.
   * However, Problem 67, is the same challenge with a triangle containing one-hundred rows; it cannot
   * be solved by brute force, and requires a clever method! ;o)
   */
  // Java way
  euler(problem(18)) {
    val ret = new ArrayBuffer[Int]
    for (row <- inputRaw.reverse) {
      if(ret.isEmpty) {
        for (i <- row) {
          ret += i.toInt
        }
      }
      else {
        for (i <- 0 to row.length - 1) {
          ret.update(i, Math.max(row(i).toInt + ret(i),row(i).toInt + ret(i+1)))
        }
        ret.trimEnd(1)
      }
    }
    ret(0)
  }


  euler(problem(18), "Alt #1, More Scala-ish") {

    def largestSumInPyramid(p: Vector[Vector[String]]) : Long = {
      def computeLine(l: Vector[String], out: Vector[Int]): Vector[Int] = {
        out.tail.zip(out.dropRight(1)).zip(l.map(_.toInt)).map(x => Math.max(x._2 + x._1._1,x._2 + x._1._2))
      }
      def reducePyramid(v: Vector[Vector[String]], out: Vector[Int]): Vector[Int] = {
        if (v.isEmpty) out
        else reducePyramid(v.dropRight(1), computeLine(v.last, out))
      }
      reducePyramid(p.dropRight(1), p.last.map(_.toInt)).head.toLong
    }

    largestSumInPyramid(inputRaw)
  }

  def inputRaw = Vector(
    Vector(                                          "75"),
    Vector(                                       "95", "64"),
    Vector(                                    "17", "47", "82"),
    Vector(                                 "18", "35", "87", "10"),
    Vector(                              "20", "04", "82", "47", "65"),
    Vector(                           "19", "01", "23", "75", "03", "34"),
    Vector(                        "88", "02", "77", "73", "07", "63", "67"),
    Vector(                     "99", "65", "04", "28", "06", "16", "70", "92"),
    Vector(                  "41", "41", "26", "56", "83", "40", "80", "70", "33"),
    Vector(               "41", "48", "72", "33", "47", "32", "37", "16", "94", "29"),
    Vector(            "53", "71", "44", "65", "25", "43", "91", "52", "97", "51", "14"),
    Vector(         "70", "11", "33", "28", "77", "73", "17", "78", "39", "68", "17", "57"),
    Vector(      "91", "71", "52", "38", "17", "14", "91", "43", "58", "50", "27", "29", "48"),
    Vector(   "63", "66", "04", "68", "89", "53", "67", "30", "73", "16", "69", "87", "40", "31"),
    Vector("04", "62", "98", "27", "23", "09", "70", "98", "73", "93", "38", "53", "60", "04", "23")
  )
}