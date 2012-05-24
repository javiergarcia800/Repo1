package com.seg47

import com.seg47.vo._

trait BaseProcesador {

  def mejorOperacion(operaciones:List[Operacion], cantidadAEncontrar:Int) : Operacion = {
    operaciones.reduceLeft( (a,b) => if ( operacionMasCercana(a.calculaOperacion(), b.calculaOperacion(), cantidadAEncontrar) ) a else b )
  }

  val operacionMasCercana: (Int, Int, Int) => Boolean = (num1, num2, cantidad) => {
    if ( math.abs(num1 - cantidad) <= math.abs(num2-cantidad)  ) true else false
  }
  
  def getOperacion(numeros:List[Int], cantidad: Int) : Operacion
  
}