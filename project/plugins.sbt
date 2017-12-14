resolvers += Resolver.sonatypeRepo("releases")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")

//addSbtPlugin("com.beachape" % "sbt-opencv" % "1.5")

addSbtPlugin("org.bytedeco" % "sbt-javacv" % "1.15")

resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.3.5")

addSbtPlugin("com.codacy" % "sbt-codacy-coverage" % "1.3.11")
