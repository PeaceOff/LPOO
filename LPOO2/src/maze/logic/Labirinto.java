package maze.logic;

public class Labirinto {

	char[][] labirinto;

	public char[][] getLabirinto() {
		return labirinto;
	}

	public void setLabirinto(char[][] labirinto) {
		this.labirinto = labirinto;
	}

	public void desenharSimb(int x, int y, char s){
		if(x < 0 || x >= labirinto[0].length || y < 0 || y >= labirinto.length)
			return;
		labirinto[y][x] = s;
	}
	
	public char obterSimb(int x, int y){
		if(x < 0 || x >= labirinto[0].length || y < 0 || y >= labirinto.length)
			return '?';
		return labirinto[y][x];
	}
	
	public String toString(){
		String res = new String();
		for (int y = 0; y < labirinto.length; y++) {
			for (int x = 0; x < labirinto[0].length; x++) {
				res += labirinto[y][x] + " ";
			}
			res += '\n';
		}
		return res;
	}

}
