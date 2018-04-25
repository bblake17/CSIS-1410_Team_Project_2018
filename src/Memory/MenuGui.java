package Memory;

import java.awt.*;

import backend.Board;
import gui.GameBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGui frame = new MenuGui();
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
	public MenuGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 730, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel MemoryGame = new JLabel("Memory Game Menu");
		MemoryGame.setOpaque(true);
		MemoryGame.setIgnoreRepaint(true);
		MemoryGame.setBackground(new Color(0, 0, 0));
		MemoryGame.setForeground(Color.WHITE);
		MemoryGame.setHorizontalTextPosition(SwingConstants.CENTER);
		MemoryGame.setIcon(new ImageIcon(MenuGui.class.getResource("/Memory/Banner.png")));
		MemoryGame.setFont(new Font("Tahoma", Font.PLAIN, 45));
		MemoryGame.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(MemoryGame, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton PlayGame = new JButton("Play Game");
		PlayGame.addActionListener(event ->{
			GameBoard.run.run();
			dispose();
		});
		PlayGame.setForeground(Color.WHITE);
		PlayGame.setBackground(Color.BLACK);
		PlayGame.setIcon(new ImageIcon(MenuGui.class.getResource("/Memory/Play.png")));
		PlayGame.setVerticalTextPosition(SwingConstants.TOP);
		PlayGame.setHorizontalTextPosition(SwingConstants.CENTER);
		PlayGame.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(PlayGame);
		
		JButton HighScores = new JButton("High Scores");
		HighScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HighScores s = new HighScores();
				s.setVisible(true);
				dispose();
			}
		});
		HighScores.setForeground(Color.WHITE);
		HighScores.setBackground(Color.BLACK);
		HighScores.setVerticalTextPosition(SwingConstants.TOP);
		HighScores.setHorizontalTextPosition(SwingConstants.CENTER);
		HighScores.setIcon(new ImageIcon(MenuGui.class.getResource("/Memory/Rank.png")));
		HighScores.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(HighScores);
		
		JButton GameSettings = new JButton("Game Settings");
		GameSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameSettingGui s = new GameSettingGui();
				s.setVisible(true);
				dispose();
			}
		});
		GameSettings.setForeground(Color.WHITE);
		GameSettings.setBackground(Color.BLACK);
		GameSettings.setVerticalTextPosition(SwingConstants.TOP);
		GameSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		GameSettings.setIcon(new ImageIcon(MenuGui.class.getResource("/Memory/Setting.png")));
		GameSettings.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(GameSettings);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton Exit = new JButton("Exit");
		Exit.setForeground(Color.WHITE);
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				System.exit(0);
			}
		});
		Exit.setBackground(Color.BLACK);
		Exit.setFont(new Font("Tahoma", Font.PLAIN, 30));
		Exit.setHorizontalTextPosition(SwingConstants.CENTER);
		Exit.setIcon(new ImageIcon(MenuGui.class.getResource("/Memory/ExitButton.png")));
		panel_1.add(Exit);
	}

}
