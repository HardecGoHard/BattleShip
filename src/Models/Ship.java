package Models;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final int countDeck;
    private List<Box> boxesOfShip;
    private final boolean isHorizontalPlacement;

    public List<Box> getBoxesOfShip() {
        return boxesOfShip;
    }


    public int getCountDeck() {
        return countDeck;
    }

    public Ship(int countDeck, boolean isHorizontalPlacement) {
        this.countDeck = countDeck;
        this.isHorizontalPlacement = isHorizontalPlacement;
        boxesOfShip = new ArrayList<>(countDeck);
    }

    public void createHorizontalShip(int x, int y, ModelsPicture modelsPicture) {
        int pointLimitValueForPaint = (ModelsPicture.COLUMNS - countDeck) * ModelsPicture.IMAGE_SIZE;
        for (int i = 0; i < countDeck; i++) {
            Box newBox;
            if (x > pointLimitValueForPaint) {
                newBox = new Box(pointLimitValueForPaint + i * ModelsPicture.IMAGE_SIZE, y, modelsPicture);
                boxesOfShip.add(newBox);
            } else {
                newBox = new Box((x + i * ModelsPicture.IMAGE_SIZE), y, modelsPicture);
                boxesOfShip.add(newBox);
            }
        }
    }

    public void createVerticalShip(int x, int y,ModelsPicture modelsPicture) {
        int pointStartPaint = (ModelsPicture.ROWS - countDeck) * ModelsPicture.IMAGE_SIZE;
        for (int i = 0; i < countDeck; i++) {
            Box newBox;
            if (pointStartPaint < y) {
                newBox = new Box(x, pointStartPaint + i * ModelsPicture.IMAGE_SIZE, modelsPicture );
                boxesOfShip.add(newBox);
            } else {
                newBox = new Box(x, (y + i * ModelsPicture.IMAGE_SIZE), modelsPicture);
                boxesOfShip.add(newBox);
            }
        }
    }

    public boolean isHorizontalPlacement() {
        return isHorizontalPlacement;
    }
}
