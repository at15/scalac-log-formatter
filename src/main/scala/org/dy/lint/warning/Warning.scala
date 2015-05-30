package org.dy.lint.warning

import scala.util.matching.Regex

/**
 * Created by Pillar on 2015/5/30.
 */
abstract class Warning(val l1:String,val l2:String,val l3:String) {
  val reg = "\\[warn]\\s(.*.scala):(\\d*):\\s(.*)".r
  // TODO: deal with l2
  val reg(fileName,lineNumber,msg) = l1
  val pos = l3.indexOf("^") - "[warn] ".length

}

object Warning {
  def getWarnType(line:String) : String = {
    line match {
      case _ if line.contains("dead code") => "dead code"
      case _ if line.endsWith(" is never used") => "unused"
      case _ => "useless"
    }
  }
}
