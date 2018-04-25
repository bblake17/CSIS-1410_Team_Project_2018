package resources;

import Memory.GameSettingGui;
import backend.Card;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 */
public class IO{
    private static List<Image> cards = new ArrayList<>();
    private static List<Image> dino = new ArrayList<>();
    private static List<Image> anim = new ArrayList<>();
    private static List<Image> cars = new ArrayList<>();
    /**
     *
     */
    static{
        Image image = Toolkit.getDefaultToolkit().createImage(IO.class.getResource(String.format("/resources/pics/0.png")));
        dino.add(image);
        anim.add(image);
        cars.add(image);
        for (int i = 1; i <= 10; i++) dino.add(Toolkit.getDefaultToolkit().createImage(IO.class.getResource(String.format("/resources/pics/dinos/%d.png", i))));
        for (int i = 1; i <= 10; i++) anim.add(Toolkit.getDefaultToolkit().createImage(IO.class.getResource(String.format("/resources/pics/animals/%d.png", i))));
        for (int i = 1; i <= 10; i++) cars.add(Toolkit.getDefaultToolkit().createImage(IO.class.getResource(String.format("/resources/pics/cars/%d.png", i))));
    }
    /**
     *
     * @param id
     * @return
     */
    public static Image getCardIcon(Card id){
        switch(GameSettingGui.theme){
            case 1: return anim.get(id.ordinal());
            case 2: return dino.get(id.ordinal());
            default: return cars.get(id.ordinal());
        }
    }
}
