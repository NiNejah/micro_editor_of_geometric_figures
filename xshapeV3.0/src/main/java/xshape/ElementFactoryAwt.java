package xshape;

import xshape.model.Rectangle;
import xshape.model.RectangleAwt;
import xshape.model.ShapeFactoryAwt;

import java.awt.geom.Point2D;

public class ElementFactoryAwt implements ElementFactory {
    public ElementFactoryAwt() {
    }
    public Rectangle createRectangle(double posX, double posY,
                                     double height, double width) {
        return new RectangleAwt(width, height, 0, 0, new Point2D.Double(posX, posY),
                0, 255, 0, 0);
    }

    @Override
    public Element createButton(double posX, double posY, double height, double width, String label,String icon ) {
        return new ButtonAwt(posX,posY,height,width,label,icon);
    }
    @Override
    public Toolbar createToolbar(double posX, double posY, double height, double width, ToolbarStyle style) {
        if(style == ToolbarStyle.VERTICAL){
            return new ToolbarAwt(width, height, new Point2D.Double(posX, posY),new ShapeFactoryAwt());
        }else if (style == ToolbarStyle.HORIZONTAL){
            return null ;
        }
        return null ;
    }

    @Override
    public Object createUI(Toolbar toolbarH, Toolbar toolbarV) {
        // TODO
        return null;
    }
}
