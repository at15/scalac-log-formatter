package org.dy.lint

import org.dy.lint.format.Plain

import io.Source
import fastparse._


object Log {
  def main(args: Array[String]) = {
    println("Let's parse log oh yeah!")
    // open the log file
    val logFile = "logs/linter-me-package.log"
    val source = Source.fromFile(logFile, "UTF-8") // though the log is GBK, but only english, so
    val parser = new LogParser
    val project = new Project("Test")
    parser.parse(source)
    for(warn <- parser.warnings){
      project.addWarning(warn)
    }
    println(project.output(new Plain))
    source.close()

  }
}
