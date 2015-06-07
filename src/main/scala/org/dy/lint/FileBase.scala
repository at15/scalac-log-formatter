package org.dy.lint

import org.dy.lint.format.Format
import org.dy.lint.warning.{Type, Warning}

abstract class FileBase {
  var tp1Count = 0
  var tp2Count = 0
  var tp3Count = 0

  def output(f: Format): String

  def addWarning(warn: Warning): Unit = {
    warn.dctype match {
      case Type.tp1 => tp1Count += 1
      case Type.tp2 => tp2Count += 1
      case Type.tp3 => tp3Count += 1
    }
    ()
  }
}
