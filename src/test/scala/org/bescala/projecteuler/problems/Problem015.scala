package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem015  extends EulerSuite {

  /**
   * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down,
   * there are exactly 6 routes to the bottom right corner.
   *
   * How many such routes are there through a 20×20 grid?
   */
  euler(problem(15)) {
    latticeCount(20,20)
  }

  /**
   *  
   * @param r an integer: the number of rows in a lattice
   * @param c an integer: the number of columns in a lattice
   * @return the numbers of paths from the top left corner to the bottom right corner when moving only 
   *         right and down
   *
   */
  def latticeCount(r: Int, c: Int): Long = {
    def latticeAcc(r: Int, c: Int, acc: Long): Long = {
      if (r == 0 && c == 0) 0
      else if (r == 0 || c == 0) 1
      else latticeAcc(r, c-1, acc+1) + latticeAcc(r-1, c, acc+1)
    }
    latticeAcc(r, c, 0)
  }
}