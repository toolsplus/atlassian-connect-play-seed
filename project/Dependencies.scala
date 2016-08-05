import sbt._

object Dependencies {
  val app = Seq(
    Library.atlassianConnectPlay,
    Library.atlassianConnectPlaySlick,
    Library.playSlickEvolutions,
    Library.postgres,
    Library.scalaTest % "test"
  )
}

object Version {
  val atlassianConnectPlay = "0.0.2"
  val atlassianConnectPlaySlick = "0.0.1"
  val playSlick = "2.1.0"
  val postgres = "9.4.1208"
  val scalaTestPlusPlay = "2.0.0"
}

object Library {
  val atlassianConnectPlay = "io.toolsplus" %% "atlassian-connect-play-core" % Version.atlassianConnectPlay
  val atlassianConnectPlaySlick = "io.toolsplus" %% "atlassian-connect-play-slick" % Version.atlassianConnectPlaySlick
  val playSlickEvolutions = "com.typesafe.play" %% "play-slick-evolutions" % Version.playSlick
  val postgres = "org.postgresql" % "postgresql" % Version.postgres
  val scalaTest = "org.scalatestplus.play" %% "scalatestplus-play" % Version.scalaTestPlusPlay
}
