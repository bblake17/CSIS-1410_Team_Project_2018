package resources;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IO{
    private static List<Image> cards = new ArrayList<>();
    static{
        for (int i = 0; i <= 10; i++) cards.add(Toolkit.getDefaultToolkit().createImage(IO.class.getClassLoader().getResource(String.format("cards/card%02d.png", i))));
    }
    public static Image getCardIcon(int id){
        return cards.get(id);
    }
}
