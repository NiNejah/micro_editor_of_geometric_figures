package xshape.model;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polygon extends AShape implements Serializable {
    private int nbSides;
    private double sideLength;

    public Polygon(int sides, double length, Point2D.Double pos, double rot, double trX,
                   double trY, double red, double green, double blue){
        super(pos, rot, pos, 0, 0, red, green, blue);
        this.nbSides = sides;
        this.sideLength = length;
    }

    public Polygon(Polygon p){
        super(p);
        this.nbSides = p.nbSides;
        this.sideLength = p.sideLength;
    }

    public void setNbSides(int nbSides) {
        this.nbSides = nbSides;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    protected List<Point2D.Double> sidesPosition(){
        ArrayList<Point2D.Double> positions = new ArrayList<>();
        double angle = Math.PI * 2 / nbSides;
        double rotationAngle = rotation();
        for(int n = 0; n < nbSides; n++){
            double posX = Math.sin(rotationAngle) * sideLength + position().getX();
            double posY = Math.cos(rotationAngle) * sideLength + position().getY();
            positions.add(new Point2D.Double(posX, posY));
            rotationAngle += angle;
        }
        return positions;
    }

    public double getSideLength(){ return this.sideLength; }

    public int getNbSides(){ return this.nbSides; }

    @Override
    public List<String> editableParameters(){
        List<String> parameters = Arrays.asList(new String[]{"Side length", "Number of sides", "Rotation", "Color"});
        return parameters;
    }

    public Shape clone(){
        return new Polygon(this);
    }
}
