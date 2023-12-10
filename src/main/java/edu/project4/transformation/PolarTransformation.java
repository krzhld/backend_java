package edu.project4.transformation;

import edu.project4.pojo.Point;

public class PolarTransformation implements ITransformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double newX = Math.atan(y / x) / Math.PI;
        double newY = Math.sqrt(x * x + y * y) - 1;

        return new Point(newX, newY);
    }
}
