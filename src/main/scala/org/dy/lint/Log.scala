package org.dy.lint

import io.Source
import fastparse._

/**
 * Created by Pillar on 2015/5/30.
 */
object Log {
  def main(args: Array[String]) = {
    println("Log lalala")
    // open the log file
    val logFile = "logs/linter-me-package.log"
    val source = Source.fromFile(logFile,"UTF-8") // though the log is GBK, but only english, so
    val linteIterator = source.getLines()
    for(oneLine <- linteIterator) {
      println(oneLine)
    }
  }
}
