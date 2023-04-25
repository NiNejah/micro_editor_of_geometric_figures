package xshape;

import xshape.model.Shape;

public class ShapeToolbarAddOperation implements ShapeOperation{
    private XShape app;
    private Shape shape;

    public ShapeToolbarAddOperation(Shape shape, FxApp app) {
        this.shape = shape;
        this.app = app;
    }

    @Override
    public void execute() {
        app.addShapeToToolbar(shape);
    }

    @Override
    public void undo() {
        app.removeShapeFromToolbar(shape);
    }
}
