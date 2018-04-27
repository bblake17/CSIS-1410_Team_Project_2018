package Memory;

import backend.Board;
import gui.GameBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.ImageIcon;

public class GameSettingGui extends JFrame{
	public static byte size = 0;
	public static byte theme = 0;

	static{
	    readFromFile();
    }

	public static void writeToFile(){
	    try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File("settings")))){
	        stream.writeByte(size);
	        stream.writeByte(theme);
        }catch(IOException ignore){}
    }

    public static void readFromFile(){
        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(new File("settings")))){
            size = stream.readByte();
            theme = stream.readByte();
        }catch(IOException ignore){}
    }

    @Override
    public void dispose(){
        super.dispose();
	    writeToFile();
    }

	private transient JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSettingGui frame = new GameSettingGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameSettingGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 100, 730, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel GameSettings = new JLabel("Game Settings");
		GameSettings.setOpaque(true);
		GameSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		GameSettings.setIcon(new ImageIcon(GameSettingGui.class.getResource("/Memory/Banner.png")));
		GameSettings.setForeground(Color.WHITE);
		GameSettings.setBackground(Color.BLACK);
		GameSettings.setHorizontalAlignment(SwingConstants.CENTER);
		GameSettings.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(GameSettings, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton PlayGame = new JButton("Play Game");
		PlayGame.setBackground(new Color(255, 0, 0));
		PlayGame.setForeground(Color.WHITE);
		PlayGame.addActionListener(event ->{
			GameBoard.run.run();
			dispose();
		});
		PlayGame.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(PlayGame);
		
		JButton MainMenu = new JButton("Main Menu");
		MainMenu.setBackground(Color.RED);
		MainMenu.setForeground(Color.WHITE);
		MainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGui s = new MenuGui();
				s.setVisible(true);
				dispose();
			}
		});
		MainMenu.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(MainMenu);
		panel.setLayout(new GridLayout(0, 1));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2));
		
		JLabel Difficulty = new JLabel("Difficulty");
		Difficulty.setOpaque(true);
		Difficulty.setHorizontalTextPosition(SwingConstants.CENTER);
		Difficulty.setIcon(new ImageIcon(GameSettingGui.class.getResource("/Memory/Border.png")));
		Difficulty.setForeground(Color.WHITE);
		Difficulty.setBackground(Color.BLACK);
		Difficulty.setFont(new Font("Tahoma", Font.PLAIN, 35));
		Difficulty.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(Difficulty);
		
		JLabel CardTheme = new JLabel("Card Theme");
		CardTheme.setHorizontalTextPosition(SwingConstants.CENTER);
		CardTheme.setIcon(new ImageIcon(GameSettingGui.class.getResource("/Memory/Border.png")));
		CardTheme.setBackground(Color.BLACK);
		CardTheme.setForeground(Color.WHITE);
		CardTheme.setOpaque(true);
		CardTheme.setFont(new Font("Tahoma", Font.PLAIN, 35));
		CardTheme.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(CardTheme);
		
		JButton cards12 = new JButton("12 Cards");
		cards12.setBackground(Color.BLACK);
		cards12.setForeground(Color.RED);
		cards12.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cards12.addActionListener(event->size=0);
		panel_1.add(cards12);
		
		JButton carsTheme = new JButton("Cars");
		carsTheme.setBackground(Color.BLACK);
		carsTheme.setIcon(new ImageIcon(GameSettingGui.class.getResource("/Memory/racing.png")));
		carsTheme.setForeground(Color.BLUE);
		carsTheme.setFont(new Font("Tahoma", Font.PLAIN, 30));
		carsTheme.addActionListener(event->theme=0);
		panel_1.add(carsTheme);
		
		JButton cards16 = new JButton("16 Cards");
		cards16.setBackground(Color.BLACK);
		cards16.setForeground(Color.BLUE);
		cards16.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cards16.addActionListener(event->size=1);
		panel_1.add(cards16);
		
		JButton animalsTheme = new JButton("Animals");
		animalsTheme.setBackground(Color.BLACK);
		animalsTheme.setIcon(new ImageIcon(GameSettingGui.class.getResource("/Memory/kitty.png")));
		animalsTheme.setForeground(Color.ORANGE);
		animalsTheme.setFont(new Font("Tahoma", Font.PLAIN, 30));
        animalsTheme.addActionListener(event->theme=1);
		panel_1.add(animalsTheme);
		
		JButton cards20 = new JButton("20 Cards");
		cards20.setBackground(Color.BLACK);
		cards20.setForeground(Color.ORANGE);
		cards20.setFont(new Font("Tahoma", Font.PLAIN, 30));
        cards20.addActionListener(event->size=2);
		panel_1.add(cards20);
		
		JButton dinosaursTheme = new JButton("Dinosaurs");
		dinosaursTheme.setBackground(Color.BLACK);
		dinosaursTheme.setIcon(new ImageIcon(GameSettingGui.class.getResource("/Memory/stegosaurus.png")));
		dinosaursTheme.setForeground(Color.RED);
		dinosaursTheme.setFont(new Font("Tahoma", Font.PLAIN, 30));
		dinosaursTheme.addActionListener(event->theme=2);
		panel_1.add(dinosaursTheme);
	}

}
