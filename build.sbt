import AssemblyKeys._

assemblySettings

name         := "amortization"
organization := "com.platform7"
version      := "0.1.0-SNAPSHOT"
scalaVersion := "2.12.3"

mainClass in assembly := Some("com.platform7.Main")
assemblyOption in assembly ~= { _.copy(includeScala = true) }
jarName in assembly := "amortization.jar"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "com.twitter" %% "finatra-http" % "2.13.0"

mergeStrategy in assembly <<= (mergeStrategy in assembly) {
  (old) => {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
  }
}
