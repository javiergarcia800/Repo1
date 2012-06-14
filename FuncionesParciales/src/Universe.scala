object Universe {

  private var c = 0

  def main(args: Array[String]) = {
    forever(reader, 42)
  }

  private def forever ( f: (Int) => Unit, escape: Int ) = while(true) f(escape)

  private val reader: (Int) => Unit = (escape) => {
    c = readInt
    if ( c == escape ) exit
    println(c)
  }
  
}