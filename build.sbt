val commonSettings = Seq(
  organization := "io.toolsplus",
  scalaVersion := "2.11.8",
  resolvers ++= Seq(
    "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
    "Bintary JCenter" at "http://jcenter.bintray.com"
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
  PlayKeys.playRunHooks <+= baseDirectory.map(base => Webpack(base)),
  compile <<= (compile in Compile) dependsOn transpileJs
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
