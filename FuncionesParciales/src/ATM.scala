object Main {

  private def format(value:Float):String = new java.text.DecimalFormat("#.00").format(value)
  
  private class Cuenta(val retiro:Int, val total:Float)
  
  def main(args:Array[String]) {
    val cuenta = readDatos
    val total = calculaTotal(cuenta) 
    print(format(total))
  }

  private def readDatos : Cuenta = {
    val s = readLine.split(" ")
    new Cuenta(s(0).toInt,s(1).toFloat)
  }
  
  private def calculaTotal(cuenta:Cuenta) : Float = {
    if ( cuenta.retiro % 5 == 0 && cuenta.retiro <= (cuenta.total - 0.5F)) {
      return cuenta.total - cuenta.retiro - 0.5F
    } else 
      cuenta.total
  }

}