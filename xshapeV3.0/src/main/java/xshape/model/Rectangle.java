package xshape.model;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Rectangle extends AShape implements Serializable {

    protected double width, height;
    protected double arcWidth, arcHeight;
    public Rectangle(double width, double height, double arcWidth, double arcHeight,
            Point2D.Double pos, double rot, double red, double green, double blue) {
        super(pos, rot, pos, 0, 0, red, green, blue);
        this.width = width; this.height = height;
        this.arcWidth = arcWidth; this.arcHeight = arcHeight;
    }

    public Rectangle(Rectangle r){
        super(r);
        this.width = r.width; this.height = r.height;
        this.arcWidth = r.arcWidth; this.arcHeight = r.arcHeight;
    }
    @Override
    public Shape translate(Point2D vec) {
        position().setLocation(position().getX() + vec.getX(),
                position().getY() + vec.getY());
        return this;
    }

    public void setColor(double r, double g, double b){
        if(r >= 0 && r <= 256) this.colorR = r;
        if(g >= 0 && g <= 256) this.colorG = g;
        if(b >= 0 && b <= 256) this.colorB = b;
    }

    @Override
    public Point2D size(){
        return new Point2D.Double(this.width, this.height);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height){
        this.height = height;
    }

    @Override
    public List<String> editableParameters(){
        List<String> parameters = Arrays.asList(new String[]{"Width", "Height", "Rotation", "Color"});
        return parameters;
    }

    public Shape clone(){
        return new Rectangle(this);
    }
}
