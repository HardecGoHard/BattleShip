package Models;

import java.util.ArrayList;
import java.util.List;

public class ShipsController {
    private final Box[][] FIELD = new Box[ModelsPicture.COLUMNS][ModelsPicture.ROWS];
    private List<Ship> shipsOneDeck = new ArrayList<>();
    private List<Ship> shipsTwoDeck = new ArrayList<>();
    private List<Ship> shipsThreeDeck = new ArrayList<>();
    private List<Ship> shipsFourDeck = new ArrayList<>();

    public Box[][] getField() {
        return FIELD;
    }

    public void addBox(Box box) {
        FIELD[box.getX() / ModelsPicture.IMAGE_SIZE][box.getY() / ModelsPicture.IMAGE_SIZE] = box;
    }

    public List<Ship> getAllShips() {
        List<Ship> allBoxesOfShips = new ArrayList<>();
        allBoxesOfShips.addAll(shipsFourDeck);
        allBoxesOfShips.addAll(shipsThreeDeck);
        allBoxesOfShips.addAll(shipsTwoDeck);
        allBoxesOfShips.addAll(shipsOneDeck);
        return allBoxesOfShips;
    }

    public List<Box> getAllBoxesOfShips() {
        List<Box> allBoxes = new ArrayList<>();
        List<Ship> allShips = getAllShips();
        for (Ship ship : allShips) {
            allBoxes.addAll(ship.getBoxesOfShip());
        }
        return allBoxes;
    }

    public List<Ship> getShipsOneDeck() {
        return shipsOneDeck;
    }

    public List<Ship> getShipsTwoDeck() {
        return shipsTwoDeck;
    }

    public List<Ship> getShipsThreeDeck() {
        return shipsThreeDeck;
    }

    public List<Ship> getShipsFourDeck() {
        return shipsFourDeck;
    }

    public void addShip(Ship ship) {
        int countDeck = ship.getCountDeck();
        switch (countDeck) {
            case 1: {

                if (shipsOneDeck.size() < 4) {
                    shipsOneDeck.add(ship);
                    for (Box box : ship.getBoxesOfShip()) {
                        addBox(box);
                    }
                }
                break;
            }
            case 2: {
                if (shipsTwoDeck.size() < 3) {
                    shipsTwoDeck.add(ship);
                    for (Box box : ship.getBoxesOfShip()) {
                        addBox(box);
                    }
                }
                break;
            }
            case 3: {
                if (shipsThreeDeck.size() < 2) {
                    shipsThreeDeck.add(ship);
                    for (Box box : ship.getBoxesOfShip()) {
                        addBox(box);
                    }
                }
                break;
            }
            case 4: {
                if (shipsFourDeck.size() < 1) {
                    shipsFourDeck.add(ship);
                    for (Box box : ship.getBoxesOfShip()) {
                        addBox(box);
                    }
                }
                break;
            }
        }
    }

    public Box getBox(Box[][] field, int x, int y) {
        int i = x / ModelsPicture.IMAGE_SIZE;
        int j = y / ModelsPicture.IMAGE_SIZE;
        int lenght = field.length - 1;
        if (!(i > lenght || j > lenght)) {
            return field[i][j];
        }
        return null;
    }

    public void openAllBoxesAroundShip(Ship ship) {
        Box startBox = ship.getBoxesOfShip().get(0);
        Box endBox = ship.getBoxesOfShip().get(ship.getCountDeck() - 1);
        for (int i = startBox.getX() - ModelsPicture.IMAGE_SIZE; i <= startBox.getX() + ModelsPicture.IMAGE_SIZE; i += ModelsPicture.IMAGE_SIZE) {
            for (int j = startBox.getY() - ModelsPicture.IMAGE_SIZE; j <= startBox.getY() + ModelsPicture.IMAGE_SIZE; j += ModelsPicture.IMAGE_SIZE) {
                Box box = getBox(FIELD, i, j);
                if (box != null) box.setBoxIsOpen(true);
            }
        }
        for (int i = endBox.getX() - ModelsPicture.IMAGE_SIZE; i <= endBox.getX() + ModelsPicture.IMAGE_SIZE; i += ModelsPicture.IMAGE_SIZE) {
            for (int j = endBox.getY() - ModelsPicture.IMAGE_SIZE; j <= endBox.getY() + ModelsPicture.IMAGE_SIZE; j += ModelsPicture.IMAGE_SIZE) {
                Box box = getBox(FIELD, i, j);
                if (box != null) box.setBoxIsOpen(true);
            }
        }
    }

    public void openBoxAroundBoxOfShip(int x, int y, boolean isHorizontalPlacement) {
        if (isHorizontalPlacement) {
            Box boxUp = getBox(FIELD, x, y - ModelsPicture.IMAGE_SIZE);
            if (boxUp != null) boxUp.setBoxIsOpen(true);
            Box boxDown = getBox(FIELD, x, y + ModelsPicture.IMAGE_SIZE);
            if (boxDown != null) boxDown.setBoxIsOpen(true);
        }
        else {
            Box boxLeft = getBox(FIELD, x - ModelsPicture.IMAGE_SIZE, y);
            if (boxLeft != null) boxLeft.setBoxIsOpen(true);
            Box boxRight = getBox(FIELD, x + ModelsPicture.IMAGE_SIZE, y);
            if (boxRight != null) boxRight.setBoxIsOpen(true);
        }
    }

    public Ship getShip(Box boxShot) {
        for (Ship ship : getAllShips()) {
            for (Box box : ship.getBoxesOfShip()) {
                if (boxShot.getX() == box.getX() && boxShot.getY() == box.getY()) {
                    return ship;
                }
            }
        }
        return null;
    }
}
