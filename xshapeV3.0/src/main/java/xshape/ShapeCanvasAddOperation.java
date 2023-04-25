package xshape;

import xshape.model.Shape;

public class ShapeCanvasAddOperation implements ShapeOperation{
    private XShape app;
    private Shape shape;

    public ShapeCanvasAddOperation(Shape shape, FxApp app) {
        this.shape = shape;
        this.app = app;
    }

    @Override
    public void execute() {
        app.addShapeToCanvas(shape);
    }

    @Override
    public void undo() {
        app.removeShapeFromCanvas(shape);
    }
}
