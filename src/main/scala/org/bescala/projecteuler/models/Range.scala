package org.bescala.projecteuler.models


import scala.annotation.tailrec


/**
 * This is a over-engineered modelization to support the rather
 * simple solution for Problem001.
 *
 * The sole purpose of this model is to illustrate some Scala language features.
 */
trait Range {

  val begin:Int
  val end:Int
  assert(begin <= end, "The beginning of Range must be before or equal to its end")

  // by default returns always true
  def filterFunc = (n:Int) => true
  def withFilter(filter: (Int) => Boolean) : Range


  def sum: Int = foldLeftMonoid(IntAddMonoid)(identity)

  def toList: List[Int] = foldLeftMonoid(ListIntMonoid) { n => List(n) }

  private def foldLeftMonoid[T](monoid:Monoid[T])(builder: (Int) => T) : T = {
    foldLeft(monoid.zero) { (acc, num) =>
      monoid.append(acc, builder(num))
    }
  }

  def foldLeft[T](z: T)(op: (T, Int) => T) : T = {

    @tailrec
    def loop(num:Int, acc:T) : T = {
      if (num > end) acc
      else if (num == end) op(acc, num)
      else loop(next(num), op(acc, num))
    }

    loop(begin, z)
  }


  override def toString = s"Range(begin = $begin, end = $end)"

  protected def next(n:Int) : Int = {

    @tailrec
    def incAndFilter(n:Int) : Int = {

      val incremented = n + 1

      if (filterFunc(incremented)) {
        incremented
      } else {
        incAndFilter(incremented)
      }
    }

    incAndFilter(n)
  }

  def previous(n:Int) : Int = {

    @tailrec
    def incAndFilter(n:Int) : Int = {

      val decremented = n - 1

      if (filterFunc(decremented)) {
        decremented
      } else {
        incAndFilter(decremented)
      }
    }

    incAndFilter(n)
  }


}

object Range {
  def apply(start: Int, end: Int) : Range = TotalRange(start, end)

  def from(start:Int) = new  {
    def to(end:Int) : Range = TotalRange(start, end)
    def until(end:Int) : Range = TotalRange(start, end - 1)
  }
}

case class TotalRange(begin:Int, end:Int) extends Range {
  def withFilter(filter: (Int) => Boolean) : Range = FilteredRange(this, filter)
}


case class FilteredRange(range:Range, override val filterFunc : Int => Boolean) extends Range {

  val begin = if (filterFunc(range.begin)) range.begin else next(range.begin)
  val end = if(filterFunc(range.end)) range.end else previous(range.end)

  def withFilter(filter: (Int) => Boolean) : Range = {
    val newFilter = (n:Int) => filterFunc(n) && filter(n)
    FilteredRange(this, newFilter)
  }

}




trait Monoid[T] {
  def zero:T
  def append(n:T, a:T) : T
}

object IntAddMonoid extends Monoid[Int] {
  def zero = 0
  def append(left:Int, right:Int) = left + right
}

object ListIntMonoid extends Monoid[List[Int]] {
  def zero = List[Int]()
  def append(left:List[Int], right:List[Int]) = left ++ right
}