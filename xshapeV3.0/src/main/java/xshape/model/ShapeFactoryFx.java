package xshape.model;

import java.awt.geom.Point2D;

public class ShapeFactoryFx implements ShapeFactory{

    public ShapeFactoryFx(){}
    @Override
    public Rectangle createRectangle(double posX, double posY, double height, double width) {
        return new RectangleFx(width, height, 0, 0, new Point2D.Double(posX, posY),
                0, 255, 0, 0);
    }
}
