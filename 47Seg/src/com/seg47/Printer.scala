package com.seg47

import com.seg47.constantes.Constantes
import com.seg47.vo.Datos

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
  
}