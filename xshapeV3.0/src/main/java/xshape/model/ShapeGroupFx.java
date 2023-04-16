package xshape.model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import xshape.Element;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShapeGroupFx extends ShapeGroup{

    private Group group;

    public ShapeGroupFx(List<Shape> shapes){
        super(shapes);
    }

    public ShapeGroupFx(){
        super();
    }

    public ShapeGroupFx(ShapeGroupFx sg){
        super(sg);
    }
    @Override
    public Object draw() {
        return group;
    }

    public void add(Shape s){
        if(group == null) group = new Group();
        this.childShapes.add(s);
        group.getChildren().add((Node) s.draw());
    }

    public void remove(Shape s){
        this.childShapes.remove(s);
        group.getChildren().remove((Node) s.draw());
    }

    @Override
    public Shape clone() {
        return new ShapeGroupFx(this);
    }
}
