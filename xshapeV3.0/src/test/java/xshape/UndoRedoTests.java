package xshape;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import xshape.model.Polygon;
import xshape.model.Rectangle;

import java.awt.geom.Point2D;

/**
 * Unit test for simple App.
 */
public class UndoRedoTests
{
    Rectangle rectangle = new Rectangle(20, 20, 0, 0, new Point2D.Double(50, 50), 0, 1, 0, 0);

    @Test
    public void testColor(){
        Rectangle rect = (Rectangle) rectangle.clone();
        double[] oldrgb = rect.getRGB();
        double[] rgb = {0, 1, 0};
        ShapeColorOperation operation = new ShapeColorOperation(rect, 0, 1, 0);
        operation.execute();
        for(int i = 0; i < 3; i++){
            assertEquals(rect.getRGB()[i], rgb[i]);
        }
        operation.undo();
        for(int i = 0; i < 3; i++){
            assertEquals(rect.getRGB()[i], oldrgb[i]);
        }
    }

    @Test
    public void testSize(){
        Rectangle rect = (Rectangle) rectangle.clone();
        double oldWidth = rect.size().getX(); double oldHeight = rect.size().getY();
        double newSize = 40;
        RectangleWidthOperation operation = new RectangleWidthOperation(rect, newSize);
        RectangleHeightOperation operation2 = new RectangleHeightOperation(rect, newSize);
        operation.execute(); operation2.execute();
        assertEquals(rect.size().getX(), newSize);
        assertEquals(rect.size().getY(), newSize);
        operation.undo(); operation2.undo();
        assertEquals(rect.size().getX(), oldWidth);
        assertEquals(rect.size().getY(), oldHeight);
    }

    @Test
    public void testRotation(){
        Rectangle rect = (Rectangle) rectangle.clone();
        double oldRotation = 0; double rotation = 30;
        ShapeRotationOperation operation = new ShapeRotationOperation(rect, rotation);
        operation.execute();
        assertEquals(rect.rotation(), rotation);
        operation.undo();
        assertEquals(rect.rotation(), oldRotation);
    }

    @Test
    public void testPolygon(){
        Polygon polygon = new Polygon(5, 20, new Point2D.Double(100, 100), 0, 0, 0, 1, 0, 0);
        double oldLenght = 20; double length = 15;
        PolygonNumberSidesOperation operation = new PolygonNumberSidesOperation(polygon, 7);
        PolygonSideLengthOperation operation2 = new PolygonSideLengthOperation(polygon, length);
        operation.execute(); operation2.execute();
        assertEquals(7, polygon.getNbSides());
        assertEquals(length, polygon.getSideLength());
        operation.undo(); operation2.undo();
        assertEquals(5, polygon.getNbSides());
        assertEquals(oldLenght, polygon.getSideLength());
    }
}
