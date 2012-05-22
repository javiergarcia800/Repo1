package com.seg47

import com.seg47.vo._
import com.seg47.vo.TipoOperacion._

object Test {

    def main(args:Array[String]) {
        println("Test...")
        restalista()
        //operaciones()
        //forAnidado()
        //tailList(1,2,3)
        //setOperandos()
        //list()
                
    }

    def restalista() {
      var x = List(2,2,4,4,6,6)
      var y = List(2,4,6,8,10)
      var z = x -- y
      var w = x.diff(y)
      println("z=" + z )
      println("w=" + w )
    }
    
    def list() {
      var x = List(1,2)
      var y = List(3,4)
      var z = x ++ y ++ Nil
      println(z)
    }
    
    /*def setOperandos() {
      var operacion = new Operacion()
      operacion.tipoOperacion = MAS
      operacion.setOperandos(List(1,2,3))
      print("operacion: " + operacion + "=" + operacion.calculaOperacion() )
    }*/
    
    def tailList(params:Int*) {
      var x = params.first
      println("x: " + x )
      var y = params.tail
      println("y: " + y )
      tail2(y:_*)
    }
    
    def tail2(params:Int*) {
      params.foreach(x => println(x + " "))
    }
    
    def forAnidado() {
      for( x <- 0 to 10;
           y <- 0 to 10 ) {
        println( "x:" + x + " y:" + y )
      }
    }
    
    def operaciones() {
        val operacion4Mas1 = new Operacion(Cifra(4), MAS, Cifra(5))
        println(operacion4Mas1 + "=" + operacion4Mas1.calculaOperacion() )

        val operacionMul5 = new Operacion(operacion4Mas1, POR, Cifra(5))
        println(operacionMul5 + "=" + operacionMul5.calculaOperacion())
        
        val operacion7Por10 = new Operacion(Cifra(7), POR, Cifra(10))
        println(operacion7Por10 + "=" + operacion7Por10.calculaOperacion() )
        
        val operacionFinal = new Operacion(operacion7Por10, MAS, operacionMul5)
        println(operacionFinal + "=" + operacionFinal.calculaOperacion() )
        
        println("Operandos: " + operacionFinal.getOperandos() )
        
        
        var operacion = operacion7Por10.operacionFaltante(115)
        println("Operacion Faltante:" + operacion )
        operacion.operando2 = operacionMul5
        println("Operacion Faltante:" + operacion )
    }
}