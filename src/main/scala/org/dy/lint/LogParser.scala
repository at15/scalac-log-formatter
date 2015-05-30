package org.dy.lint

import org.dy.lint.warning.{Warning, DeadCode, Unused}

import collection.mutable.ArrayBuffer
import io.Source

/**
 * Created by Pillar on 2015/5/30.
 */
class LogParser {

  val warnings = ArrayBuffer[Warning]()

  def parse(src: Source) = {
    val lines = src.getLines().toArray
    var oneLine: String = ""
    var warnType: String = ""
    var i = 0

    while (i < lines.length) {
      oneLine = lines(i)
      if (oneLine.startsWith("[warn]")) {
        //        println(oneLine)
        warnType = Warning.getWarnType(oneLine)
        warnType match {
          case "dead code" => {
            warnings += new DeadCode(lines(i), lines(i + 1), lines(i + 2))
            i += 2
          }
          case "unused" => {
            warnings += new Unused(lines(i), lines(i + 1), lines(i + 2))
            i += 2
          }
          case "useless" => {
            println("useless warning" + lines(i))
          }
        }
      }
      i += 1
    }
  }
}
