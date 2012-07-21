package mx.com.reinas.gui

import scala.swing._
import scala.swing.Button
import scala.swing.Panel

import java.awt.{Color, Dimension}
import java.awt.geom._
import javax.swing._



object ReinasGUI extends SimpleSwingApplication {
  
  def top = new MainFrame{
    title = "8 Reinas"
    contents = new MyCanvasPanel
    size = new Dimension(501,523)
    /*contents += new Button(""){
      text = "Click me"
    } */ 
  }
  
}

class MyCanvasPanel extends Panel{

  val BACKGROUND_COLOR = new Color(6, 15, 33)
  
  val COLOR_BORDE_EXTERNO_2 = new Color(34, 85, 179)
  val COLOR_BORDE_EXTERNO_1 = new Color(66, 151, 217)
  val COLOR_BORDE_EXTERNO_0 = new Color(114, 250, 253)
  
  val WHITE_COLOR = new Color(51, 102, 204)
  
  val BLACK_COLOR = new Color(29, 60, 130)
  
  val TAMANO_CASILLAS = 40
  
  val NUMERO_REINAS = 8
  
  val ESPACIO_LIBRE = 3
  
  val ANCHO_MARCO = 4
  
  override def paint(g:Graphics2D){
    
    // Dibuja fondo
    g.setColor(BACKGROUND_COLOR)
    g.fillRect(0,0,(NUMERO_REINAS*TAMANO_CASILLAS+(ESPACIO_LIBRE*2)+(ANCHO_MARCO*2)),(NUMERO_REINAS*TAMANO_CASILLAS+(ESPACIO_LIBRE)+(ANCHO_MARCO*2)+ 3))
    
    // Dibuja el borde
    //g.setColor(BORDER_COLOR)
    this.dibujaBorde(g, ESPACIO_LIBRE, ESPACIO_LIBRE, (NUMERO_REINAS*TAMANO_CASILLAS+(ANCHO_MARCO*2)), (NUMERO_REINAS*TAMANO_CASILLAS+(ANCHO_MARCO*2)), ANCHO_MARCO)
    
    var INIT_X = ESPACIO_LIBRE + ANCHO_MARCO;
    var INIT_Y = ESPACIO_LIBRE + ANCHO_MARCO;
    
    var x = INIT_X
    var y = INIT_Y
    var color = WHITE_COLOR
    
    for (idx <- 0 until NUMERO_REINAS) {
      for (jdx <- 0 until NUMERO_REINAS) {
        if ( (idx+jdx) % 2 == 0 ) {
          color = WHITE_COLOR; 
        } else {
          color = BLACK_COLOR;
        }
        dibujaCasilla(g, x, y, color)
        x += TAMANO_CASILLAS
      }
      y += TAMANO_CASILLAS
      x = INIT_X
    }
    
    
  }
  
  def dibujaCasilla(g:Graphics2D, x:Int, y:Int, color:Color) {
    g.setColor(color)
    g.fill(new Rectangle2D.Double(x, y, TAMANO_CASILLAS, TAMANO_CASILLAS))
  }
  
  /**
   * Dibuja el borde del tablero.
   */
  def dibujaBorde(g:Graphics2D, x:Int, y:Int, width:Int, height:Int, widthLinea:Int) {
    // El borde se construye con 6 lineas.
    g.setColor(COLOR_BORDE_EXTERNO_2)
    g.draw(new Rectangle2D.Double(x + 0, y + 0, width - 1, height - 1))
    g.setColor(COLOR_BORDE_EXTERNO_1)
    g.draw(new Rectangle2D.Double(x + 1, y + 1, width - 2 - 1, height - 2 - 1))
    g.setColor(COLOR_BORDE_EXTERNO_0)
    g.draw(new Rectangle2D.Double(x + 2, y + 2, width - 4 - 1, height - 4 - 1))
    g.setColor(COLOR_BORDE_EXTERNO_1)
    g.draw(new Rectangle2D.Double(x + 3, y + 3, width - 6 - 1, height - 6 - 1))
    //g.setColor(COLOR_BORDE_EXTERNO_2)
    //g.draw(new Rectangle2D.Double(x + 4, y + 4, width - 8 - 1, height - 8 - 1))
    
    /*for (i<- 0 until widthLinea ){
      g.draw((new Rectangle2D.Double(x + i, y + i, width-(2*i)-1, height-(2*i)-1)))
    }*/
    
  }

}