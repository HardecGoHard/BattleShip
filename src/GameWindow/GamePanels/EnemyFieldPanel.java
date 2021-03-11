package GameWindow.GamePanels;

import GameWindow.BattleShipWindow;
import Models.ModelsPicture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnemyFieldPanel extends JPanel {
    BattleShipWindow battleShipWindow;
    MouseAction mouseAction;

    public EnemyFieldPanel(BattleShipWindow battleShipWindow) {
        this.battleShipWindow = battleShipWindow;
        this.setPreferredSize(new Dimension(ModelsPicture.COLUMNS * ModelsPicture.IMAGE_SIZE, ModelsPicture.ROWS * ModelsPicture.IMAGE_SIZE));
        addMouseAction();
    }
    private class MouseAction extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int x = (e.getX() / ModelsPicture.IMAGE_SIZE) * ModelsPicture.IMAGE_SIZE;
            int y = (e.getY() / ModelsPicture.IMAGE_SIZE) * ModelsPicture.IMAGE_SIZE;
            if (e.getButton() == MouseEvent.BUTTON1 && (x >= ModelsPicture.IMAGE_SIZE && y >= ModelsPicture.IMAGE_SIZE)) {
                battleShipWindow.getShoot(x,y);
                repaint();
            }
        }
    }
    public void addMouseAction(){
        mouseAction = new MouseAction();
        this.addMouseListener(mouseAction);
    }
    public void deleteMouseAction(){
        this.removeMouseListener(mouseAction);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        battleShipWindow.repaintEnemyField(g);
    }
}
