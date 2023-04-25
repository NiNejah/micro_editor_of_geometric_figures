package xshape;

import xshape.model.Shape;

public class ShapeColorOperation implements ShapeOperation{
    private Shape shape;

    private double oldRed, oldGreen, oldBlue;

    private double red, green, blue;
    public ShapeColorOperation(Shape shape, double r, double g, double b){
        this.shape = shape;
        this.red = r; this.green = g; this.blue = b;
        this.oldRed = shape.getRGB()[0]; this.oldGreen = shape.getRGB()[1]; this.oldBlue = shape.getRGB()[2];
    }

    @Override
    public void execute() {
        shape.setColor(red, green, blue);
        shape.update();
    }

    @Override
    public void undo() {
        shape.setColor(oldRed, oldGreen, oldBlue);
        shape.update();
    }
}
