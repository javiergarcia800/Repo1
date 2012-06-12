package mx.com.barberia.core

import scala.actors.Actor

case object VeABarberia
case object MarchateBarberia
case object VeASillaEspera
case object VeASillaBarbero

class Cliente(val nombre: String) extends Actor {

  private val ENTIDAD = "Cliente " + nombre + ": "

  def act = loop {
    react {
      case VeABarberia      => irABarberia
      case MarchateBarberia => marcharseBarberia
      case VeASillaEspera   => esperaEnSilla
      case VeASillaBarbero  => irASillaBarbero
                               Barbero ! CortaCabello(this)
    }
  }

  private def irABarberia = println(ENTIDAD + " Va a barbería.")

  private def marcharseBarberia = println(ENTIDAD + " Se marcha de barbería.") 

  private def esperaEnSilla = println(ENTIDAD + " Espera su turno en silla de espera.") 

  private def irASillaBarbero = println(ENTIDAD + " Va a silla barbero.")

}