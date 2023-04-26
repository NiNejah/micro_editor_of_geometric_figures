package xshape.model;

import javafx.scene.Group;
import xshape.Element;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ShapeGroup implements Shape, Serializable {
    // TODO
    protected List<Shape> childShapes;

    public ShapeGroup(List<Shape> shapes){
        this.childShapes = new ArrayList<>();
        for(Shape s: shapes){
            add(s);
        }
    }

    public ShapeGroup(){
        this.childShapes = new ArrayList<>();
    }

    public ShapeGroup(ShapeGroup sg){
        this.childShapes = new ArrayList<>();
        if(sg != null){
            List<Shape> list = sg.getChilds();
            for(Shape s: list){
                add(s.clone());
            }
        }
    }

    public List<String> editableParameters(){
        List<String> parameters = Arrays.asList(new String[]{"Rotation"});
        return parameters;
    }

    @Override
    public double[] getRGB() {
        return new double[0];
    }

    public void add(Shape s){
        this.childShapes.add(s);
    }

    public void remove(Shape s){
        this.childShapes.remove(s);
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

    public double maxX(){
        double max = 0;
        for(Shape s: childShapes){
            if(s.position().getX() > max) max = s.position().getX();
        }
        return max;
    }

    public double maxY(){
        double max = 0;
        for(Shape s: childShapes){
            if(s.position().getY() > max) max = s.position().getY();
        }
        return max;
    }

    public double getWidth(){ return maxX() - minX(); }
    public double getHeight(){ return maxY() - minY(); }

    public void removeAll(){
        this.childShapes.clear();
    }

    public int groupSize(){ return this.childShapes.size(); }

    public List<Shape> getChilds(){
        return this.childShapes;
    }

    public void setPosition(Point2D.Double position) {
        HashMap<Shape, Point2D> newPositions = new HashMap<>();
        for(Shape shape: childShapes){
            if(shape instanceof ShapeGroup){
                shape.setPosition(position);
            } else {
                Point2D offset = shape.offset(position());
                Point2D newPosition = new Point2D.Double(position.getX() - offset.getX(),
                        position.getY() - offset.getY());
                newPositions.put(shape, newPosition);
            }
        }
        for(Shape shape: childShapes){
            if(!(shape instanceof ShapeGroup))
                shape.setPosition((Point2D.Double) newPositions.get(shape));
        }
        update();
    }

    public void update() {
        for(Shape shape: childShapes){
            shape.update();
        }
    }

    public Point2D offset(Point2D pos){
        return null;
    }

    @Override
    public Point2D position() {
        return new Point2D.Double((maxX() + minX())/2, (maxY() + minY())/2);
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
        for(Shape s: childShapes){
            s.rotation(angle);
        }
    }

    @Override
    public Point2D rotationCenter() {
        return null;
    }

    @Override
    public void rotationCenter(Point2D.Double centerOfRotation) {

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
    public void setColor(double r, double g, double b) {
        for (Shape s: childShapes){
            s.setColor(r, g, b);
        }
    }

    public Object draw() {return null;}

    public Shape clone() {
        return new ShapeGroup(this);
    }

    public void setGenericChilds(){
        ArrayList<Shape> shapes = new ArrayList<>();
        for(Shape s: childShapes){
            if(s instanceof Rectangle){
                Rectangle rect = new Rectangle((Rectangle) s);
                shapes.add(rect);
            } else if(s instanceof Polygon){
                Polygon poly = new Polygon((Polygon) s);
                shapes.add(poly);
            } else if(s instanceof ShapeGroup){
                ShapeGroup sg = new ShapeGroup((ShapeGroup) s);
                sg.setGenericChilds();
                shapes.add(sg);
            }
        }
        removeAll();
        for(Shape s: shapes){
            add(s);
        }
    }
}
