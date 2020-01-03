package windows;

//import java.awt.EventQueue;

import troops.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class gameWindow {

	 JFrame frame;
	private ImageIcon iconbackground;

	/**
	 * Launch the application.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gameWindow window = new gameWindow(1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 * Create the application.
	 */
	public gameWindow(int i) {
		switch (i) {
		case 1:
			iconbackground= new ImageIcon("src/resources/GrassTerrain.png");
			break;
		case 2:
			iconbackground= new ImageIcon("src/resources/IceTerrain.png");
			break;
		case 3:
			iconbackground= new ImageIcon("src/resources/DessertTerrain.png");
			break;
		}
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 645);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbbackground = new JLabel(iconbackground);
		lbbackground.setBounds(0, 0, 768, 480);
		frame.getContentPane().add(lbbackground);
		
		JPanel panel = new JPanel();
		panel.setBounds(926, 10, 260, 190);
		TitledBorder border=BorderFactory.createTitledBorder("Player 1");
		panel.setBorder(border);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("End Turn");
		btnNewButton.setBounds(13, 25, 235, 68);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Surrender");
		btnNewButton_1.setBounds(13, 109, 235, 68);
		panel.add(btnNewButton_1);
		
		JPanel infopanel = new JPanel();
		infopanel.setBounds(926, 210, 260, 399);
		infopanel.setBorder(BorderFactory.createTitledBorder("Information"));
		frame.getContentPane().add(infopanel);
	}
}
