package xshape.model;

import javafx.scene.Group;
import javafx.scene.Node;
import xshape.Element;

import java.awt.geom.Point2D;
import java.util.List;

public class ShapeGroup implements Shape{

    private List<Shape> childShapes;

    private Group group;

    public ShapeGroup(List<Shape> shapes){
        for(Shape s: shapes){
            add(s);
        }
    }
    @Override
    public Object draw() {
        Group group = new Group();
        group.getChildren().removeAll();
        for(Shape shape: childShapes){
            group.getChildren().add((Node) shape.draw());
        }
        return group;
    }

    public void add(Shape s){
        this.childShapes.add(s);
    }

    @Override
    public Point2D size() {
        return null;
    }

    @Override
    public Element size(Point2D vec) {
        return null;
    }

    @Override
    public Point2D position() {
        double x = childShapes.get(0).position().getX();
        double y = childShapes.get(0).position().getY();
        for(Shape s: childShapes){
            if(s.position().getX() < x) x = s.position().getX();
            if(s.position().getY() < y) y = s.position().getY();
        }
        return new Point2D.Double(x, y);
    }

    @Override
    public Element position(Point2D position) {
        return null;
    }

    @Override
    public Shape translate(Point2D vec) {
        return null;
    }

    @Override
    public double rotation() {
        return 0;
    }

    @Override
    public void rotation(double angle) {

    }

    @Override
    public Point2D rotationCenter() {
        return null;
    }

    @Override
    public void rotationCenter(Point2D.Double centerOfRotation) {

    }

    @Override
    public void setPosition(Point2D.Double position) {

    }

    @Override
    public Shape clone() {
        return null;
    }
}
