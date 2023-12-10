package edu.project4.transformation;

import edu.project4.pojo.Point;

public class DiskTransformation implements ITransformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();

        double prefValue = Math.atan(y / x) / Math.PI;
        double trigonometricValue = Math.PI * Math.sqrt(x * x + y * y);

        double newX = prefValue * Math.sin(trigonometricValue);
        double newY = prefValue * Math.cos(trigonometricValue);

        return new Point(newX, newY);
    }
}
