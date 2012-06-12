package mx.com.barberia.core

import scala.actors.Actor
import Actor._

case class CortarCabello(val cliente:Cliente)
case object Dormir

object Barbero extends Actor {

  private val name = "Barbero :"

  override def start:Actor = {
    super.start();
    this ! Dormir
    this
  }

  def act = loop {
    react {
      case CortarCabello(cliente) => println(name + "Cortando cabello a" + cliente.nombre )
                                     Barberia ! BarberoDesocupado
      case Dormir                 => println(name + "zzzzZZZZZ")
    }
  }

}