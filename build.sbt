ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.18"

lazy val root = (project in file("."))
  .settings(
    name := "CafeX"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.20" % Test