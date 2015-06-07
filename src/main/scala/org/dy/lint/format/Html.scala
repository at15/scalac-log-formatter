package org.dy.lint.format

import org.dy.lint.warning.{Unused, DeadCode, Warning}

/**
 * Created by Pillar on 2015/5/31.
 */
class Html extends Format {
  // TODO:add highlight
  def transform(s: String): String = "<pre>" + s + "</pre>"

  def transform(w: Warning): String = w match {
    case w: DeadCode => render(w.code, "dead-code", w.msg)
    case w: Unused => render(w.code, "unused", w.msg)
  }

  def fileSeparatorStart(fileName: String) = {
    "<p><strong>" + fileName + "</strong></p>"
  }

  def fileSeparatorEnd(fileName: String) = {
    "</br>"
  }

  override def outputHeader() = {
    "<html>" +
      "<head>" +
      "<style>.dead-code{background-color:lightgrey;} .unused{background-color:lightyellow;}</style>" +
      "<head><body>"
  }

  override def outputFooter() = {
    "</body></html>"
  }

  private def render(code: String, htmlClass: String, msg: String): String = {
    "<pre class=\"" + htmlClass + "\" title=\"" + msg + "\">" + code + "</pre>"
  }
}
