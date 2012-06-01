package com.seg47

import com.seg47.util._
import com.seg47.vo._

/**
 * Trail para especificar las operaciones requeridas de un Procesador,
 * tiene implementaciones de varios m&eacute;todos para compara operaciones.
 */
trait BaseProcesador {

  /**
   * Declaraci&oacute;n de la operaci&oacute;n principal de un procesador.
   * @param numeros:List[Int] .- Lista de n&uacute;meros para combinarlos y obtener una cantidad.
   * @param cantidad: Int .- Cantidad a obtener con la combinaci&oacute;n de n&uacute;meros.
   * @return Operacion .- Objeto de tipo Operacion con la mejor combinaci&oacute;n de n&uacute;meros. 
   */
  def getOperacion(numeros:List[Int], cantidad: Int) : Operacion

  /**
   * M&eacute;todo para obtener la mejor operaci&oacute;n de una lista de operaciones.
   * @param operaciones:List[Operacion] .- Lista de posibles combinaciones de n&uacute;meros.
   * @param cantidadAEncontrar:Int .- Cantidad a obtener con la combinaci&oacute;n de n&uacute;meros.
   * @return Operacion .- La mejor operaci&oacute;n de la lista de operaciones pasada como par&aacute;metro.
   */
  def mejorOperacion(operaciones:List[Operacion], cantidadAEncontrar:Int) : Operacion = {
    operaciones.reduceLeft( (a,b) => if ( Util.cantidadMasCercana(a.calculaOperacion(), b.calculaOperacion(), cantidadAEncontrar) ) a else b )
  }

}