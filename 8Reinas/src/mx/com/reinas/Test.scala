package mx.com.reinas

object Test {

  def main(args:Array[String]) {
    var r = new Array[Reina](8)
    r(0) = new Reina(1,2)
    r.foreach( x  => println("x.fila=" + x.fila + " y.columna=" + x.columna ))
    
  }
  
}