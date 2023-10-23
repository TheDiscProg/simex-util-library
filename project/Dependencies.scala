import sbt._

object Dependencies {

  lazy val all = Seq(
    "com.github.jwt-scala" %% "jwt-circe" % "9.3.0",
    "dev.profunktor" %% "http4s-jwt-auth" % "1.2.0",
    "org.scalactic" %% "scalactic" % "3.2.15",
    "io.circe" %% "circe-core" % "0.14.5",
    "io.circe" %% "circe-generic" % "0.14.5",
    "io.circe" %% "circe-parser" % "0.14.5",
    "org.scalatest" %% "scalatest" % "3.2.15" % Test,
    "org.scalatestplus" %% "scalacheck-1-17" % "3.2.17.0" % Test
  )
}
