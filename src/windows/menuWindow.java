package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class menuWindow {

	private JFrame frame;
	gameWindow game;
	WindowAdapter onGameClosed;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuWindow window = new menuWindow();
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
	public menuWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(522, 375, 200, 70);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setBounds(522, 470, 200, 70);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnContinue);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(522, 565, 200, 70);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnHelp);
		
		JButton btnGrass = new JButton("Grass");
		btnGrass.setBounds(522, 375, 200, 70);
		btnGrass.setVisible(false);
		btnGrass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game=new gameWindow(1);
				frame.setVisible(false);
				game.frame.addWindowListener(onGameClosed);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnGrass);
		
		JButton btnSnow = new JButton("Snow");
		btnSnow.setBounds(522, 470, 200, 70);
		btnSnow.setVisible(false);
		btnSnow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game=new gameWindow(2);
				frame.setVisible(false);
				game.frame.addWindowListener(onGameClosed);
			}
		});
		frame.getContentPane().add(btnSnow);
		
		JButton btnDesert = new JButton("Desert");
		btnDesert.setBounds(522, 565, 200, 70);
		btnDesert.setVisible(false);
		btnDesert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game=new gameWindow(3);
				frame.setVisible(false);
				game.frame.addWindowListener(onGameClosed);
			}
		});
		frame.getContentPane().add(btnDesert);
		
		JPanel panel = new JPanel();
		panel.setBounds(124, 12, 1000, 300);
		frame.getContentPane().add(panel);
		ImageIcon titleicon = new ImageIcon("src/resources/Title.png");
		JLabel title = new JLabel(titleicon);
		panel.add(title);
		
		//ActionListener for btnNewGame
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewGame.setVisible(false);
				btnContinue.setVisible(false);
				btnHelp.setVisible(false);
				btnGrass.setVisible(true);
				btnSnow.setVisible(true);
				btnDesert.setVisible(true);
			}
		});
		
		onGameClosed=new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				btnNewGame.setVisible(true);
				btnContinue.setVisible(true);
				btnHelp.setVisible(true);
				btnGrass.setVisible(false);
				btnSnow.setVisible(false);
				btnDesert.setVisible(false);
				frame.setVisible(true);
			}
		};
	}
}
