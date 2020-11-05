import sbt._

object Dependencies {
  val app = Seq(
    Library.atlassianConnectPlay,
    Library.atlassianConnectPlaySlick,
    Library.playSlickEvolutions,
    Library.postgres,
    Library.scalaTest % "test",
    Library.h2 % "test"
  )
}

object Version {
  val atlassianConnectPlay = "0.2.0"
  val atlassianConnectPlaySlick = "0.2.0"
  val playSlick = "5.0.0"
  val scalaTestPlusPlay = "5.1.0"
  val postgres = "9.4.1208"
  val h2 = "1.4.193"
}

object Library {
  val atlassianConnectPlay = "io.toolsplus" %% "atlassian-connect-play-core" % Version.atlassianConnectPlay
  val atlassianConnectPlaySlick = "io.toolsplus" %% "atlassian-connect-play-slick" % Version.atlassianConnectPlaySlick
  val playSlickEvolutions = "com.typesafe.play" %% "play-slick-evolutions" % Version.playSlick
  val scalaTest = "org.scalatestplus.play" %% "scalatestplus-play" % Version.scalaTestPlusPlay
  val postgres = "org.postgresql" % "postgresql" % Version.postgres
  val h2 = "com.h2database" % "h2" % Version.h2
}
