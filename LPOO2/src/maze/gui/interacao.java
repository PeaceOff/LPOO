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
	private JButton btnGerarNovoLabirinto;
	private JComboBox comboBox;
	private GameLogic gl;
	private GameGraphics gGraphics = new GameGraphics(null);
	private JFrame frame2 = new JFrame();
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
		
		frame2.getContentPane().add(gGraphics);
		
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
					//frame.getContentPane().add(gGraphics);  
					gGraphics.setBounds(0,0, dim*50, dim*50); 
					gGraphics.repaint();
					gGraphics.requestFocus(); 
					
				}catch (IllegalArgumentException e){
					return;
				}
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
	}
}
