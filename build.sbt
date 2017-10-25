sbtPlugin := true

name := (name in ThisBuild).value

inThisBuild(Seq(
  name := "sbt-classpath-jar",
  organization := "org.lolhens",
  version := "1.2.0",

  bintrayReleaseOnPublish := false
))

addCrossSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.2.2")

def addCrossSbtPlugin(dependency: ModuleID): Setting[Seq[ModuleID]] =
  libraryDependencies += {
    val sbtV = (sbtBinaryVersion in pluginCrossBuild).value
    val scalaV = (scalaBinaryVersion in update).value
    Defaults.sbtPluginExtra(dependency, sbtV, scalaV)
  }

crossSbtVersions := Seq("0.13.16", "1.0.0")
