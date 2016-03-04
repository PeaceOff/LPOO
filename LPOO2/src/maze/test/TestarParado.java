package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.*;

public class TestarParado {

	char [][] labirinto = {	{'X', 'X', 'X', 'X', 'X'},
							{'X', ' ', ' ', 'H', 'S'},
							{'X', ' ', 'X', ' ', 'X'},
							{'X', 'E', ' ', 'D', 'X'},
							{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testHeroiMoveCelulaLivre() {
	
		GameLogic gl = new GameLogic(labirinto,Dragao.Estado.Parado);
		gl.update();
		gl.atualizarDragao();
		assertEquals(true, gl.moverHeroi(-1, 0));
		
	}
	
	@Test
	public void testHeroiMoveContraParede() {
		GameLogic gl = new GameLogic(labirinto,Dragao.Estado.Parado);
		gl.update();
		assertEquals(false, gl.moverHeroi(0, -1));	
	}
	
	@Test
	public void testHeroiApanhaEspada(){
		GameLogic gl = new GameLogic(labirinto,Dragao.Estado.Parado);
		gl.update();
		gl.moverHeroi(-2, 2);
		assertEquals(true,gl.isHeroiArmado());	
	}
	
	@Test
	public void testHeroiMorreParaDragao() {
		GameLogic gl = new GameLogic(labirinto,Dragao.Estado.Parado);
		gl.update();
		gl.moverHeroi(0, 1);
		assertEquals(GameLogic.Estado.Morto,gl.update());
	}
	
	@Test
	public void testHeroiMataDragao() {
		GameLogic gl = new GameLogic(labirinto,Dragao.Estado.Parado);
		gl.update();
		gl.moverHeroi(-2, 2);
		gl.moverHeroi(1, 0);
		gl.update();
		assertEquals(true,gl.isDragaoMorto());
	}
	
	@Test
	public void testHeroiGanha() {
		GameLogic gl = new GameLogic(labirinto,Dragao.Estado.Parado);
		gl.update();
		gl.moverHeroi(-2, 2);
		gl.moverHeroi(1, 0);
		gl.update();
		gl.moverHeroi(2, -2);
		assertEquals(GameLogic.Estado.Vitoria,gl.update());
	}
	
	@Test
	public void testHeroiSaiSemEspada(){
		GameLogic gl = new GameLogic(labirinto,Dragao.Estado.Parado);
		gl.update();
		assertEquals(false, gl.moverHeroi(1, 0));
	}
	
	@Test
	public void testHeroiArmadoTentaEscapar(){
		GameLogic gl = new GameLogic(labirinto,Dragao.Estado.Parado);
		gl.update();
		gl.moverHeroi(-2, 2);
		gl.update();
		assertEquals(false,gl.moverHeroi(3, -2));
		
	}
	
	@Test
	public void testGetLabirinto(){
		char [][] lab = {{'X','X'}};
		
		GameLogic gl = new GameLogic(lab,Dragao.Estado.Parado);
		
		assertArrayEquals(lab,gl.getLabirinto().getLabirinto());
		
		assertEquals("X X \n",gl.getLabirinto().toString());
	}
	
	@Test
	public void testGetEntidadesLabirinto(){
		GameLogic gl = new GameLogic(labirinto,Dragao.Estado.Parado);
		gl.update();
		gl.moverHeroi(-1, 0);
		gl.update();
		String s = "X X X X X \nX   H   X \nX   X   X \nX E   D X \nX X X X X \n";
		assertEquals(s,gl.getLabirinto().toString());
		gl.moverHeroi(-1, 2);
		gl.update();
		s = "X X X X X \nX       X \nX   X   X \nX A   D X \nX X X X X \n";
		assertEquals(s,gl.getLabirinto().toString());
		gl.moverHeroi(1, 0);
		gl.update();
		s = "X X X X X \nX       S \nX   X   X \nX   A   X \nX X X X X \n";
		assertEquals(s,gl.getLabirinto().toString());
	}
	
}
