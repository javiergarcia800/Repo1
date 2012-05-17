package com.seg47

import scala.util.Sorting

import com.seg47.vo.Datos
import com.seg47.vo.Operacion
import com.seg47.vo.TipoOperacion._

object Processor {

    def process(datos: Datos) {
      val numeros = datos.numeros.toList.sort(_ > _)
      println("Numeros ordenados")
      numeros.foreach(x=> println(x+ " "))
      val cantidad = datos.cantidad
      val pares = List(mejoresPares(numeros, cantidad, SUMA),
                       mejoresPares(numeros, cantidad, RESTA),
                       mejoresPares(numeros, cantidad, MULTIPLIACION),
                       mejoresPares(numeros, cantidad, DIVISION))
      val operacion = mejorPar(pares, cantidad)
    }

    private def mejoresPares(numeros:List[Int], cantidadAEncontrar: Int, tipoOperacion:Tipo) : Operacion = {
        val operacion:Operacion = new Operacion(Nil, tipoOperacion)
        for ( i <- 0 to numeros.length ) {
            for ( j <- (i + 1) to numeros.length ) {
                if ( operacion.operandos == Nil ) {
                    operacion.operandos = List(numeros(i), numeros(j))
                } else if ( mejorOperacion( numeros(i) * numeros(j), operacion.calcula(), cantidadAEncontrar ) ) {
                    operacion.operandos = List(numeros(i), numeros(j))
                }
            }
        }
        operacion.operacionExterna = if ( operacion.calcula() < cantidadAEncontrar ) SUMA else RESTA
        operacion
    }

    private def mejorPar(operaciones:List[Operacion], cantidadAEncontrar:Int) : Operacion = {
        operaciones.reduceLeft( (a,b) => if ( mejorOperacion(a.calcula, b.calcula, cantidadAEncontrar) ) a else b )
    }
    
    val mejorOperacion: (Int, Int, Int) => Boolean = (num1, num2, cantidad) => {
        if ( math.abs(num1 - cantidad) < math.abs(num2-cantidad)  ) true else false
    }
    
}
