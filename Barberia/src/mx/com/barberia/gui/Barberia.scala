package mx.com.barberia.gui

import scala.swing._
import scala.swing.Button

object Barberia extends SimpleGUIApplication {

  def top = new Frame{
    title = "Barber�a"
      contents = new Button{
          text = "Click me"
      }
    /*contents += new Button(""){
      text = "Click me"
    } */ 
  }
  

  
}