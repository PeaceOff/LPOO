package maze.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import maze.logic.Labirinto;

public class LabEditor extends JPanel implements MouseInputListener, MouseListener {

	char[][] result;
	char charInUse;
	Labirinto lab;
	BufferedImage labImg;
	BufferedImage heroImg;
	BufferedImage espadaImg;
	BufferedImage dragaoImg;
	BufferedImage saidaImg;
	
	LabEditor(){
		this.addMouseListener(this);
		lab = new Labirinto();
		initResult(11);
		lab.setLabirinto(result);
		try{
			labImg = ImageIO.read(new File("lab.jpg"));
			heroImg = ImageIO.read(new File("hero.png"));
			espadaImg = ImageIO.read(new File("espada.png"));
			dragaoImg = ImageIO.read(new File("dragon.png"));
			saidaImg = ImageIO.read(new File("end.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void setCharInUse(char i){
		charInUse = i;
	}
	
	public void initResult(int dim){
		result = new char[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if(i == 0 || j == 0 || i == (dim-1) || j == (dim - 1))
				result[i][j] = 'X';
			}
		}
		lab.setLabirinto(result);
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		DrawingTools.DesenharLab(g,labImg,saidaImg,dragaoImg, heroImg, espadaImg, lab);
		
	} 
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		x /= 50;
		y /= 50;
		
		if( e.getButton() == MouseEvent.BUTTON1){
			result[y][x] = charInUse;
		}

		repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
