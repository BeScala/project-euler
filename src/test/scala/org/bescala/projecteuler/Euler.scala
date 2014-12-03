package org.bescala.projecteuler

import org.bescala.projecteuler.problems._
import org.scalatest.Spec

import scala.collection.immutable.IndexedSeq

class Euler extends Spec {

  override def nestedSuites =
    IndexedSeq(
      new Problem000,
      new Problem001,
      new Problem003,
      new Problem004,
      new Problem005,
      new Problem006,
      new Problem007,
      new Problem009,
      new Problem010,
      new Problem013,
      new Problem015,
      new Problem018,
      new Problem021,
      new Problem024,
      new Problem025
    )

}
