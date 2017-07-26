name := "ze-scala"

version := "1.0"

scalaVersion := "2.11.11"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

// Jackson
libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.3.3",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.10" % "2.3.3"
)

// MediaMath scala-json
resolvers += "mmreleases" at "https://artifactory.mediamath.com/artifactory/libs-release-global"
libraryDependencies += "com.mediamath" %% "scala-json" % "1.0"
// @accessor annotation support
resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

// spray-json
libraryDependencies += "io.spray" %%  "spray-json" % "1.3.3"
