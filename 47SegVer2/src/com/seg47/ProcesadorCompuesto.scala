package com.seg47

import com.seg47.vo._
import com.seg47.vo.TipoOperacion._

import scala.collection.mutable._

object ProcesadorCompuesto extends BaseProcesador {

  def getOperacion(numeros:List[Int], cantidad: Int) : Operacion = {
    val cifraRedondeada = (cantidad / 10) * 10;
    val listDigitosClave = getDigitosClave(cifraRedondeada)
    var operacion = getCombinaciones(listDigitosClave, numeros)
    
    if ( operacion.tipoOperacion != NOTHING ) {
      val cantidadFaltante = operacion.cantidadFaltante(cantidad)
      val numerosSinUsar = numeros.diff(operacion.getOperandos())

      if ( cantidadFaltante == 0 )
         return operacion

      if ( numerosSinUsar.length == 1 ) {
         var numero = numerosSinUsar.first
         if ( numero <= cantidadFaltante ) {
            operacion = operacion.operacionFaltante(cantidad)
            operacion.operando2 = Cifra(numero)
         }
      } else if ( numerosSinUsar.length > 1 ) {
         val operacionDoble = operacion.operacionFaltante(cantidad)
         operacionDoble.operando2 = ProcesadorSimple.getOperacion(numerosSinUsar, cantidadFaltante)
         operacion = super.mejorOperacion(List(operacion, operacionDoble), cantidad)
       }
    }
    
    operacion
  }

  def getDigitosClave(cifra: Int) : List[Int] = { 
    for ( i <- 1 to 10; 
          j <- (i + 1) to 10;
          k <- (j + 1) to 10 ) {
      if ( i * j * k == cifra ) {
        return List(i,j,k)
      }
    }
    List()
  }

  def getCombinaciones(numerosClave:List[Int], numerosDisponibles: List[Int]) : Operacion = {
    var operacion = new Operacion()
    var numClaves = new ListBuffer[Int]
    var numDisponibles = new ListBuffer[Int]
    
    numerosClave.foreach( numClaves += _ )
    numerosDisponibles.foreach( numDisponibles += _ )
    
    var operandosUnitarios = getCifrasSimplesEncontradas(numerosClave, numerosDisponibles)
    
    operandosUnitarios.foreach( x=> {
      numClaves -= x
      numDisponibles -= x
    } )
    
    var operacionesParEncontradas = getOperacionesSimplesEncontradas(numClaves.toList, numDisponibles.toList)
    
    // Solo si se obtuvieron los numerosClave
    if ( operandosUnitarios.size + operacionesParEncontradas.size == numerosClave.size ) {
      operandosUnitarios.size match {
        case 1 => operacion.operando1 = Cifra(operandosUnitarios.first)
                  operacion.tipoOperacion = POR
                  operacion.operando2 = Operacion.construyeOperando2(operacionesParEncontradas, POR)
        case 2 => operacion.tipoOperacion = POR
                  operacion.setOperandos(operandosUnitarios)
                  if ( operacionesParEncontradas.length > 0 ) {
                      operacion = new Operacion(operacion, POR, operacionesParEncontradas.first)
                  }
        case 3 => operacion.tipoOperacion = POR
                  operacion.setOperandos(operandosUnitarios)
        case _ =>
      }
      
    }

    operacion
  }

  /**
   * Regresa un lista de Cifras con los n&uacute;meros clave encontrados en la lista de n&uacute;meros disponibles.
   *
   * @param numerosClave Lista de n&uacute;meros a buscar.
   * @param numerosDisponibles Lista de n&uacute;meros en donde buscar.
   * @return Lista de n&uacute;meros encontrados.
   */
  def getCifrasSimplesEncontradas(numerosClave:List[Int], numerosDisponibles:List[Int] ) : List[Int] = {
    var encontrados = new ListBuffer[Int]
    var numDisponibles = new ListBuffer[Int]
    numerosDisponibles.foreach(numDisponibles += _)
    for ( x <- numerosClave ) {
      if ( numDisponibles.contains(x) ) {
        numDisponibles -= x
        encontrados += x
      }
    }
    encontrados.toList
  }

  /**
   * Regresa una lista de operaciones que su calculo es igual a los numeros Clave, si no se obtuvieron todas las operaciones
   * para todos los numerosClave se regresa una lista vac&iacute;a.
   *
   * @param numerosClave:ListBuffer[Int] Lista de numeros clave a encontrar.
   * @param numerosDisponibles:ListBuffer[Int] Lista de numeros disponibles para utilizar en las operaciones.
   * @return Lista de operaciones que calculan los n&uacute;meros clave.
   */
  def getOperacionesSimplesEncontradas(numerosClave:List[Int], numerosDisponibles:List[Int] ) : List[Operacion] = {
    var cifrasPorEncontrar = numerosClave.length
    var cifrasEncontradas = 0;
    var operacionesEncontradas = new ListBuffer[Operacion]
    var operacion:Operacion = new Operacion()
    var numDisponibles = new ListBuffer[Int]
    numerosDisponibles.foreach(numDisponibles += _)
    for ( i <- 0 until cifrasPorEncontrar ) {
      operacion = getOperacionParaNumeroClave(numerosClave(i), numDisponibles)
      if ( operacion.tipoOperacion != NOTHING ) {
        operacionesEncontradas += operacion
        operacion.getOperandos().foreach( numDisponibles -= _ )
      }
    }
    if ( operacionesEncontradas.length < cifrasEncontradas )
      return List()
    operacionesEncontradas.toList
  }

  def getOperacionParaNumeroClave(numeroClave:Int, numerosDisponibles:ListBuffer[Int]) : Operacion = {
    for ( i <- 0 until numerosDisponibles.length;
          j <- (i + 1) until numerosDisponibles.length ) {
      if ( numerosDisponibles(i) + numerosDisponibles(j) == numeroClave ) {
        return new Operacion(Cifra(numerosDisponibles(i)), MAS, Cifra(numerosDisponibles(j)))
      } else if ( numerosDisponibles(i) - numerosDisponibles(j) == numeroClave ) {
        return new Operacion(Cifra(numerosDisponibles(i)), MENOS, Cifra(numerosDisponibles(j)))
      }
    }
    return new Operacion
  }
  
}