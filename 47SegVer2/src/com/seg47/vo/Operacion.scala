package com.seg47.vo

import com.seg47.vo.TipoOperacion._

class Operacion (var operando1:Operando, var tipoOperacion:Tipo, var operando2:Operando) extends Operando {
  
  override def toString() : String = "[" + operando1.toString() + " " + tipoOperacion + " " + operando2.toString() + "]"

  override def calculaOperacion() : Int = {
    tipoOperacion match {
      case MAS   => calculaOperacion(_+_)
      case MENOS => calculaOperacion(_-_)
      case POR   => calculaOperacion(_*_)
      case ENTRE => calculaOperacion(_/_)
      case _ => 0
    }
  }

  private def calculaOperacion(operacion: (Int, Int)=>Int ) : Int = {
    operacion(operando1.calculaOperacion(), operando2.calculaOperacion())
  }
  
}