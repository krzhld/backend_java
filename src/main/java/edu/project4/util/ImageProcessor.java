package edu.project4.util;

import edu.project4.pojo.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
