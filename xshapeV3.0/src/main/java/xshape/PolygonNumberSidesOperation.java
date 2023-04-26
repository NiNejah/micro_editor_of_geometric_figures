package xshape;

import xshape.model.Polygon;

public class PolygonNumberSidesOperation implements ShapeOperation{
    private Polygon polygon;

    int nb, oldNB;

    public PolygonNumberSidesOperation(Polygon polygon, int nb){
        this.polygon = polygon;
        this.nb = nb;
        this.oldNB = polygon.getNbSides();
    }

    @Override
    public void execute() {
        polygon.setNbSides(nb);
        polygon.update();
    }

    @Override
    public void undo() {
        polygon.setNbSides(oldNB);
        polygon.update();
    }
}
