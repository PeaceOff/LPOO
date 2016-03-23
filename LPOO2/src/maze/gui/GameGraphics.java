/**
 * 
 */
package maze.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.GameLogic;

/**
 * @author Joao
 *
 */
public class GameGraphics extends JPanel implements KeyListener {
	
	BufferedImage labImg;
	BufferedImage heroImg;
	BufferedImage espadaImg;
	GameLogic gl;

	final int imgSize = 50;
	String mensagem;
	public GameGraphics(GameLogic gl){
		this.addKeyListener(this); 
		this.gl = gl;
		try{
			labImg = ImageIO.read(new File("lab.jpg"));
			heroImg = ImageIO.read(new File("hero.png"));
			espadaImg = ImageIO.read(new File("espada.png"));
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
		super.paintComponents(g);
		if(gl!=null)
			DrawingTools.DesenharLab(g,labImg,heroImg,espadaImg,gl.getLabirinto());
		else{ 
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(),this.getHeight());
			g.setColor(Color.black);
			g.drawChars(mensagem.toCharArray(), 0, mensagem.toCharArray().length, (this.getWidth()/2) - ((mensagem.length() / 2) * 4), this.getHeight()/2);
		}
		
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
				mensagem ="Parabens! Ganhaste!";
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
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
