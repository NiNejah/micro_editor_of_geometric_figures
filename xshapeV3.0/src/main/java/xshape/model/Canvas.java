package xshape.model;

import java.util.ArrayList;
import java.util.List;

public class Canvas {

    private ArrayList<Shape> shapes;

    public Canvas(){
        this.shapes = new ArrayList<>();
    }

    public List<Object> draw(){
        ArrayList<Object> graphicShapes = new ArrayList<>();
        for(Shape s: shapes){
            graphicShapes.add(s.draw());
        }
        return graphicShapes;
    }

    public List<Shape> getShapes(){
        return this.shapes;
    }

    public void addShape(Shape shape){
        if(shape != null){
            this.shapes.add(shape);
        }
    }

    public void removeShape(Shape shape){
        if(shape != null){
            this.shapes.remove(shape);
        }
    }

    public boolean isEmpty(){
        return this.shapes.size() == 0;
    }
}
