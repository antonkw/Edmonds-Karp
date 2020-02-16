package kov.util

object Logger {
  def debug(s: => String) = {
    println(s)
  }

  def info(s: String) = println(s)
}
