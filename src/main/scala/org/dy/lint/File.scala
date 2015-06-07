package org.dy.lint

import org.dy.lint.warning.Warning
import org.dy.lint.format.Format

import io.Source
import collection.mutable.ArrayBuffer
import collection.mutable.HashMap

class File(val fileName: String, val encoding: String) extends FileBase {
  val warnings = HashMap[Int, Warning]()
  val warningLines = ArrayBuffer[Int]()

  override def addWarning(warn: Warning): Unit = {
    // TODO: order by line number
    warnings(warn.lineNumber.toInt) = warn
    warningLines += warn.lineNumber.toInt
    super.addWarning(warn)
    //    () // for discarded non-Unit value warning
  }

  private def hasWarning(lineNumber: Int): Boolean = {
    warningLines.contains((lineNumber))
  }

  def output(f: Format): String = {
    val src = Source.fromFile(fileName, encoding).getLines()
    var lineNumber = 0
    var s = ""
    for (line <- src) {
      lineNumber += 1
      if (hasWarning((lineNumber))) {
        s += f.transform(warnings(lineNumber))
      } else {
        s += f.transform(line)
      }
      s += "\n"
    }
    s
  }
}
