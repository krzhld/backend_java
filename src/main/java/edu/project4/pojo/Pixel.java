package edu.project4.pojo;

public class Pixel {
    public RGB rgb;
    public int hitCount;
    public double normal;

    public Pixel(int r, int g, int b, int hitCount) {
        rgb = new RGB(r, g, b);
        this.hitCount = hitCount;
    }
}
