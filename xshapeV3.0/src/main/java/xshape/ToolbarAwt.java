package xshape;

import xshape.model.*;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ToolbarAwt extends RectangleAwt implements xshape.Toolbar{

    private List<Shape> shapes = new ArrayList<>();

    private ToolbarStyle _style  = ToolbarStyle.VERTICAL;

    private ShapeFactory _factory;

    public ToolbarAwt(double width, double height, Point2D.Double pos, ShapeFactory shapeFactory) {
        super(width,height,0,0,pos,0,125,125,125);
        _factory = shapeFactory ;
    }
    public void addShape(Shape s){
        shapes.add(s);
    }
    @Override
    public Object draw() {
        JPanel jp = (JPanel) super.draw();
        for (Shape shape: shapes){
            jp.add((JPanel) shape.draw());
        }
        return jp;
    }
}
