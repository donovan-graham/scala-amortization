name := "amort"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

mainClass in (Compile, run) := Some("com.platform7.Main")