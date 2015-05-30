package org.dy.lint

import org.dy.lint.warning.Warning
import org.dy.lint.format.Format

import io.Source
import collection.mutable.ArrayBuffer
import collection.mutable.HashMap

/**
 * Created by Pillar on 2015/5/30.
 */
class File(val fileName: String) {
  //  val warnings = ArrayBuffer[Warning]()
  val warnings = HashMap[Int, Warning]()
  val warningLines = ArrayBuffer[Int]()

  def addWarning(warn: Warning) = {
    // TODO: order by line number
    warnings(warn.lineNumber.toInt) = warn
    warningLines += warn.lineNumber.toInt
  }

  def folder(): String = {
    "this is my folder"
  }

  private def hasWarning(lineNumber: Int): Boolean = {
    warningLines.contains((lineNumber))
  }

  def output(f: Format): String = {
    val src = Source.fromFile(fileName, "UTF-8").getLines()
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
