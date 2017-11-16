name := "CSYE7200_FinalProject"

version := "0.1"

scalaVersion := "2.11.10"

val scalaTestVersion = "2.2.4"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9.2",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6",
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1"
)

val sprayGroup = "io.spray"
val sprayJsonVersion = "1.3.2"
libraryDependencies ++= List("spray-json") map {c => sprayGroup %% c % sprayJsonVersion}

// For Akka-Stream
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream-experimental" % "2.0.3"
)

// For Swing
libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    // if scala 2.11+ is used, add dependency on scala-xml module
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-swing" % "2.0.0-M2")
    case _ =>
      // or just libraryDependencies.value if you don't depend on scala-swing
      libraryDependencies.value :+ "org.scala-lang" % "scala-swing" % scalaVersion.value
  }
}

// Allows us to stop when using run without killing SBT
fork in run := true
