package mx.com.barberia.core

import scala.actors.Actor
import Actor._

case class CortaCabello(val cliente:Cliente)
case object Duerme
case object Despierta

object Barbero extends Actor {

  private val ENTIDAD = "Barbero :"

  override def start:Actor = {
    super.start();
    this ! Duerme
    this
  }

  def act = loop {
    react {
      case CortaCabello(cliente) => cortarCabello(cliente)
                                     Barberia ! BarberoDesocupado
      case Duerme                 => dormir
      case Despierta              => despertar
    }
  }

  private def cortarCabello(cliente:Cliente) {
    println(ENTIDAD + "Cortando cabello a" + cliente.nombre + " ...")
    Thread.sleep(5000)
    println(ENTIDAD + "Corte terminado.")
  }
  
  private def dormir = println(ENTIDAD + "zzzzZZZZZ")
  
  private def despertar = println(ENTIDAD + "Despierta")
  
}