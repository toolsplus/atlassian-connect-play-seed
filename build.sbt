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

lazy val publishSettings = Seq(
  releasePublishArtifactsAction := PgpKeys.publishSigned.value,
  homepage := Some(
    url("https://github.com/toolsplus/atlassian-connect-play-seed")),
  licenses := Seq(
    "Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ =>
    false
  },
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  autoAPIMappings := true,
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/toolsplus/atlassian-connect-play-seed"),
      "scm:git:git@github.com:toolsplus/atlassian-connect-play-seed.git"
    )
  ),
  developers := List(
    Developer("tbinna",
              "Tobias Binna",
              "tobias.binna@toolsplus.ch",
              url("https://twitter.com/tbinna"))
  )
)

lazy val noPublishSettings = Seq(
  publish := (),
  publishLocal := (),
  publishArtifact := false,
  publishTo := Some(
    Resolver.file("Unused transient repository", file("target/dummyrepo")))
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
  .settings(publishSettings)
  .settings(moduleSettings(project))
