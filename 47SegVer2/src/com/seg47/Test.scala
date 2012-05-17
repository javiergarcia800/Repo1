package com.seg47

import com.seg47.vo._
import com.seg47.vo.TipoOperacion._

object Test {

    def main(args:Array[String]) {
        println("Test...")

        val operacion4Mas1 = new Operacion(Cifra(4), MAS, Cifra(5))
        println(operacion4Mas1 + "=" + operacion4Mas1.calculaOperacion() )

        val operacionMul5 = new Operacion(operacion4Mas1, POR, Cifra(5))
        println(operacionMul5 + "=" + operacionMul5.calculaOperacion())
        
        val operacion7Por10 = new Operacion(Cifra(7), POR, Cifra(10))
        println(operacion7Por10 + "=" + operacion7Por10.calculaOperacion() )
        
        val operacionFinal = new Operacion(operacion7Por10, MAS, operacionMul5)
        println(operacionFinal + "=" + operacionFinal.calculaOperacion() )
        
    }

}