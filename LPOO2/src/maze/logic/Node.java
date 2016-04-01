package maze.logic;


/**
* <h1>Node</h1>
* The  Node is a subdivision of the map.
* <p>
* It is used on the labyrinth build to facilitate the search of the position related to the map.
*
* @author  David and João
*/
public class Node {
	

	private int x;
	private int y;
	private boolean validado;
	
	/**
	 * Constructor for the class Node
	 * @param x the x coordinate of the node on the map
	 * @param y the y coordinate of the node on the map
	 * @param validado boolean that stores the state of validation of the node.
	 */
	public Node(int x, int y, boolean validado){
		this.x = x;
		this.y = y;
		this.validado = validado;
	}
	
	/**
	 * Get method for the x attribute.
	 * @return The value of the x coordinate of the node.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get method for the y attribute.
	 * @return The value of the y coordinate of the node.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Get value of the variable validado.
	 * @return If the node was validated or not.
	 */
	public boolean isValidado() {
		return validado;
	}
	/**
	 * Set method for the variable validado. 
	 * @param validado Value of the new validation state.
	 */
	public void setValidado(boolean validado) {
		this.validado = validado;
	}

}
