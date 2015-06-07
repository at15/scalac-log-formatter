package org.dy.lint.warning

/**
 * Created by Pillar on 2015/5/30.
 */
class Unused(fileName:String,lineNumber:Int,msg:String) extends Warning(fileName,lineNumber,msg){

}

object Unused{
  val pattern = "\\[warn]\\s(.*.scala):(\\d*):\\s(.*is never used)".r
}
