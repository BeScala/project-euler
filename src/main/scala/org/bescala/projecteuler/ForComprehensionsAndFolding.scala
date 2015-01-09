package org.bescala.projecteuler

trait TestDataFoldingAndForComprehensions {
  // Some test data
  val data1A = List(1, 2, 3, 5)
  val data1B = List(4, 3, 3, 2)
  val data1C = data1A zip data1B
  val data2A = List(Some(1), Some(2), None, Some(5), None, Some(8), Some(6), Some(4), None, None, Some(1))

  // A separator printed to mark start of new example
  def separator = println("=" * 80)
}

object ForComprehensions extends App with TestDataFoldingAndForComprehensions {

  // Case 1 - for loop, single generator
  println("Case 1 - for")
  for {
    item <- data1A     // Generator
  
  } println(item)

  println("Case 1 - foreach")
  // Case 1 - Equivalent code using foreach
  data1A foreach(item => println(item))

  separator

  //------------------------------------------------
  // Case 2 - for loop, 2 generators
  println("Case 2 - for with 2 generators")
  for {
    item1 <- data1A     // Generator 1
    item2 <- data1B     // Generator 2

  } println(item1 * item2)

  // Case 2 - Equivalent code using foreach
  println("Case 2 - for with 2 generators")
  data1A map { item1 => data1B.foreach(item2 => println(item1 * item2)) }

  separator

  // Case 3 - For comprehension with yield
  println("Case 3 - for and yield - single generator")
  val case3_A = for {
    item1 <- data1A
  } yield item1 * 2
  println(case3_A)

  val case3_B = data1A map { item1 => item1 * 2}
  println(case3_B)

  separator

  // Case 4 - For comprehension with yield
  println("Case 4 - for and yield - dual generators")
  val case4_A = for {
    item1 <- data1A
    item2 <- data1B
  } yield item1 + item2
  println(case4_A)

  val case4_B = data1A flatMap { item1 => data1B map { item2 => item1 + item2 } }
  println(case4_B)

  separator

  // Generalization example 1 - Option

  println("Case 5 - for and Option")
  println(for {
    item  <- data2A
    cItem <- item
  } yield (cItem))

  println(data2A flatMap ( item => item map (cItem => cItem )))

  separator

  // Case 5 - For comprehension with yield - dual generators and a guard
  println("Case 6 - for and yield - dual generators and a guard")
  val case5_A = for {
    item1 <- data1A
    item2 <- data1B if item2 % 2 != 0
  } yield item1 + item2
  println(case5_A)

  val case5_B = data1A flatMap { item1 => data1B withFilter { item2 => item2 % 2 != 0 } map { item2  => item1 + item2 } }
  println(case5_B)

  separator

  // Case 6 - even more general
  println("Case 7 - more general case - pattern matching & a guard")
  val case6_A = for {
    (item1, item2) <- data1C
    if (item1 + item2) % 2 != 0 // Note that guard can be put on a separate line
  } yield item1 * item2
  println(case6_A)

  val case6_B = data1C withFilter { case (item1, item2) => (item1 + item2) % 2 != 0 } map { case (item1, item2) => item1 * item2 }
  println(case6_B)

  // Case 7 - for comprehensions and Try -
  // Source http://danielwestheide.com/blog/2012/12/26/the-neophytes-guide-to-scala-part-6-error-handling-with-try.html

  println("Case 8 - for and Try")
  import scala.util.Try
  import java.net.URL
  def parseURL(url: String): Try[URL] = Try(new URL(url))

  import scala.io.Source
  def getURLContent(url: String): Try[Iterator[String]] =
    for {
      url <- parseURL(url)
      connection <- Try(url.openConnection())
      is <- Try(connection.getInputStream)
      source = Source.fromInputStream(is)
    } yield source.getLines()

  import java.net.MalformedURLException
  import java.io.FileNotFoundException
  def getContent(url: String) = getURLContent(url) recover {
    case e: FileNotFoundException => Iterator("Requested page does not exist")
    case e: MalformedURLException => Iterator("Please make sure to enter a valid URL")
    case _                        => Iterator("An unexpected error has occurred. We are so sorry!")
  }

  separator
  getContent("garbage").get.foreach(println)
  separator
  getContent("http://danielwestheide.com/foobar").get.foreach(println)
  separator
  getContent("http://danielwestheide.com").get.drop(20).take(10).foreach(println)
  separator
}

object Folding extends App with TestDataFoldingAndForComprehensions {
  // Simple example of foldLeft and foldRight
  // def foldLeft[B](z: B)(f: (B, A) ⇒ B): B

  {
    // Using foldLeft to calculate sum of elements in a List
    val sum = data1A.foldLeft(0)((pSum, x) => pSum + x)
    println(s"1 - Sum of elements in data1A: $sum")
  }

  {
    // Using foldLeft to calculate sum of elements in a List - Alternative notation
    val sum = (data1A foldLeft 0)((pSum, x) => pSum + x)
    println(s"2 - Sum of elements in data1A: $sum")
  }

  {
    // Using foldLeft to calculate sum of elements in a List - use partial functions
    val sum = (data1A foldLeft 0){ case (pSum, x) => pSum + x }
    println(s"3 - Sum of elements in data1A: $sum")
  }

  {
    // Using foldLeft to calculate sum of elements in a List - /: notation for the sake of completeness :-)
    val sum = (0 /: data1A)((pSum, x) => pSum + x)
    println(s"4 - Sum of elements in data1A: $sum")
  }

  // foldRight - note a position of accumulator & next value are swapped compared to foldLeft
  // def foldLeft[B](z: B)(f: (B, A) ⇒ B): B
  // def foldRight[B](z: B)(op: (A, B) ⇒ B): B

  {
    // Using foldRight to calculate sum of elements in a List
    val sum = data1A.foldRight(0)((x, pSum) => pSum + x)
    println(s"5 - Sum of elements in data1A: $sum")
  }

  // The argument for using partial functions
  // calculate sum of elements, squares and cubes
  {
    // Base case - no partial functions
    val sumX23 = (data1A foldLeft(0, 0, 0)) { (acc, x) => (acc._1 + x, acc._2 + x * x, acc._3 + x * x * x)}
    println(s"6 - Sum of elements: ${sumX23._1}, sum of squares: ${sumX23._2}, sum of cubes: ${sumX23._3}")
  }

  {
    // use partial functions + pattern matching in variable assignment
    val (sumX, sumX2, sumX3) = (data1A foldLeft (0, 0, 0)) {
      case ((pSumX, pSumX2, pSumX3), x) =>
        (pSumX + x, pSumX2 + x * x, pSumX3 + x * x * x)
    }
    println(s"7 - Sum of elements: ${sumX}, sum of squares: ${sumX2}, sum of cubes: ${sumX3}")
  }
}

object PalinSamdrome {
  val N = 9999

  val N2 = (N * N).toString

  val palindrome =

    Iterator.from(N, -1)
      .map { n => (n-1, n) }
      .flatMap { case (n1, n2) =>
      (0L to 1) flatMap { deltaOne =>
        (0L to N - n2 + deltaOne) map { d2 => (n1 - d2, n2 - deltaOne + d2) } }
    } map { case (n1, n2) => n1 * n2 }


  val palindrome2 = for {
    (n1, n2) <- Iterator.from(N, -1).map { n => (n-1, n) }
    deltaOne <- 0L to 1
    d2 <- 0L to N - n2 + deltaOne
  } yield (n1 - d2) * (n2 - deltaOne + d2)

}