package maze.logic;

import java.util.ArrayList;
import java.util.Random;

import com.sun.javafx.collections.MappingChange.Map;

import javafx.util.Pair;

public class MazeBuilder implements IMazeBuilder {
	
	Node[][] nodesArray ;
	char[][] mapa;
	
	@Override
	public char[][] buildMaze(int size) throws IllegalArgumentException {
		if(size % 2 == 0)
			throw new IllegalArgumentException();
		
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
		//Posicao Dragao
		nEscolhido =visitados.get(rnd.nextInt(visitados.size()));
		DefinirChar(nEscolhido, 'D');
		
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
	
	void CriarCaminho(Node p1, Node p2){
		
		int p1x = p1.getX()*2 + 1;
		int p2x = p2.getX()*2 + 1;
		
		int x = p1x + (p2x-p1x)/2;

		int p1y = p1.getY()*2 +1;
		int p2y = p2.getY()*2 +1;
		
		int y = p1y + (p2y-p1y)/2;
		
		mapa[y][x]= ' ';
	}
	
	void DefinirChar(Node a, char ch){
		int x = a.getX() * 2 + 1;
		int y = a.getY() * 2 + 1; 
		mapa[y][x] = ch;
		
	}
	
	ArrayList<Node> obterVizinhos(Node n){
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