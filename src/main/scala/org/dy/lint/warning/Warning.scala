package org.dy.lint.warning

import scala.util.matching.Regex

/**
 * Created by Pillar on 2015/5/30.
 */
//abstract class Warning(val l1:String,val l2:String,val l3:String) {
//  val reg = "\\[warn]\\s(.*.scala):(\\d*):\\s(.*)".r
//  // TODO: deal with l2
//  val reg(fileName,lineNumber,msg) = l1
//  val code = l2.substring(7)
//  val colNumber = l3.indexOf("^") - "[warn] ".length
//}

abstract class Warning(val fileName: String, val lineNumber: Int, val msg: String) {
  var code = ""
  var col = 0

  def addCode(code: String) = {
    this.code = code
  }

  def addCol(colStr: String) = {

  }
}

object Warning {
  //  def baseRegexp :String  = "\\[warn]\\s(.*.scala):(\\d*):\\s(.*)"
  def allWarnings = Seq(

  )

  def getWarnType(line: String): String = {
    line match {
      case _ if line.contains("dead code") => "dead code"
      case _ if line.endsWith(" is never used") => "unused"
      case _ => "useless"
    }
  }

  def getWarn(line: String): Warning = {
    line match {
      case DeadCode.pattern(f, l, m) => new DeadCode(f, l.toInt, m)
      case Unused.pattern(f, l, m) => new Unused(f, l.toInt, m)
      case _ => new NoWarning
    }
  }
}
