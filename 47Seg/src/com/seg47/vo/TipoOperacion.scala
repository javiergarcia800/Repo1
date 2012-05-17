package com.seg47.vo

object TipoOperacion extends Enumeration {
    type Tipo = Value
    val SUMA           = Value("+")
    val RESTA          = Value("-") 
    val DIVISION       = Value("\\")
    val MULTIPLICACION = Value("*")
    val NOTHING        = Value("")
    
    /*override def toString() : String = {
      Value match {
        case SUMA           => "+"
        case RESTA          => "_"
        case DIVISION       => "/"
        case MULTIPLICACION => "*"
        case _ => ""
      }
    } */
    
}
