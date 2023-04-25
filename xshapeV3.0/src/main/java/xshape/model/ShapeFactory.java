package xshape.model;

import java.util.List;

public interface ShapeFactory {
    Rectangle createRectangle(double posX, double posY, double height, double width);

    ShapeGroup createShapeGroup();

    ShapeGroup createShapeGroup(List<Shape> shapes);

    Polygon createPolygon(double posX, double posY, int sides, double sideLength);
}
