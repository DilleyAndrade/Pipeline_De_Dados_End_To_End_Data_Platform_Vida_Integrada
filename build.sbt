ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "vida_integrada",
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.5.7",
      "org.apache.spark" %% "spark-sql" % "3.5.7",
      "org.postgresql" % "postgresql" % "42.7.3",
      "com.typesafe" % "config" % "1.4.3"
    )
  )
