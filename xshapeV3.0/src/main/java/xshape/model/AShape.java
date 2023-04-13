package xshape.model;

import xshape.Element;

import java.awt.geom.Point2D;

public abstract class AShape implements Shape {
    private Point2D.Double position;
    private double rotation;
    protected int colorR, colorB, colorG;
    private Point2D.Double rotationCenter;
    private double translationX, translationY;

    public AShape(Point2D.Double pos, double rot, Point2D.Double roC, double trX,
                  double trY, int red, int green, int blue){
        this.position = pos;
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
    public Object draw() {return null;}

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
}
