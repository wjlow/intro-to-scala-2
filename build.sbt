name := "intro-to-scala"

version := "0.1"

libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test

scalaVersion := "2.13.0"

scalacOptions ++= Seq(
  "-Werror"
)
