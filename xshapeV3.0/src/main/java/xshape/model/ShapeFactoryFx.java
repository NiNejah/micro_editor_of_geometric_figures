package xshape.model;

import java.awt.geom.Point2D;
import java.util.List;

public class ShapeFactoryFx implements ShapeFactory{

    public ShapeFactoryFx(){}
    @Override
    public Rectangle createRectangle(double posX, double posY, double height, double width) {
        return new RectangleFx(width, height, 0, 0, new Point2D.Double(posX, posY),
                0, 1.0, 0, 0);
    }

    @Override
    public ShapeGroup createShapeGroup() {
        return new ShapeGroupFx();
    }

    @Override
    public ShapeGroup createShapeGroup(List<Shape> shapes) {
        return new ShapeGroupFx(shapes);
    }

    @Override
    public Polygon createPolygon(double posX, double posY, int sides, double sideLength) {
        return new PolygonFx(sides, sideLength, new Point2D.Double(posX, posY), 0, 0, 0, 1.0, 0, 0);
    }
}
