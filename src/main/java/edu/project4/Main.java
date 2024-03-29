package edu.project4;

import edu.project4.pojo.Display;
import edu.project4.pojo.FractalImage;
import edu.project4.pojo.ImageFormat;
import edu.project4.renderer.FractalFlameRenderer;
import edu.project4.renderer.FractalMultiThreadedRenderer;
import edu.project4.renderer.IFractalFlameRenderer;
import edu.project4.util.ImageProcessor;
import edu.project4.util.LogarithmicGammaCorrectionOfImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
public class Main {
    private Main() {

    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        ImageProcessor imageProcessor = new LogarithmicGammaCorrectionOfImage();

        // Without MultiThread
        // 2291 ms
        IFractalFlameRenderer fractalFlameGenerator = new FractalFlameRenderer(20);
        long startTime = System.currentTimeMillis();
        FractalImage image = new FractalImage(1920, 1080);
        fractalFlameGenerator.render(image, 1000, 500);
        imageProcessor.process(image);
        Display.createImage(image, "C:\\Users\\krzhld", "image_without_multithread", ImageFormat.BMP);
        LOGGER.info("Result without multiThread: "
            + (System.currentTimeMillis() - startTime));

        // With MultiThread
        // 406 ms
        fractalFlameGenerator = new FractalMultiThreadedRenderer(20);
        startTime = System.currentTimeMillis();
        image = new FractalImage(1920, 1080);
        fractalFlameGenerator.render(image, 1000, 500);
        imageProcessor.process(image);
        Display.createImage(image, "C:\\Users\\krzhld", "image_with_multithread", ImageFormat.BMP);
        LOGGER.info("Result with multiThread: "
            + (System.currentTimeMillis() - startTime));
    }
}
