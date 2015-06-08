package org.dy.lint.format

import org.dy.lint.{Project, File}
import org.dy.lint.warning.Warning

/**
 * Created by Pillar on 2015/5/30.
 */
abstract class Format {
  def transform(s: String): String

  def transform(w: Warning): String

  def fileSeparatorStart(file:File): String

  def fileSeparatorEnd(fileName: String): String

  def outputHeader(p:Project): String = ""

  def outputFooter(): String = ""
}
