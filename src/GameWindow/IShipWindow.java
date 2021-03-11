package GameWindow;

import Models.GameHelper;
import Models.Ship;
import Models.ShipsController;

import java.awt.*;

public interface IShipWindow {
    void addShip(Ship ship);
    void setGameHelper(GameHelper gameHelper);
    void startGUI();
}
