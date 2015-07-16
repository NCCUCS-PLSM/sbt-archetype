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

import edu.nccu.plsm.Dependencies
import edu.nccu.plsm.Settings

lazy val root = (project in file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(Settings.default: _*)
  .settings(Settings.plugin: _*)
  .settings(Settings.play: _*)
  .settings(
    name := "sbt-archetype-root"
  ).aggregate(subProject, playProject)

lazy val subProject = (project in file("sub"))
  .enablePlugins(BuildInfoPlugin)
  .settings(Settings.default: _*)
  .settings(Settings.plugin: _*)
  .settings(
    name := "sbt-archetype-project",
    libraryDependencies ++= Dependencies.project
  )

lazy val playProject = (project in file("play"))
  .enablePlugins(PlayScala, SbtWeb, BuildInfoPlugin)
  .settings(Settings.default: _*)
  .settings(Settings.plugin: _*)
  .settings(Settings.play: _*)
  .settings(
    name := "sbt-archetype-play",
    libraryDependencies ++= Dependencies.playProject
  ).dependsOn(subProject)