package org.bescala.projecteuler

import org.bescala.projecteuler.problems._
import org.scalatest.Spec

import scala.collection.immutable.IndexedSeq

class Euler extends Spec {

  override def nestedSuites =
    IndexedSeq(
      new Problem001,
      new Problem002,
      new Problem003,
      new Problem004
    )

}
