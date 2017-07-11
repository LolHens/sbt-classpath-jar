package org.lolhens.sbt

import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.universal.UniversalPlugin
import sbt.Keys.{mappings, target}
import sbt.{AutoPlugin, IO, _}

/**
  * Created by u016595 on 11.07.2017.
  */
class ClasspathJarPlugin extends AutoPlugin {

  object autoImport {
    val additionalClasspathEntries: SettingKey[Seq[String]] = SettingKey[Seq[String]]("additionalClasspathEntries")
  }

  override def requires: Plugins = JavaAppPackaging && UniversalPlugin

  import JavaAppPackaging.autoImport._
  import UniversalPlugin.autoImport._
  import autoImport._

  override lazy val projectSettings = Seq(
    scriptClasspath := {
      val manifest = new java.util.jar.Manifest()
      manifest.getMainAttributes.putValue("Class-Path", (scriptClasspath.value ++ additionalClasspathEntries.value).mkString(" "))
      val classpathJar = (target in Universal).value / "lib" / "classpath.jar"
      IO.jar(Seq.empty, classpathJar, manifest)
      Seq("classpath.jar")
    },

    mappings in Universal += ((target in Universal).value / "lib" / "classpath.jar", "lib/classpath.jar")
  )
}
