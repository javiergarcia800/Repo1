package com.seg47

import com.seg47.util._

object Principal47Seg {

    def main(args:Array[String]) {
      Printer.printStart()
      try     { ejecutaAplicacion }
      catch   { case x => Printer.imprimeEntradaIncorrecta; println(x); x.printStackTrace() }
      finally { Printer.imprimeSalida }
    }

    def ejecutaAplicacion {
      var con:Int = 0
        while (true) {
          Printer.nuevoCalculo
          val data = Reader.readData
          Printer.imprimeDatos(data)
          Procesador.encuentraCombinacion(data)
          Printer.imprimeContinuar
          if ( "" != readLine ) exit(0)
        }
    }

}