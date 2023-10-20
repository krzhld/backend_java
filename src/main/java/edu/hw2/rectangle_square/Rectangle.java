package edu.hw2.rectangle_square;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle() {

    }

    public Rectangle(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public Rectangle setWidth(int width) {
        return new Rectangle(width, this.height);
    }

    public Rectangle setHeight(int height) {
        return new Rectangle(this.width, height);
    }

    public double area() {
        return width * height;
    }
}
