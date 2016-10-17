package maze.logic;

/**<h1>Dragao</h1>
 * Class responsible for the management of the game's dragons.
 * 
 * @author David and João
 *
 */
public class Dragao extends Entidade {

	/**
	 * Represents the possible states of the dragons.
	 */
	public static enum Estado{
		Parado,
		Move,
		Dorme
	}
	
	private boolean morto = false;
	private Estado est = Estado.Parado;
	private int descanso = 0;
	
	/**
	 * Constructor of the class Dragao.
	 * @param x the x coordinate of the dragon.
	 * @param y the y coordinate of the dragon.
	 * @param e the state of the dragon.
	 */
	public Dragao(int x, int y, Estado e) {
		super(x, y,'D');
		est = e;
		
	}
	
	/**
	 * Check if the dragon is awake.
	 * @return true is not asleep.
	 */
	public boolean isAcordado(){
		return (descanso == 0);
	}

	/**
	 * Set the dragon to rest.
	 * @param descanso number of rounds the dragon will sleep.
	 */
	public void setDescanso(int descanso) {
		this.descanso = descanso;
	}

	/**
	 * Check if the dragon is dead.
	 * @return true if dead.
	 */
	public boolean isMorto() {
		return morto;
	}

	/**
	 * Set method for the morto attribute.
	 * @param morto new value of the morto variable.
	 */
	public void setMorto(boolean morto) {
		this.morto = morto;
	}

	@Override
	public char getSimbolo(){
		if(isAcordado()) return super.getSimbolo();
		return Character.toLowerCase(super.getSimbolo());
	}
	
	/**
	 * Function to reduce the number of rounds the dragon will be asleep for.
	 */
	public void dormir(){
		if(descanso > 0)
			descanso--;
	}

	/**
	 * Get method for the state of the dragon.
	 * @return state of the dragon.
	 */
	public Estado getEst() {
		return est;
	}
}
