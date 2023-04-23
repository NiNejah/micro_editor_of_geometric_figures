package xshape.model;

import xshape.Element;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AShape implements Shape, Serializable {
    private Point2D.Double position;
    private double rotation;
    protected double colorR, colorB, colorG;
    private Point2D.Double rotationCenter;
    private double translationX, translationY;

    public AShape(Point2D.Double pos, double rot, Point2D.Double roC, double trX,
                  double trY, double red, double green, double blue){
        this.position = pos;
        this.rotationCenter = pos;
        this.rotation = rot;
        this.colorR = red; this.colorB = blue; this.colorG = green;
        this.rotationCenter = roC;
        this.translationX = trX; this.translationY = trY;
    }

    public AShape(AShape a){
        if(a != null){
            this.position = a.position;
            this.rotation = a.rotation;
            this.colorR = a.colorR; this.colorB = a.colorB; this.colorG = a.colorG;
            this.rotationCenter = a.rotationCenter;
            this.translationX = a.translationX; this.translationY = a.translationY;
        }
    }

    @Override
    public List<String> editableParameters() {
        List<String> parameters = Arrays.asList(new String[]{"rotation", "color"});
        return parameters;
    }

    @Override
    public Object draw() {return null;}

    public double[] getRGB(){
        double[] rgb = new double[]{colorR, colorG, colorB};
        return rgb;
    }

    @Override
    public void setColor(double r, double g, double b) {
        this.colorR = r;
        this.colorG = g;
        this.colorB = b;
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
        return this.position;
    }

    @Override
    public Element position(Point2D position) {
        return null;
    }

    @Override
    public void setPosition(Point2D.Double position) {
        this.position = position;
    }

    @Override
    public Shape translate(Point2D vec) {
        return null;
    }

    @Override
    public double rotation() {
        return this.rotation;
    }

    @Override
    public void rotation(double angle) {
        this.rotation = angle;
    }

    @Override
    public Point2D rotationCenter() {
        return this.rotationCenter;
    }

    @Override
    public void rotationCenter(Point2D.Double centerOfRotation) {
        this.rotationCenter = centerOfRotation;
    }

    @Override
    public Shape clone() {
        return null;
    }

    public Point2D offset(Point2D pos){
        double offsetX = pos.getX() - position.x;
        double offsetY = pos.getY() - position.y;
        return new Point2D.Double(offsetX, offsetY);
    }

    public void update(){}
}
