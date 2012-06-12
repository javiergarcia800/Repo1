package mx.com.barberia.core

import scala.actors.Actor
import Actor._

case object BarberoDesocupado
case class ClienteLlegaBarberia(val cliente:Cliente)

object Barberia extends Actor {

  private val ENTIDAD = "Barberia: "

  private val MAX_SILLAS = 3

  private var sillas:Array[Cliente] = new Array[Cliente](MAX_SILLAS);

  private var sillasDeEsperaOcupadas = 0
  private var siguienteSillaDeEsperaDesocupada = 0;

  private var sillaBarberoOcupada = false
  private def sillaBarberoDesocupada = !sillaBarberoOcupada

  def act = loop {
    react {
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
                                                            println(ENTIDAD + sillasDeEsperaOcupadas + " sillas ocupadas.")
                                                }
    }
  }

  this.start
  
  private def siguienteCliente:Cliente = {
    var sillaSiguienteCliente = siguienteSillaDeEsperaDesocupada - 1
    if ( sillaSiguienteCliente < 0 ) {
      sillaSiguienteCliente = MAX_SILLAS - 1
    }
    sillas(sillaSiguienteCliente)
  }

  private def clienteEnEspera(cliente:Cliente) {
    println(cliente.nombre + " espera en silla " + siguienteSillaDeEsperaDesocupada )
    sillas(siguienteSillaDeEsperaDesocupada) = cliente
    siguienteSillaDeEsperaDesocupada += 1
    if ( siguienteSillaDeEsperaDesocupada == MAX_SILLAS ) {
      if ( salaEsperaLlena ) {
        siguienteSillaDeEsperaDesocupada = -1
      } else {
        siguienteSillaDeEsperaDesocupada = 0;
      }
    }
  }

  private def salaEsperaLlena = MAX_SILLAS == sillasDeEsperaOcupadas
  
}