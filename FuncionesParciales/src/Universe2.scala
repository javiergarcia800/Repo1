/*Funciones Parciales*/
object Main {

  def main(args: Array[String]) = {
    foreverUntil(readInt, untilNumberOrPrint(42))
  }

  private def untilNumberOrPrint (x:Int)(y:Int) = if (x == y) exit else println(y)

  private def foreverUntil(function: => Int, untilFunction:(Int) => Unit) = {
    while(true) untilFunction(function)
  }

}
