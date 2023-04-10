package xshape;

import java.awt.geom.Point2D;

public abstract class Rectangle extends ElementAbstract implements Shape {

    @Override
    public Shape translate(Point2D vec) {
        position().setLocation(position().getX() + vec.getX(),
                position().getY() + vec.getY());
        return this;
    }
}
