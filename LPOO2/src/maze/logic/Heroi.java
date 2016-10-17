package maze.logic;

/**
 * <h1>Heroi</H1>
 * 
 * Class that handles the object hero.
 * 
 *  @author David and João
 *
 */
public class Heroi extends Entidade {

	private boolean armado = false;
	
	/**
	 * Get method for the variable armado
	 * @return If the hero is armed or not.
	 */
	public boolean isArmado() {
		return armado;
	}
	/**
	 * Set method for the variable armado
	 * @param armado the new value of the variable.
	 */
	public void setArmado(boolean armado) {
		this.setSimbolo('A');
		this.armado = armado;
	}
	
	/**
	 * Constructor of the class Hero.
	 * @param x the position on the x coordinate.
	 * @param y the position on the y coordinate.
	 */
	public Heroi(int x, int y) {
		super(x, y,'H');
	}
	
}
