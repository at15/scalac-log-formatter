package org.dy.lint.warning

class Unused(fileName: String, lineNumber: Int, msg: String) extends Warning(fileName, lineNumber, msg) {
  override val dctype = Type.tp2
}

object Unused {
  val pattern = "\\[warn]\\s(.*.scala):(\\d*):\\s(.*is never used)".r
}
