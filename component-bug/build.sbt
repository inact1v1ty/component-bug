organization := "com.example"

name := "component-bug"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.8"

val korolevVersion = "0.9.0"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-simple" % "1.7.+",
  "com.github.fomkin" %% "korolev-server-akkahttp" % korolevVersion
)
