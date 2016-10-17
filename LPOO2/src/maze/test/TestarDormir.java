package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.Dragao;
import maze.logic.GameLogic;

public class TestarDormir {

	char [][] labirinto = {	{'X', 'X', 'X', 'X', 'X'},
							{'X', ' ', ' ', 'H', 'S'},
							{'X', ' ', 'X', ' ', 'X'},
							{'X', 'E', ' ', 'D', 'X'},
							{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testDragaoDormir() {
		GameLogic gl = new GameLogic (labirinto,Dragao.Estado.Dorme);
		while(gl.isDragaoAcordado(0)){
			gl.atualizarDragoes();
		}
		gl.atualizarDragoes();
		assertEquals(false,gl.isDragaoAcordado(0));
	}
	
	@Test
	public void testDragaoDormirJogadorAoLado(){
		GameLogic gl = new GameLogic (labirinto,Dragao.Estado.Dorme);
		gl.update();
		gl.adormecerDragao(0);
		gl.moverHeroi(0, 1);
		assertEquals(GameLogic.Estado.Jogar,gl.update());
	}

}
