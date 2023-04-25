package xshape;

import xshape.model.Shape;

public class ShapeCanvasRemoveOperation implements ShapeOperation{
    private XShape app;
    private Shape shape;

    public ShapeCanvasRemoveOperation(Shape shape, FxApp app) {
        this.shape = shape;
        this.app = app;
    }

    @Override
    public void execute() {
        app.removeShapeFromCanvas(shape);
    }

    @Override
    public void undo() {
        app.addShapeToCanvas(shape);
    }
}
