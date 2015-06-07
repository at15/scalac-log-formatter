package org.dy.lint.format

import org.dy.lint.File
import org.dy.lint.warning.{UnusedParam, Unused, DeadCode, Warning}

/**
 * Created by Pillar on 2015/5/31.
 */
class Html extends Format {
  // TODO:add highlight
  def transform(s: String): String = "<pre>" + s + "</pre>"

  def transform(w: Warning): String = w match {
    case w: DeadCode => render(w.code,w.lineNumber, "dead-code", w.msg)
    case w: Unused => render(w.code,w.lineNumber, "unused", w.msg)
    case w: UnusedParam => render(w.code, w.lineNumber, "unused-param", w.msg)
  }

  def fileSeparatorStart(file: File) = {
    "<p><strong>" +
      file.fileName +
      "</strong></p>" +
      "<p><ol>" +
      "<li>" + file.tp1Count + "</li>" +
      "<li>" + file.tp2Count + "</li>" +
      "<li>" + file.tp3Count + "</li>" +
      "</ol></p>\r\n"
  }

  def fileSeparatorEnd(fileName: String) = {
    "</br>"
  }

  override def outputHeader() = {
    "<html>" +
      "<head>" +
      "<style>.dead-code{background-color:lightgrey;} .unused{background-color:lightyellow;} .unused-param{background-color:#FF8161}</style>" +
      "<head><body>"
  }

  override def outputFooter() = {
    "</body></html>"
  }

  private def render(code: String, lineNumber:Int,htmlClass: String, msg: String): String = {
    "<pre class=\"" + htmlClass + "\" title=\"" + msg + "\">" + lineNumber + ": " + code + "</pre>\n"
  }
}
