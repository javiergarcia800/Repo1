package com.seg47

import scala.util.Sorting

import com.seg47.vo.Datos
import com.seg47.vo.Operacion
import com.seg47.vo.TipoOperacion._
import com.seg47.constantes._

object Processor {

    def process(datos: Datos) {
      val numeros = datos.numeros.toList
      val cantidad = datos.cantidad
      
      val operacion2Operadores = mejorPar(numeros, cantidad)
      val operacion3Operadores = mejorTrio(numeros, cantidad)
      val operacion4Operadores = mejorCuarteto(numeros, cantidad)
      val operacion5Operadores = mejorQuinteto(numeros, cantidad)
      val operacion6Operadores = mejorSexteto(numeros, cantidad)
      
      Printer.printMejorOperacion(operacion2Operadores, cantidad)
      Printer.printMejorOperacion(operacion3Operadores, cantidad)
      Printer.printMejorOperacion(operacion4Operadores, cantidad)
      Printer.printMejorOperacion(operacion5Operadores, cantidad)
      Printer.printMejorOperacion(operacion6Operadores, cantidad)

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

    private def mejorTrio(numeros:List[Int], cantidad: Int) : Operacion = {
        val trios = List(mejorTrioPorOperacion(numeros, cantidad, SUMA),
                         mejorTrioPorOperacion(numeros, cantidad, RESTA),
                         mejorTrioPorOperacion(numeros, cantidad, MULTIPLICACION)/*,
                         mejorTrioPorOperacion(numeros, cantidad, DIVISION)*/)
        val operacion = mejorOperacion(trios, cantidad)
      
        println("\n")
        trios.foreach( x => {
            println("Operacion: " + x.operacionInterna + " = " + x.calcula()  )
        })
        operacion
    }

    private def mejorCuarteto(numeros:List[Int], cantidad: Int) : Operacion = {
        val cuartetos = List(mejorCuartetoPorOperacion(numeros, cantidad, SUMA),
                             mejorCuartetoPorOperacion(numeros, cantidad, RESTA),
                             mejorCuartetoPorOperacion(numeros, cantidad, MULTIPLICACION)/*,
                             mejorTrioPorOperacion(numeros, cantidad, DIVISION)*/)
        val operacion = mejorOperacion(cuartetos, cantidad)
      
        println("\n")
        cuartetos.foreach( x => {
            println("Operacion: " + x.operacionInterna + " = " + x.calcula()  )
        })
        operacion
    }

    private def mejorQuinteto(numeros:List[Int], cantidad: Int) : Operacion = {
        val quintetos = List(mejorQuintetoPorOperacion(numeros, cantidad, SUMA),
                             mejorQuintetoPorOperacion(numeros, cantidad, RESTA),
                             mejorQuintetoPorOperacion(numeros, cantidad, MULTIPLICACION)/*,
                             mejorTrioPorOperacion(numeros, cantidad, DIVISION)*/)
        val operacion = mejorOperacion(quintetos, cantidad)
      
        println("\n")
        quintetos.foreach( x => {
            println("Operacion: " + x.operacionInterna + " = " + x.calcula()  )
        })
        operacion
    }

    private def mejorSexteto(numeros:List[Int], cantidad: Int) : Operacion = {
        val sexteto = List(mejorSextetoPorOperacion(numeros, cantidad, SUMA),
                             mejorSextetoPorOperacion(numeros, cantidad, RESTA),
                             mejorSextetoPorOperacion(numeros, cantidad, MULTIPLICACION)/*,
                             mejorTrioPorOperacion(numeros, cantidad, DIVISION)*/)
        val operacion = mejorOperacion(sexteto, cantidad)
      
        println("\n")
        sexteto.foreach( x => {
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
    
    private def mejorTrioPorOperacion(numeros:List[Int], cantidadAEncontrar: Int, tipoOperacion:Tipo) : Operacion = {
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

    private def mejorCuartetoPorOperacion(numeros:List[Int], cantidadAEncontrar: Int, tipoOperacion:Tipo) : Operacion = {
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

    private def mejorQuintetoPorOperacion(numeros:List[Int], cantidadAEncontrar: Int, tipoOperacion:Tipo) : Operacion = {
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

    private def mejorSextetoPorOperacion(numeros:List[Int], cantidadAEncontrar: Int, tipoOperacion:Tipo) : Operacion = {
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
        operacion.operacionExterna = if ( operacion.calcula() < cantidadAEncontrar ) SUMA else RESTA
        operacion
    }

    private def mejorOperacion(operaciones:List[Operacion], cantidadAEncontrar:Int) : Operacion = {
        operaciones.reduceLeft( (a,b) => if ( operacionMasCercana(a.calcula, b.calcula, cantidadAEncontrar) ) a else b )
    }
    
    val operacionMasCercana: (Int, Int, Int) => Boolean = (num1, num2, cantidad) => {
        if ( math.abs(num1 - cantidad) < math.abs(num2-cantidad)  ) true else false
    }
    
}
