package xshape;

import xshape.model.Shape;
import xshape.model.ShapeGroup;

import java.util.List;

public class ShapeGroupOperation implements ShapeOperation{
    private XShape app;

    private List<Shape> group;
    private ShapeGroup newGroup;


    public ShapeGroupOperation(XShape app, List<Shape> group){
        this.app = app;
        this.group = group;
    }

    @Override
    public void execute() {
        app.currentSelection = group;
        app.createGroup();
        newGroup = (ShapeGroup) app.canvas.getShapes().get(app.canvas.getShapes().size() - 1);
    }

    @Override
    public void undo() {
        app.destroyGroup(newGroup);
    }
}
