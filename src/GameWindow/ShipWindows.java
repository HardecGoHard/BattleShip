package GameWindow;

import GameWindow.GamePanels.ChoosePanel;
import GameWindow.GamePanels.FieldPanel;
import Models.GameHelper;
import Models.Ship;
import Models.ShipsController;

import javax.swing.*;
import java.awt.*;

public abstract class ShipWindows extends JFrame implements IShipWindow {
    protected ShipsController friendlyShipsController;
    protected ShipsController enemyShipsController;
    protected GameHelper gameHelper;


    @Override
    public abstract void startGUI();

    @Override
    public void addShip(Ship ship) {
        gameHelper.addShip(ship,friendlyShipsController);
    }

    @Override
    public void setGameHelper(GameHelper gameHelper) {
        this.gameHelper = gameHelper;
    }

    public void setFriendlyShipsController(ShipsController friendlyShipsController) {
        this.friendlyShipsController = friendlyShipsController;
    }

    public void setEnemyShipsController(ShipsController enemyShipsController) {
        this.enemyShipsController = enemyShipsController;
    }
    public void messageWarningPanel(String text){
        JOptionPane.showMessageDialog(
                null, text,
                "Внимание!", JOptionPane.WARNING_MESSAGE);
    }
    public void messageErorrPanel(String text){
        JOptionPane.showMessageDialog(
                null, text,
                "Внимание!", JOptionPane.ERROR_MESSAGE);
    }
}
