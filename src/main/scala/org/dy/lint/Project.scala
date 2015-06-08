package org.dy.lint

import org.dy.lint.warning.Warning
import org.dy.lint.format.Format

import collection.mutable.ArrayBuffer

// TODO:dist is useless now.
class Project(val projectName: String, val encoding: String, val dist: String) extends FileBase {
  val fileNames = ArrayBuffer[String]()
  val files = ArrayBuffer[File]()

  override def addWarning(warn: Warning): Unit = {
    if (!fileNames.contains(warn.fileName)) {
      fileNames += warn.fileName
      files += new File(warn.fileName, encoding)
    }
    val i = fileNames.lastIndexOf(warn.fileName)
    files(i).addWarning(warn)
    super.addWarning(warn)
  }

  def output(f: Format): String = {
    var s = f.outputHeader(this)
    for (file <- files) {
      s += f.fileSeparatorStart(file)
      s += file.output(f)
      s += f.fileSeparatorEnd(file.fileName)
    }
    s + f.outputFooter()
  }
}
