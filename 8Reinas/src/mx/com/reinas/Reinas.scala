package mx.com.reinas

class Reina(var fila:Int = 0, var columna:Int = 0) {

  def mismaPosicion(reina:Reina) : Boolean = {
    reina.fila == fila && reina.columna == columna
  }

  def setPosicion(fila:Int, columna:Int) {
      this.fila = fila
      this.columna = columna
  }

}

object Reinas {

  private val NUM_REINAS = 8

  private val reinas:Seq[Reina] = (1 to NUM_REINAS).map(x => new Reina)

  def main(args:Array[String]) {
    colocaListaReinas(NUM_REINAS)
    reinas.foreach( r => println("Posicion: [" + r.fila + ", " + r.columna + "]" ))
  }

  private def colocaListaReinas(numReinas: Int): Boolean = {
    numReinas match {
      case num if ( num > 0 ) => val fila = reinas.reduceLeft( (x,y) => if (x.fila > y.fila) x else y ).fila + 1
                                 for ( columna <- 1 to NUM_REINAS ) {
                                   if ( colocaReina(fila, columna) && colocaListaReinas(num-1) )
                                     return true
                                   else
                                     removeReina(fila, columna)
                                 }
                                 false
      case _ => true
    }
  }

  private def colocaReina(fila: Int, columna:Int) : Boolean = {
    if ( posicionValida(fila, columna) ) {
      addReina(fila, columna)
      return true
    }
    false
  }

  private def addReina(fila: Int, columna: Int) {
    reinas.foreach(x=> { if ( x.fila == 0 ) { x.setPosicion(fila, columna); return } })
  }

  private def removeReina(fila: Int, columna: Int) {
    reinas.foreach(x=> { if ( x.fila == fila && x.columna == columna ) { x.setPosicion(0, 0); return } })
  }
  
  private def posicionValida(x:Int, y:Int) : Boolean = {
    linealValida(x, y) && diagonalValida(x, y)
  }

  private def linealValida(x:Int, y:Int) : Boolean = {
    reinas.foreach( r => if ( r.fila == x || r.columna == y ) return false )
    true
  }

  private def diagonalValida(x: Int, y:Int) : Boolean = {
    diagonalSube(x, y) && diagonalBaja(x, y)
  }

  private def diagonalSube(x:Int, y:Int) : Boolean = {
    val reinaTest = new Reina(x, y)
    /*Se obtiene el inicio de la diagonal que sube */
    for ( i <- 0 until 8 if ( reinaTest.fila - 1 > 0 && reinaTest.columna - 1 > 0 ) ) {
      reinaTest.fila -= 1
      reinaTest.columna -= 1
    }
    for ( i <- 0 until NUM_REINAS if (reinaTest.fila <= NUM_REINAS && reinaTest.columna <= NUM_REINAS )) {
      reinas.foreach(reina => { if ( reina.mismaPosicion(reinaTest) ) return false })
      reinaTest.fila += 1
      reinaTest.columna += 1
    }
    true
  }

  private def diagonalBaja(x:Int, y:Int) : Boolean = {
    val reinaTest = new Reina(x, y)
    /*Se obtiene el inicio de la diagonal que baja */
    for ( i <- 0 until NUM_REINAS if ( reinaTest.fila + 1 <= NUM_REINAS && reinaTest.columna - 1 > 0 ) ) {
      reinaTest.fila += 1
      reinaTest.columna -= 1
    }
    for ( i <- 0 until NUM_REINAS if (reinaTest.fila > 0 && reinaTest.columna <= NUM_REINAS )) {
      reinas.foreach(reina => {if ( reina.mismaPosicion(reinaTest) ) return false })
      reinaTest.fila -= 1
      reinaTest.columna += 1
    }
    true
  }

}