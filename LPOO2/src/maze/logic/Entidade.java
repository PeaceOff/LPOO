package maze.logic;

/**<h1>Entidade</h1>
 * Class that handles the position and the symbol of the entity.
 * @author David and João
 *
 */
public class Entidade {
	private int posicaoX;
	private int posicaoY;
	private char simbolo;
	
	/**
	 * Constructor of the class.
	 * @param x the value of the x coordinate.
	 * @param y the value of the y coordinate.
	 * @param s the symbol of the entity.
	 */
	public Entidade(int x, int y, char s){
		posicaoX = x;
		posicaoY = y;
		simbolo = s;
	}
	/**
	 * Get method for the Entity's position.
	 * @return the x coordinate.
	 */
	public int getPosicaoX() {
		return posicaoX;
	}
	/**
	 * Set method for the Entity's position. 
	 * @param posicaoX value of the x coordinate.
	 */
	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}
	/**
	 * Get method for the Entity's position.
	 * @return the y coordinate.
	 */
	public int getPosicaoY() {
		return posicaoY;
	}

	/**
	 * Set method for the Entity's position. 
	 * @param posicaoY value of the y coordinate.
	 */
	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}
	
	/**
	 * Get method for the Entity's symbol.
	 * @return the symbol of the entity.
	 */
	public char getSimbolo() {
		return simbolo;
	}
	
	/**
	 * Set method for the Entity's symbol.
	 * @param simbolo the new value to set.
	 */
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	
	@Override
	public boolean equals(Object e){
		Entidade ent = (Entidade)e;
		if(ent == null) return false;
		
		return (posicaoX == ent.getPosicaoX() && posicaoY == ent.getPosicaoY());
	}
	
	/**
	 * Method to calculate the distance between two entities.
	 * @param e1 the first entity to compare.
	 * @param e2 the second entity to compare.
	 * @return the distance in double between the two entities.
	 */
	public static double distancia(Entidade e1,Entidade e2){
		double dx = e1.getPosicaoX() - e2.getPosicaoX();
		double dy = e1.getPosicaoY() - e2.getPosicaoY();
		double d = Math.sqrt(dx*dx + dy*dy);
		return d;
	}

}
