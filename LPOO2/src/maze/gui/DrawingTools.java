package maze.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import maze.logic.GameLogic;
import maze.logic.Labirinto;

public class DrawingTools {
	
	final static int imgSize = 50;

	private static void DesenharHeroi(Graphics g, int x, int y, char simb,BufferedImage heroImg){
		x*=imgSize;
		y*=imgSize; 
		int x1 = (simb == 'A')? imgSize : 0;
		
		g.drawImage(heroImg, x, y, x+imgSize,y+ imgSize, x1, 0, x1 + imgSize,heroImg.getHeight(), null);
	}
	
	private static void DesenharDragao(Graphics g, int x, int y,char simb, BufferedImage dragaoImg){
		g.setColor(Color.red);
		x*=imgSize;
		y*=imgSize;
		int x1 = 0;
		if(simb == 'd')
			x1 = 1;
		else if(simb == 'F')
			x1 = 2;
		x1*=imgSize;
		g.drawImage(dragaoImg, x, y, x+imgSize, y+imgSize, x1, 0, x1+imgSize, dragaoImg.getHeight(), null);
		g.fillOval(x*imgSize, y*imgSize, imgSize, imgSize);
		
	}
	
	private static void DesenharImg(Graphics g, int x, int y, BufferedImage espadaImg){
		x*=imgSize;
		y*=imgSize;
		g.drawImage(espadaImg, x, y, x+imgSize,y+ imgSize, 0, 0,imgSize,espadaImg.getHeight(), null);
	}
	
	public static void DesenharLab(Graphics g, BufferedImage labImg,BufferedImage saidaImg, BufferedImage dragaoImg, BufferedImage heroImg, BufferedImage espadaImg, Labirinto gl){
		char[][] lab = gl.getLabirinto();
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
						DesenharHeroi(g,x,y, ch,heroImg);
					}else if(ch == 'D' || ch == 'd' || ch == 'F'){ 
						DesenharDragao(g, x, y,ch,dragaoImg);
					}else if (ch == 'E')
						DesenharImg(g, x, y,espadaImg);
					else if(ch == 'S')
						DesenharImg(g, x, y,saidaImg); 
					
					continue;
				}
				
				byte vizinhos  = procurarVizinnhos(x, y,gl);
				
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
	}
	
	private static byte procurarVizinnhos(int x, int y, Labirinto gl){
		char[][] lab = gl.getLabirinto();
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
}
