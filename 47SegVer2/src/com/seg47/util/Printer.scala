package com.seg47.util

import com.seg47.vo._

object Printer {

    private def printWithColon(i:Int) = print(i + ",")
    
    def printStart() {
      println("47Seg.\n");
    }
    
    def printData(datos: Datos) {
        println("============")
        print("Numeros : [")
        //data.numbers.take(Constantes.AMOUNT_NUMBERS -1).foreach(x=> printWithColon(x)))
        datos.numeros.take(Constantes.AMOUNT_NUMBERS -1).foreach(printWithColon(_))
        print(datos.numeros.last)
        println("]")
        println("CANTIDAD: " + datos.cantidad );
    }

    def printMejorOperacion(operacion:Operacion, cantidad: Int) {
        //println("\nLa mejor primera operación (" + operacion.operandos.length + " OPERADORES) para encontrar " + cantidad + " es: " + operacion.operacionInterna )
        //operacion.operandos.take(operacion.operandos.length -1).foreach(x => { print(x + " " + operacion.operacionInterna.toString() + " " )  })
        //print(operacion.operandos.last + " = " + operacion.calcula() )
    }
    
}