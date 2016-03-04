package maze.logic;

public class Entidade {
	private int posicaoX;
	private int posicaoY;
	private char simbolo;
	
	public Entidade(int x, int y, char s){
		posicaoX = x;
		posicaoY = y;
		simbolo = s;
	}
	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}

	public void Update(){
		
	}
	
	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

	public boolean equals(Object e){
		Entidade ent = (Entidade)e;
		if(ent == null) return false;
		
		return (posicaoX == ent.getPosicaoX() && posicaoY == ent.getPosicaoY());
	}
	
	public static double distancia(Entidade e1,Entidade e2){
		double dx = e1.getPosicaoX() - e2.getPosicaoX();
		double dy = e1.getPosicaoY() - e2.getPosicaoY();
		double d = Math.sqrt(dx*dx + dy*dy);
		return d;
	}

}
