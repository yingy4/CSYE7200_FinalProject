import sbt.Keys.{autoCompilerPlugins, libraryDependencies, resolvers}

lazy val commonSettings = Seq(
  organization := "org.mskim",
  scalaVersion := "2.11.11",
  version := "0.0.3-SNAPSHOT",
  licenses += "Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.html"),
  parallelExecution in Test := false,
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-optimize", "-Xlint"),
  // Some dependencies like `javacpp` are packaged with maven-plugin packaging
  classpathTypes += "maven-plugin",

  resolvers ++= Seq(
    Resolver.sonatypeRepo("snapshots"),
    "ImageJ Releases" at "http://maven.imagej.net/content/repositories/releases/",
    // Use local maven repo for local javacv builds
    "Local Maven Repository" at "file:///" + Path.userHome.absolutePath + "/.m2/repository"
  ),

  autoCompilerPlugins := true,

  // fork a new JVM for 'run' and 'test:run'
  fork := true,

  // add a JVM option to use when forking a JVM for 'run'
  javaOptions += "-Xmx1G",

  // Set the prompt (for this build) to include the project id.
  shellPrompt in ThisBuild := { state => "sbt:" + Project.extract(state).currentRef.project + "> "},

  //val scalaTestVersion = "2.2.4",

  libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

)

lazy val core = project
  .settings(commonSettings)
  .settings(Seq(
    libraryDependencies ++= Seq(
      "org.tensorflow" % "tensorflow" % "1.2.1",
      "org.apache.spark" % "spark-core_2.11" % "2.2.0",
      "org.apache.spark" % "spark-sql_2.11" % "2.2.0",
      "net.imagej"                   % "ij"              % "1.47v",
      "junit"                        % "junit"           % "4.11" % "test",
      "com.novocode"                 % "junit-interface" % "0.10" % "test",
      "com.github.fommil.netlib" % "all" % "1.1.2" pomOnly()
    )
  ))

lazy val rest = project
  .dependsOn(core)
  .settings(commonSettings)
  .settings(Seq(
    resolvers += "Twitter Maven" at "http://maven.twttr.com",
    libraryDependencies ++= Seq(
      "com.twitter" %% "finatra-http" % "2.11.0",
      "ch.qos.logback" % "logback-classic" % "1.1.7"
    ),
    excludeDependencies += "org.slf4j" % "slf4j-log4j12"
  ))

lazy val root = (project in file("."))
  .aggregate(core, rest)
  .dependsOn(core, rest)
  .settings(commonSettings)


