package org.dy.lint.warning

/**
 * Created by Pillar on 2015/5/30.
 */
abstract class Warning(val l1:String,val l2:String,val l3:String) {
}

object Warning {
  def getWarnType(line:String) : String = {
    line match {
      case _ if line.contains("dead code") => "dead code"
      case _ if line.endsWith(" is never used") => "unused"
      case _ => "useless"
    }
  }

  def isCode(line:String) : Boolean = {
    false
  }

  def isPos(line:String) : Boolean = {
    false
  }
}
