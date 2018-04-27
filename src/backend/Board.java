package backend;

import gui.GameBoard;

import java.util.*;
import java.util.List;
/**
 *
 */
public class Board{
    private final List<Card> cards;
    private Card selectedCard;
    private int c1;
    public final  int sizeX;
    public final int sizeY;
    public long startTime;
    /**
     * TODO
     * @param sizeX
     * @param sizeY
     */
    public Board(int sizeX, int sizeY){
        cards = new ArrayList<>(sizeX*sizeY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        Card[] subArray = Arrays.copyOfRange(Card.values(),1,sizeX*sizeY/2 + 1);
        Collections.addAll(cards, subArray);
        Collections.addAll(cards, subArray);
        Collections.shuffle(cards);
    }
    /**
     *
     * @param index
     * @return The card that was matched if there was a match, null otherwise.
     */
    public Card flipCard(GameBoard game, int index){
        final Card card = cards.get(index);
        if(selectedCard != null){
            if (selectedCard == card){
                if (index == c1) return card;
                cards.set(c1, null);
                cards.set(index, null);
                game.match(c1, index, true);
            }else game.match(c1, index, false);
            selectedCard = null;
        }else{
            selectedCard = card;
            c1 = index;
        }
        return card;
    }
    /**
     *
     * @return A list of indexes of cards that are sill available to be matched. If the game is won, the list will be
     * empty.
     */
    public List<Integer> getRemainingCards(){
        List<Integer> toReturn = new LinkedList<>();
        for(int i = 0; i < cards.size(); i++) if (cards.get(i) != null) toReturn.add(i);
        return toReturn;
    }
}
