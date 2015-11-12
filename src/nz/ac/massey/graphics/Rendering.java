//Jumaah, Bilal, 12232659, Assignment 3, 159.235
//Jackson, Jonny, 14089365, Assignment 3, 159.235
package nz.ac.massey.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import nz.ac.massey.graphics.data.Sort;


public class Rendering { //rendering happens here
	
	public Rendering(Graphics g, Model m_model, Canvas m_canvas) 
	{ 
		Sort sort = new Sort(m_model.verList); //sets a new sorted list of the vertices based on the z value
												//to make it draw based on the painters algorithm
	    final Graphics2D graphics = (Graphics2D) g; //make a new graphics 2D object
	    
	    //graphics settings to make the drawing appear normally since java have the Y axes flipped so the image
	    //appear normal and not upside down
		graphics.transform(AffineTransform.getTranslateInstance(0., m_canvas.getHeight()));
	    graphics.transform(AffineTransform.getScaleInstance(1., -1.));
	    //graphics settings to apply anti aliasing
	    graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    
	    for(int i = 0; i < sort.verList.size(); i++){
	        float X, Y, Z, primeX, primeY, primeZ;
	        X = (sort.verList.get(i).getX()* ModelViewer.m_Mod_V.scale)+ m_canvas.getWidth()/2;
	        Y = (sort.verList.get(i).getY()* ModelViewer.m_Mod_V.scale)+ m_canvas.getHeight()/2;
	        Z = (sort.verList.get(i).getZ()* ModelViewer.m_Mod_V.scale)- 1000 ;
	        
	        primeZ = Z-50;
	        primeX = (X * (primeZ/Z));
	        primeY = (Y * (primeZ/Z));
	        //The equations above written by rofrankel from Stackoverflow: 
	        //http://stackoverflow.com/questions/724219/how-to-convert-a-3d-point-into-2d-perspective-projection
	        
	        Line2D dots = new Line2D.Float(primeX, primeY, primeX, primeY);
	        graphics.setColor(Color.red);
	        graphics.draw(dots);
	     }
	    
	      
	    if(m_canvas.wireFrameRender == true || m_canvas.solidRender == true) 
	    {
	        for (int i = 0; i < m_model.triList.size(); ++i)  
	        {
	            Polygon triangle = new Polygon();
	            int triPoint = m_model.triList.get(i).getV1();
	            float X, Y, Z, primeX, primeY, primeZ;                        
	            X = (sort.verList.get(triPoint).getX()*(ModelViewer.m_Mod_V.scale))+m_canvas.getWidth()/2;
	            Y = (sort.verList.get(triPoint).getY()*(ModelViewer.m_Mod_V.scale))+m_canvas.getHeight()/2;
	            Z = (sort.verList.get(triPoint).getZ()*(ModelViewer.m_Mod_V.scale))-1000;
	           
	            primeZ = Z -50;
            	primeX = (X * (primeZ/Z));
	            primeY = (Y * (primeZ/Z));
	            
	            triangle.addPoint((int)primeX, (int)primeY);
	            
	          //--------------------------------------------------------------------------------            
	            triPoint = m_model.triList.get(i).getV2();                   
	            X = (sort.verList.get(triPoint).getX()*(ModelViewer.m_Mod_V.scale))+m_canvas.getWidth()/2;
	            Y = (sort.verList.get(triPoint).getY()*(ModelViewer.m_Mod_V.scale))+m_canvas.getHeight()/2;
	            Z = (sort.verList.get(triPoint).getZ()*(ModelViewer.m_Mod_V.scale))-1000;
	            
	            primeZ = Z - 50;
	            primeX = (X * (primeZ/Z));
	            primeY = (Y * (primeZ/Z));
	            
	            triangle.addPoint((int)primeX, (int)primeY);
	            
	          //--------------------------------------------------------------------------------	            
	            triPoint = m_model.triList.get(i).getV3();             
	            X = (sort.verList.get(triPoint).getX()*(ModelViewer.m_Mod_V.scale))+m_canvas.getWidth()/2;
	            Y = (sort.verList.get(triPoint).getY()*(ModelViewer.m_Mod_V.scale))+m_canvas.getHeight()/2;
	            Z = (sort.verList.get(triPoint).getZ()*(ModelViewer.m_Mod_V.scale))-1000;
	            
	            primeZ = Z - 50;
	            primeX = (X*(primeZ/Z));
	            primeY = (Y*(primeZ/Z));
	            
	            triangle.addPoint((int)primeX, (int)primeY);
	            
	           //--------------------------------------------------------------------------------
	           if(m_canvas.cull == true) {
	        	  graphics.setColor(Color.green);
	        	  graphics.draw(triangle);
	           	  graphics.setColor(Color.black);
	           	  graphics.fillPolygon(triangle);
	           }
	         //--------------------------------------------------------------------------------
	           if(m_canvas.solidRender == false)
	           {
	        	   graphics.setColor(Color.green);
	        	   graphics.draw(triangle);
	           }
	         //--------------------------------------------------------------------------------
	           if(m_canvas.solidRender == true)
	           {
	           	  graphics.setColor(Color.white);
	           	  graphics.fillPolygon(triangle);
	           }
	         //--------------------------------------------------------------------------------
	           if((m_canvas.solidRender == true) && (m_canvas.wireFrameRender == true) && (m_canvas.cull == false))
	           {
	        	  graphics.setColor(Color.green);
	        	  graphics.draw(triangle);
	           	  graphics.setColor(Color.white);
	           	  graphics.fillPolygon(triangle);
	           }
	        }    	
	    }
	}
}
