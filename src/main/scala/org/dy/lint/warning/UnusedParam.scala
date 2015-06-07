package org.dy.lint.warning

class UnusedParam(fileName: String, lineNumber: Int, msg: String) extends Warning(fileName, lineNumber, msg) {
  override val dctype = Type.tp2
}

object UnusedParam {
  val pattern = "\\[warn]\\s(.*.scala):(\\d*):\\s(.*\\[unused param].*)".r
}
