package org.dy.lint.format

import org.dy.lint.warning.Warning

/**
 * Created by Pillar on 2015/5/30.
 */
abstract class Format {
  def transform(s: String): String

  def transform(w: Warning): String

  def fileSeparatorStart(fileName: String): String

  def fileSeparatorEnd(fileName: String): String

  def outputHeader(): String = ""

  def outputFooter(): String = ""
}
