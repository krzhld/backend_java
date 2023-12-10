package edu.project4.transformation;

import edu.project4.pojo.Point;

public class SinusoidalTransformation implements ITransformation {
    @Override
    public Point apply(Point point) {
        double newX = Math.sin(point.x());
        double newY = Math.sin(point.y());

        return new Point(newX, newY);
    }
}
