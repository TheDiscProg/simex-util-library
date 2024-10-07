import sbt.librarymanagement.CrossVersion

lazy val scala2 = "2.13.14"
lazy val scala3 = "3.5.1"
lazy val supportedScalaVersions = List(scala2, scala3)

ThisBuild / organization := "thediscprog"

ThisBuild / version := "0.8.1"

lazy val commonSettings = Seq(
  scalaVersion := scala3,
  libraryDependencies ++= Dependencies.all,
  crossScalaVersions := supportedScalaVersions
)

lazy val root = (project in file("."))
  .enablePlugins(ScalafmtPlugin)
  .settings(
    commonSettings,
    name := "util-library",
    Compile / doc / sources := Seq.empty,
    scalacOptions ++= Scalac.options,
    libraryDependencies ++= {
      if (scalaVersion.value.startsWith("2"))
        Seq(
          compilerPlugin(("org.typelevel" %% "kind-projector" % "0.13.3").cross(CrossVersion.full)),
          compilerPlugin(("com.olegpy" %% "better-monadic-for" % "0.3.1")),
        )
      else
        Seq()
    },
    scalacOptions ++= {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2,13)) => Seq("-Ytasty-reader")
        case _ => Seq("-Yretain-trees")
      }
    }
  )

githubOwner :="TheDiscProg"
githubRepository := "util-library"

addCommandAlias("cleanTest", ";clean;scalafmt;test:scalafmt;test;")
addCommandAlias("cleanCoverage", ";clean;scalafmt;test:scalafmt;coverage;test;coverageReport;")