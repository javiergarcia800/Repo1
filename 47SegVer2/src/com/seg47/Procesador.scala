package com.seg47

import com.seg47.vo._

object Procesador {

  def encuentraCombinacion(datos: Datos) {
    val numeros = datos.numeros.toList.sort(_ > _)
    val cantidad = datos.cantidad
    var operacionSimple = ProcesadorSimple.getOperacion(numeros, cantidad)
    
    println("\nOperacion Simple:\t" + operacionSimple.descripcion() );
    println("Numeros utilizados: " +  operacionSimple.getOperandos().length )
    println("Diferencia: " + operacionSimple.cantidadFaltante(cantidad) );
    if ( operacionSimple.cantidadFaltante(cantidad) == 0 ) println("EXACTO!!!")
    
    
    var operacionCompuesta = ProcesadorCompuesto.getOperacion(numeros, cantidad)
    println("\nOperacion Compuesta:\t" + operacionCompuesta.descripcion() );
    println("Numeros utilizados: " +  operacionCompuesta.getOperandos().length )
    println("Diferencia: " + operacionCompuesta.cantidadFaltante(cantidad) );
    if ( operacionCompuesta.cantidadFaltante(cantidad) == 0 ) println("EXACTO!!!")
    
    
  }
  
}