package com.seg47.vo

import com.seg47.util._
import com.seg47.vo.TipoOperacion._

class Operacion (var operando1:Operando, var tipoOperacion:Tipo, var operando2:Operando) extends Operando {

  def this() = {
    this(new Nothing(), NOTHING, new Nothing())
  }

  override def toString() : String = " [" + operando1.toString() + " " + tipoOperacion + " " + operando2.toString() + "] "

  def descripcion() : String = toString() + " = " + calculaOperacion()

  def imprimePasos() {
    operando1 match {
      case c: Cifra =>
      case o: Operacion => o.imprimePasos()
    }
    operando2 match {
      case c: Cifra =>
      case o: Operacion => o.imprimePasos()
    }
    println(this.descripcion)
  }

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

  def setOperandos(operandos: Int*) {
    setOperandos(operandos.toList)
  }

  def setOperandos(operandos: List[Int]) {
    operando1 = Cifra (operandos.first)
    operando2 = Operacion.construyeOperando2(operandos.tail, tipoOperacion)
  }
  
  def getOperandos() : List[Int] = {
    getOperandos(operando1) ++ getOperandos(operando2)
  }
  
  private def getOperandos(operador: Operando) : List[Int] = {
    operador match {
      case c:Cifra => c.valor :: Nil
      case o:Operacion => getOperandos(o.operando1) ++ getOperandos(o.operando2) ++ Nil
    }
  }
  
  def operacionFaltante(cantidadAEncontrar:Int) : Operacion = {
    var operacion = new Operacion();
    val calculoOperacion = this.calculaOperacion()
    operacion.tipoOperacion = if ( cantidadAEncontrar > calculoOperacion ) MAS else MENOS
    operacion.operando1 = this
    operacion.operando2 = new Nothing()
    operacion
  }

  def cantidadFaltante(cantidadAEncontrar:Int) : Int = {
    math.abs(cantidadAEncontrar - this.calculaOperacion())
  }
  
}

object Operacion {

  def calculaOperacion(tipoOper:Tipo, numeros: Int*) : Int = {
    tipoOper match {
      case MAS   => aplicaOperacion(_+_, numeros.toList)
      case MENOS => aplicaOperacion(_-_, numeros.toList)
      case POR   => aplicaOperacion(_*_, numeros.toList)
      case ENTRE => aplicaOperacion(_/_, numeros.toList)
      case _ => 0
    }
  }

  private def aplicaOperacion(f: (Int, Int)=> Int, operandos:List[Int]) : Int = {
    operandos.reduceLeft( (x,y) => if ( Util.tieneDecimales(f(x , y)) ) return 0 else f(x,y) )
  } 
  
  private def construyeOperando2(operandos: List[Int], tipoOperacion:Tipo) : Operando = {
    operandos match {
      case x :: Nil => new Cifra(x)
      case x :: tail => new Operacion(Cifra(x), tipoOperacion, construyeOperando2(tail, tipoOperacion))
      case Nil => new Nothing
    }
  }

  def construyeOperando2(operaciones: List[Operacion], tipoOperacion:Tipo) : Operando = {
    operaciones match {
      case x :: Nil => x
      case x :: tail => new Operacion(x, tipoOperacion, construyeOperando2(tail, tipoOperacion))
      case Nil =>new Nothing
    }
  }
  
}