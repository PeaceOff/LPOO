package maze.logic;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
	public static enum Estado {
		Jogar, Morto, Vitoria
	}

	private Heroi h;
	private Espada e;
	private ArrayList<Dragao> dragoes = new ArrayList<Dragao>();
	private Labirinto l = new Labirinto();
	private Entidade s;

	public Labirinto getLabirinto() {
		return l;
	}
	
	public boolean isHeroiArmado() {
		return this.h.isArmado();
	}

	public boolean isDragaoEm(int x, int y, int index) {
		return (dragoes.get(index).getPosicaoX() == x && dragoes.get(index).getPosicaoY() == y);
	}

	public boolean DragaoNaEspada() {
		for (int i = 0; i < dragoes.size(); i++)
			if (isDragaoEm(e.getPosicaoX(), e.getPosicaoY(), i))
				return true;
		return false;
	}

	public boolean isDragaoAcordado(int index) {
		return this.dragoes.get(index).isAcordado();
	}

	public void adormecerDragao(int index) {
		this.dragoes.get(index).setDescanso(5);
	}

	public boolean isDragaoMorto(int index) {
		return this.dragoes.get(index).isMorto();
	}

	public boolean DragoesMortos() {
		for (int i = 0; i < dragoes.size(); i++)
			if (!dragoes.get(i).isMorto())
				return false;

		return true;
	}

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
				}
			}
		}
	}

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

	public Estado update() {

		if (DragaoAoLadoJogador())
			return Estado.Morto;

		desenharEntidades();

		if (DragaoNaEspada() && e.isVisivel()) {
			l.desenharSimb(e.getPosicaoX(), e.getPosicaoY(), 'F');
		}
		if (!DragoesMortos()) {
			l.desenharSimb(s.getPosicaoX(), s.getPosicaoY(), 'X');
		} else {
			l.desenharSimb(s.getPosicaoX(), s.getPosicaoY(), 'S');
		}

		if (h.equals(s)) {
			return Estado.Vitoria;
		}

		return Estado.Jogar;
	}

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

		l.desenharSimb(d.getPosicaoX(), d.getPosicaoY(), ' ');
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

		d.setPosicaoX(xF);
		d.setPosicaoY(yF);
	}

	public void desenharEntidades() {
		l.desenharSimb(h.getPosicaoX(), h.getPosicaoY(), h.getSimbolo());
		if (e.isVisivel())
			l.desenharSimb(e.getPosicaoX(), e.getPosicaoY(), e.getSimbolo());
		for (int i = 0; i < dragoes.size(); i++)
			if (!dragoes.get(i).isMorto())
				l.desenharSimb(dragoes.get(i).getPosicaoX(), dragoes.get(i).getPosicaoY(), dragoes.get(i).getSimbolo());
	}

	public boolean moverHeroi(int x, int y) {
		l.desenharSimb(h.getPosicaoX(), h.getPosicaoY(), ' ');
		int xF, yF;
		xF = x + h.getPosicaoX();
		yF = y + h.getPosicaoY();

		if (l.obterSimb(xF, yF) == 'X' || l.obterSimb(xF, yF) == 'D' || l.obterSimb(xF, yF) == 'd'
				|| l.obterSimb(xF, yF) == 'F')
			return false;

		if (xF == e.getPosicaoX() && yF == e.getPosicaoY()) {
			h.setArmado(true);
			e.setVisivel(false);
		}

		h.setPosicaoX(xF);
		h.setPosicaoY(yF);
		return true;
	}

}
