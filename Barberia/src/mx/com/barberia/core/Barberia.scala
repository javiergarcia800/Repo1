package mx.com.barberia.core

import scala.actors.Actor
import Actor._

case class RecibeCliente(val cliente:Cliente)

case object BarberoDesocupado

object Barberia extends Actor {

  val MAX_LUGARES = 3

  val nombre = "Barberia :"

  var sillasOcupadas = 0
  
  
  def act = loop {
    react {
      case RecibeCliente(cliente) if ( sillasOcupadas < MAX_LUGARES ) =>  sillasOcupadas+=1
                                                                          println(nombre + " Acepta cliente " + cliente.nombre )
                                                                          
      case RecibeCliente(cliente) if ( sillasOcupadas == MAX_LUGARES ) => println(nombre + "Rechaza cliente " + cliente.nombre )
    }
  }
  
}