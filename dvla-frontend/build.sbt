name := """tdd-test-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

// routesGenerator := StaticRoutesGenerator

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatest" %% "scalatest" % "2.2.2" % "test",
  "org.scalatestplus" %% "play" % "1.2.0" % "test",
  "com.typesafe.play" %% "play-test" % "2.3.8" % "test",
  "org.pegdown" % "pegdown" % "1.4.2" % "test",
  "org.mockito" % "mockito-all" % "1.10.19" % "test"
)

