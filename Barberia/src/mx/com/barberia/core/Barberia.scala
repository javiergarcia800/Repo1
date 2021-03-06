package mx.com.barberia.core

import scala.actors.Actor
import scala.collection.mutable.Queue
import Actor._

case object Cerrar
case object BarberoDesocupado
case class ClienteLlegaBarberia(val cliente:Cliente)

object Barberia extends Actor {

  private val ENTIDAD = "Barberia: "

  private val MAX_SILLAS = 3

  private var sillas = new Queue[Cliente]()
  //private var sillas:Array[Cliente] = new Array[Cliente](MAX_SILLAS);

  private var sillasDeEsperaOcupadas = 0
  private var siguienteSillaDeEsperaDesocupada = 1;

  private var sillaBarberoOcupada = false
  private def sillaBarberoDesocupada = !sillaBarberoOcupada

  def act = loop{
    react{
      case BarberoDesocupado => if ( sillasDeEsperaOcupadas > 0 ) {
                                  sillasDeEsperaOcupadas -= 1
                                  siguienteCliente ! VeASillaBarbero
                                } else Barbero ! Duerme
                                  sillaBarberoOcupada = false

      case ClienteLlegaBarberia(cliente) => sillasDeEsperaOcupadas 
      											match {
      											  case 0 if (sillaBarberoDesocupada) => sillaBarberoOcupada = true 
      											                                        Barbero ! Despierta
                                                                                        cliente ! VeASillaBarbero
                                                  case n if n == MAX_SILLAS => cliente ! MarchateBarberia
                                                  case _ => sillasDeEsperaOcupadas += 1
                                                            clienteEnEspera(cliente)
                                                            cliente ! VeASillaEspera
                                                            muestraSillasOcupadas
                                                }
      case Cerrar => cerrarBarberia
                     exit
    }
  }

  this.start
  
  private def muestraSillasOcupadas {
    sillasDeEsperaOcupadas match {
      case 0 => println(ENTIDAD + "Ninguna silla ocupada.")
      case 1 => println(ENTIDAD + "1  silla ocupada.")
      case _ => println(ENTIDAD + sillasDeEsperaOcupadas + " sillas ocupadas.")
    }
  }
  
  private def siguienteCliente:Cliente = {
    sillas.dequeue()
  }

  private def clienteEnEspera(cliente:Cliente) {
    cliente.silla = siguienteSillaDeEsperaDesocupada
    sillas.enqueue(cliente)
    siguienteSillaDeEsperaDesocupada += 1
    if ( siguienteSillaDeEsperaDesocupada > MAX_SILLAS ) {
      siguienteSillaDeEsperaDesocupada = 1
    }
  }

  private def salaEsperaLlena = MAX_SILLAS == sillasDeEsperaOcupadas
  
  private def cerrarBarberia {
    println(ENTIDAD + "cierra.")
  }
  
}