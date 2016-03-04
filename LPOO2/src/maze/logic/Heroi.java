package maze.logic;

public class Heroi extends Entidade {

	private boolean armado = false;
	
	public boolean isArmado() {
		return armado;
	}

	public void setArmado(boolean armado) {
		this.setSimbolo('A');
		this.armado = armado;
	}

	public Heroi(int x, int y) {
		super(x, y,'H');
	}
	
}
