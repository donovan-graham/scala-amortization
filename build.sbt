name := "amort"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "net.liftweb" %% "lift-json" % "3.1.0"

mainClass in (Compile, run) := Some("com.platform7.Main")