package xshape;

import javafx.scene.Group;

public class ElementFactoryFx implements ElementFactory {
    Group grp;
    public ElementFactoryFx(Group root) {
        grp = root;
    }
    @Override
    public Rectangle createRectangle(double posX, double posY, 
    double height, double width) {
        return new RectangleFx(posX, posY, height, width, grp);
    } 
}
