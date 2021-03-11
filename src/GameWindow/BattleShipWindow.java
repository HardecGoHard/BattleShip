package GameWindow;

import GameWindow.GamePanels.EnemyFieldPanel;
import GameWindow.GamePanels.MyField;
import Models.*;
import Models.Box;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleShipWindow extends ShipWindows {
    private MyField myField;
    private EnemyFieldPanel enemyField;
    private ArrayList<Box> myShoots = new ArrayList<>();
    private ArrayList<Box> enemyShoots = new ArrayList<>();

    private Random rnd = new Random(System.currentTimeMillis());

    BattleShipWindow() {
        try {
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Can't use the specified look and feel on this platform.");
        } catch (Exception e) {
            System.err.println("Couldn't get specified look and feel, for some reason.");
        }
        setTitle("Морской бой");
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void startGUI() {
        myField = new MyField(this);
        enemyField = new EnemyFieldPanel(this);
        gameHelper.createNewEnemyField();
        generateEnemyField();
        add(myField, BorderLayout.WEST);
        add(enemyField, BorderLayout.EAST);
        pack();
        revalidate();
        setVisible(true);
    }

    public void repaintMyField(Graphics g) {
        Box[][] matrix = friendlyShipsController.getField();
        for (Box[] boxes : matrix)
            for (Box box : boxes) {
                if (box == null) continue;
                if ((box.getPicture() == ModelsPicture.EMPTY || box.getPicture() == ModelsPicture.SHIP)) {
                    if (box.isBoxIsOpen() && box.getPicture() == ModelsPicture.EMPTY) {
                        g.drawImage(ModelsPicture.getImage(ModelsPicture.POINT.name()), box.getX(), box.getY(), myField);
                    } else if ((box.isBoxIsOpen() && box.getPicture() == ModelsPicture.SHIP)) {
                        g.drawImage(ModelsPicture.getImage(ModelsPicture.DESTROY_SHIP.name()), box.getX(), box.getY(), myField);
                    } else if ((!box.isBoxIsOpen() && box.getPicture() == ModelsPicture.SHIP)) {
                        g.drawImage(ModelsPicture.getImage(ModelsPicture.SHIP.name()), box.getX(), box.getY(), myField);
                    } else
                        g.drawImage(ModelsPicture.getImage(ModelsPicture.EMPTY.name()), box.getX(), box.getY(), myField);
                } else g.drawImage(ModelsPicture.getImage(box.getPicture().name()), box.getX(), box.getY(), myField);
            }
    }

    public int getCountOpenBoxOfShip(Ship ship) {
        int count = 0;
        for (Box box : ship.getBoxesOfShip()) {
            if (box.isBoxIsOpen()) count++;
        }
        return count;
    }

    public void openBoxesAround(Box boxShot) {
        Ship ship = enemyShipsController.getShip(boxShot);
        if (ship != null) {
            if (ship.getCountDeck() == getCountOpenBoxOfShip(ship)) enemyShipsController.openAllBoxesAroundShip(ship);
            else if (getCountOpenBoxOfShip(ship) == 1) return;
            else {
                for (Box box : ship.getBoxesOfShip()) {
                    if (box.isBoxIsOpen())
                        enemyShipsController.openBoxAroundBoxOfShip(box.getX(), box.getY(), ship.isHorizontalPlacement());
                }
            }
        }
    }

    public void openFriendBoxesAround(Box boxShot) {
        Ship ship = friendlyShipsController.getShip(boxShot); //по боксу в который выстрелили получаем корабль
        if (ship != null) {
            if (ship.getCountDeck() == getCountOpenBoxOfShip(ship))
                friendlyShipsController.openAllBoxesAroundShip(ship);
            else if (getCountOpenBoxOfShip(ship) == 1) return;
            else {
                for (Box box : ship.getBoxesOfShip()) {
                    if (box.isBoxIsOpen())
                        friendlyShipsController.openBoxAroundBoxOfShip(box.getX(), box.getY(), ship.isHorizontalPlacement());
                }
            }
        }
    }

    public void repaintEnemyField(Graphics g) {
        Box[][] matrix = enemyShipsController.getField();
        for (Box[] boxes : matrix) {
            for (Box box : boxes) {
                if (box == null) continue;
                if ((box.getPicture() == ModelsPicture.EMPTY || box.getPicture() == ModelsPicture.SHIP)) {
                    if (box.isBoxIsOpen() && box.getPicture() == ModelsPicture.EMPTY) {
                        g.drawImage(ModelsPicture.getImage(ModelsPicture.POINT.name()), box.getX(), box.getY(), enemyField);
                    } else if ((box.isBoxIsOpen() && box.getPicture() == ModelsPicture.SHIP)) {
                        g.drawImage(ModelsPicture.getImage(ModelsPicture.DESTROY_SHIP.name()), box.getX(), box.getY(), enemyField);
                    } else
                        g.drawImage(ModelsPicture.getImage(ModelsPicture.EMPTY.name()), box.getX(), box.getY(), enemyField);
                } else g.drawImage(ModelsPicture.getImage(box.getPicture().name()), box.getX(), box.getY(), enemyField);
            }

        }
    }


    public void generateEnemyField() {
        while (!enemyFieldIsFull()) {
            boolean placement = rnd.nextBoolean();
            Ship ship = new Ship(getCountShips(), placement);
            int x = (1 + rnd.nextInt(10)) * ModelsPicture.IMAGE_SIZE;
            int y = (1 + rnd.nextInt(10)) * ModelsPicture.IMAGE_SIZE;
            if (placement) {
                ship.createHorizontalShip(x, y, ModelsPicture.SHIP);
            } else {
                ship.createVerticalShip(x, y, ModelsPicture.SHIP);
            }
            addShip(ship);
        }
    }

    public boolean enemyFieldIsFull() {
        return (enemyShipsController.getShipsOneDeck().size() == 4 && enemyShipsController.getShipsTwoDeck().size() == 3
                && enemyShipsController.getShipsThreeDeck().size() == 2 && enemyShipsController.getShipsFourDeck().size() == 1);
    }

    public int getCountShips() {
        if (enemyShipsController.getShipsFourDeck().size() != 1)
            return 4;
        if (enemyShipsController.getShipsThreeDeck().size() != 2)
            return 3;
        if (enemyShipsController.getShipsTwoDeck().size() != 3)
            return 2;
        if (enemyShipsController.getShipsOneDeck().size() != 4)
            return 1;
        return 1;
    }

    public void addShip(Ship ship) {
        gameHelper.addShip(ship, enemyShipsController);
    }

    public boolean checkEndOfGame() {
        return gameHelper.checkEndField(enemyShipsController) || gameHelper.checkEndField(friendlyShipsController);
    }

    public void getShoot(int x, int y) {
        Box box = enemyShipsController.getBox(enemyShipsController.getField(), x, y);
        if (!box.isBoxIsOpen()) {
            box.setBoxIsOpen(true);
            openBoxesAround(box);
        }
        if (!checkEndOfGame()) {
            enemyField.deleteMouseAction();
            getEnemyShoot();
            enemyField.addMouseAction();
        } else {
            if (gameHelper.checkEndField(enemyShipsController))
                messageWarningPanel("ВЫ ВЫИГРАЛИ!");
            else {
                messageErorrPanel("ВЫ ПРОИГРАЛИ!");
            }
            enemyField.deleteMouseAction();
        }
        enemyField.repaint();
        myField.repaint();
    }

    public void getEnemyShoot() {
        int x =(1 + rnd.nextInt(10));
        int y = (1 + rnd.nextInt(10));
      //  while (!checkShootingBox(x,y,enemyShoots)){}
        Box box = friendlyShipsController.getBox(friendlyShipsController.getField(),
                x * ModelsPicture.IMAGE_SIZE,
                y * ModelsPicture.IMAGE_SIZE);
        if (!box.isBoxIsOpen()) {
            box.setBoxIsOpen(true);
            openFriendBoxesAround(box);
        }
    }

    public boolean checkShootingBox(int x, int y, ArrayList<Box> arrayShoots) {
        Box temp = new Box(x, y);
        for (Box box : arrayShoots) {
            if (box.equals(temp))
                return true;
        }
        return false;
    }
}

