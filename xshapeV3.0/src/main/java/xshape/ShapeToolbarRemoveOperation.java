package xshape;

import xshape.model.Shape;

public class ShapeToolbarRemoveOperation implements ShapeOperation{
    private XShape app;
    private Shape shape;

    public ShapeToolbarRemoveOperation(Shape shape, FxApp app) {
        this.shape = shape;
        this.app = app;
    }

    @Override
    public void execute() {
        app.removeShapeFromToolbar(shape);
    }

    @Override
    public void undo() {
        app.addShapeToToolbar(shape);
    }
}
