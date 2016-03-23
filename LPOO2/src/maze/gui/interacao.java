package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JWindow;

import maze.logic.Dragao;
import maze.logic.GameLogic;
import maze.logic.Labirinto;
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
	private JButton btnGerarNovoLabirinto;
	private JComboBox comboBox;
	private GameLogic gl;
	private GameGraphics gGraphics = new GameGraphics(null);
	private JFrame frame2 = new JFrame("Happy Easter!");
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
			}
		});
		heroi.setBounds(50, 180, 100, 50);
		frame.getContentPane().add(heroi);
		
		parede = new JButton("Parede");
		parede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lEdit.setCharInUse('X');
			}
		});
		parede.setBounds(170, 180, 100, 50);
		frame.getContentPane().add(parede);
 		
		dragao = new  JButton("Dragao");
		dragao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lEdit.setCharInUse('D');
			}
		});
		dragao.setBounds(50, 240, 100, 50);
		frame.getContentPane().add(dragao);
 		
		espada = new JButton("Espada");
		espada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lEdit.setCharInUse('E');
			}
		});
		espada.setBounds(170, 240, 100, 50);
		frame.getContentPane().add(espada);
		
		saida = new JButton("Saida");
		saida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lEdit.setCharInUse('S');
			}
		});
		saida.setBounds(296, 180, 100, 50);
		frame.getContentPane().add(saida);
		
		borracha = new JButton("Borracha");
		borracha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lEdit.setCharInUse('B');
			}
		});
		borracha.setBounds(296, 240, 100, 50);
		frame.getContentPane().add(borracha);
		
		aplicar = new JButton("Aplicar");
		aplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dim = 11;
				dim = Integer.parseInt(textFDim.getText());
				lEdit.initResult(dim);
				frame3.getContentPane().add(lEdit);
				frame3.setVisible(true);
				frame3.setSize(dim*50  + 15, dim*50 + 40);
				lEdit.setVisible(true);
				lEdit.setSize(dim * 50, dim * 50);
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
				int estado = comboBox.getSelectedIndex();
				
				try{
					dim = Integer.parseInt(textFDim.getText());
					ndrag = Integer.parseInt(textFNumD.getText());
					mb.setNumdragoes(ndrag); 
					gl = new GameLogic(mb.buildMaze(dim), Dragao.Estado.values()[estado]);
					gl.update();
					frame2.setVisible(true);
				
					frame2.setSize(dim*50 + 15, dim*50 + 40);
					gGraphics.setGl(gl); 
					gGraphics.setVisible(true);
					gGraphics.setBounds(0,0, dim*50, dim*50); 
					gGraphics.repaint();
					gGraphics.requestFocus();
					
				}catch (IllegalArgumentException e){
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
			}
		});
		btnEdit.setBounds(229, 22, 167, 57);
		frame.getContentPane().add(btnEdit);
		
		setCompEdit(false);
	}
	
	public void setCompEdit(boolean b){
		textFNumD.setVisible(b);
		btnGerarNovoLabirinto.setVisible(b);
		comboBox.setVisible(b);
		lblNewLabel.setVisible(b);
		lblNewLabel_1.setVisible(b);
		heroi.setVisible(!b);
		parede.setVisible(!b);
		dragao.setVisible(!b);
		espada.setVisible(!b);
		saida.setVisible(!b);
		borracha.setVisible(!b);
		aplicar.setVisible(!b);
		
		
	}
}
