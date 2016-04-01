package maze.logic;

/**
 * <h1>Labirinto</h1>
 * 
 * This class is responsible to handle the labyrinth and make changes to it.
 * 
 *  @author David and João
 *
 */
public class Labirinto {

	char[][] labirinto;
	/**
	 * 
	 * Get method for the variable labirinto.
	 * @return The labyrinth stored on the class.
	 */
	public char[][] getLabirinto() {
		return labirinto;
	}
	/**
	 * Set method for the variable labirinto
	 * @param labirinto The new labyrinth to store.
	 */
	public void setLabirinto(char[][] labirinto) {
		this.labirinto = labirinto;
	}

	/**
	 * Method to set a specific symbol in a given position on the stored labyrinth.
	 * @param x the x coord based on the labyrinth
	 * @param y the y coord based on the labyrinth
	 * @param s the symbol to set
	 */
	public void desenharSimb(int x, int y, char s){
		if(x < 0 || x >= labirinto[0].length || y < 0 || y >= labirinto.length)
			return;
		labirinto[y][x] = s;
	}
	
	/**
	 * Get method of the symbol of the stored labyrinth in a given position
	 * @param x the x coord based on the labyrinth
	 * @param y the y coord based on the labyrinth
	 * @return The symbol in that given position
	 */
	public char obterSimb(int x, int y){
		if(x < 0 || x >= labirinto[0].length || y < 0 || y >= labirinto.length)
			return '?';
		return labirinto[y][x];
	}
	
	@Override
	public String toString(){
		String res = new String();
		for (int y = 0; y < labirinto.length; y++) {
			for (int x = 0; x < labirinto[0].length; x++) {
				res += labirinto[y][x] + " ";
			}
			res += '\n';
		}
		return res;
	}

}
