package xshape;

public class ElementFactoryAwt implements ElementFactory {
    public ElementFactoryAwt() {
    }
    @Override
    public Rectangle createRectangle(double posX, double posY, 
    double height, double width) {
        return new RectangleAwt(posX, posY, height, width);
    }
}
