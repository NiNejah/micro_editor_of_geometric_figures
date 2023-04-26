package xshape.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.awt.geom.Point2D;
import java.util.List;

public class PolygonFx extends xshape.model.Polygon {

    Polygon _adapted = new Polygon();

    public PolygonFx(int sides, double length, Point2D.Double pos, double rot, double trX,
                   double trY, double red, double green, double blue){
        super(sides, length, pos, rot, trX, trY, red, green, blue);
    }

    public PolygonFx(xshape.model.Polygon p){
        super(p);
    }

    public Object draw(){
        List<Point2D.Double> positions = sidesPosition();
        for(Point2D.Double pos: positions){
            _adapted.getPoints().addAll(pos.getX(), pos.getY());
        }
        _adapted.setFill(Color.color(colorR, colorG, colorB));
        return _adapted;
    }

    public Shape clone(){
        return new PolygonFx(this);
    }

    public void update(){
        _adapted.getPoints().clear();
        List<Point2D.Double> positions = sidesPosition();
        for(Point2D.Double pos: positions){
            _adapted.getPoints().addAll(pos.getX(), pos.getY());
        }
        _adapted.setFill(Color.color(colorR, colorG, colorB));
    }
}
