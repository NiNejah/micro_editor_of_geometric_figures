package xshape.model;

import java.awt.geom.Point2D;
import java.util.List;

public class ShapeFactoryAwt implements ShapeFactory{

    public ShapeFactoryAwt(){}
    @Override
    public Rectangle createRectangle(double posX, double posY, double height, double width) {
        return new RectangleAwt(width, height, 0, 0, new Point2D.Double(posX, posY),
                0, 255, 0, 0);
    }

    @Override
    public ShapeGroup createShapeGroup() {
        // TODO
        return null;
    }

    @Override
    public ShapeGroup createShapeGroup(List<Shape> shapes) {
        // TODO
        return null;
    }
}
