package maze.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * <h1>MazeBuilder</h1>
 * MazeBuilder is the class that generates a new maze based on a given odd dimension (bigger than 4)
 * @author David and João
 *
 */
public class MazeBuilder implements IMazeBuilder {
	
	private Node[][] nodesArray ;
	private char[][] mapa;
	private int numDragoes = 1;
	
	
	/**
	 * Get method for the numDragoes attribute
	 * @return the number of Dragons
	 */
	public int getNumdragoes() {
		return numDragoes;
	}
	
	/**
	 * Set method for the numDragoes attribute
	 * @param numdragoes the new number of dragons to be set
	 */
	public void setNumdragoes(int numdragoes) {
		this.numDragoes = numdragoes;
	}

	
	@Override
	public char[][] buildMaze(int size) throws IllegalArgumentException {
		
		int nDragoes = (int)(size * 0.3);
		
		if(size < 5)
			throw new IllegalArgumentException("O numero tem de ser maior ou igual a 5!");
		
		if(size % 2 == 0)
			throw new IllegalArgumentException("O numero tem de ser impar!");
		
		if(numDragoes > nDragoes)
			throw new IllegalArgumentException("O numero de dragoes e superior a " + nDragoes + ".");
		
		
		int dimensao = (size-1)/2;
		nodesArray = new Node[dimensao][dimensao];
		ArrayList<Node> visitados = new ArrayList<Node>();
		ArrayList<Node> porVisitar = new ArrayList<Node>();
		
		mapa = new char[size][size];
		
		for(int y = 0; y < size; y ++){
			for(int x = 0; x < size; x++){
				
				if((x-1)%2 ==0 && (y-1)%2 == 0){
					mapa[y][x] = ' '; 
				}else{
					mapa[y][x] = 'X';
				}
				if(x >= dimensao || y >= dimensao ) continue;
				
				nodesArray[y][x]=new Node(x,y , false);
				porVisitar.add(nodesArray[y][x]);
			}
		}
		
		Random rnd = new Random();
		
		while(porVisitar.size() > 0){
			Node atual = null;
			
			if(visitados.size() == 0){
				atual = porVisitar.get(0);
			}else{
				
				for(Node n : visitados){
					
					ArrayList<Node> vizinhos = obterVizinhos(n);
					
					if(vizinhos.size() >0){
						atual = vizinhos.get(rnd.nextInt(vizinhos.size()));
						CriarCaminho(n, atual); 
						break;
					}
				}
				
			}
			
			visitados.add(atual);
			atual.setValidado(true);
			porVisitar.remove(atual);
			
			
			while(true){ //Enquanto tiver vizinhos disponiveis
				ArrayList<Node> viz = obterVizinhos(atual);
				if(viz.size()==0)break;
				
				Node proximo = viz.get( rnd.nextInt(viz.size()) );
				
				CriarCaminho(atual, proximo);
				
				visitados.add(proximo);
				porVisitar.remove(proximo);
				proximo.setValidado(true);
				atual = proximo;
			}
		}
		
		//Posicao Heroi
		Node nEscolhido =visitados.get(rnd.nextInt(visitados.size()));
		DefinirChar(nEscolhido, 'H');
		visitados.remove(nEscolhido);
		//Posicao Espada
		nEscolhido =visitados.get(rnd.nextInt(visitados.size()));
		DefinirChar(nEscolhido, 'E');
		visitados.remove(nEscolhido);
		
		
		//Posicao Dragoes		
		for (int i = 0; i < numDragoes; i++) {
			nEscolhido =visitados.get(rnd.nextInt(visitados.size()));
			DefinirChar(nEscolhido, 'D');
			visitados.remove(nEscolhido);
		}
		
		
		int pos = rnd.nextInt(size-2) + 1;
		
		switch(rnd.nextInt(4)){
		case 0:
			mapa[0][pos] = 'S';
			if(mapa[1][pos]=='X')
				mapa[1][pos] = ' ';
			break;
		case 1:
			mapa[size-1][pos] = 'S';
			if(mapa[size-2][pos]=='X')
				mapa[size-2][pos] = ' ';
			break;
		case 2:
			mapa[pos][0] = 'S';
			if(mapa[pos][1]=='X')
				mapa[pos][1] = ' ';
			break;
		case 3:
			mapa[pos][size-1] = 'S';
			if(mapa[pos][size-2]=='X')
				mapa[pos][size-2] = ' ';
			break;
		}
		 
		return mapa;
	}
	
	private void CriarCaminho(Node p1, Node p2){
		
		int p1x = p1.getX()*2 + 1;
		int p2x = p2.getX()*2 + 1;
		
		int x = p1x + (p2x-p1x)/2;

		int p1y = p1.getY()*2 +1;
		int p2y = p2.getY()*2 +1;
		
		int y = p1y + (p2y-p1y)/2;
		
		mapa[y][x]= ' ';
	}
	
	private void DefinirChar(Node a, char ch){
		int x = a.getX() * 2 + 1;
		int y = a.getY() * 2 + 1; 
		mapa[y][x] = ch;
		
	}
	
	private ArrayList<Node> obterVizinhos(Node n){
		if(n == null) return null;
		
		ArrayList<Node> res = new ArrayList<Node>();
		
		for(int i =-1; i<2; i+=2){
		
			int nX = n.getX() + i;
			int nY = n.getY() + i;
			
			if(nX >=0 && nX < nodesArray[0].length){
				if(!nodesArray[n.getY()][nX].isValidado())
					res.add(nodesArray[n.getY()][nX]);
			}
			if(nY >=0 && nY < nodesArray.length){
				if(!nodesArray[nY][n.getX()].isValidado())
					res.add(nodesArray[nY][n.getX()]);		
			}
		}
	 
		return res;
		
	}

}
