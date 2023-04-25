package xshape;

import xshape.model.Rectangle;
import xshape.model.Shape;

public class ShapeWidthOperation implements ShapeOperation{
    private Rectangle shape;

    private double width, oldWidth;

    public ShapeWidthOperation(Rectangle shape, double width){
        this.shape = shape;
        this.width = width;
        this.oldWidth = shape.size().getX();
    }

    @Override
    public void execute() {
        shape.setWidth(width);
        shape.update();
    }

    @Override
    public void undo() {
        shape.setWidth(oldWidth);
        shape.update();
    }
}
