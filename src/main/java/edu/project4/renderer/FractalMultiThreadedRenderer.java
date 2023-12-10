package edu.project4.renderer;

import edu.project4.pojo.FractalImage;
import edu.project4.pojo.Pixel;
import edu.project4.pojo.Point;
import edu.project4.pojo.RGB;
import edu.project4.transformation.AffineTransformation;
import edu.project4.transformation.ITransformation;
import edu.project4.transformation.SphericalTransformation;
import edu.project4.util.Randomizer;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FractalMultiThreadedRenderer implements IFractalFlameRenderer {
    private final Random randomizer = new Random();
    private final ArrayList<AffineTransformation> affineTransformations = new ArrayList<>();
    private final ArrayList<ITransformation> nonLinealTransformations = new ArrayList<>();
    private final double xMin = -1.5;
    private final double xMax = 1.5;
    private final double yMin = -1.5;
    private final double yMax = 1.5;

    public FractalMultiThreadedRenderer(int countOfAffineTransformations) {
        for (int i = 0; i < countOfAffineTransformations; ++i) {
            affineTransformations.add(new AffineTransformation());
        }

        nonLinealTransformations.add(new SphericalTransformation());
    }

    @Override
    public void render(FractalImage image, int samples, int iterationsPerSample) {
        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int sample = 0; sample < samples; ++sample) {
            double x = Randomizer.nextDouble(xMin, xMax);
            double y = Randomizer.nextDouble(yMin, yMax);
            Point P = new Point(x, y);

            service.submit(new IterationThread(image, iterationsPerSample, P));
        }

        service.shutdown();
    }

    private class IterationThread implements Runnable {
        private FractalImage image;
        private int iterationPerSample;
        private Point p;

        public IterationThread(FractalImage image, int iterationPerSample, Point p) {
            this.image = image;
            this.iterationPerSample = iterationPerSample;
            this.p = p;
        }

        @Override
        public void run() {
            int height = image.height;
            int width = image.width;
            for (int step = -20; step < iterationPerSample; ++step) {
                for (var nonLinealTransformation : nonLinealTransformations) {
                    int affineTransformationIndex = randomizer.nextInt(affineTransformations.size());
                    AffineTransformation affineTransformation = affineTransformations.get(affineTransformationIndex);
                    p = affineTransformation.apply(p);

                    Point finalP = nonLinealTransformation.apply(p);

                    double Xf = finalP.x();
                    double Yf = finalP.y();

                    int newX = width - (int) (((xMax - Xf) / (xMax - xMin)) * width);
                    int newY = height - (int) (((yMax - Yf) / (yMax - yMin)) * height);

                    if (step < 0 || Xf < xMin || Xf > xMax
                        || Yf < yMin || Yf > yMax
                        || newX >= width || newY >= height) {
                        continue;
                    }

                    Pixel pixel = image.getPixel(newX, newY);

                    if (pixel.hitCount == 0) {
                        pixel.rgb = affineTransformation.rgb;
                    } else {
                        RGB oldRGB = pixel.rgb;
                        RGB defaultRGB = affineTransformation.rgb;
                        int newR = (oldRGB.getR() + defaultRGB.getR()) / 2;
                        int newG = (oldRGB.getG() + defaultRGB.getG()) / 2;
                        int newB = (oldRGB.getB() + defaultRGB.getB()) / 2;

                        pixel.rgb = new RGB(newR, newG, newB);
                    }

                    pixel.hitCount++;
                }
            }
        }
    }
}
