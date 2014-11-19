package org.bescala.projecteuler

import scala.collection.immutable.IndexedSeq

class Euler extends EulerSpec {

  override def nestedSuites =
    IndexedSeq(
      new EulerTest
    )

}
