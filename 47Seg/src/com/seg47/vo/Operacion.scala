package com.seg47.vo

import TipoOperacion._

class Operacion (var operandos:List[Int], var operacionInterna:Tipo){

    var operacionExterna:Tipo = NOTHING
  
    def calcula():Int = {
        operacionInterna match {
            case SUMA           => aplicaOperacion(_ + _)
            case RESTA          => aplicaOperacion(_ - _)
            case MULTIPLICACION => aplicaOperacion(_ * _)
            case DIVISION       => aplicaOperacion(_ / _)
        }
    }

    def calcula(numeros:Int*):Int = {
        operacionInterna match {
            case SUMA           => aplicaOperacion(_ + _, numeros.toList)
            case RESTA          => aplicaOperacion(_ - _, numeros.toList)
            case MULTIPLICACION => aplicaOperacion(_ * _, numeros.toList)
            case DIVISION       => aplicaOperacion(_ / _, numeros.toList)
        }
    }
    
    private def aplicaOperacion(f: (Int, Int)=> Int, operandosAplicarOperacion:List[Int] = operandos) : Int = {
        operandosAplicarOperacion.reduceLeft(f)
    } 
    
}
