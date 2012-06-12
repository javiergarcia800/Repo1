package mx.com.barberia.core

object Main {

  def main(args:Array[String]) {
    println("Barberia...")
    val clientes = new Array[Cliente](4)
    for ( x <- 0 until 4 ) { clientes(x) = new Cliente("Cliente" + x ) }
    clientes.foreach(_ ! VeABarberia)
    
  }

}