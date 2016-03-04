package maze.logic;

public class Espada extends Entidade {

	private boolean visivel = true;
	
	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

	public Espada(int x, int y) {
		super(x, y, 'E');
		
	}
	
}
