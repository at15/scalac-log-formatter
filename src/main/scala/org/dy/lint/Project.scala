package org.dy.lint

import warning.Warning
import collection.mutable.ArrayBuffer

/**
 * Created by Pillar on 2015/5/30.
 */
class Project {
  private val fileNames = ArrayBuffer[String]()
  private val files = ArrayBuffer[File] ()

  def addWarning(warn:Warning): Unit ={
      if(!fileNames.contains(warn.fileName)){
        fileNames += warn.fileName
        files += new File
      }
      val i = fileNames.lastIndexOf(warn.fileName)
      files(i).addWarning(warn)
  }
}
