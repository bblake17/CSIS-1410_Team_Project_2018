package Memory;

import gui.GameBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.io.*;

public class HighScores extends JFrame{

	public static long score1 = Long.MAX_VALUE;
    public static long score2 = Long.MAX_VALUE;
    public static long score3 = Long.MAX_VALUE;

    public static String name1 = "";
    public static String name2 = "";
    public static String name3 = "";

	public static void addHighScore(long time){
	    if (time > score3) return;
        String name = JOptionPane.showInputDialog("Enter Your name");
        name = name.trim();
	    if (time < score1){
	        score3 = score2;
	        name3 = name2;
	        score2 = score1;
	        name2 = name1;
	        score1 = time;
	        name1 = name;
        }else if (time < score2){
	        score3 = score2;
            name3 = name2;
	        score2 = time;
	        name2 = name;
        }else if (time < score3){
            score3 = time;
            name3 = name;
        }
        writeToFile();
    }

    static{
        readFromFile();
    }

    public static void writeToFile(){
        try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File("scores")))){
            stream.writeLong(score1);
            stream.writeLong(score2);
            stream.writeLong(score3);
            stream.writeUTF(name1);
            stream.writeUTF(name2);
            stream.writeUTF(name3);
        }catch(IOException ignore){}
    }

    public static void readFromFile(){
        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(new File("scores")))){
            score1 = stream.readLong();
            score2 = stream.readLong();
            score3 = stream.readLong();
            name1 = stream.readUTF();
            name2 = stream.readUTF();
            name3 = stream.readUTF();
        }catch(IOException ignore){}
    }

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
				try {
					HighScores frame = new HighScores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}

	/**
	 * Create the frame.
	 */
	public HighScores() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 100, 730, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel HighScores = 
				new JLabel("High Scores");
		HighScores.setHorizontalTextPosition(SwingConstants.CENTER);
		HighScores.setIcon(new ImageIcon(HighScores.class.getResource("/Memory/Banner.png")));
		HighScores.setBackground(Color.BLACK);
		HighScores.setOpaque(true);
		HighScores.setForeground(Color.WHITE);
		HighScores.setHorizontalAlignment(SwingConstants.CENTER);
		HighScores.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(HighScores, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblPlayer = new JLabel("Player");
		lblPlayer.setOpaque(true);
		lblPlayer.setBackground(Color.BLACK);
		lblPlayer.setForeground(Color.WHITE);
		lblPlayer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer.setIcon(new ImageIcon(HighScores.class.getResource("/Memory/Border.png")));
		lblPlayer.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPlayer);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setOpaque(true);
		lblScore.setBackground(Color.BLACK);
		lblScore.setForeground(Color.WHITE);
		lblScore.setHorizontalTextPosition(SwingConstants.CENTER);
		lblScore.setIcon(new ImageIcon(HighScores.class.getResource("/Memory/Border.png")));
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblScore);
		
		panel.setLayout(new GridLayout(0, 2));
		
		JLabel FirstPlacePlayer = new JLabel(name1);
		FirstPlacePlayer.setHorizontalTextPosition(SwingConstants.RIGHT);
		FirstPlacePlayer.setIcon(new ImageIcon(HighScores.class.getResource("/Memory/first.png")));
		FirstPlacePlayer.setForeground(Color.WHITE);
		FirstPlacePlayer.setBackground(Color.BLACK);
		FirstPlacePlayer.setOpaque(true);
		FirstPlacePlayer.setFont(new Font("Tahoma", Font.PLAIN, 30));
		FirstPlacePlayer.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(FirstPlacePlayer);
		
		JLabel FirstPlaceScore = new JLabel(score1 == Long.MAX_VALUE ? "No score yet." : String.format("%5.1f", score1/1000.0));
		FirstPlaceScore.setForeground(Color.WHITE);
		FirstPlaceScore.setBackground(Color.BLACK);
		FirstPlaceScore.setOpaque(true);
		FirstPlaceScore.setFont(new Font("Tahoma", Font.PLAIN, 30));
		FirstPlaceScore.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(FirstPlaceScore);
		
		JLabel SecondPlacePlayer = new JLabel(name2);
		SecondPlacePlayer.setHorizontalTextPosition(SwingConstants.RIGHT);
		SecondPlacePlayer.setIcon(new ImageIcon(HighScores.class.getResource("/Memory/second.png")));
		SecondPlacePlayer.setForeground(Color.WHITE);
		SecondPlacePlayer.setBackground(Color.BLACK);
		SecondPlacePlayer.setOpaque(true);
		SecondPlacePlayer.setFont(new Font("Tahoma", Font.PLAIN, 30));
		SecondPlacePlayer.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(SecondPlacePlayer);
		
		JLabel SecondPlaceScore = new JLabel(score2 == Long.MAX_VALUE ? "No score yet." : String.format("%5.1f", score2/1000.0));
		SecondPlaceScore.setForeground(Color.WHITE);
		SecondPlaceScore.setBackground(Color.BLACK);
		SecondPlaceScore.setOpaque(true);
		SecondPlaceScore.setFont(new Font("Tahoma", Font.PLAIN, 30));
		SecondPlaceScore.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(SecondPlaceScore);
		
		JLabel ThirdPlacePlayer = new JLabel(name3);
		ThirdPlacePlayer.setHorizontalTextPosition(SwingConstants.RIGHT);
		ThirdPlacePlayer.setIcon(new ImageIcon(HighScores.class.getResource("/Memory/third.png")));
		ThirdPlacePlayer.setForeground(Color.WHITE);
		ThirdPlacePlayer.setBackground(Color.BLACK);
		ThirdPlacePlayer.setOpaque(true);
		ThirdPlacePlayer.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ThirdPlacePlayer.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(ThirdPlacePlayer);
		
		JLabel ThirdPlaceScore = new JLabel(score3 == Long.MAX_VALUE ? "No score yet." : String.format("%5.1f", score3/1000.0));
		ThirdPlaceScore.setForeground(Color.WHITE);
		ThirdPlaceScore.setBackground(Color.BLACK);
		ThirdPlaceScore.setOpaque(true);
		ThirdPlaceScore.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ThirdPlaceScore.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(ThirdPlaceScore);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton PlayGame = new JButton("Play Game");
		PlayGame.setForeground(Color.WHITE);
		PlayGame.setBackground(Color.RED);
		PlayGame.addActionListener(event ->{
			GameBoard.run.run();
			dispose();
		});
		PlayGame.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_1.add(PlayGame);
		
		JButton MainMenu = new JButton("Main Menu");
		MainMenu.setForeground(Color.WHITE);
		MainMenu.setBackground(Color.RED);
		MainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGui s = new MenuGui();
				s.setVisible(true);
				dispose();
			}
		});
		MainMenu.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_1.add(MainMenu);
		panel_1.setLayout(new GridLayout(0, 1));
	}

}
