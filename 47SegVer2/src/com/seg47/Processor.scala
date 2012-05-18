package com.seg47

import scala.util.Sorting

import com.seg47.vo._
import com.seg47.vo.TipoOperacion._
import com.seg47.util._

object Processor {
  
  def process(datos: Datos) {
    val numeros = datos.numeros.toList.sort(_ > _)
    val cantidad = datos.cantidad
    var operacion = getOperacion(numeros, cantidad)
    
    println("\nOperacion Final:\t" + operacion.descripcion() );
    println("Numeros utilizados: " +  operacion.getOperandos().length )
    println("Diferencia: " + operacion.cantidadFaltante(cantidad) );
    if ( operacion.cantidadFaltante(cantidad) == 0 ) println("EXACTO!!!")
    
  }

  private def getOperacion(numeros:List[Int], cantidad: Int) : Operacion = {
    var operaciones = numeros.length match {
      case 2 => List( mejorCombinacion(numeros, cantidad, mejorPar     ))
      case 3 => List( mejorCombinacion(numeros, cantidad, mejorPar      ),
                      mejorCombinacion(numeros, cantidad, mejorTrio     ))
      case 4 => List( mejorCombinacion(numeros, cantidad, mejorPar      ),
                      mejorCombinacion(numeros, cantidad, mejorTrio     ),
                      mejorCombinacion(numeros, cantidad, mejorCuarteto ))
      case 5 => List( mejorCombinacion(numeros, cantidad, mejorPar      ),
                      mejorCombinacion(numeros, cantidad, mejorTrio     ),
                      mejorCombinacion(numeros, cantidad, mejorCuarteto ),
                      mejorCombinacion(numeros, cantidad, mejorQuinteto ))
      case 6 => List( mejorCombinacion(numeros, cantidad, mejorPar      ),
                      mejorCombinacion(numeros, cantidad, mejorTrio     ),
                      mejorCombinacion(numeros, cantidad, mejorCuarteto ),
                      mejorCombinacion(numeros, cantidad, mejorQuinteto ),
                      mejorCombinacion(numeros, cantidad, mejorSexteto  ))
      case _ => Nil
    }
    var operacion = mejorOperacion(operaciones, cantidad)

    println("Operacion Parcial:\t" + operacion.descripcion() )

    val cantidadFaltante = operacion.cantidadFaltante(cantidad)
    val numerosSinUsar = numeros -- operacion.getOperandos()

    if ( cantidadFaltante == 0 )
      return operacion

    if ( numerosSinUsar.length == 1 ) {
      var numero = numerosSinUsar.first
      if ( numero <= cantidadFaltante ) {
        operacion = operacion.operacionFaltante(cantidad)
        operacion.operando2 = Cifra(numero)
      }
    } else if ( numerosSinUsar.length > 1 ) {
      operacion = operacion.operacionFaltante(cantidad)
      operacion.operando2 = getOperacion(numerosSinUsar, cantidadFaltante)
    }
    operacion
  }

  private def mejorCombinacion(numeros:List[Int], cantidad: Int, mejorCombinacionPorOperacion: (List[Int], Int, Tipo)=> Operacion ) : Operacion = {
    val pares = TipoOperacion.operaciones.map(mejorCombinacionPorOperacion(numeros, cantidad, _))
    mejorOperacion(pares, cantidad)
  }

  private val mejorPar: (List[Int], Int, Tipo) => Operacion = (numeros, cantidadAEncontrar, tipoOperacion) => {
    var operacion:Operacion = new Operacion()
    for ( i <- 0 until numeros.length;
          j <- (i+1) until numeros.length ) {        
      if ( operacion.tipoOperacion == NOTHING ) {
          operacion.tipoOperacion = tipoOperacion
          operacion.setOperandos(numeros(i), numeros(j))
      } else if ( operacionMasCercana( Operacion.calculaOperacion(tipoOperacion, numeros(i), numeros(j)), operacion.calculaOperacion(), cantidadAEncontrar ) ) {
          operacion.setOperandos(numeros(i), numeros(j))
      }
    }
    operacion
  }

  private val mejorTrio: (List[Int], Int, Tipo) => Operacion = (numeros, cantidadAEncontrar, tipoOperacion) => {
    var operacion:Operacion = new Operacion()
    for ( i <- 0 until numeros.length;
          j <- (i + 1) until numeros.length;
          k <- (j + 1) until numeros.length ) {
      if ( operacion.tipoOperacion == NOTHING ) {
        operacion.tipoOperacion = tipoOperacion
        operacion.setOperandos(numeros(i), numeros(j), numeros(k))
      } else if ( operacionMasCercana( Operacion.calculaOperacion(tipoOperacion, numeros(i), numeros(j), numeros(k)), operacion.calculaOperacion(), cantidadAEncontrar ) ) {
            operacion.setOperandos(numeros(i), numeros(j), numeros(k))
      }
    }
    operacion
  }

  private val mejorCuarteto: (List[Int], Int, Tipo) => Operacion = (numeros, cantidadAEncontrar, tipoOperacion) => {
    var operacion:Operacion = new Operacion()
    for ( i <- 0 until numeros.length;
          j <- (i + 1) until numeros.length;
          k <- (j + 1) until numeros.length;
          l <- (k + 1) until numeros.length ) {
      if ( operacion.tipoOperacion == NOTHING ) {
          operacion.tipoOperacion = tipoOperacion
          operacion.setOperandos(numeros(i), numeros(j), numeros(k), numeros(l))
      } else if ( operacionMasCercana( Operacion.calculaOperacion(tipoOperacion, numeros(i), numeros(j), numeros(k), numeros(l)), operacion.calculaOperacion(), cantidadAEncontrar ) ) {
          operacion.setOperandos(numeros(i), numeros(j), numeros(k), numeros(l))
      }
    }
    operacion
  }

  private val mejorQuinteto: (List[Int], Int, Tipo) => Operacion = (numeros, cantidadAEncontrar, tipoOperacion) => {
    var operacion:Operacion = new Operacion()
    for ( i <- 0 until numeros.length;
          j <- (i + 1) until numeros.length;
          k <- (j + 1) until numeros.length;
          l <- (k + 1) until numeros.length;
          m <- (l + 1) until numeros.length ) {
      if ( operacion.tipoOperacion == NOTHING ) {
        operacion.tipoOperacion = tipoOperacion
        operacion.setOperandos(numeros(i), numeros(j), numeros(k), numeros(l), numeros(m))
      } else if ( operacionMasCercana( Operacion.calculaOperacion(tipoOperacion, numeros(i), numeros(j), numeros(k), numeros(l), numeros(m)), operacion.calculaOperacion(), cantidadAEncontrar ) ) {
        operacion.setOperandos(numeros(i), numeros(j), numeros(k), numeros(l), numeros(m))
      }
    }
    operacion
  }

  private val mejorSexteto: (List[Int], Int, Tipo) => Operacion = (numeros, cantidadAEncontrar, tipoOperacion) => {
    var operacion:Operacion = new Operacion()
    for ( i <- 0 until numeros.length;
          j <- (i + 1) until numeros.length;
          k <- (j + 1) until numeros.length;
          l <- (k + 1) until numeros.length;
          m <- (l + 1) until numeros.length;
          n <- (m + 1) until numeros.length ) {
      if ( operacion.tipoOperacion == NOTHING ) {
        operacion.tipoOperacion = tipoOperacion
        operacion.setOperandos(numeros(i), numeros(j), numeros(k), numeros(l), numeros(m), numeros(n))
      } else if ( operacionMasCercana( Operacion.calculaOperacion(tipoOperacion, numeros(i), numeros(j), numeros(k), numeros(l), numeros(m), numeros(n)), operacion.calculaOperacion(), cantidadAEncontrar ) ) {
        operacion.setOperandos(numeros(i), numeros(j), numeros(k), numeros(l), numeros(m), numeros(n))
      }
    }
    operacion
  }

  private def mejorOperacion(operaciones:List[Operacion], cantidadAEncontrar:Int) : Operacion = {
    operaciones.reduceLeft( (a,b) => if ( operacionMasCercana(a.calculaOperacion(), b.calculaOperacion(), cantidadAEncontrar) ) a else b )
  }

  private val operacionMasCercana: (Int, Int, Int) => Boolean = (num1, num2, cantidad) => {
    if ( math.abs(num1 - cantidad) <= math.abs(num2-cantidad)  ) true else false
  }

}
