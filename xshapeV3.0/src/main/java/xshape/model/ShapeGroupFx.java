package xshape.model;

import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class ShapeGroupFx extends ShapeGroup{

    private Group group;

    public ShapeGroupFx(List<Shape> shapes){
        super(shapes);
    }

    public ShapeGroupFx(){
        super();
    }

    public ShapeGroupFx(ShapeGroup sg){
        super(sg);
    }
    @Override
    public Object draw() {
        if(group == null) group = new Group();
        group.getChildren().clear();
        for(Shape s: childShapes){
            group.getChildren().add((Node) s.draw());
        }
        return group;
    }

    public void add(Shape s){
        this.childShapes.add(s);
    }

    public void remove(Shape s){
        this.childShapes.remove(s);
        group.getChildren().remove((Node) s.draw());
    }

    @Override
    public Shape clone() {
        return new ShapeGroupFx(this);
    }

    public void removeGenericChilds(){
        ArrayList<Shape> shapes = new ArrayList<>();
        for(Shape s: childShapes){
            if(s instanceof Rectangle){
                RectangleFx rect = new RectangleFx((Rectangle) s);
                shapes.add(rect);
            } else if(s instanceof Polygon){
                PolygonFx poly = new PolygonFx((Polygon) s);
                shapes.add(poly);
            } else if(s instanceof ShapeGroup){
                ShapeGroupFx sg = new ShapeGroupFx((ShapeGroup) s);
                sg.removeGenericChilds();
                shapes.add(sg);
            }
        }
        removeAll();
        for(Shape s: shapes){
            add(s);
        }
    }
}
