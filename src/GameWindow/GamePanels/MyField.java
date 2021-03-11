package GameWindow.GamePanels;

import Models.ModelsPicture;
import GameWindow.BattleShipWindow;

import javax.swing.*;
import java.awt.*;

public class MyField extends JPanel {
    BattleShipWindow battleShipWindow;

    public MyField(BattleShipWindow battleShipWindow) {
        this.battleShipWindow = battleShipWindow;
        this.setPreferredSize(new Dimension(ModelsPicture.COLUMNS * ModelsPicture.IMAGE_SIZE, ModelsPicture.ROWS * ModelsPicture.IMAGE_SIZE));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        battleShipWindow.repaintMyField(g);
    }
}
