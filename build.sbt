sbtPlugin := true

name := (name in ThisBuild).value

inThisBuild(Seq(
  name := "sbt-classpath-jar",
  organization := "org.lolhens",
  version := "1.1.4",

  scalaVersion := "2.10.6",

  externalResolvers := Seq(
    Resolver.defaultLocal,
    "artifactory-maven" at "http://lolhens.no-ip.org/artifactory/maven-public/",
    Resolver.url("artifactory-ivy", url("http://lolhens.no-ip.org/artifactory/ivy-public/"))(Resolver.ivyStylePatterns)
  ),

  scalacOptions ++= Seq("-Xmax-classfile-name", "127")
))

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.2.2")

crossSbtVersions := Seq("0.13.16", "1.0.0")
