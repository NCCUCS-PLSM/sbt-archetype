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
package edu.nccu.plsm.archetype

import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

/**
 * @version
 * @since
 */
object Main extends App with HelloWorld

trait HelloWorld {
  private[this] val logger = LoggerFactory.getLogger(getClass)

  def run(): Unit = {
    if (logger.isInfoEnabled) {
      (BuildInfo.toMap.toSeq ++ sys.props.toSeq.sortBy(_._1.charAt(0))) foreach {
        case (k, v) => logger.info(s"$k: $v")
      }
    }
    logger info getMessage
  }

  def getMessage: String = {
    val config = ConfigFactory.load()
    val appConfig = config.getConfig("app")
    appConfig.getString("message")
  }

  run()
}