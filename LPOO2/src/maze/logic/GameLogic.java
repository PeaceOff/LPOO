package maze.logic;
import java.util.Random;

public class GameLogic {
	public static enum Estado{
		Jogar,
		Morto,
		Vitoria
	}
	private Heroi h;
	private Espada e;
	private Dragao d;
	private Labirinto l = new Labirinto();
	private Entidade s;
	
	public Labirinto getLabirinto(){
		return l;
	}
	
	public boolean isHeroiArmado(){
		return this.h.isArmado();
	}
	
	public boolean isDragaoEm(int x, int y){
		return (d.getPosicaoX() == x && d.getPosicaoY() == y);
	}
	
	public boolean isDragaoAcordado(){
		return this.d.isAcordado();
	}
	
	public void adormecerDragao(){
		this.d.setDescanso(5);
	}
	
	public boolean isDragaoMorto(){
		return this.d.isMorto();
	}
	
	public GameLogic(char[][] lab,Dragao.Estado est){
		l.setLabirinto(lab);
		for (int y = 0; y < lab.length; y++) {
			for (int x = 0; x < lab[0].length; x++) {
				switch(lab[y][x]){
				case 'E':
					e = new Espada(x,y);
					break;
				case 'D':
					d = new Dragao(x,y,est);
					break;
				case 'H':
					h = new Heroi(x,y);
					break;
				case 'S':
					s = new Entidade(x,y,'S');
					break;
				}
			}
		}
	}
	
	public Estado update(){
		
		apagarEntidades();
		if(Entidade.distancia(h, d) <= 1.0){
			if(h.isArmado()){
				d.setMorto(true);
			} else if (d.isAcordado()){
				return Estado.Morto;
			}	
		}
		
		desenharEntidades();
		
		if(e.equals(d) && e.isVisivel()){
			l.desenharSimb(e.getPosicaoX(), e.getPosicaoY(), 'F');
		}
		if(!d.isMorto()){
			l.desenharSimb(s.getPosicaoX(), s.getPosicaoY(), 'X');
		} else {
			l.desenharSimb(s.getPosicaoX(), s.getPosicaoY(), 'S');
		}		
		
		if(h.equals(s)){
			return Estado.Vitoria;
		}
		
		
		
		return Estado.Jogar;
	}
	
	public void atualizarDragao(){
		if (d.getEst() == Dragao.Estado.Parado) return;
		
		Random rand = new Random();
		
		if (d.getEst() == Dragao.Estado.Dorme){
			
			if(!d.isAcordado()){
				d.dormir();
				return;
			} else {
			
				int h = rand.nextInt(4);
				if(h == 1){
					d.setDescanso(rand.nextInt(5) + 20);
					return;
				}	
			}
			
		}
		
		l.desenharSimb(d.getPosicaoX(), d.getPosicaoY(), ' ');
		int xF, yF, h;
		h = rand.nextInt(2);
		if(h == 0){
			xF = d.getPosicaoX() + rand.nextInt(3) - 1;
			yF = d.getPosicaoY();
		} else {
			xF = d.getPosicaoX();
			yF = d.getPosicaoY() + rand.nextInt(3) - 1;
		}
		
		if(l.obterSimb(xF,yF) != ' ' && l.obterSimb(xF, yF) != 'E') return;
		
		d.setPosicaoX(xF);
		d.setPosicaoY(yF);
	}
	
	public void desenharEntidades(){
		l.desenharSimb(h.getPosicaoX(), h.getPosicaoY(), h.getSimbolo());
		if(e.isVisivel())
			l.desenharSimb(e.getPosicaoX(), e.getPosicaoY(), e.getSimbolo());
		if(!d.isMorto())
			l.desenharSimb(d.getPosicaoX(), d.getPosicaoY(), d.getSimbolo());
	}
	
	public void apagarEntidades(){
		l.desenharSimb(h.getPosicaoX(), h.getPosicaoY(), ' ');
		l.desenharSimb(e.getPosicaoX(), e.getPosicaoY(), ' ');
		l.desenharSimb(d.getPosicaoX(), d.getPosicaoY(), ' ');
	}
	
	public boolean moverHeroi(int x, int y){
		l.desenharSimb(h.getPosicaoX(), h.getPosicaoY(), ' ');
		int xF, yF;
		xF = x + h.getPosicaoX();
		yF = y + h.getPosicaoY();
		
		if(xF == e.getPosicaoX() && yF == e.getPosicaoY()){
			h.setArmado(true);
			e.setVisivel(false);
		} else if (l.obterSimb(xF,yF) == 'X' || (xF == d.getPosicaoX() && yF == d.getPosicaoY() && !d.isMorto())) return false;
		
		h.setPosicaoX(xF);
		h.setPosicaoY(yF);
		return true;
	}
	
}
