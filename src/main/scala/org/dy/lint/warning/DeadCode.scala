package org.dy.lint.warning

class DeadCode(fileName: String, lineNumber: Int, msg: String) extends Warning(fileName, lineNumber, msg) {
  override val dctype = Type.tp1
}

object DeadCode {
  val pattern = "\\[warn]\\s(.*.scala):(\\d*):\\s(.*dead code.*)".r
}
