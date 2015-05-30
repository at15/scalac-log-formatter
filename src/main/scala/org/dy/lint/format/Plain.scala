package org.dy.lint.format

import org.dy.lint.warning.{Unused, DeadCode, Warning}
import org.dy.lint.Project

// plain txt format
//class Plain(val project: Project) extends Format {
class Plain extends Format {
  def transform(s:String):String = s
  def transform(w:Warning):String = w match {
    case w: DeadCode => w.code + " // Dead Code: " + w.msg
    case w: Unused => w.code + " // Unused: " + w.msg
  }
}