addCommandAlias("euler", "~test-only org.bescala.projecteuler.Euler")

name := """project-euler-scala"""

version := "1.0"

scalaVersion := "2.11.4"

parallelExecution in Test := false

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.6" % "test"

initialCommands := """|import org.bescala.projecteuler._
                      |""".stripMargin
