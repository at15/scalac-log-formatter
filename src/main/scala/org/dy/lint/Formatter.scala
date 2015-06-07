package org.dy.lint

import org.dy.lint.format.{Html, Plain}

import collection.mutable.ArrayBuffer
import io.Source


object Formatter {

  def main(args: Array[String]) = {
    var logFile = ""
    var encoding = "UTF-8"
    for (arg <- args) {
      if (arg.startsWith("-logSrc")) {
        logFile = arg.substring("-logSrc:".length)
      }
      // TODO:better detect encoding automatically
      if(arg.startsWith("-logEncoding")){
        encoding = arg.substring("-logEncoding:".length)
      }
    }

    // TODO:check if file exists
    try{
      val source = Source.fromFile(logFile, "UTF-8")
    }


//    println(logFile)
//    try {
//      // TODO: need to be gbk in windows
//      val source = Source.fromFile(logFile, "UTF-8") // though the log is GBK, but only english, so
//      val parser = new LogParser
//      val project = new Project("Test")
//      parser.parse(source)
//      for (warn <- parser.warnings) {
//        project.addWarning(warn)
//      }
//      //    println(project.output(new Plain))
//      val txt = new PrintWriter("commented.txt");
//      val html = new PrintWriter("highlight.html");
//      txt.print(project.output(new Plain))
//      html.print(project.output(new Html))
//      source.close()
//      txt.close()
//      html.close()
//    } catch {
//      case e : Throwable => {
//        println("Can't read log file!" + e.getMessage)
//        System.exit(-1)
//      }
//    }
  }
}
