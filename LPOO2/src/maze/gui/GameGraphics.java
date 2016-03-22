/**
 * 
 */
package maze.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import com.sun.prism.Image;

import javafx.scene.input.KeyCode;
import maze.logic.GameLogic;

/**
 * @author Joao
 *
 */
public class GameGraphics extends JPanel implements KeyListener, MouseInputListener {
	
	BufferedImage labImg;
	BufferedImage heroImg;
	GameLogic gl;
	final int imgSize = 50;
	String mensagem;
	public GameGraphics(GameLogic gl){
		this.addKeyListener(this); 
		this.gl = gl;
		try{
			labImg = ImageIO.read(new File("lab.jpg"));
			heroImg = ImageIO.read(new File("hero.png"));
		}catch(IOException e){
			e.printStackTrace();
		}  
	
	}
	
	public GameLogic getGl() {
		return gl;
	}

	public void setGl(GameLogic gl) {
		this.gl = gl;
	
	}
	
	public void atualizar(){
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
		if(gl!=null)
			DesenharLab(g);
		else{ 
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(),this.getHeight());
			g.setColor(Color.black);
			g.drawChars(mensagem.toCharArray(), 0, mensagem.toCharArray().length, 200, 200);
		}
		
	} 
	
	private void DesenharHeroi(Graphics g, int x, int y){
		x*=imgSize;
		y*=imgSize; 
		g.drawImage(heroImg, x, y, x+imgSize,y+ imgSize, 0, 0, heroImg.getWidth(),heroImg.getHeight(), null);
	}
	
	private void DesenharDragao(Graphics g, int x, int y){
		g.setColor(Color.red);
		g.fillOval(x*imgSize, y*imgSize, imgSize, imgSize);
		
	}
	
	private void DesenharEspada(Graphics g, int x, int y){
		g.setColor(Color.yellow);
		g.fillOval(x*imgSize, y*imgSize, imgSize, imgSize);
		
	}
	
	private void DesenharLab(Graphics g){
		char[][] lab = gl.getLabirinto().getLabirinto();
		for(int y = 0; y < lab.length; y++){
			for (int x = 0; x < lab[0].length; x++) {
				int x1 = x*imgSize;
				int y1 = y*imgSize;
				
				int x2 = 5*imgSize;
				int y2 = imgSize;
				   
				if(lab[y][x] != 'X'){
					g.drawImage(labImg, x1, y1, x1+imgSize, y1+imgSize, x2, y2, x2+ imgSize,y2+ imgSize, null);
					char ch = lab[y][x];
					if(ch == 'H' || ch == 'A'){ 
						DesenharHeroi(g,x,y);
					}else if(ch == 'D' || ch == 'd' || ch == 'F'){ 
						DesenharDragao(g, x, y);
					}else if (ch == 'E')
						DesenharEspada(g, x, y);
					
					continue;
				}
				
				byte vizinhos  = procurarVizinnhos(x, y);
				
				switch(vizinhos){
				case 0:
					x2 = 5;
					y2 = 0; 
					break;
				case 1:
					x2 = 4;
					y2 = 2;
					break;
				case 2:
					x2 = 4;
					y2 = 0;
					break;
				case 3:
					x2 = 4;
					y2 = 1;
					break;
				case 4:
					x2 = 3;
					y2 = 0;
					break;
				case 5:
					x2 = 0;
					y2 = 0;
					break;
				case 6:
					x2 = 2;
					y2 = 0;
					break;
				case 7:
					x2 = 1;
					y2 = 0;
					break;
				case 8:
					x2 = 3;
					y2 = 2;
					break;
				case 9:
					x2 = 0;
					y2 = 2;
					break;
				case 10:
					x2 = 2;
					y2 = 2;
					break;
				case 11:
					x2 = 1;
					y2 = 2;
					break;
				case 12:
					x2 = 3;
					y2 = 1;
					break;
				case 13:
					x2 = 0;
					y2 = 1;
					break;
				case 14:
					x2 = 2;
					y2 = 1;
					break;
				case 15:
					x2 = 1;
					y2 = 1;
					break;
				}
				
				
				x2*=imgSize;
				y2*=imgSize;
				
				g.drawImage(labImg, x1, y1, x1+imgSize, y1+imgSize, x2, y2, x2+ imgSize,y2+ imgSize, null);
				
			}
		}
		repaint();
	}
	
	private byte procurarVizinnhos(int x, int y){
		char[][] lab = gl.getLabirinto().getLabirinto();
		byte res = 0;
		
		int x1 = x+1;
		int x2 = x-1;
		int y1 = y+1;
		int y2 = y-1;
		
		if(x1 < lab[0].length)
			if(lab[y][x1] == 'X')
				res += 1; //Direita
		
		if(x2 >= 0)
			if(lab[y][x2] == 'X')
				res += 2; //Esquerda
		
		if(y1 < lab.length)
			if(lab[y1][x] == 'X')
				res += 4; //Baixo
		
		if(y2 >= 0)
			if(lab[y2][x] == 'X')
				res += 8; //Cima
		
		return res;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(gl == null) return;
		boolean update = false;
		
		switch(e.getKeyCode()){
		
		case KeyEvent.VK_W:
			update = gl.moverHeroi(0, -1);
			break;
			
		case KeyEvent.VK_A:
			update = gl.moverHeroi(-1, 0);
			break;
			
		case KeyEvent.VK_S:
			update = gl.moverHeroi(0, 1);
			break;
			
		case KeyEvent.VK_D:
			update = gl.moverHeroi(1, 0);
			break;
		} 
		
		if(update){
			gl.atualizarDragoes();
			GameLogic.Estado estado;
			estado = gl.update();
			
			switch (estado) {
			case Jogar:
				
				break;
			case Vitoria:
				mensagem ="Parabens! Ganhaste";
				gl = null;
				break;
			case Morto:
				mensagem ="Foste Morto pelo Dragao! :(";
				gl = null;
				break;
			}
			
			repaint(); 
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON1)
			this.requestFocus();
		
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

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
