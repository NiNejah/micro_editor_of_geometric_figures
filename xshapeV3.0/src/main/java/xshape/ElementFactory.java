package xshape;

public interface ElementFactory {
    Rectangle createRectangle(double posX, double posY, double height, double width);
    Element createButton (double posX, double posY, double height, double width,String label,String icon);
    Toolbar createToolbar(double posX, double posY, double height, double width ) ;
}

