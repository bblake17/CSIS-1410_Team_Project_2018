package gui;

import resources.IO;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GameBoard{
    static final int CARD_WIDTH = 27;
    static final int CARD_HEIGHT = 42;
    private int sizex;
    private int sizey;
    GameBoard(int width, int height){
        sizex = width;
        sizey = height;
    }
    private JPanel panel;
    private List<JButton> cards = new ArrayList<>();
    JPanel setup(){
        panel = new JPanel();
        Insets insets = new Insets(0, 0, 0, 0);
        GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1d, 1d, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 1, 1);
        panel.setLayout(new GridBagLayout());
        for(int j = 0; j < sizey; j++) for(int i = 0; i < sizex; i++){
            JButton button = new JButton();
            button.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
            button.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
            button.setIcon(new ImageIcon(IO.getCardIcon(0)));
            constraints.gridx = i;
            constraints.gridy = j;
            final int index = i + j*sizex;
            button.addActionListener(event -> new Animation(index).start());
            cards.add(button);
            panel.add(button, constraints);
        }
        return panel;
    }

    private class Animation extends Timer implements ActionListener{
        final int index;
        int startingWidth = CARD_WIDTH;
        int width = startingWidth;
        int dx = 4;
        int id = 0;
        private Animation(int index){
            super(100, null);
            this.addActionListener(this);
            this.index = index;
        }
        @Override
        public void actionPerformed(ActionEvent ignore){
            JButton button = cards.get(index);
            width -= dx;
            if(width <= 0){
                width = -width;
                dx = -dx;
                id = new Random().nextInt(10)+1;
            }else if(width >= startingWidth){
                width = startingWidth;
                this.stop();
            }
            button.setIcon(new ImageIcon(IO.getCardIcon(id).getScaledInstance(width, CARD_HEIGHT, Image.SCALE_FAST)));
            button.setPreferredSize(new Dimension(width, CARD_HEIGHT));
            button.setSize(button.getPreferredSize());
            panel.revalidate();
        }
    }
}
