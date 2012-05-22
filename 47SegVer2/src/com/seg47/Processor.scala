package com.seg47

import scala.util.Sorting

import com.seg47.vo.Datos
import com.seg47.vo.TipoOperacion._
import com.seg47.util._

object Processor {

    /*def process(datos: Datos) {
      val numeros = datos.numeros.toList
      val cantidad = datos.cantidad
      
      val operaciones = List (   mejorPar(numeros, cantidad),
                                 mejorCombinacion(numeros, cantidad, mejorTrio     ),
                                 mejorCombinacion(numeros, cantidad, mejorCuarteto ),
                                 mejorCombinacion(numeros, cantidad, mejorQuinteto ),
                                 mejorCombinacion(numeros, cantidad, mejorSexteto  )
                                 )

      val mejorPrimeraOperacion = mejorOperacion(operaciones,cantidad)
      
      Printer.printMejorOperacion(operaciones(0), cantidad)
      Printer.printMejorOperacion(operaciones(1), cantidad)
      Printer.printMejorOperacion(operaciones(2), cantidad)
      Printer.printMejorOperacion(operaciones(3), cantidad)
      Printer.printMejorOperacion(operaciones(4), cantidad)
      
      println("\nLA MEJOR OPERACION")
      Printer.printMejorOperacion(mejorPrimeraOperacion, cantidad)

      val numerosRestantes = numeros -- mejorPrimeraOperacion.operandos
      val cantidadRestante = cantidad - mejorPrimeraOperacion.calcula()
      
      println("\nNumerosRestantes:" + numerosRestantes )
      println("cantidadRestantes:" + cantidadRestante )
      
      
    }

    private def mejorPar(numeros:List[Int], cantidad: Int) : Operacion = {
        val pares = List(mejoresParesPorOperacion(numeros, cantidad, SUMA),
                         mejoresParesPorOperacion(numeros, cantidad, RESTA),
                         mejoresParesPorOperacion(numeros, cantidad, MULTIPLICACION),
                         mejoresParesPorOperacion(numeros, cantidad, DIVISION))
        val operacion = mejorOperacion(pares, cantidad)
      
        println("\n")
        pares.foreach( x => {
            println("Operacion: " + x.operacionInterna + " = " + x.calcula()  )
        })
        operacion
    }

    private def mejorCombinacion(numeros:List[Int], cantidad: Int, mejorCombinacionPorOperacion: (List[Int], Int, Tipo)=> Operacion ) : Operacion = {
        val combinacion = List(mejorCombinacionPorOperacion(numeros, cantidad, SUMA),
                               mejorCombinacionPorOperacion(numeros, cantidad, RESTA),
                               mejorCombinacionPorOperacion(numeros, cantidad, MULTIPLICACION)/*,
                               mejorTrioPorOperacion(numeros, cantidad, DIVISION)*/)
        val operacion = mejorOperacion(combinacion, cantidad)

        println("\n")
        combinacion.foreach( x => {
            println("Operacion: " + x.operacionInterna + " = " + x.calcula()  )
        })
        operacion
    }

    private def mejoresParesPorOperacion(numeros:List[Int], cantidadAEncontrar: Int, tipoOperacion:Tipo) : Operacion = {
        val operacion:Operacion = new Operacion(Nil, tipoOperacion)
        for ( i <- 0 until numeros.length ) {
            for ( j <- (i + 1) until numeros.length ) {
                if ( operacion.operandos == Nil ) {
                    operacion.operandos = List(numeros(i), numeros(j))
                } else if ( operacionMasCercana( operacion.calcula(numeros(i) , numeros(j)), operacion.calcula(), cantidadAEncontrar ) ) {
                    operacion.operandos = List(numeros(i), numeros(j))
                }
            }
        }
        operacion.operacionExterna = if ( operacion.calcula() < cantidadAEncontrar ) SUMA else RESTA
        operacion
    }
    
    private def mejorTrio(numeros:List[Int], cantidadAEncontrar: Int, tipoOperacion:Tipo) : Operacion = {
        val operacion:Operacion = new Operacion(Nil, tipoOperacion)
        for ( i <- 0 until numeros.length ) {
            for ( j <- (i + 1) until numeros.length ) {
                for ( k <- (j + 1) until numeros.length ) {
                    if ( operacion.operandos == Nil ) {
                        operacion.operandos = List(numeros(i), numeros(j), numeros(k))
                    } else if ( operacionMasCercana( operacion.calcula(numeros(i) , numeros(j), numeros(k)), operacion.calcula(), cantidadAEncontrar ) ) {
                        operacion.operandos = List(numeros(i), numeros(j), numeros(k))
                    }
                }
            }
        }
        operacion.operacionExterna = if ( operacion.calcula() < cantidadAEncontrar ) SUMA else RESTA
        operacion
    }

    private def mejorCuarteto(numeros:List[Int], cantidadAEncontrar: Int, tipoOperacion:Tipo) : Operacion = {
        val operacion:Operacion = new Operacion(Nil, tipoOperacion)
        for ( i <- 0 until numeros.length ) {
            for ( j <- (i + 1) until numeros.length ) {
                for ( k <- (j + 1) until numeros.length ) {
                    for ( l <- (k + 1) until numeros.length ) {
                        if ( operacion.operandos == Nil ) {
                            operacion.operandos = List(numeros(i), numeros(j), numeros(k), numeros(l))
                        } else if ( operacionMasCercana( operacion.calcula(numeros(i) , numeros(j), numeros(k), numeros(l)), operacion.calcula(), cantidadAEncontrar ) ) {
                            operacion.operandos = List(numeros(i), numeros(j), numeros(k), numeros(l))
                        }
                    }
                }
            }
        }
        operacion.operacionExterna = if ( operacion.calcula() < cantidadAEncontrar ) SUMA else RESTA
        operacion
    }

    private def mejorQuinteto(numeros:List[Int], cantidadAEncontrar: Int, tipoOperacion:Tipo) : Operacion = {
        val operacion:Operacion = new Operacion(Nil, tipoOperacion)
        for ( i <- 0 until numeros.length ) {
            for ( j <- (i + 1) until numeros.length ) {
                for ( k <- (j + 1) until numeros.length ) {
                    for ( l <- (k + 1) until numeros.length ) {
                        for ( m <- (l + 1) until numeros.length ) {
                            if ( operacion.operandos == Nil ) {
                                operacion.operandos = List(numeros(i), numeros(j), numeros(k), numeros(l), numeros(m))
                            } else if ( operacionMasCercana( operacion.calcula(numeros(i), numeros(j), numeros(k), numeros(l),numeros(m)), operacion.calcula(), cantidadAEncontrar ) ) {
                                operacion.operandos = List(numeros(i), numeros(j), numeros(k), numeros(l), numeros(m))
                            }
                        }
                    }
                }
            }
        }
        operacion.operacionExterna = if ( operacion.calcula() < cantidadAEncontrar ) SUMA else RESTA
        operacion
    }

    private def mejorSexteto(numeros:List[Int], cantidadAEncontrar: Int, tipoOperacion:Tipo) : Operacion = {
        val operacion:Operacion = new Operacion(Nil, tipoOperacion)
        for ( i <- 0 until numeros.length ) {
            for ( j <- (i + 1) until numeros.length ) {
                for ( k <- (j + 1) until numeros.length ) {
                    for ( l <- (k + 1) until numeros.length ) {
                        for ( m <- (l + 1) until numeros.length ) {
                            for ( n <- (m + 1) until numeros.length ) {
                                if ( operacion.operandos == Nil ) {
                                    operacion.operandos = List(numeros(i), numeros(j), numeros(k), numeros(l), numeros(m), numeros(n))
                                } else if ( operacionMasCercana( operacion.calcula(numeros(i), numeros(j), numeros(k), numeros(l),numeros(m),numeros(n)), operacion.calcula(), cantidadAEncontrar ) ) {
                                    operacion.operandos = List(numeros(i), numeros(j), numeros(k), numeros(l), numeros(m), numeros(n))
                                }
                            }
                        }
                    }
                }
            }
        }
        operacion.operacionExterna = if ( operacion.calcula() <= cantidadAEncontrar ) SUMA else RESTA
        operacion
    }

    private def mejorOperacion(operaciones:List[Operacion], cantidadAEncontrar:Int) : Operacion = {
        operaciones.reduceLeft( (a,b) => if ( operacionMasCercana(a.calcula, b.calcula, cantidadAEncontrar) ) a else b )
    }
    
    val operacionMasCercana: (Int, Int, Int) => Boolean = (num1, num2, cantidad) => {
        if ( math.abs(num1 - cantidad) <= math.abs(num2-cantidad)  ) true else false
    }*/
    
}
