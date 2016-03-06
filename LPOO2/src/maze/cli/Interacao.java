package maze.cli;
import java.util.Scanner;

import maze.logic.Dragao;
import maze.logic.GameLogic;
import maze.logic.MazeBuilder;

public class Interacao {

	static char[][] labirinto = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X','D','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X','E','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}};

	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		MazeBuilder mb = new MazeBuilder();
		
		
		
		Dragao.Estado estado = Dragao.Estado.Parado;
		System.out.println("Como deseja o seu dragao?[Dormir(d),Parado(p),Mover(m)]");
		switch(s.nextLine()){
		case "d":
			estado = Dragao.Estado.Dorme;
			break;
		case "p":
			estado = Dragao.Estado.Parado;
			break;
		case "m":
			estado = Dragao.Estado.Move;
			break;
		}
		GameLogic gl = new GameLogic(mb.buildMaze(29),estado); 
		GameLogic.Estado est = GameLogic.Estado.Jogar;
		
		
		gl.update();
		System.out.println(gl.getLabirinto());
		
		while(est == GameLogic.Estado.Jogar){
			gl.atualizarDragao();
			String tecla = s.nextLine();
			switch(tecla){
			case "w":
				gl.moverHeroi(0,-1);
				break;
			case "a":
				gl.moverHeroi(-1,0);
				break;
			case "s":
				gl.moverHeroi(0,1);
				break;
			case "d":
				gl.moverHeroi(1,0);
				break;
			}
			
			est = gl.update();
			System.out.println(gl.getLabirinto());
		}
		
		gl.update();
		System.out.println(gl.getLabirinto());
		switch(est){ 
		case Morto:
			System.out.println("Morreste! Ganda nabo!");
			break;
		case Vitoria:
			System.out.println("Ganhaste! Ganda nabo!");
			break;
			default:
				break;
		}
		s.close();
	}

}
