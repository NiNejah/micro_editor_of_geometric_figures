package xshape;

import xshape.model.Shape;
import xshape.model.ShapeGroup;

import java.util.List;

public class ShapeDegroupOperation implements ShapeOperation{
    private XShape app;

    private ShapeGroup group;
    private List<Shape> shapes;

    public ShapeDegroupOperation(XShape app, ShapeGroup sg){
        this.app = app;
        this.group = sg;
        this.shapes = sg.getChilds();
    }

    @Override
    public void execute() {
        app.destroyGroup(group);
    }

    @Override
    public void undo() {
        for(Shape s: shapes){
            app.removeShapeFromCanvas(s);
        }
        app.currentSelection = shapes;
        app.createGroup();
        group = (ShapeGroup) app.canvas.getShapes().get(app.canvas.getShapes().size() - 1);
    }
}
