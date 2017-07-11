package org.lolhens.sbt

import java.util.jar.Attributes

import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.universal.UniversalPlugin
import sbt.Keys.{mappings, target}
import sbt.{AutoPlugin, IO, _}

/**
  * Created by u016595 on 11.07.2017.
  */
class PackagedClasspathJarPlugin extends AutoPlugin {

  object autoImport {
    val classpathJarName: SettingKey[String] = SettingKey[String]("classpathJarName")

    val additionalClasspathEntries: SettingKey[Seq[String]] = SettingKey[Seq[String]]("additionalClasspathEntries")
  }

  override def requires: Plugins = JavaAppPackaging && UniversalPlugin

  import JavaAppPackaging.autoImport._
  import UniversalPlugin.autoImport._
  import autoImport._

  override lazy val projectSettings = Seq(
    classpathJarName := "classpath.jar",

    scriptClasspath := {
      val manifest = new java.util.jar.Manifest()

      manifest.getMainAttributes.putValue(
        Attributes.Name.CLASS_PATH.toString,
        (scriptClasspath.value ++ additionalClasspathEntries.value).mkString(" ")
      )

      val classpathJar = (target in Universal).value / "lib" / classpathJarName.value

      IO.jar(Seq.empty, classpathJar, manifest)

      Seq(classpathJarName.value)
    },

    mappings in Universal += (
      (target in Universal).value / "lib" / classpathJarName.value,
      s"lib/${classpathJarName.value}"
    )
  )
}
