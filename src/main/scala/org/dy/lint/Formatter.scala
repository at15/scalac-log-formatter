package org.dy.lint

import scala.io.Source
import java.io.PrintWriter

import org.dy.lint.format.{Html, Plain}

object Formatter {

  def main(args: Array[String]) = {
    var logFile = ""
    var encoding = "UTF-8"
    for (arg <- args) {
      if (arg.startsWith("-logSrc")) {
        logFile = arg.substring("-logSrc:".length)
      }
      // TODO:better detect encoding automatically
      if (arg.startsWith("-logEncoding")) {
        encoding = arg.substring("-logEncoding:".length)
      }
    }

    try {
      println("parse log " + logFile + " in " + encoding + " format")
      val source = Source.fromFile(logFile, encoding)
      val parser = new Parser
      val project = new Project("linter-me")
      parser.parse(source)
      for (warn <- parser.warnings) {
        project.addWarning(warn)
      }
      val txt = new PrintWriter("commented.txt");
      val html = new PrintWriter("highlight.html");
      txt.print(project.output(new Plain))
      html.print(project.output(new Html))
      source.close()
      txt.close()
      html.close()
    } catch {
      case e: Throwable => {
        println("Error formatting log")
        println(e.getMessage)
      }
    }
  }
}
