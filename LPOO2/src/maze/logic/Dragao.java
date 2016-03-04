package maze.logic;

public class Dragao extends Entidade {

	public static enum Estado{
		Parado,
		Move,
		Dorme
	}
	private boolean morto = false;
	private Estado est = Estado.Parado;
	private int descanso = 0;
	
	public Dragao(int x, int y, Estado e) {
		super(x, y,'D');
		est = e;
		
	}
	
	public boolean isAcordado(){
		return (descanso == 0);
	}

	public int getDescanso() {
		return descanso;
	}

	public void setDescanso(int descanso) {
		this.descanso = descanso;
	}

	public boolean isMorto() {
		return morto;
	}

	public void setMorto(boolean morto) {
		this.morto = morto;
	}

	public char getSimbolo(){
		if(isAcordado()) return super.getSimbolo();
		return Character.toLowerCase(super.getSimbolo());
	}
	
	public void dormir(){
		if(descanso > 0)
			descanso--;
	}

	public Estado getEst() {
		return est;
	}

	public void setEst(Estado est) {
		this.est = est;
	}
	
}
