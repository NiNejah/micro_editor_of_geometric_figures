package xshape;

public interface ElementFactory {
    Element createButton (double posX, double posY, double height, double width,String label,String icon);
    Toolbar createToolbar(double posX, double posY, double height, double width, ToolbarStyle style) ;

    Object createUI(Toolbar toolbarH, Toolbar toolbarV);
}

