package org.dy.lint.format

import org.dy.lint.{Project, File}
import org.dy.lint.warning.{UnusedParam, Unused, DeadCode, Warning}

/**
 * Created by Pillar on 2015/5/31.
 */
class Html extends Format {
  // TODO:add highlight
  def transform(s: String): String = "<pre>" + s + "</pre>"

  def transform(w: Warning): String = w match {
    case w: DeadCode => render(w.code, w.lineNumber, "dead-code", w.msg)
    case w: Unused => render(w.code, w.lineNumber, "unused", w.msg)
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
      "</ol></p>\n"
  }

  def fileSeparatorEnd(fileName: String) = {
    "</br>"
  }

  override def outputHeader(p: Project) = {
    "<html>" +
      "<head>" +
      "<title>" + p.projectName + "</title>" +
      "<style>.dead-code{color:green;} .unused{color:blue;} .unused-param{color:#FF8161} .line-number{color:white;background-color:grey}</style>" +
      "<head>" +
      "<body>" +
      "<h2>Dead code for project " + p.projectName + "</h2>" +
      "<div> type 1 " + p.tp1Count + "</div>" +
      "<div> type 2 " + p.tp2Count + "</div>" +
      "<div> type 3 " + p.tp3Count + "</div>"
  }

  override def outputFooter() = {
    "</body></html>"
  }

  private def render(code: String, lineNumber: Int, htmlClass: String, msg: String): String = {
    "<div class=\"\">" +
      "<div class=\"" + htmlClass + "\">" + msg + "</div>" +
      "<span class=\"line-number\">" + lineNumber + "</span>" + code.replace(" ", "&nbsp") +
      "</div>"
    //    "<pre class=\"" + htmlClass + "\" title=\"" + msg + "\">" + lineNumber + ": " + code + "</pre>\n"
  }
}
