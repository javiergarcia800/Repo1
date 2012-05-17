package com.seg47

import scala.collection.mutable.ArrayBuffer

object Principal47Seg {

    def main(args:Array[String]) {
        Printer.printStart()
        val data = Reader.readData()
        Printer.printData(data)
        Processor.process(data)
    }

}