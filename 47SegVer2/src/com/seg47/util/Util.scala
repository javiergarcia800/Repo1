package com.seg47.util

object Util {

  def tieneDecimales(number:Double) : Boolean = {
    number - number.intValue() > 0
  }

    /**
   * Regresa verdadero si el par&aacute;metro num1 es m&aacute;s cercano a la cantidad pasada como par&aacute;metro,
   * regresa falso en caso contrario. Si los 2 n&uacute;meros est&aacute;n igual de cercanos a la cantidad se regresa
   * verdadero.
   * @param num1:Int .- Primer n&uacute;mero a comparar.
   * @param num2:Int .- Segundo n&uacute;mero a comparar.
   * @param cantidad:Int .- Cantidad a compara contra los n&uacute;meros.
   */
  def cantidadMasCercana(num1:Int, num2:Int, cantidad:Int) : Boolean = {
    if ( math.abs(num1 - cantidad) <= math.abs(num2-cantidad)  ) true else false
  }
  
}