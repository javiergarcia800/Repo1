package com.seg47.vo

import com.seg47.util._
import com.seg47.vo.TipoOperacion._

class Operacion (var operando1:Operando, var tipoOperacion:Tipo, var operando2:Operando) extends Operando {

  def this() = {
    this(new Nothing(), NOTHING, new Nothing())
  }

  override def toString() : String = " [" + operando1.toString() + " " + tipoOperacion + " " + operando2.toString() + "] "

  override def calculaOperacion() : Int = {
    Operacion.calculaOperacion(tipoOperacion, getOperandos)
  }

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
    calculaOperacion(tipoOper, numeros.toList)
  }

  private def calculaOperacion(tipoOper:Tipo, numeros: List[Int]) : Int = {
    tipoOper match {
      case MAS   => aplicaOperacion(_+_, numeros)
      case MENOS => aplicaOperacion(_-_, numeros)
      case POR   => aplicaOperacion(_*_, numeros)
      case ENTRE => aplicaOperacionDecimal(_/_, numeros)
      case _ => 0
    }
  }

  /**
   * Aplica la operaci&oacute;n correspondiente a una lista de operandos, validando que la operación regrese
   * un n&uacute;mero entero; si la operaci&oacute;n no regresa un n&uacute;mero entero, el m&eacute;todo 
   * regresa 0. Utilizado para operaciones de divisi&oacute;n.
   * @param f:(Double, Int) => Double .- Funci&oacute;n a aplicar a los operandos.
   * @param operandos:List[Int] .- Operandos sobre los que se aplica la operaci&oacute;n.
   * @return Int .- Resultado de la operaci&oacute;n aplicada a los operandos.
   */
  def aplicaOperacionDecimal(f: (Double, Int)=> Double, operandos:List[Int]) : Int = {
    operandos.reduceLeft( (x,y) => if ( Util.tieneDecimales(f(x.doubleValue() , y)) ) return 0 else f(x,y).intValue() )
  }

  private def aplicaOperacion(f: (Int, Int)=> Int, operandos:List[Int]) : Int = {
    operandos.reduceLeft( f(_,_) )
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