package org.dy.lint.warning

abstract class Warning(val fileName: String, val lineNumber: Int, val msg: String) {
  var code = ""
  var col = 0
  val dctype: Int

  def addCode(code: String) = {
    this.code = code.substring("[warn] ".length)
  }

  def addCol(colStr: String) = {
    col = colStr.indexOf("^") - "[warn] ".length
  }
}

object Warning {
  def getWarn(line: String): Warning = {
    line match {
      case DeadCode.pattern(f, l, m) => new DeadCode(f, l.toInt, m)
      case Unused.pattern(f, l, m) => new Unused(f, l.toInt, m)
      case _ => new NoWarning
    }
  }
}
