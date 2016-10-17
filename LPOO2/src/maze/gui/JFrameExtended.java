package maze.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


import javax.swing.JFrame;


public class JFrameExtended extends JFrame implements ComponentListener {
	
	int temp = 0;
	
	JFrameExtended(String s){
		super(s); 
		this.addComponentListener(this); 
	
		
	}
	 
	
	
	@Override
	public void componentResized(ComponentEvent arg0) {
	    Rectangle b = arg0.getComponent().getBounds();
	    temp = b.width;
	    arg0.getComponent().setSize(getWidth(),getWidth());
	    repaint();

	}
	
	@Override
	public void setSize(Dimension d) {
	}
	
	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		super.setSize(width, width);
	}
	

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
