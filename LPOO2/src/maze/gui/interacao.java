package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JWindow;

import maze.logic.Dragao;
import maze.logic.GameLogic;
import maze.logic.MazeBuilder;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class interacao {

	private JFrame frame;
	private JTextField textFDim;
	private JTextField textFNumD;
	private JButton btnTerminar;
	private JButton btnCima;
	private JButton btnGerarNovoLabirinto;
	private JButton btnBaixo;
	private JButton btnEsquerda;
	private JButton btnDireita;
	private JLabel lblEstado;
	private JComboBox comboBox;
	private JTextArea textADisplay;
	private GameLogic gl;
	private GameGraphics gGraphics = new GameGraphics(null);
	private JWindow window;
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
	
	public void moverHeroi(int x, int y){
		gl.moverHeroi(x, y);
		gl.atualizarDragoes();
		GameLogic.Estado estado;
		estado  = gl.update();
		textADisplay.setText(gl.getLabirinto().toString());
		boolean terminou = false;
		switch(estado){
		case Jogar:
			lblEstado.setText("Parabens! Continua o jogo!");
			break;
		case Morto:
			terminou= true;
			lblEstado.setText("Parabens! Perdeste o jogo!");
			break;
		case Vitoria:
			terminou = true;
			lblEstado.setText("Parabens! Venceste o jogo!");
			break;
		}
		
		
		if(terminou){
			btnBaixo.setEnabled(false);;
			btnCima.setEnabled(false);;
			btnEsquerda.setEnabled(false);
			btnDireita.setEnabled(false);
		}
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 465, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDimensao = new JLabel("Dimensao do Labirinto");
		lblDimensao.setBounds(10, 11, 113, 24);
		frame.getContentPane().add(lblDimensao);
		
		textFDim = new JTextField();
		textFDim.setText("11");
		textFDim.setBounds(133, 13, 86, 20);
		frame.getContentPane().add(textFDim);
		textFDim.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Numero de Dragoes");
		lblNewLabel.setBounds(10, 46, 113, 14);
		frame.getContentPane().add(lblNewLabel); 
		
		textFNumD = new JTextField();
		textFNumD.setText("1");
		textFNumD.setBounds(133, 44, 86, 20);
		frame.getContentPane().add(textFNumD);
		textFNumD.setColumns(10);
		comboBox = new JComboBox(Dragao.Estado.values());
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(133, 87, 218, 20);
		frame.getContentPane().add(comboBox);
		 
		JLabel lblNewLabel_1 = new JLabel("Tipo de Dragoes");
		lblNewLabel_1.setBounds(10, 90, 113, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnGerarNovoLabirinto = new JButton("Gerar Novo Labirinto");
		btnGerarNovoLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MazeBuilder mb = new MazeBuilder();
				
				int dim = 11;
				int ndrag = 1;
				int estado = comboBox.getSelectedIndex();
				
				try{
					dim = Integer.parseInt(textFDim.getText());
					ndrag = Integer.parseInt(textFNumD.getText());
					mb.setNumdragoes(ndrag); 
					gl = new GameLogic(mb.buildMaze(dim), Dragao.Estado.values()[estado]);
					gl.update();
					gGraphics.setGl(gl);
					frame.setSize(900, 900);
					gGraphics.setVisible(true);
					//gGraphics.setPreferredSize(new Dimension(200,200));
					frame.getContentPane().add(gGraphics);  
					gGraphics.setBounds(10,115, 11*50, 11*50); 
					gGraphics.repaint();
					gGraphics.requestFocus(); 
					
				}catch (IllegalArgumentException e){
					lblEstado.setText(e.getMessage());
					btnBaixo.setEnabled(false);;
					btnCima.setEnabled(false);;
					btnEsquerda.setEnabled(false);
					btnDireita.setEnabled(false);
					return;
				}
				
				
				lblEstado.setText("Pronto a jogar!");
				textADisplay.setText(gl.getLabirinto().toString());
				
				btnCima.setEnabled(true);
				btnBaixo.setEnabled(true);
				btnEsquerda.setEnabled(true);
				btnDireita.setEnabled(true);
				
			}
		});
		btnGerarNovoLabirinto.setBounds(259, 12, 180, 23);
		frame.getContentPane().add(btnGerarNovoLabirinto);
		
		btnTerminar = new JButton("Terminar Programa");
		btnTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminar.setBounds(259, 46, 180, 23);
		frame.getContentPane().add(btnTerminar);
		
		textADisplay = new JTextArea();
		textADisplay.setForeground(Color.BLACK);
		textADisplay.setBackground(Color.WHITE);
		textADisplay.setFont(new Font("Courier New", Font.PLAIN, 13));
		textADisplay.setEditable(false);
		textADisplay.setWrapStyleWord(true);
		textADisplay.setBounds(10, 115, 243, 210);
		frame.getContentPane().add(textADisplay);
		
		btnCima = new JButton("Cima");
		btnCima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moverHeroi(0,-1);
			}
		});
		btnCima.setEnabled(false);
		btnCima.setBounds(301, 159, 86, 23);
		frame.getContentPane().add(btnCima);
		
		btnBaixo = new JButton("Baixo");
		btnBaixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moverHeroi(0,1);
			}
		});
		btnBaixo.setEnabled(false);
		btnBaixo.setBounds(301, 227, 86, 23);
		frame.getContentPane().add(btnBaixo);
		
		btnEsquerda = new JButton("Esquerda");
		btnEsquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moverHeroi(-1,0);
			}
		});
		btnEsquerda.setEnabled(false);
		btnEsquerda.setBounds(259, 193, 86, 23);
		frame.getContentPane().add(btnEsquerda);
		
		btnDireita = new JButton("Direita");
		btnDireita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moverHeroi(1,0);
			} 
		});
		btnDireita.setEnabled(false);
		btnDireita.setBounds(353, 193, 86, 23);
		frame.getContentPane().add(btnDireita);
		 
		lblEstado = new JLabel("Clique no Botao Gerar Labirinto para Comecar");
		lblEstado.setBounds(10, 337, 429, 14);
		frame.getContentPane().add(lblEstado);
	}
}
