package xshape;

import xshape.model.Shape;

import java.awt.geom.Point2D;

public class ShapePositionOperation implements ShapeOperation{
    Shape shape;

    private Point2D.Double position, oldPosition;

    public ShapePositionOperation(Shape shape, Point2D.Double position){
        this.shape = shape;
        this.position = position;
        this.oldPosition = (Point2D.Double) shape.position();
    }


    @Override
    public void execute() {
        shape.setPosition(position);
        shape.rotationCenter(position);
        shape.update();
    }

    @Override
    public void undo() {
        shape.setPosition(oldPosition);
        shape.rotationCenter(oldPosition);
        shape.update();
    }
}
