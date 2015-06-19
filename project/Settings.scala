/*
 * Licensed to the Programming Language and Software Methodology Lab (PLSM)
 * under one or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership.
 * The PLSM licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.atlassian.labs.gitstamp.GitStampPlugin._
import org.scalastyle.sbt.ScalastylePlugin._
import sbt.Keys._
import sbt._
import sbtbuildinfo.BuildInfoPlugin.autoImport._

import scala.compat.Platform

object Settings {
  lazy val default = Seq(
    organization := "edu.nccu.plsm.archetype",
    version := "0.0.1-SNAPSHOT",
    crossScalaVersions := Seq(Version.scalaVersion12, Version.scalaVersion11),
    scalaVersion := crossScalaVersions.value.head,
    scalacOptions ++= commonScalacOptions,
    scalacOptions ++= {
      scalaVersion.value match {
        case ScalaMinorVersion("11") => {
          ConsoleLogger().info("Using scala 2.11 configuration")
          Seq(
            "-optimise",
            "-Xverify"
          )
        }
        case ScalaMinorVersion("12") => {
          ConsoleLogger().info("Using scala 2.12 configuration")
          Seq(
            "-Ybackend:GenBCode",
            "-Ylinearizer:dump",
            "-Yopt-inline-heuristics:everything"
          )
        }
      }
    },
    javacOptions ++= Seq(
      "-source", "1.8",
      "-target", "1.8",
      "-encoding", "UTF-8",
      "-Xlint:all",
      "-Xdoclint:all",
      "-XDignore.symbol.file",
      "-g",
      "-deprecation"
    ),
    javaOptions in run ++= Seq(
      "-Djava.net.preferIPv4Stack=true",
      "-ea"
    ),
    logLevel := Level.Info,
    persistLogLevel := Level.Info,
    ivyLoggingLevel := UpdateLogging.Full,
    resolvers ++= Seq(
      "PLSM Maven" at "http://www.plsm.cs.nccu.edu.tw/repository/public",
      Resolver.url("PLSM Ivy", url("http://www.plsm.cs.nccu.edu.tw/repository/remote-ivy-repos"))(Resolver.ivyStylePatterns)
    ),
    conflictManager := ConflictManager.latestRevision
  )
  lazy val plugin = Seq(
    compileScalastyle <<= scalastyle in Compile toTask "",
    testScalastyle <<= scalastyle in Test toTask "",
    (compile in Compile) <<= (compile in Compile) dependsOn compileScalastyle,
    (compile in Test) <<= (compile in Test) dependsOn testScalastyle,

    buildInfoKeys := Seq(
      name,
      version,
      scalaVersion,
      sbtVersion,
      BuildInfoKey.action("buildTime") {
        Platform.currentTime
      },
      buildInfoBuildNumber
    ),
    buildInfoPackage <<= organization,
    buildInfoOptions := Seq(BuildInfoOption.ToMap, BuildInfoOption.ToJson),

    packageOptions <+= (packageOptions in Compile, packageOptions in packageBin) map {
      (a, b) =>
        Package.ManifestAttributes(repoInfo: _*)
    }
  )
  private[this] lazy val ScalaMinorVersion = """^2\.([0-9]+)\..+$""".r
  private[this] lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")
  private[this] lazy val testScalastyle = taskKey[Unit]("testScalastyle")
  private[this] lazy val commonScalacOptions = Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-explaintypes",
    "-feature",
    "-g:vars",
    "-target:jvm-1.8",
    "-unchecked",
    "-Xcheckinit",
    "-Xexperimental",
    "-Xfuture",
    "-Xlint:_",
    "-Xlog-free-terms",
    "-Xlog-free-types",
    "-Xlog-reflective-calls",
    "-Xmigration",
    "-Xprint-types",
    "-Ybreak-cycles",
    "-Yclosure-elim",
    "-Yconst-opt",
    "-Ydead-code",
    "-Yinline",
    "-Yinline-handlers",
    "-Yopt:_",
    "-Ywarn-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-inaccessible",
    "-Ywarn-infer-any",
    "-Ywarn-nullary-override",
    "-Ywarn-nullary-unit",
    "-Ywarn-numeric-widen",
    "-Ywarn-unused",
    "-Ywarn-unused-import",
    "-Ywarn-value-discard"
  )

}
