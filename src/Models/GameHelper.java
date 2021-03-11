package Models;

import GameWindow.PreparationGameWindow;

import java.util.List;

public class GameHelper {
    private PreparationGameWindow gameWindow;
    private ShipsController friendlyShipsController;
    private ShipsController EnemyShipsController;

    public GameHelper(PreparationGameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }
    public void setFriendModel(ShipsController friendlyShipsController) {
        this.friendlyShipsController = friendlyShipsController;
    }

    public void setEnemyFieldModel(ShipsController EnemyShipsController) {
        this.EnemyShipsController = EnemyShipsController;
    }


    public void createNewField() {
        friendlyShipsController.getShipsOneDeck().clear();
        friendlyShipsController.getShipsTwoDeck().clear();
        friendlyShipsController.getShipsThreeDeck().clear();
        friendlyShipsController.getShipsFourDeck().clear();
        for (int i = 0; i < ModelsPicture.ROWS; i++) {
            for (int j = 0; j < ModelsPicture.COLUMNS; j++) {
                if (i == 0 && j == 0) {
                    friendlyShipsController.addBox(new Box(0 , 0, ModelsPicture.HARDEC));
                } else if (i == 0 && j > 0) {
                    friendlyShipsController.addBox(new Box(ModelsPicture.IMAGE_SIZE * i, ModelsPicture.IMAGE_SIZE * j, ModelsPicture.valueOf("NUMBER" + j)));
                } else if (j == 0 && i > 0) {
                    friendlyShipsController.addBox(new Box(ModelsPicture.IMAGE_SIZE * i, ModelsPicture.IMAGE_SIZE * j, ModelsPicture.valueOf("LETTER" + i)));
                } else {
                    friendlyShipsController.addBox(new Box(ModelsPicture.IMAGE_SIZE * i, ModelsPicture.IMAGE_SIZE * j, ModelsPicture.EMPTY));
                }

            }
        }
    }
    public void createNewEnemyField() {
        for (int i = 0; i < ModelsPicture.ROWS; i++) {
            for (int j = 0; j < ModelsPicture.COLUMNS; j++) {
                if (i == 0 && j == 0) {
                    EnemyShipsController.addBox(new Box(ModelsPicture.IMAGE_SIZE, ModelsPicture.IMAGE_SIZE, ModelsPicture.HARDEC));
                } else if (i == 0 && j > 0) {
                    EnemyShipsController.addBox(new Box(ModelsPicture.IMAGE_SIZE * i, ModelsPicture.IMAGE_SIZE * j, ModelsPicture.valueOf("NUMBER" + j)));
                } else if (j == 0 && i > 0) {
                    EnemyShipsController.addBox(new Box(ModelsPicture.IMAGE_SIZE * i, ModelsPicture.IMAGE_SIZE * j, ModelsPicture.valueOf("LETTER" + i)));
                } else {
                    EnemyShipsController.addBox(new Box(ModelsPicture.IMAGE_SIZE * i, ModelsPicture.IMAGE_SIZE * j, ModelsPicture.EMPTY));
                }
            }
        }
    }

    public void addShip(Ship ship, ShipsController shipsController) {
        List<Box> boxesOfShip = ship.getBoxesOfShip();
        for (Box boxShip : boxesOfShip) {
            if (checkAround(boxShip, boxesOfShip,shipsController)) {
                boxesOfShip.clear();
                return;
            }
        }
        if (boxesOfShip.size() != 0) shipsController.addShip(ship);
    }
    private boolean checkAround(Box box, List<Box> boxesOfShip, ShipsController shipsController) {
        int myX = box.getX();
        int myY = box.getY();
        for (int i = myX - ModelsPicture.IMAGE_SIZE; i <= myX + ModelsPicture.IMAGE_SIZE; i += ModelsPicture.IMAGE_SIZE) {
            for (int j = myY - ModelsPicture.IMAGE_SIZE; j <= myY + ModelsPicture.IMAGE_SIZE; j += ModelsPicture.IMAGE_SIZE) {
                Box boxFromMatrix = shipsController.getBox(shipsController.getField(), i, j);
                if (boxFromMatrix != null && boxFromMatrix.getPicture() == ModelsPicture.SHIP && !boxesOfShip.contains(boxFromMatrix)) {
                    boxesOfShip.clear();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkEndField(ShipsController shipsController) {
        List<Box> allBoxesOfFriendShip = shipsController.getAllBoxesOfShips();
        for (Box box : allBoxesOfFriendShip) {
            if (box.getPicture() == ModelsPicture.SHIP && !box.isBoxIsOpen()) {
                return false;
            }

        }
        return true;
    }
}
