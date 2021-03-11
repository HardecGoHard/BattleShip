package Models;

import Models.ModelsPicture;

import java.util.Objects;

public class Box {
    private int x;
    private int y;
    private ModelsPicture picture;
    private boolean boxIsOpen = false;

    public Box(int x, int y, ModelsPicture picture) {
        this.x = x;
        this.y = y;
        this.picture = picture;
    }
    public Box(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isBoxIsOpen() {
        return boxIsOpen;
    }

    public void setBoxIsOpen(boolean boxIsOpen) {
        this.boxIsOpen = boxIsOpen;
    }


    public ModelsPicture getPicture() {
        return picture;
    }

    public void setPicture(ModelsPicture picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return x == box.x &&
                y == box.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
