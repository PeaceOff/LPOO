package maze.logic;

public class Node {
	

	private int x;
	private int y;
	private boolean validado;
	
	public Node(int x, int y, boolean validado){
		this.x = x;
		this.y = y;
		this.validado = validado;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isValidado() {
		return validado;
	}

	public void setValidado(boolean validado) {
		this.validado = validado;
	}

}
