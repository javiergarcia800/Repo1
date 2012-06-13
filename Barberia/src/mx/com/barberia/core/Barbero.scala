package mx.com.barberia.core

import scala.actors.Actor
import Actor._

case class CortaCabello(val cliente:Cliente)
case object Duerme
case object Despierta
case object Exit

object Barbero extends Actor {

  private val ENTIDAD = "Barbero: "

  def act = loop{
    react{
      case CortaCabello(cliente) =>  cortarCabello(cliente)
                                     cliente ! MarchateBarberiaConCorte
                                     Barberia ! BarberoDesocupado
      case Duerme                 => dormir
      case Despierta              => despertar
      case Exit                   => salir
                                     exit
    }
  }

  this.start
  Barbero ! Duerme
  
  private def cortarCabello(cliente:Cliente) {
    println(ENTIDAD + "Cortando cabello a " + cliente.nombre + " ...")
    Thread.sleep(5000)
    println(ENTIDAD + "Corte terminado.")
  }
  
  private def dormir = println(ENTIDAD + "zzzzZZZZZ")
  
  private def despertar = println(ENTIDAD + "Despierta")
  
  private def salir  {
    println(ENTIDAD + "sale de la barbería.")
    Barberia ! Cerrar
  }
  
}