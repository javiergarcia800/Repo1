package com.seg47.util

import com.seg47.vo.Datos

object Reader {
  
  def readData():Datos = {
    val nums = new Array[Int](Constantes.AMOUNT_NUMBERS)
    for (i <- 0 until nums.length) {
      print("Numero : ")
      nums(i) = readInt
    }
    print("CIFRA  : ")
    new Datos(nums, readInt)
  }
  
}