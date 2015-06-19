import sbt.Def

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
trait Dependencies {
  object Library extends Library {
    override protected[this] def version = Release
  }
  import Library._

  lazy val logback = Seq(
    logbackCore,
    logbackClassic
  )

  lazy val slf4j = Seq(
    slf4jApi
  )

  lazy val tool = Seq(
    scopt,
    typesafeConfig
  )

  lazy val scalaTools = Def.setting{
    Seq(scalaCompiler.value, scalaReflect.value)
  }

  lazy val testing = Seq(
    scalatest,
    mockito
  )

  lazy val project = slf4j ++ logback ++ tool ++ testing

}

object Dependencies extends Dependencies