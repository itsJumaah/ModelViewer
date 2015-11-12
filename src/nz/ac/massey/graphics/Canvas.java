//Jumaah, Bilal, 12232659, Assignment 3, 159.235
//Jackson, Jonny, 14089365, Assignment 3, 159.235
package nz.ac.massey.graphics;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The draw area.
 */
class Canvas extends JPanel {
	

  private static final long serialVersionUID = 1L;
  private Model m_model;
  public boolean wireFrameRender = true;
  public boolean solidRender = false;
  public boolean cull = false;
  
  public Canvas()  
  {
    setOpaque(true);
    this.setBackground(Color.BLACK);
  }
  //////////////////////////////////////////////////////////////////////////////
  public void setModel(final Model model) 
  {
    m_model = model;
  }
  //////////////////////////////////////////////////////////////////////////////
  public void changeWire() { //switch listens to the checkbox and changes it self
	  if(wireFrameRender == true) 
	  {
		  wireFrameRender = false;
	  }
	  else 
	  {
		  wireFrameRender = true;
	  }
  }
  //////////////////////////////////////////////////////////////////////////////
  public void changeSolid() {//switch listens to the checkbox and changes it self
	  if(solidRender == true) 
	  {
		  solidRender = false;
	  }
	  else 
	  {
		  solidRender = true;
	  }
  }
  //////////////////////////////////////////////////////////////////////////////
  public void changeCull() {//switch listens to the checkbox and changes it self
	  if(cull == true) 
	  {
		  cull = false;
	  }
	  else 
	  {
		  cull = true;
	  }
  }

  //////////////////////////////////////////////////////////////////////////////
  @Override
  protected void paintComponent(Graphics g) 
  {
    super.paintComponent(g);
    
    if (m_model == null) return;
    
    new Rendering(g, m_model, this); //rendering happen here
    
  }
}
