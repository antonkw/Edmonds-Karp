ThisBuild / scalaVersion := "2.11.12"
ThisBuild / organization := "kov"

lazy val hello = (project in file("."))
  .settings(
    name := "Hello"
  )