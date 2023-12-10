package edu.project4.renderer;

import edu.project4.pojo.FractalImage;

public interface IFractalFlameRenderer {
    void render(FractalImage image, int samples, int iterationsPerSample);
}
