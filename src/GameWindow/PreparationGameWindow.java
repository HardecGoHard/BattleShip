package GameWindow;

import GameWindow.GamePanels.ChoosePanel;
import GameWindow.GamePanels.FieldPanel;
import Models.*;
import Models.Box;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;

public class PreparationGameWindow extends ShipWindows {
    private ChoosePanel chosePanel;
    private FieldPanel fieldPanel;
    private BattleShipWindow battleShipWindow;

    public boolean isGameIsReady() {
        return (friendlyShipsController.getShipsFourDeck().size() +
                friendlyShipsController.getShipsThreeDeck().size() +
                friendlyShipsController.getShipsTwoDeck().size() +
                friendlyShipsController.getShipsOneDeck().size()) == 10;

    }

    public PreparationGameWindow() {
        try {
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            messageErorrPanel("Can't use the specified look and feel on this platform.");
        } catch (Exception e) {
            messageErorrPanel("Couldn't get specified look and feel, for some reason.");
        }
        setTitle("Морской бой");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void startGUI() {
        gameHelper.createNewField();
        chosePanel = new ChoosePanel(this);
        fieldPanel = new FieldPanel(this);
        fieldPanel.setChoosePanel(chosePanel);
        add(chosePanel, BorderLayout.EAST);
        add(fieldPanel, BorderLayout.WEST);
        pack();
        revalidate();
        setVisible(true);
    }

    public void changeCountShipOnChoosePanel(int countDeck) {
        switch (countDeck) {
            case 1: {
                chosePanel.setNameOneDeck(4 - friendlyShipsController.getShipsOneDeck().size());
                break;
            }
            case 2: {
                chosePanel.setNameTwoDeck(3 - friendlyShipsController.getShipsTwoDeck().size());
                break;
            }
            case 3: {
                chosePanel.setNameThreeDeck(2 - friendlyShipsController.getShipsThreeDeck().size());
                break;
            }
            case 4: {
                chosePanel.setNameFourDeck(1 - friendlyShipsController.getShipsFourDeck().size());
                break;
            }
        }
        chosePanel.revalidate();
    }

    public void repaintMyField(Graphics g) {
        Box[][] matrix = friendlyShipsController.getField();
        for (Box[] boxes : matrix) {
            for (Box box : boxes) {
                if (box != null) {
                    g.drawImage(ModelsPicture.getImage(box.getPicture().name()), box.getX(), box.getY(), fieldPanel);
                }
            }
        }
    }

    public void loadEmptyMyField() {
        gameHelper.createNewField();
        fieldPanel.repaint();
        chosePanel.setNameOneDeck(4);
        chosePanel.setNameTwoDeck(3);
        chosePanel.setNameThreeDeck(2);
        chosePanel.setNameFourDeck(1);
    }

    public void loadBattleShipWindow() {

        ShipsController enemyShipsController = new ShipsController();
        battleShipWindow = new BattleShipWindow();
        gameHelper.setEnemyFieldModel(enemyShipsController);
        battleShipWindow.setGameHelper(this.gameHelper);
        battleShipWindow.setEnemyShipsController(enemyShipsController);
        battleShipWindow.setFriendlyShipsController(friendlyShipsController);
        battleShipWindow.startGUI();

    }
}
