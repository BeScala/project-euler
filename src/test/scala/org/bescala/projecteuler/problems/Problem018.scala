package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

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
  val someSpecificSolution = s"some specific solution for problem ${18}"

  euler(problem(18), someSpecificSolution) {

    abstract class Tree
    case class Branch(top: Int, left: Tree, right: Tree) extends Tree
    case class Leaf(z: Int) extends Tree

    def maxPath(tree: Tree): List[Int] =
      tree match {
        case Leaf(z) =>
          List(z)
        case Branch(top, left, right) =>
          val leftMaxPath = maxPath(left)
          val rightMaxPath = maxPath(right)

          val leftMax = leftMaxPath.foldLeft(0)(_ + _)
          val rightMax = rightMaxPath.foldLeft(0)(_ + _)

          if (leftMax <= rightMax) top :: rightMaxPath
          else top :: leftMaxPath
      }

    def mkTree(inputRaw: Vector[Vector[String]]): Tree = {
      if (inputRaw.length == 1) {
        Leaf(Integer.parseInt(inputRaw(0)(0)))
      } else if (inputRaw.length == 2) {
        val top = Integer.parseInt(inputRaw(0)(0))
        val left = Integer.parseInt(inputRaw(1)(0))
        val right = Integer.parseInt(inputRaw(1)(1))
        Branch(top, Leaf(left), Leaf(right))
      } else if (inputRaw.length >= 3) {
        val top = Integer.parseInt(inputRaw(0)(0))
        val left = Integer.parseInt(inputRaw(1)(0))
        val right = Integer.parseInt(inputRaw(1)(1))
        val leftInputRaw = inputRaw.drop(2).map { v =>
          v.reverse.drop(2).reverse
        }
        val midInputRaw = inputRaw.drop(2).map { v =>
          v.drop(1).reverse.drop(1).reverse
        }
        val rightInputRaw = inputRaw.drop(2).map { v =>
          v.drop(2)
        }
        val leftTree_ = mkTree(leftInputRaw)
        val midTree_ = mkTree(midInputRaw)
        val rightTree_ = mkTree(rightInputRaw)
        val leftTree = Branch(left, leftTree_, midTree_)
        val rightTree = Branch(right, midTree_, rightTree_)
        Branch(top, leftTree, rightTree)
      } else {
        sys.error("this should never happen")
      }
    }

    maxPath(mkTree(inputRaw)).foldLeft(0)(_ + _)

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