package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.Dragao;
import maze.logic.GameLogic;

public class TestarMover {

	char [][] labirinto = {	{'X', 'X', 'X', 'X', 'X'},
							{'X', ' ', ' ', 'H', 'S'},
							{'X', ' ', 'X', 'X', 'X'},
							{'X', ' ', 'E', 'D', 'X'},
							{'X', 'X', 'X', 'X', 'X'}};
					
	@Test
	public void testDragraoMove() {
		GameLogic gl = new GameLogic(labirinto, Dragao.Estado.Move);
		
		while(gl.isDragaoEm(3, 3,0))
			gl.atualizarDragoes();
		
		assertEquals(true,(gl.isDragaoEm(2,3,0)));
		gl.update();
		assertEquals('F',gl.getLabirinto().obterSimb(2, 3));
	}
	

}
