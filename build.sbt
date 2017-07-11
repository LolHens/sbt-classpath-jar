sbtPlugin := true

name := (name in ThisBuild).value

inThisBuild(Seq(
  name := "sbt-classpath-jar",
  organization := "org.lolhens",
  version := "1.0.0",

  scalaVersion := "2.10.6",

  externalResolvers := Seq(
    "artifactory-maven" at "http://lolhens.no-ip.org/artifactory/maven-public/",
    Resolver.url("artifactory-ivy", url("http://lolhens.no-ip.org/artifactory/ivy-public/"))(Resolver.ivyStylePatterns)
  ),

  scalacOptions ++= Seq("-Xmax-classfile-name", "254"),

  publishTo := Some(Resolver.file("file", new File("target/releases")))
))

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.2.0")
