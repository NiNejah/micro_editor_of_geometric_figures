package xshape;

import javafx.scene.Group;
import xshape.model.Rectangle;
import xshape.model.RectangleFx;
import xshape.model.ShapeFactoryFx;

import java.awt.geom.Point2D;

public class ElementFactoryFx implements ElementFactory {
    public ElementFactoryFx() {
    }

    @Override
    public Element createButton(double posX, double posY, double height, double width, String label , String icon) {
        return new ButtonFx(posX,posY,height,width,label,icon);
    }

    @Override
    public Toolbar createToolbar(double posX, double posY, double height, double width) {
        return new ToolbarFx(new ShapeFactoryFx());
    }

}
