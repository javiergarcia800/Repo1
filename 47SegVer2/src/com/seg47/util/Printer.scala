package com.seg47.util

import com.seg47.util.Constantes._
import com.seg47.vo._

object Printer {

  private val _Oacute = Integer.parseInt("00E0", 16).toChar //Ó En windows: 00D3
  private val _oacute = Integer.parseInt("00A2", 16).toChar //ó En windows: 00F3
  private val _uacute = Integer.parseInt("00A3", 16).toChar //ú En windows: 00FA

  private def printWithColon(i:Int) = print(i + ",")

  def printStart() {
    println("47Seg.\n");
  }

  def imprimeTextoNumero {
    print("N" + _uacute + "mero : ")
  }

  def imprimeTextoCifra {
    print("CIFRA  : ")
  }
  
  def imprimeDatos(datos: Datos) {
    println("============")
    print("\nN" + _uacute + "meros : [")
    datos.numeros.take(CANTIDAD_NUMEROS -1).foreach(printWithColon(_))
    print(datos.numeros.last)
    println("]")
    println("CANTIDAD: " + datos.cantidad )
    println()
  }

  def imprimeContinuar() {
    println("\n\n")
    println("Presione: ENTER para continuar ")
    println("        " + _Oacute + " CUALQUIER TECLA + ENTER para salir.")
    println("\n\n")
  }

  def nuevoCalculo() {
    println("\n")
    println("NUEVO CALCULO")
    println("=============")
  }

  def imprimeEntradaIncorrecta() {
    println()
    println("Entrada Incorrecta.")
  }

  def imprimeSalida() {
    println("Aplicaci" + _oacute + "n Finalizada.")
    println()
  }

}