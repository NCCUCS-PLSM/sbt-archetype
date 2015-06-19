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

import sbt.Keys._
import sbt._

trait BasicLibrary extends VersionHelper {
  protected[this] def javaLibrary(group: String, version: String) = {
    (module: String) => group % module % v"$version"
  }

  protected[this] def scalaLibrary(group: String, version: String) = {
    (module: String) => group %% module % v"$version"
  }
}

trait Slf4jLibrary extends BasicLibrary {
  lazy val slf4jApi = module("slf4j-api")
  lazy val osgiOverSlf4j = module("osgi-over-slf4j")
  lazy val log4jOverSlf4j = module("log4j-over-slf4j")
  lazy val jclOverSlf4j = module("jcl-over-slf4j")
  lazy val julToSlf4j = module("jul-to-slf4j")

  private[this] def module = javaLibrary("org.slf4j", Version.slf4j)
}

trait LogbackLibrary extends BasicLibrary {
  lazy val logbackCore = module("logback-core")
  lazy val logbackClassic = module("logback-classic")

  private[this] def module = javaLibrary("ch.qos.logback", Version.logback)
}

trait ToolLibrary extends BasicLibrary {
  lazy val scalaReflect       = Def.setting { "org.scala-lang"      %   "scala-reflect"        %   (scalaVersion in ThisBuild).value}
  lazy val scalaCompiler      = Def.setting { "org.scala-lang"      %   "scala-compiler"       %   (scalaVersion in ThisBuild).value}
  lazy val typesafeConfig     =               "com.typesafe"        %   "config"               %  v"1.3.0"
  lazy val scopt              =               "com.github.scopt"    %   "scopt_2.11"           %  v"3.3.0"
}

trait TestingLibrary extends BasicLibrary {
  lazy val mockito            =               "org.mockito"         %   "mockito-core"         %  v"2.0.14-beta"   %  Test
  lazy val scalatest          =               "org.scalatest"       %%  "scalatest"            %  v"2.2.5-M1"      %  Test
}

trait Library extends Slf4jLibrary with LogbackLibrary with ToolLibrary with TestingLibrary
