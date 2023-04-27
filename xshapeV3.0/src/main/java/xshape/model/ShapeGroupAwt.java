package xshape.model;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class ShapeGroupAwt extends ShapeGroup{

    public ShapeGroupAwt(List<Shape> shapes){
        super(shapes);
    }

    public ShapeGroupAwt(){
        super();
    }

    public ShapeGroupAwt(ShapeGroup sg){
        super(sg);
    }

    public void add(Shape s){
        this.childShapes.add(s);
    }

    public void remove(Shape s){
        this.childShapes.remove(s);
    }

    public void removeGenericChilds() {
        ArrayList<Shape> shapes = new ArrayList<>();
        for (Shape s : childShapes) {
            if (s instanceof Rectangle) {
                RectangleAwt rect = new RectangleAwt((Rectangle) s);
                shapes.add(rect);
            } else if (s instanceof Polygon) {
                PolygonAwt poly = new PolygonAwt((Polygon) s);
                shapes.add(poly);
            } else if (s instanceof ShapeGroup) {
                ShapeGroupAwt sg = new ShapeGroupAwt((ShapeGroup) s);
                sg.removeGenericChilds();
                shapes.add(sg);
            }
        }
        removeAll();
        for (Shape s : shapes) {
            add(s);
        }
    }
    // TODO
}
