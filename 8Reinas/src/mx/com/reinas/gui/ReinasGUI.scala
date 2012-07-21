package mx.com.reinas.gui

import scala.swing._
import scala.swing.RichWindow.Undecorated
import scala.swing.Panel

import java.awt.{Color, Dimension, Toolkit}
import java.awt.geom._
import javax.swing._



object ReinasGUI extends SimpleSwingApplication {
  
  val tableroAjedrez = new MyCanvasPanel
  
  val boundsCenter = getRectangleCenter(tableroAjedrez.getDimension())
  
  def top = new MainFrame with Undecorated{
    title = "8 Reinas"
    bounds = boundsCenter
    contents = tableroAjedrez
    size = new Dimension(tableroAjedrez.getDimension().width, tableroAjedrez.getDimension().height)
    resizable = false
  }
  
  def getRectangleCenter(windowDimension:Dimension) = {
    var screen = Toolkit.getDefaultToolkit().getScreenSize()
    var X = (screen.width / 2) - (windowDimension.getWidth() / 2); // Center horizontally.
    var Y = (screen.height / 2) - (windowDimension.getHeight() / 2); // Center vertically.
    new Rectangle(X.intValue(), Y.intValue(), windowDimension.getWidth().intValue(), windowDimension.getHeight().intValue())
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
    this.dibujaFondo(g)
    this.dibujaBorde(g)
    this.dibujaCuadros(g)
  }
  
  /**
   * Dibuja el fondo del tablero.
   */
  def dibujaFondo(g:Graphics2D) {
    g.setColor(BACKGROUND_COLOR)
    g.fillRect(0,0,(NUMERO_REINAS*TAMANO_CASILLAS+(ESPACIO_LIBRE*2)+(ANCHO_MARCO*2)),(NUMERO_REINAS*TAMANO_CASILLAS+(ESPACIO_LIBRE)+(ANCHO_MARCO*2)+ 3))
  }
  
  /**
   * Dibuja el borde del tablero.
   */
  def dibujaBorde(g:Graphics2D) {
    var x = ESPACIO_LIBRE
    var y = ESPACIO_LIBRE
    var width = (NUMERO_REINAS*TAMANO_CASILLAS+(ANCHO_MARCO*2))
    var height = (NUMERO_REINAS*TAMANO_CASILLAS+(ANCHO_MARCO*2))
    // El borde se construye con 5 lineas.
    g.setColor(COLOR_BORDE_EXTERNO_2)
    g.draw(new Rectangle2D.Double(x + 0, y + 0, width - 1, height - 1))
    g.setColor(COLOR_BORDE_EXTERNO_1)
    g.draw(new Rectangle2D.Double(x + 1, y + 1, width - 2 - 1, height - 2 - 1))
    g.setColor(COLOR_BORDE_EXTERNO_0)
    g.draw(new Rectangle2D.Double(x + 2, y + 2, width - 4 - 1, height - 4 - 1))
    g.setColor(COLOR_BORDE_EXTERNO_1)
    g.draw(new Rectangle2D.Double(x + 3, y + 3, width - 6 - 1, height - 6 - 1))
  }
  
  /**
   * Dibuja el tablero completo.
   */
  def dibujaCuadros(g:Graphics2D) {
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
  
  /**
   * Dibuja una casilla el tablero, blanca o negra.
   */
  def dibujaCasilla(g:Graphics2D, x:Int, y:Int, color:Color) {
    g.setColor(color)
    g.fill(new Rectangle2D.Double(x, y, TAMANO_CASILLAS, TAMANO_CASILLAS))
  }
  
  /**
   * Regresa las dimensiones de la ventana del tablero.
   */
  def getDimension() =
    new Dimension(NUMERO_REINAS * TAMANO_CASILLAS+ (ESPACIO_LIBRE*2) + (ANCHO_MARCO*2),
                  NUMERO_REINAS * TAMANO_CASILLAS+ (ESPACIO_LIBRE*2) + (ANCHO_MARCO*2))

}