package com.seg47.vo

object TipoOperacion extends Enumeration {

    type Tipo = Value

    val MAS     = Value("+")
    val MENOS   = Value("-") 
    val ENTRE   = Value("\\")
    val POR     = Value("*")
    val NOTHING = Value("")

    val operaciones = List(MAS, MENOS, ENTRE, POR)
    
}