package org.dy.lint

import scala.io.Source
import java.io.PrintWriter

import org.dy.lint.format.{Html, Plain}

object Formatter {

  def main(args: Array[String]) = {
    var logFile = "logs/unused_param.log"
    //    var encoding = "UTF-8"
    var logEncoding = "GBK"
    var srcEncoding = "UTF-8"
    var dist = "dist"
    var projectName = "default"
    for (arg <- args) {
      if (arg.startsWith("-logSrc")) {
        logFile = arg.substring("-logSrc:".length)
      }
      // TODO:better detect encoding automatically
      if (arg.startsWith("-logEncoding")) {
        logEncoding = arg.substring("-logEncoding:".length)
      }
      if (arg.startsWith("-srcEncoding")) {
        srcEncoding = arg.substring("-srcEncoding:".length)
      }
      // TODO:create folder when dist doesn't exist
      if (arg.startsWith("-dist")) {
        dist = arg.substring("-dist:".length)
      }
      if (arg.startsWith("-projectName")) {
        projectName = arg.substring("-projectName:".length)
      }
    }

    //    try {
    println("parse log " + logFile + " in " + logEncoding + " format" + " to " + dist + " read src file in " + srcEncoding)
    val source = Source.fromFile(logFile, logEncoding)
    val parser = new Parser
    val project = new Project(projectName, srcEncoding, dist)
    parser.parse(source)
    for (warn <- parser.warnings) {
      project.addWarning(warn)
    }
    val txt = new PrintWriter(dist + "/commented.txt");
    val html = new PrintWriter(dist + "/highlight.html");
    txt.print(project.output(new Plain))
    html.print(project.output(new Html))
    source.close()
    txt.close()
    html.close()
    println("got type1 " + project.tp1Count + " warnings")
    println("got type2 " + project.tp2Count + " warnings")
    println("got type3 " + project.tp3Count + " warnings")
    //    } catch {
    //      case e: Throwable => {
    //        println("Error formatting log")
    //        println(e.getMessage)
    //        println(e.getStackTrace.toString)
    //      }
    //    }
  }

  //  def toSource(inputStream:InputStream): scala.io.BufferedSource = {
  //    import java.nio.charset.Charset
  //    import java.nio.charset.CodingErrorAction
  //    val decoder = Charset.forName("UTF-8").newDecoder()
  //    decoder.onMalformedInput(CodingErrorAction.IGNORE)
  //    scala.io.Source.fromInputStream(inputStream)(decoder)
  //  }
}
