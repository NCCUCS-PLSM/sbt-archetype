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

package edu.nccu.plsm

// reviewed: 20150720
trait BasicVersion {
  lazy val akka = "2.4-M2"
  lazy val logback = "1.1.4-PLSM-SNAPSHOT"
  lazy val slf4j = "1.7.12"

  lazy val scala12 = "2.12.0-M2"
  lazy val scala11_7 = "2.11.7"
  lazy val scala11_6 = "2.11.6"
  lazy val scala11_5 = "2.11.5"
  lazy val scala11_4 = "2.11.4"
  lazy val integration = "latest.integration"
  lazy val milestone = "latest.milestone"
  lazy val release = "latest.release"
}