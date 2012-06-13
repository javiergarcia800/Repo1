package mx.com.barberia.core

import scala.actors.Actor

case object VeABarberia
case object MarchateBarberia
case object MarchateBarberiaConCorte
case object VeASillaEspera
case object VeASillaBarbero

class Cliente(val nombre: String) extends Actor {

  var silla = 0
  
  private val ENTIDAD = nombre + " "

def act = loop{
    react{
      case VeABarberia              => irABarberia
                                       Barberia ! ClienteLlegaBarberia(this)
      case MarchateBarberia         => marcharseBarberia
                                       exit
      case MarchateBarberiaConCorte => marcharseBarberiaConCorte
                                       exit
      case VeASillaEspera           => esperaEnSilla
      case VeASillaBarbero          => irASillaBarbero
                                       Barbero ! CortaCabello(this)
    }
  }

  this.start
  
  private def irABarberia = println(ENTIDAD + " Va a barbería.")

  private def marcharseBarberia = {
    println(ENTIDAD + " Se marcha de barbería.")
  }

  private def marcharseBarberiaConCorte = {
    println(ENTIDAD + " Se marcha de barbería con corte.")
  }
  
  private def esperaEnSilla = println(ENTIDAD + " espera su turno en la silla " + this.silla + ".") 

  private def irASillaBarbero = println(ENTIDAD + " Va a la silla del barbero.")

}