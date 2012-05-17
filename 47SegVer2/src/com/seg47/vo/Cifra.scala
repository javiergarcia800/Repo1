package com.seg47.vo

case class Cifra (val valor:Int) extends Operando{
  
  override def calculaOperacion() : Int = valor
  
  override def toString() : String = {
    valor.toString()
  }
  
}
