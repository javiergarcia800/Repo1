package com.seg47.util

import com.seg47.util.Constantes._
import com.seg47.vo.Datos

object Reader {
  
  def readData():Datos = {
    val nums = new Array[Int](CANTIDAD_NUMEROS)
    for (i <- 0 until nums.length) {
      print("Numero : ")
      nums(i) = readInt
    }
    print("CIFRA  : ")
    new Datos(nums, readInt)
  }
  
}