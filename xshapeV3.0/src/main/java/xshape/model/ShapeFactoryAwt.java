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
    public Rectangle createRectangle(Rectangle r) {
        return new RectangleAwt(r);
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

    @Override
    public Polygon createPolygon(double posX, double posY, int sides, double sideLength) {
        return new PolygonAwt(sides, sideLength, new Point2D.Double(posX, posY), 0, 0, 0, 255, 0, 0);
    }

    @Override
    public Polygon createPolygon(Polygon p) {
        return new PolygonAwt(p);
    }
}
