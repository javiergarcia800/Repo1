package com.seg47.util

import com.seg47.util.Constantes._
import com.seg47.vo._

object Printer {

  private def printWithColon(i:Int) = print(i + ",")
    
  def printStart() {
    println("47Seg.\n");
  }
  
  def imprimeDatos(datos: Datos) {
    println("============")
    print("\nNumeros : [")
    datos.numeros.take(CANTIDAD_NUMEROS -1).foreach(printWithColon(_))
    print(datos.numeros.last)
    println("]")
    println("CANTIDAD: " + datos.cantidad );
    println("");
  }
  
  def imprimeContinuar() {
    println("\n\n")
    println("Presione ENTER para continuar " + '\u00E0' )
    println("         CUALQUIER TECLA + ENTER para salir.")
    println("\n\n")
  }

  def nuevoCalculo() {
    println("\n")
    println("NUEVO CALCULO")
    println("=============")
  }
  
}