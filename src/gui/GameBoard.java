package gui;

import backend.Board;
import backend.Card;
import resources.IO;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 *
 */
public class GameBoard{
    static final int CARD_WIDTH = 27;
    static final int CARD_HEIGHT = 42;
    private final GameBoard instance = this;
    public Board board;
    private JPanel panel;
    private List<JButton> buttons = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();
    /**
     *
     * @param board
     */
    GameBoard(Board board){
        this.board = board;
    }
    private boolean lock1 = false;
    private boolean lock2 = false;
    private boolean lock3 = false;
    /**
     *
     * @return
     */
    private boolean getLock(){
        if (lock1){
            if(lock3) return false;
            lock2 = lock3 = true;
            return true;
        }
        lock1 = true;
        return true;
    }
    /**
     *
     * @return
     */
    private void releaseLock(){
        if (lock2) lock2 = false;
        else if (lock1){
            lock1 = lock3 = false;
        }
    }
    /**
     *
     * @param c1
     * @param c2
     * @param success
     */
    public void match(int c1, int c2, boolean success){
        if (success){
            buttons.get(c1).setEnabled(false);
            buttons.get(c2).setEnabled(false);
        }else{
            new Animation(c1).start();
            new Animation(c2).start();
        }
        if (board.getRemainingCards().isEmpty()){
            JOptionPane.showMessageDialog(panel, "You win!");
            System.exit(0);
        }
    }
    /**
     *
     * @return
     */
    JPanel setup(){
        panel = new JPanel();
        Insets insets = new Insets(0, 0, 0, 0);
        GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1d, 1d, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 1, 1);
        panel.setLayout(new GridBagLayout());
        for(int j = 0; j < board.sizeY; j++) for(int i = 0; i < board.sizeX; i++){
            JButton button = new JButton();
            button.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
            button.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
            button.setIcon(new ImageIcon(IO.getCardIcon(Card.ZERO)));
            constraints.gridx = i;
            constraints.gridy = j;
            final int index = i + j*board.sizeX;
            button.addActionListener(event -> new Animation(index).start());
            cards.add(Card.ZERO);
            buttons.add(button);
            panel.add(button, constraints);
        }
        return panel;
    }
    /**
     *
     */
    private class Animation extends Timer implements ActionListener{
        private boolean lock = false;
        final int index;
        int startingWidth = CARD_WIDTH;
        int width = startingWidth;
        int dx = 4;
        Card id;
        /**
         *
         * @param index
         */
        private Animation(int index){
            super(100, null);
            this.addActionListener(this);
            this.index = index;
        }
        /**
         *
         * @param ignore - We don't really care about this
         */
        @Override
        public void actionPerformed(ActionEvent ignore){
            if(!this.lock){
                lock = getLock();
                if (!lock) return;
            }
            JButton button = buttons.get(index);
            id = cards.get(index);
            width -= dx;
            if(width <= 0){
                width = -width;
                dx = -dx;
                cards.set(index, id == Card.ZERO ? board.flipCard(instance, index) : Card.ZERO);
            }else if(width >= startingWidth){
                width = startingWidth;
                this.stop();
                releaseLock();
            }
            button.setIcon(new ImageIcon(IO.getCardIcon(id).getScaledInstance(width, CARD_HEIGHT, Image.SCALE_FAST)));
            button.setPreferredSize(new Dimension(width, CARD_HEIGHT));
            button.setSize(button.getPreferredSize());
            panel.revalidate();
        }
    }
}
