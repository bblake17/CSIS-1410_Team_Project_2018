package resources;

import backend.Card;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 */
public class IO{
    private static List<Image> cards = new ArrayList<>();
    /**
     *
     */
    static{
        for (int i = 0; i <= 10; i++) cards.add(Toolkit.getDefaultToolkit().createImage(IO.class.getClassLoader().getResource(String.format("cards/card%02d.png", i))));
    }
    /**
     *
     * @param id
     * @return
     */
    public static Image getCardIcon(Card id){
        return cards.get(id.ordinal());
    }
}
