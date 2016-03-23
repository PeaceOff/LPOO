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
	int x;
	int y;
	
	LabEditor(){
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
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
	
	public int getDim(){
		return result.length;
	}
	
	public void setResult(char[][] r){
		result = r;
		lab.setLabirinto(result);
	}

	public String getLab(){
		return lab.toString();
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
		g.drawRect(x*50, y*50, 50, 50);
		
	} 
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		atualizarZona(e);

		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		x /= 50;
		y /= 50;
		if(y >= result.length || x >= result[0].length || y < 0 || x < 0)
			return;
		
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(e.getButton() == MouseEvent.BUTTON1)
			atualizarZona(e);
	
		repaint();
		
	}
	
	public void atualizarZona(MouseEvent e){
		x = e.getX();
		y = e.getY();
		x /= 50;
		y /= 50;
		boolean canto1 = (x == 0 && y == 0);
		boolean canto2 = (x == 0 && y == result.length - 1);
		boolean canto3 = (x == result[0].length - 1 && y == 0);
		boolean canto4 = (x == result[0].length - 1 && y == result.length - 1);
		
		if(canto1 || canto2 || canto3 || canto4)
			return;
		
		if(y >= result.length || x >= result[0].length || y < 0 || x < 0)
			return;
		
		switch(charInUse){
		case 'S':
			if(!(x == 0 || y == 0 || x == result[0].length - 1 || y == result.length - 1))
				return;
			substituir('S','X');
			break;
		case 'E':
			if(x == 0 || y == 0 || x == result[0].length - 1 || y == result.length - 1)
				return;
			substituir('E',' ');
			break;
		case 'H':
			if(x == 0 || y == 0 || x == result[0].length - 1 || y == result.length - 1)
				return;
			substituir('H',' ');
			break;
		case ' ':
			if(result[y][x] == 'S')
				substituir('S','X');
			if(x == 0 || y == 0 || x == result[0].length - 1 || y == result.length - 1)
				return;
			break;
		case 'D':
			if(x == 0 || y == 0 || x == result[0].length - 1 || y == result.length - 1)
				return;
			break;
		}
		
		result[y][x] = charInUse;
		
	}
	
	public void substituir(char c, char s){
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				if(result[i][j] == c)
					result[i][j] = s;
			}
		}
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
