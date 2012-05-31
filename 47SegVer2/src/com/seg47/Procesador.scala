package com.seg47

import com.seg47.vo._

object Procesador {

  def encuentraCombinacion(datos: Datos) {
    val numeros = datos.numeros.toList.sort(_ > _)
    val cantidad = datos.cantidad
    var operacionSimple = ProcesadorSimple.getOperacion(numeros, cantidad)
    var operacionCompuesta = ProcesadorCompuesto.getOperacion(numeros, cantidad)
    
    val operacion = ProcesadorSimple.mejorOperacion(List(operacionSimple, operacionCompuesta), cantidad)
    if ( operacion == operacionSimple ) println("OPERACION SIMPLE")
    if ( operacion == operacionCompuesta ) println("OPERACION COMPUESTA")
    println("Pasos: ")
    operacion.imprimePasos()
    println("Operacion Final:    " + operacion.descripcion() )
    println("Numeros utilizados: " +  operacion.getOperandos().length )
    println("Diferencia: " + operacion.cantidadFaltante(cantidad) );
    if ( operacion.cantidadFaltante(cantidad) == 0 ) println("EXACTO!!!")
    
  }
  
}