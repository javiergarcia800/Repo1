package com.seg47

import com.seg47.util._
import com.seg47.vo._
import com.seg47.vo.TipoOperacion._
import java.io.PrintStream
import scala.collection.mutable._

object Test {

    def main(args:Array[String]) {
        println("Test...")
        //getDigitosClave(450)
        //testCompuesto()
        //testYield()
        //cifraRed()
        //restalista()
        //operaciones()
        //forAnidado()
        //tailList(1,2,3)
        //setOperandos()
        //list()
        //testDiff()
        //testTieneDecimales
        //testOperacion
        testUnicode
        //testRead
    }

    def testUnicode {
      
      var z = Integer.parseInt("00F3", 16);
      println(z)
      var z1 : Char = z.toChar
      println( z1 )
      val caracteres = List("0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F")
      for ( x <- 10 until 16;
            y <-  0 until 16 )  {
        
        print( "00" + caracteres(x) + caracteres(y) + "=" )
        println( Integer.parseInt("00" + caracteres(x) + caracteres(y) , 16 ).toChar  )
      }
    }
    
    def testRead {
      var a:Char = '\u00E0'
      println(a)
    }
    
    def testOperacion {
      
      val lista2 = List(8,5)
      
      val reduce1 = Operacion.aplicaOperacionDecimal(_/_,lista2)
      //val reduce2 = Operacion.aplicaOperacion(_/_,lista2)
      //val reduce3 = Operacion.aplicaOperacion(_/_,lista3)
      
      println("reduce1:" + reduce1 );
      //println("reduce2:" + reduce2 );
      //println("reduce3:" + reduce3 );
      
    }
    
    def testTieneDecimales {
      val decimal = 1.3;
      var tieneDecimales = Util.tieneDecimales(decimal)
      val decimal2:Double = 1.0;
      var tieneDecimales2 = Util.tieneDecimales(decimal2)
      println("TieneDecimales [" + decimal + " ]=" + tieneDecimales );
      println("TieneDecimales [" + decimal2 + " ]=" + tieneDecimales2 );
    }
    
    def testDiff() {
      var x = ListBuffer(1,2,3,3,3)
      x -= 3
      println(x)
    }
    
    def testCompuesto() {
      var p = ProcesadorCompuesto
      var operacion = p.getCombinaciones(List(5,9,10), List(4,8,6,5,1,2))
      println(operacion.descripcion())
    }
    
    def testYield() {
        var numerosClave = List(5, 5, 10)
        var numerosDisponibles = ListBuffer(4, 8, 5, 3, 1, 2)
        var numerosEncontrados = new ListBuffer[Int]
        
        for ( x <- numerosClave ) {
          if ( numerosDisponibles.contains(x) ) {
            numerosDisponibles -= x
            numerosEncontrados += x
          }
        }
        println("x:" + numerosEncontrados.toList )
    }

  val containsAndRemove: (ListBuffer[Int], Int) => Boolean = (numerosDisponibles, numeroClave) => {
    println("numerosDisp:" + numerosDisponibles )
    println("numerosClave:" + numeroClave )
    if ( numerosDisponibles.contains(numeroClave) ) {
      numerosDisponibles -= numeroClave
      println("true")
      true
    }
    false
  }

  def getDigitosClave(cifra: Int) : List[Int] = { 
    for ( i <- 1 to 10; 
          j <- 1 to 10;
          k <- 1 to 10 ) {
      if ( i * j * k == cifra ) {
        return List(i,j,k)
      }
    }
    List()
  }
    
    def cifraRed() {
      val cantidad = 345
      val cifra = (345/10) * 10
      println(cifra)
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