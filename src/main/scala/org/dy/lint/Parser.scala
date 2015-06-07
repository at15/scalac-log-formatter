package org.dy.lint

import org.dy.lint.warning.{NoWarning, Warning}

import collection.mutable.ArrayBuffer
import io.Source

// the new parser
class Parser {
  val warnings = ArrayBuffer[Warning]()

  def parse(src: Source) = {
    val lines = src.getLines().toArray
    var oneLine: String = ""
    var i = 0

    while (i < lines.length) {
      oneLine = lines(i)
//      println(oneLine)
      // TODO: use warning without sbt won't have the [warn] prefix
      if (oneLine.startsWith("[warn]")) {
        Warning.getWarn(oneLine) match {
          case _: NoWarning => // do nothing
          case w: Warning => {
            w.addCode(lines(i + 1))
            w.addCol(lines(i + 2))
            warnings.append(w)
            i += 2
          }
        }
      }
      i += 1
    }
  }
}