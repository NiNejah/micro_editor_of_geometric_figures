package xshape.model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import xshape.Element;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ShapeGroupFx implements Shape{

    private List<Shape> childShapes;

    private Group group;

    public ShapeGroupFx(List<Shape> shapes){
        this.childShapes = new ArrayList<>();
        this.group = new Group();
        for(Shape s: shapes){
            add(s);
        }
    }

    public ShapeGroupFx(){
        this.childShapes = new ArrayList<>();
        this.group = new Group();
    }

    public ShapeGroupFx(ShapeGroupFx sg){
        this.childShapes = new ArrayList<>();
        this.group = new Group();
        if(sg != null){
            List<Shape> list = sg.getChilds();
            for(Shape s: list){
                add(s.clone());
            }
        }
    }
    @Override
    public Object draw() {
        return group;
    }

    public double minX(){
        double min = 1000000;
        for(Shape s: childShapes){
            if(s.position().getX() < min) min = s.position().getX();
        }
        return min;
    }

    public double minY(){
        double min = 1000000;
        for(Shape s: childShapes){
            if(s.position().getY() < min) min = s.position().getY();
        }
        return min;
    }

    public void add(Shape s){
        this.childShapes.add(s);
        group.getChildren().add((Node) s.draw());
    }

    public void remove(Shape s){
        this.childShapes.remove(s);
        group.getChildren().remove((Node) s.draw());
    }

    public void removeAll(){
        this.childShapes.clear();
    }

    public int groupSize(){ return this.childShapes.size(); }

    public List<Shape> getChilds(){
        return this.childShapes;
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
        return new ShapeGroupFx(this);
    }
}
