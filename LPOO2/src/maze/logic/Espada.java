package maze.logic;

/**
 * <h1>Espada</h1>
 * Class related to the sword
 * 
 * @author David and João
 *
 */
public class Espada extends Entidade {

	private boolean visivel = true;
	
	/**
	 * Get method for the visivel attribute.
	 * @return the visivel variable.
	 */
	public boolean isVisivel() {
		return visivel;
	}

	/**
	 * Set method for the visivel attribute.
	 * @param visivel the new value of visivel.
	 */
	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	/**
	 * Constructor of the class Espada.
	 * @param x the x coordinate of the sword.
	 * @param y the y coordinate of the sword.
	 */
	public Espada(int x, int y) {
		super(x, y, 'E');
		
	}
	
}
