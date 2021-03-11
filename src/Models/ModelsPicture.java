package Models;

import javax.swing.*;
import java.awt.*;

public enum ModelsPicture {
    CLOSED,
    SHIP,
    DESTROY_SHIP,
    NUMBER1,NUMBER2,NUMBER3,NUMBER4,NUMBER5,NUMBER6,NUMBER7,NUMBER8,NUMBER9,NUMBER10,
    LETTER1,LETTER2,LETTER3,LETTER4,LETTER5,LETTER6,LETTER7,LETTER8,LETTER9,LETTER10,
    HARDEC,
    POINT,
    EMPTY;
    public static final int IMAGE_SIZE = 40;
    public static final int COLUMNS = 11;
    public static final int ROWS = 11;

    public static Image getImage(String name) {
        return (new ImageIcon("resources/img/" + name + ".png")).getImage();
    }
}
