package edu.project4.transformation;

import edu.project4.pojo.Coefficients;
import edu.project4.pojo.Point;
import edu.project4.pojo.RGB;

import java.util.Random;

public class AffineTransformation implements ITransformation {
    Random randomGenerator = new Random();
    Coefficients cf = new Coefficients();
    public final RGB rgb;

    public AffineTransformation() {
        this.rgb = new RGB(
            randomGenerator.nextInt(256),
            randomGenerator.nextInt(256),
            randomGenerator.nextInt(256)
        );
    }

    @Override
    public Point apply(Point point) {
        double newX = cf.getA() * point.x() + cf.getB() * point.y() + cf.getC();
        double newY = cf.getD() * point.x() + cf.getE() * point.y() + cf.getF();

        return new Point(newX, newY);
    }
}
