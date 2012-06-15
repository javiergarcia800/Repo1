import java.math.BigInteger

import scala.collection.mutable.ArrayBuffer

object FuncionZ {
  
  private def MAXIMO = 1000000000 
  
  private def dividendos = getDividendos(MAXIMO)
  
  private def getDividendos(maximo:Int) : ArrayBuffer[Int] = {
    var dividendos = new ArrayBuffer[Int]()
    var x = 1
    while ( x < maximo ) {
      x = x*5
      dividendos.append(x)
    }
    dividendos
  }

  private def z (numero:Int) : Int = dividendos.foldLeft(0) (_ + numero/_)
  
  def main(args:Array[String]) {
    for (x<- 1 to readInt) {
        println(z(readInt))
    }
  }

}