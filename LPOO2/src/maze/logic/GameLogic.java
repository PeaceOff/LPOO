package maze.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * <h1> GameLogic </h1>
 * Handles all the logic of the game.
 *  @author David and João
 *
 */
public class GameLogic {
	
	/**
	 * Represents the different game states.
	 */
	public static enum Estado {
		Jogar, Morto, Vitoria
	}

	private Heroi h;
	private Espada e = null;
	private ArrayList<Dragao> dragoes = new ArrayList<Dragao>();
	private Labirinto l = new Labirinto();
	private Entidade s = null;

	/**
	 * Get method for the l attribute.
	 * @return the maze stored.
	 */
	public Labirinto getLabirinto() {
		return l; 
	}
	
	/**
	 * Get method for the l attribute.
	 * @return the maze stored in a string.
	 */
	public String getLab(){
		l.desenharSimb(s.getPosicaoX(), s.getPosicaoY(), s.getSimbolo());
		return l.toString();
	}
	
	/**
	 * Check if the hero is armed.
	 * @return boolean armado relative to the hero.
	 */
	public boolean isHeroiArmado() {
		return this.h.isArmado();
	}

	/**
	 * Function to check if a given dragon is on a given position of the map.
	 * @param x the x coordinate to check.
	 * @param y the y coordinate to check.
	 * @param index the index to check.
	 * @return A boolean indicating if the dragon is or not there.
	 */
	public boolean isDragaoEm(int x, int y, int index) {
		return (dragoes.get(index).getPosicaoX() == x && dragoes.get(index).getPosicaoY() == y);
	}

	/**
	 * Checks if any of the dragons is on the same position as the sword.
	 * @return A boolean indicating the result.
	 */
	public boolean DragaoNaEspada() {
		for (int i = 0; i < dragoes.size(); i++)
			if (isDragaoEm(e.getPosicaoX(), e.getPosicaoY(), i))
				return true;
		return false;
	}

	/**
	 * Checks if a given dragon is awake.
	 * @param index the dragon to check.
	 * @return the result of the verification.
	 */
	public boolean isDragaoAcordado(int index) {
		return this.dragoes.get(index).isAcordado();
	}

	/**
	 * Function to put a dragon to sleep.
	 * @param index of the dragon that is going to sleep.
	 */
	public void adormecerDragao(int index) {
		this.dragoes.get(index).setDescanso(5);
	}

	/**
	 * Checks if a given dragon is dead.
	 * @param index of the dragon to check.
	 * @return result of the verification.
	 */
	public boolean isDragaoMorto(int index) {
		return this.dragoes.get(index).isMorto();
	}

	/**
	 * Checks if all the dragons are dead.
	 * @return result of the verification.
	 */
	public boolean DragoesMortos() {
		for (int i = 0; i < dragoes.size(); i++)
			if (!dragoes.get(i).isMorto())
				return false;
 
		return true;
	}

	/**
	 * Constructor of the class GameLogic
	 * @param lab the corresponding maze of the GameLogic.
	 * @param est state of the dragons.
	 */
	public GameLogic(char[][] lab, Dragao.Estado est) {
		l.setLabirinto(lab);
		for (int y = 0; y < lab.length; y++) {
			for (int x = 0; x < lab[0].length; x++) {
				switch (lab[y][x]) {
				case 'E':
					e = new Espada(x, y);
					break;
				case 'D':
					dragoes.add(new Dragao(x, y, est));
					break;
				case 'H':
					h = new Heroi(x, y);
					break;
				case 'S':
					s = new Entidade(x, y, 'S');
					break;
				case 'd':
					dragoes.add(new Dragao(x,y, est));
					break;
				case 'F':
					e = new Espada(x,y);
					dragoes.add(new Dragao(x,y,est));
					break;
				case 'A':
					h = new Heroi(x,y);
					h.setArmado(true);
					break;
				}
			}
		}
	}

	/**
	 * Function to check is any dragon is besides the player.
	 * @return result of the verification.
	 */
	public boolean DragaoAoLadoJogador() {
		for (Dragao d : dragoes) {
			if (d.isMorto())
				continue;
			if (Entidade.distancia(h, d) <= 1.0) {
				if (h.isArmado()) {
					d.setMorto(true);
					l.desenharSimb(d.getPosicaoX(), d.getPosicaoY(), ' ');
				} else if (d.isAcordado()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Function that updates the GameLogic
	 * @return the current state of the game.
	 */
	public Estado update() {

		if (DragaoAoLadoJogador())
			return Estado.Morto;

		desenharEntidades();
		if(e!=null)
		if (DragaoNaEspada() && e.isVisivel()) {
			l.desenharSimb(e.getPosicaoX(), e.getPosicaoY(), 'F');
		}
		if(s!=null)
			if (!DragoesMortos()) {
				l.desenharSimb(s.getPosicaoX(), s.getPosicaoY(), 'X');
			} else {
				l.desenharSimb(s.getPosicaoX(), s.getPosicaoY(), 'S');
			}
		if(s!=null)
			if (h.equals(s)) {
				return Estado.Vitoria;
			}

		return Estado.Jogar;
	}

	/**
	 * Function the update all of the dragons of the game.
	 */
	public void atualizarDragoes() {
		for (Dragao d : dragoes)
			atualizarDragao(d);
	}
	

	private void atualizarDragao(Dragao d) {
		if (d.getEst() == Dragao.Estado.Parado)
			return;

		Random rand = new Random();

		if (d.getEst() == Dragao.Estado.Dorme) {

			if (!d.isAcordado()) {
				d.dormir();
				return;
			} else {

				int h = rand.nextInt(4);
				if (h == 1) {
					d.setDescanso(rand.nextInt(5) + 2);
					return;
				}
			}

		}

		int xF, yF, h;
		h = rand.nextInt(2);
		if (h == 0) {
			xF = d.getPosicaoX() + rand.nextInt(3) - 1;
			yF = d.getPosicaoY();
		} else {
			xF = d.getPosicaoX();
			yF = d.getPosicaoY() + rand.nextInt(3) - 1;
		}

		if (l.obterSimb(xF, yF) != ' ' && l.obterSimb(xF, yF) != 'E')
			return;
		
		l.desenharSimb(d.getPosicaoX(), d.getPosicaoY(), ' ');
		d.setPosicaoX(xF);
		d.setPosicaoY(yF);
	}

	
	private void desenharEntidades() {
		l.desenharSimb(h.getPosicaoX(), h.getPosicaoY(), h.getSimbolo());
		if(e != null)
			if (e.isVisivel())
				l.desenharSimb(e.getPosicaoX(), e.getPosicaoY(), e.getSimbolo());
		for (int i = 0; i < dragoes.size(); i++)
			if (!dragoes.get(i).isMorto())
				l.desenharSimb(dragoes.get(i).getPosicaoX(), dragoes.get(i).getPosicaoY(), dragoes.get(i).getSimbolo());
	}

	/**
	 * Function to move a hero to a new position.
	 * @param x the amount to move on the x position
	 * @param y the amount to move on the y position
	 * @return true if the hero moved.
	 */
	public boolean moverHeroi(int x, int y) {
		int xF, yF;
		xF = x + h.getPosicaoX();
		yF = y + h.getPosicaoY();

		if (l.obterSimb(xF, yF) == 'X' || l.obterSimb(xF, yF) == 'D' || l.obterSimb(xF, yF) == 'd'
				|| l.obterSimb(xF, yF) == 'F'){	
			return false;
		}
		
		if(e!=null)
			if (xF == e.getPosicaoX() && yF == e.getPosicaoY()) {
				h.setArmado(true);
				e.setVisivel(false);
			} 
		
		l.desenharSimb(h.getPosicaoX(), h.getPosicaoY(), ' ');
		h.setPosicaoX(xF);
		h.setPosicaoY(yF);
		return true;
	}
	
	

}
