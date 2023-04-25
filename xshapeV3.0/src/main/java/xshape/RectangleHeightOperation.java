package xshape;

import xshape.model.Rectangle;

public class RectangleHeightOperation implements ShapeOperation{
    private Rectangle shape;

    private double height, oldHeight;

    public RectangleHeightOperation(Rectangle shape, double height){
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
