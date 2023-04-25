package xshape;

import xshape.model.Shape;

public class ShapeRotationOperation implements ShapeOperation{
    private Shape shape;
    private double angle, oldAngle;

    public ShapeRotationOperation(Shape shape, double angle){
        this.shape = shape;
        this.angle = angle;
        this.oldAngle = shape.rotation();
    }

    @Override
    public void execute() {
        shape.rotation(angle);
        shape.update();
    }

    @Override
    public void undo() {
        shape.rotation(oldAngle);
        shape.update();
    }
}
