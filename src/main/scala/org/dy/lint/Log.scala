package org.dy.lint

import org.dy.lint.format.{Html, Plain}

import io.Source
import java.io.PrintWriter
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
//    println(project.output(new Plain))
    val txt = new PrintWriter("commented.txt");
    val html = new PrintWriter("highlight.html");
    txt.print(project.output(new Plain))
    html.print(project.output(new Html))
    source.close()
    txt.close()
    html.close()

  }
}
