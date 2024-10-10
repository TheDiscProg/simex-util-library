import sbt._

object Dependencies {

  private lazy val simexVersion = "0.9.1"
  private lazy val circeVersion = "0.14.10"
  private lazy val scaffeineVersion = "5.3.0"

  lazy val all = Seq(
    "io.github.thediscprog" %% "simex-messaging" % simexVersion,
    "com.github.jwt-scala" %% "jwt-circe" % "10.0.1",
    "dev.profunktor" %% "http4s-jwt-auth" % "1.2.3",
    "org.scalactic" %% "scalactic" % "3.2.19",
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,
    "com.github.blemale" %% "scaffeine" % scaffeineVersion,
    "org.scalatest" %% "scalatest" % "3.2.19" % Test,
    "org.scalatestplus" %% "scalacheck-1-18" % "3.2.19.0" % Test
  )
}
