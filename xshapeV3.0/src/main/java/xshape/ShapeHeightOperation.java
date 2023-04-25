package xshape;

import xshape.model.Rectangle;
import xshape.model.Shape;

public class ShapeHeightOperation implements ShapeOperation{
    private Rectangle shape;

    private double height, oldHeight;

    public ShapeHeightOperation(Rectangle shape, double height){
        this.shape = shape;
        this.height = height;
        this.oldHeight = shape.size().getY();
    }

    @Override
    public void execute() {
        shape.setHeight(height);
        shape.update();
    }

    @Override
    public void undo() {
        shape.setHeight(oldHeight);
        shape.update();
    }
}
