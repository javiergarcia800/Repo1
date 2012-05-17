package com.seg47.vo

import TipoOperacion._

class Operacion (var operandos:List[Int], var operacionInterna:Tipo){

    var operacionExterna:Tipo = NOTHING
  
    def calcula():Int = {
        operacionInterna match {
            case SUMA          => aplicaOperacion(_ + _)
            case RESTA         => aplicaOperacion(_ - _)
            case MULTIPLIACION => aplicaOperacion(_ * _)
            case DIVISION      => aplicaOperacion(_ / _)
        }
    }

    private def aplicaOperacion(f: (Int, Int)=> Int) : Int = {
        operandos.foldLeft(0)(f)
    } 

}

object Operacion {

    def calcula(num1:Int, num2:Int, operacion: Tipo) {
          operacion match {
            case SUMA          => num1 + num2
            case RESTA         => num1 - num2
            case MULTIPLIACION => num1 * num2
            case DIVISION      => num1 / num2
          }
    }
    
}