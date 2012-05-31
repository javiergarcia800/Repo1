package com.seg47.util

object Util {

  def tieneDecimales(number:Double) : Boolean = {
    number - number.intValue() > 0
  }
  
}