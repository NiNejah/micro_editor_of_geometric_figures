package xshape;

import xshape.model.Polygon;

public class PolygonSideLengthOperation implements ShapeOperation{
    private Polygon polygon;

    double length, oldLength;

    public PolygonSideLengthOperation(Polygon polygon, double length){
        this.polygon = polygon;
        this.length = length;
        this.oldLength = polygon.getSideLength();
    }

    @Override
    public void execute() {
        polygon.setSideLength(length);
        polygon.update();
    }

    @Override
    public void undo() {
        polygon.setSideLength(oldLength);
        polygon.update();
    }
}
