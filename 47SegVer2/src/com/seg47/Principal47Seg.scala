package com.seg47

import com.seg47.util._

object Principal47Seg {

    def main(args:Array[String]) {
        var con:Int = 0
        Printer.printStart()
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