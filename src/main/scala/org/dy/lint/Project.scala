package org.dy.lint

import org.dy.lint.warning.Warning
import org.dy.lint.format.Format

import collection.mutable.ArrayBuffer


class Project(val name: String) {
  //  private val fileNames = ArrayBuffer[String]()
  val fileNames = ArrayBuffer[String]()
  val files = ArrayBuffer[File]()

  def addWarning(warn: Warning): Unit = {
    if (!fileNames.contains(warn.fileName)) {
      fileNames += warn.fileName
      files += new File(warn.fileName)
    }
    val i = fileNames.lastIndexOf(warn.fileName)
    files(i).addWarning(warn)
  }

  def output(f: Format): String = {
    var s = f.outputHeader()
    for (file <- files) {
      s += f.fileSeparatorStart(file.fileName)
      s += file.output(f)
      s += f.fileSeparatorEnd(file.fileName)
    }
    s + f.outputFooter()
  }
}
