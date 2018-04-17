package gui;

import gui.GameBoard;

import javax.swing.*;

public class Launch{
    public static void main(String... args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.add(new GameBoard(12, 12).setup());
        frame.setVisible(true);
    }
}
