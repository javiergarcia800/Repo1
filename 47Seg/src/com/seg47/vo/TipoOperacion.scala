package com.seg47.vo

object TipoOperacion extends Enumeration {
    type Tipo = Value
    val SUMA, RESTA, DIVISION, MULTIPLICACION, NOTHING = Value
    
    override def toString() : String = {
      Value match {
        case SUMA           => "+"
        case RESTA          => "_"
        case DIVISION       => "/"
        case MULTIPLICACION => "*"
        case _ => ""
      }
    } 
    
}
