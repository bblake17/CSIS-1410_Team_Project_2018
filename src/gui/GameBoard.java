package gui;

import Memory.GameSettingGui;
import Memory.HighScores;
import Memory.MenuGui;
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
    public static Runnable run = ()->{
        int size = GameSettingGui.size;
        GameBoard board;
        switch(size){
            case 1: board = new GameBoard(new Board(4, 4)); break;
            case 2: board = new GameBoard(new Board(5, 4)); break;
            default: board = new GameBoard(new Board(3, 4)); break;
        }
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        frame.add(board.setup(), BorderLayout.CENTER);
        frame.setBackground(Color.BLACK);

        JLabel timer = new JLabel("000.000");
        timer.setOpaque(true);
        timer.setHorizontalAlignment(JLabel.CENTER);
        timer.setBackground(Color.BLACK);
        timer.setForeground(Color.RED);
        new Timer(100, event ->{
            timer.setText(String.format("%05.1f", (System.currentTimeMillis()-board.board.startTime)/1000.0));
        }).start();
        frame.add(timer, BorderLayout.NORTH);

        frame.setVisible(true);
        board.board.startTime = System.currentTimeMillis();
    };
    static final int CARD_WIDTH = 100;
    static final int CARD_HEIGHT = 100;
    private final GameBoard instance = this;
    public Board board;
    private JPanel panel;
    private List<JButton> buttons = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();
    /**
     *
     * @param board
     */
    public GameBoard(Board board){
        this.board = board;
    }
    private boolean lock1 = false;
    private boolean lock2 = false;
    private boolean lock3 = false;
    /**
     *
     * @return
     */
    private boolean getLock(int numLocks){
        if(numLocks > 1) return !lock1 && !lock2 && (lock1 = lock2 = true);
        if (lock3) return !lock2 && (lock2 = true);
        return lock1 = lock3 = true;
    }
    /**
     *
     * @return
     */
    private void releaseLock(int numLocks){
        if(numLocks > 1) lock1 = lock2 = lock3 = false;
        if (lock2) lock2 = false;
        else if (lock1) lock1 = false;
    }
    /**
     *
     * @param c1
     * @param c2
     * @param success
     */
    public void match(int c1, int c2, boolean success){
        long finish = System.currentTimeMillis();
        if (success){
            buttons.get(c1).setEnabled(false);
            buttons.get(c2).setEnabled(false);
            lock3 = false;
        }else{
            new Animation(c1, c2).start();
        }
        if (board.getRemainingCards().isEmpty()){
            Container container = this.panel;
            while(!(container instanceof JFrame)) container = container.getParent();
            ((JFrame)container).dispose();
            JOptionPane.showMessageDialog(null, String.format("You won the game in %.1f seconds!", (finish-board.startTime)/1000.0));
            if (this.cards.size() < 20) JOptionPane.showMessageDialog(null, "Play with 20 cards to get added to the high scores!");
            else HighScores.addHighScore(finish-board.startTime);
            new MenuGui().setVisible(true);
        }
    }
    /**
     *
     * @return
     */
    public JPanel setup(){
        panel = new JPanel();
        panel.setBackground(Color.BLACK);
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
    private class Animation extends Timer{
        private boolean lock = false;
        final int index;
        final int index0;
        int startingWidth = CARD_WIDTH;
        int width = startingWidth;
        int dx = 13;//Must be relatively prime with CARD_WIDTH
        /**
         *
         * @param index
         */
        private Animation(int index){
            super(100, null);
            this.addActionListener(this::flipCard);
            this.index = index;
            this.index0 = 0;
        }
        /**
         *
         * @param index1
         * @param index2
         */
        private Animation(int index1, int index2){
            super(100, null);
            this.addActionListener(this::resetCards);
            this.index = index1;
            this.index0 = index2;
        }
        /**
         *
         * @param ignore - We don't really care about this
         */
        public void flipCard(ActionEvent ignore){
            if(!this.lock){
                lock = getLock(1);
                if (!lock) return;
            }
            JButton button = buttons.get(index);
            Card id = cards.get(index);
            width -= dx;
            if(width <= 0){
                width = -width;
                dx = -dx;
                cards.set(index, board.flipCard(instance, index));
            }else if(width >= startingWidth){
                width = startingWidth;
                this.stop();
                releaseLock(1);
            }
            button.setIcon(new ImageIcon(IO.getCardIcon(id).getScaledInstance(width, CARD_HEIGHT, Image.SCALE_FAST)));
            button.setPreferredSize(new Dimension(width, CARD_HEIGHT));
            button.setSize(button.getPreferredSize());
            panel.revalidate();
        }
        /**
         *
         * @param ignore
         */
        public void resetCards(ActionEvent ignore){
            if(!this.lock){
                lock = getLock(2);
                if (!lock) return;
            }
            JButton button1 = buttons.get(index);
            JButton button2 = buttons.get(index0);
            Card id1 = cards.get(index);
            Card id2 = cards.get(index0);
            width -= dx;
            if(width <= 0){
                width = -width;
                dx = -dx;
                cards.set(index, Card.ZERO);
                cards.set(index0, Card.ZERO);
            }else if(width >= startingWidth){
                width = startingWidth;
                this.stop();
                releaseLock(2);
            }
            button1.setIcon(new ImageIcon(IO.getCardIcon(id1).getScaledInstance(width, CARD_HEIGHT, Image.SCALE_FAST)));
            button1.setPreferredSize(new Dimension(width, CARD_HEIGHT));
            button1.setSize(button1.getPreferredSize());

            button2.setIcon(new ImageIcon(IO.getCardIcon(id2).getScaledInstance(width, CARD_HEIGHT, Image.SCALE_FAST)));
            button2.setPreferredSize(new Dimension(width, CARD_HEIGHT));
            button2.setSize(button2.getPreferredSize());
            panel.revalidate();
        }
    }
}
