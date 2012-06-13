package mx.com.barberia.core

object Main {

  def main(args:Array[String]) {
    println("Barberia...")
    val clientes = new Array[Cliente](5)
    for ( x <- 0 until 5 ) { clientes(x) = new Cliente("Cliente " + x ) }
    clientes.foreach(_ ! VeABarberia)
   
    Thread.sleep(5100);
    new Cliente("Cliente 5") ! VeABarberia
    
  }

}