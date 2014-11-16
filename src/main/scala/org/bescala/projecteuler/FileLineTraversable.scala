package org.bescala.projecteuler

/**

  Kindly borrowed from 'Scala in depth' by Joshua Suereth

 */
import java.io.{BufferedReader, File, FileReader}

import scala.language.postfixOps

class FileLineTraversable (file: File) extends Traversable[String] {

  override def foreach[U](f: String => U) : Unit = {
    val input = new BufferedReader(new FileReader(file))
    try {
      var line = input.readLine
      while(line != null) {
        f(line)
        line = input.readLine
      }
    } finally {
      input.close()
    }
  }
  override def toString =
    "{Lines of " + file.getAbsolutePath + "}"
}
