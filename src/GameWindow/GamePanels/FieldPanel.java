package GameWindow.GamePanels;

import GameWindow.PreparationGameWindow;
import Models.ModelsPicture;
import Models.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FieldPanel extends JPanel {
    PreparationGameWindow gameWindow;
    ChoosePanel choosePanel;

    public void setChoosePanel(ChoosePanel choosePanel) {
        this.choosePanel = choosePanel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameWindow.repaintMyField(g);
    }

    public FieldPanel(PreparationGameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.setPreferredSize(new Dimension(ModelsPicture.COLUMNS * ModelsPicture.IMAGE_SIZE, ModelsPicture.ROWS * ModelsPicture.IMAGE_SIZE));
        this.addMouseListener(new MouseAction());
    }

    private class MouseAction extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int x = (e.getX() / ModelsPicture.IMAGE_SIZE) * ModelsPicture.IMAGE_SIZE;
            int y = (e.getY() / ModelsPicture.IMAGE_SIZE) * ModelsPicture.IMAGE_SIZE;
            int countDeck = choosePanel.getCountDeck();
            int placement = choosePanel.getPlacement();
            Ship ship;
            if (e.getButton() == MouseEvent.BUTTON1 && (x >= ModelsPicture.IMAGE_SIZE && y >= ModelsPicture.IMAGE_SIZE)) {

                switch (placement) {
                    case 1: {
                        ship = new Ship(countDeck, false);
                        ship.createVerticalShip(x, y, ModelsPicture.SHIP);
                        gameWindow.addShip(ship);
                        break;
                    }
                    case 2: {
                        ship = new Ship(countDeck, true);
                        ship.createHorizontalShip(x, y, ModelsPicture.SHIP);
                        gameWindow.addShip(ship);
                        break;
                    }
                    default:
                }
                gameWindow.changeCountShipOnChoosePanel(countDeck);
            }

            repaint();
        }
    }

}

