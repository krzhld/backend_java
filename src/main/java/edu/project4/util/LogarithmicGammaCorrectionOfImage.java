package edu.project4.util;

import edu.project4.pojo.FractalImage;
import edu.project4.pojo.Pixel;

public class LogarithmicGammaCorrectionOfImage implements ImageProcessor {
    @Override
    public void process(FractalImage image) {
        double max = 0;
        double gamma = 2.2;

        for (int i = 0; i < image.height; ++i) {
            for (int j = 0; j < image.width; ++j) {
                Pixel pixel = image.getPixel(j, i);
                if (pixel.hitCount != 0) {
                    pixel.normal = Math.log10(pixel.hitCount);

                    if (pixel.normal > max) {
                        max = pixel.normal;
                    }
                }
            }
        }

        for (int i = 0; i < image.height; ++i) {
            for (int j = 0; j < image.width; ++j) {
                Pixel pixel = image.getPixel(j, i);

                pixel.normal /= max;

                pixel.rgb.setR((int) (pixel.rgb.getR() * Math.pow(pixel.normal, (1d / gamma))));
                pixel.rgb.setG((int) (pixel.rgb.getG() * Math.pow(pixel.normal, (1d / gamma))));
                pixel.rgb.setB((int) (pixel.rgb.getB() * Math.pow(pixel.normal, (1d / gamma))));
            }
        }
    }
}
