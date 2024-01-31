package edu.project4.pojo;

public class FractalImage {
    private final Pixel[][] pixels;
    public final int width;
    public final int height;

    public FractalImage(int width, int height) {
        pixels = new Pixel[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                pixels[i][j] = new Pixel(0, 0, 0, 0);
            }
        }

        this.width = width;
        this.height = height;
    }

    public Pixel getPixel(int x, int y) {
        return pixels[y][x];
    }
}
