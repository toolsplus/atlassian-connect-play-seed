import scala.sys.process.Process

val commonSettings = Seq(
  organization := "io.toolsplus",
  scalaVersion := "2.13.2",
  resolvers ++= Seq(
    "Typesafe repository releases" at "https://repo.typesafe.com/typesafe/releases/",
    "Bintary JCenter" at "https://jcenter.bintray.com"
  )
)

val scoverageSettings = Seq(
  coverageExcludedPackages := """.*controllers\..*Reverse.*;.*Routes.*;"""
)

def moduleSettings(project: Project) = {
  Seq(
    description := project.id.split("-").map(_.capitalize).mkString(" "),
    name := project.id
  )
}

val transpileJs = taskKey[Unit]("Transpile frontend javascript")

lazy val jsSettings = Seq(
  transpileJs := {
    Process(s"rm -rfv /public/js", baseDirectory.value).!
    Process("npm install", baseDirectory.value).!
    Process("node_modules/.bin/webpack", baseDirectory.value).!
  },
  PlayKeys.playRunHooks += baseDirectory.map(Webpack.apply).value,
  compile := ((compile in Compile) dependsOn transpileJs).value
)

lazy val `atlassian-connect-play-seed` = project
  .in(file("."))
  .enablePlugins(PlayScala)
  .settings(libraryDependencies ++= Dependencies.app)
  .settings(commonSettings: _*)
  .settings(jsSettings: _*)
  .settings(
    TwirlKeys.templateFormats += ("json" -> "play.twirl.api.TxtFormat"))
  .settings(scoverageSettings: _*)
  .settings(moduleSettings(project))
