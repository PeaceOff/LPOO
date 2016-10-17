package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JWindow;

import maze.logic.Dragao;
import maze.logic.GameLogic;
import maze.logic.Labirinto;
import maze.logic.MazeBuilder;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class interacao {

	private JFrame frame;
	private JTextField textFDim;
	private JTextField textFNumD;
	private JButton btnTerminar;
	private JButton btnGerarNovoLabirinto;
	private JComboBox comboBox;
	private GameLogic gl;
	private GameGraphics gGraphics = new GameGraphics(null);
	private JFrame frame2 = new JFrameExtended("Happy Easter!"); 
	private JFrame frame3 = new JFrame("Editor de Labirinto");
	private JButton btnEdit;
	private LabEditor lEdit;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton heroi;
	private JButton parede;
	private JButton dragao;
	private JButton espada;
	private JButton saida;
	private JButton borracha;
	private JButton aplicar; 
	private JLabel display;
	private JButton btnCarregarMaze;
	private JButton btnGuardar;
	private boolean isJogar = false;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interacao window = new interacao();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public interacao() {
		initialize(); 
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Bem-Vindo!");
		frame.setBounds(100, 100, 465, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDimensao = new JLabel("Dimensao do Labirinto");
		lblDimensao.setBounds(10, 136, 113, 24);
		frame.getContentPane().add(lblDimensao);
		
		textFDim = new JTextField();
		textFDim.setText("11");
		textFDim.setBounds(133, 138, 86, 20);
		frame.getContentPane().add(textFDim);
		textFDim.setColumns(10);
		
		lblNewLabel = new JLabel("Numero de Dragoes");
		lblNewLabel.setBounds(10, 171, 113, 14);
		frame.getContentPane().add(lblNewLabel); 
		
		textFNumD = new JTextField();
		textFNumD.setText("1");
		textFNumD.setBounds(133, 169, 86, 20);
		frame.getContentPane().add(textFNumD);
		textFNumD.setColumns(10);
		comboBox = new JComboBox(Dragao.Estado.values());
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(133, 212, 218, 20);
		frame.getContentPane().add(comboBox);
		 
		lblNewLabel_1 = new JLabel("Tipo de Dragoes");
		lblNewLabel_1.setBounds(10, 215, 113, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		frame2.getContentPane().add(gGraphics);
		
		lEdit = new LabEditor();
		
		heroi = new JButton("Heroi");
		heroi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lEdit.setCharInUse('H');
				display.setText("O Heroi esta selecionado!");
			}
		});
		heroi.setBounds(50, 180, 100, 50);
		frame.getContentPane().add(heroi);
		
		parede = new JButton("Parede");
		parede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lEdit.setCharInUse('X');
				display.setText("A Parede esta selecionada!");
			}
		});
		parede.setBounds(170, 180, 100, 50);
		frame.getContentPane().add(parede);
 		
		dragao = new  JButton("Dragao");
		dragao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lEdit.setCharInUse('D');
				display.setText("O Dragao esta selecionado!");
			}
		});
		dragao.setBounds(50, 240, 100, 50);
		frame.getContentPane().add(dragao);
 		
		espada = new JButton("Espada");
		espada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lEdit.setCharInUse('E');
				display.setText("A Espada esta selecionada!");
			}
		});
		espada.setBounds(170, 240, 100, 50);
		frame.getContentPane().add(espada);
		 
		saida = new JButton("Saida");
		saida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lEdit.setCharInUse('S');
				display.setText("A Saida esta selecionada!");
			}
		});
		saida.setBounds(296, 180, 100, 50);
		frame.getContentPane().add(saida);
		
		borracha = new JButton("Borracha");
		borracha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lEdit.setCharInUse(' ');
				display.setText("A Borracha esta selecionada!");
			}
		});
		borracha.setBounds(296, 240, 100, 50);
		frame.getContentPane().add(borracha);
		
		aplicar = new JButton("Aplicar");
		aplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				carregarEdit(null);
				
			}
		});
		aplicar.setBounds(273,131,123,35);
		frame.getContentPane().add(aplicar);
		
		btnGerarNovoLabirinto = new JButton("Gerar Novo Labirinto");
		btnGerarNovoLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MazeBuilder mb = new MazeBuilder();
				
				int dim = 11;
				int ndrag = 1;
				
				try{
					dim = Integer.parseInt(textFDim.getText());
					ndrag = Integer.parseInt(textFNumD.getText());
					mb.setNumdragoes(ndrag); 
					carregarGL(mb.buildMaze(dim));
					
					
				}catch (IllegalArgumentException e){
					display.setText(e.getMessage());
					return;
				}
			}
		});
		btnGerarNovoLabirinto.setBounds(259, 137, 180, 52);
		frame.getContentPane().add(btnGerarNovoLabirinto);
		
		btnTerminar = new JButton("Terminar Programa");
		btnTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminar.setBounds(133, 90, 180, 23);
		frame.getContentPane().add(btnTerminar);
		
		JButton btnPlay = new JButton("Jogar");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCompEdit(true);
			}
		});
		btnPlay.setBounds(52, 22, 167, 57);
		frame.getContentPane().add(btnPlay);
		
		btnEdit = new JButton("Editar");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCompEdit(false);
				display.setText("Escolha a dimensao e clique em \"Aplicar\"");
			}
		});
		btnEdit.setBounds(229, 22, 167, 57);
		frame.getContentPane().add(btnEdit);
		
		display = new JLabel();
		display.setHorizontalAlignment(SwingConstants.CENTER);
		display.setBounds(50, 300, 346, 14);
		frame.getContentPane().add(display);
		
		btnCarregarMaze = new JButton("Carregar");
		btnCarregarMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[][] maze = openGame();
				if(maze == null)
					return;
				if(isJogar){
					carregarGL(maze);
				} else {
					carregarEdit(maze);
				}
			}
		});
		btnCarregarMaze.setBounds(24, 90, 106, 23);
		frame.getContentPane().add(btnCarregarMaze);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveGame();
			}
		});
		btnGuardar.setBounds(315, 90, 113, 23);
		frame.getContentPane().add(btnGuardar);
		
		carregarEdit(null);
		setCompEdit(true);
		
	}
	
	public char[][] openGame(){
		char[][] res = null;
		
		JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("labirinto","lab");
		file.setFileFilter(filtro);  
		
		int i = file.showOpenDialog(frame.getParent());
		
		if(i == JFileChooser.APPROVE_OPTION){
			try{
				FileReader ler = new FileReader(file.getSelectedFile());
				Scanner s = new Scanner(ler);
				String linha = s.nextLine();
				int dim = Integer.parseInt(linha);
				res = new char[dim][dim];
				int y = 0;
				while(s.hasNextLine()){
					linha = s.nextLine();
					for (int x = 0; x < linha.length(); x++) {
						if((x % 2) != 0)
							continue;
						res[y][x/2] = linha.charAt(x);
						
					}
					y++;
				}
					
				
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public void saveGame(){
		JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("labirinto","lab");
		file.setFileFilter(filtro);  
		int i = file.showSaveDialog(frame.getParent());
		
		if(i == JFileChooser.APPROVE_OPTION){
			try{
				
				if(!isJogar){
					if(!lEdit.hasHero()){
						JOptionPane.showMessageDialog(frame, "Heroi nao existe!");
						
						return;
					}
				}
				FileWriter escrever = new FileWriter(file.getSelectedFile()+ ".lab");
				int dim = (isJogar)? gl.getLabirinto().getLabirinto().length : lEdit.getDim();
				escrever.write(Integer.toString(dim) + '\n');
				
				escrever.write((isJogar)? gl.getLab(): lEdit.getLab());
				escrever.close();
				
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void carregarEdit(char[][] maze){
		int dim = 11;
		
		if(maze == null){
			dim = Integer.parseInt(textFDim.getText());
			lEdit.initResult(dim);
		}else{
			dim = maze.length;
			textFDim.setText(Integer.toString(dim));
			lEdit.setResult(maze);
		}
		
		lEdit.setCharInUse('X');
		display.setText("A Parede esta selecionada!");
		frame3.getContentPane().add(lEdit);
		frame3.setVisible(true);
		frame3.setSize(dim*50  + 15, dim*50 + 40);
		lEdit.setVisible(true);
		lEdit.setSize(dim * 50, dim * 50);
	}
	
	public void carregarGL(char[][] maze){
		int estado = comboBox.getSelectedIndex();
		int dim = maze.length;
		gl = new GameLogic(maze, Dragao.Estado.values()[estado]);
		gl.update();
		frame2.setVisible(true);
		
		frame2.setSize(dim*50 + 15, dim*50 + 40);
		gGraphics.setGl(gl); 
		gGraphics.setVisible(true);
		gGraphics.setBounds(0,0, dim*50, dim*50); 
		gGraphics.repaint();
		gGraphics.requestFocus();
	} 
	

	
	public void setCompEdit(boolean b){
		isJogar = b;
		
		textFNumD.setVisible(b);
		btnGerarNovoLabirinto.setVisible(b);
		comboBox.setVisible(b);
		lblNewLabel.setVisible(b);
		lblNewLabel_1.setVisible(b);
		
		if(!b)
			frame2.setVisible(b);
		
		frame3.setVisible(!b);
		heroi.setVisible(!b);
		parede.setVisible(!b);
		dragao.setVisible(!b);
		espada.setVisible(!b);
		saida.setVisible(!b);
		borracha.setVisible(!b);
		aplicar.setVisible(!b);
		display.setText("");
		
		
	}
}
