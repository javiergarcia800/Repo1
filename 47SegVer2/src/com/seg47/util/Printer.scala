package com.seg47.util

import com.seg47.vo._

object Printer {

    private def printWithColon(i:Int) = print(i + ",")
    
    def printStart() {
      println("47Seg.\n");
    }
    
    def printData(datos: Datos) {
        println("============")
        print("\nNumeros : [")
        datos.numeros.take(Constantes.AMOUNT_NUMBERS -1).foreach(printWithColon(_))
        print(datos.numeros.last)
        println("]")
        println("CANTIDAD: " + datos.cantidad );
        println("");
    }
    
}