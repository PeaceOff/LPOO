package maze.logic;

/**
 * <h1> IMazeBuilder </h1>
 * Interface that handle the method to generate a new maze.
 *
 *  @author David and João
 *
 */
public interface IMazeBuilder {
	
	/**
	 * Method to generate a new maze.
	 * @param size the size of the maze to generate.
	 * @return returns the maze generated.
	 * @throws IllegalArgumentException In the case of an wrong value of size.
	 */
	public char[][] buildMaze(int size) throws IllegalArgumentException;
	
}
