package xshape;

import javafx.scene.Group;

import java.awt.geom.Point2D;

public class ElementFactoryFx implements ElementFactory {
    Group grp;
    public ElementFactoryFx(Group root) {
        grp = root;
    }
    @Override
    public Rectangle createRectangle(double posX, double posY, 
    double height, double width) {
        return new RectangleFx(width, height, 0, 0, new Point2D.Double(posX, posY),
                0, 255, 0, 0, grp);
    }

    @Override
    public Element createButton(double posX, double posY, double height, double width, String label , String icon) {
        return new ButtonFx(posX,posY,height,width,label,icon,grp);
    }

    @Override
    public Toolbar createToolbar(double posX, double posY, double height, double width) {
        return new ToolbarFx(this);
    }

}
