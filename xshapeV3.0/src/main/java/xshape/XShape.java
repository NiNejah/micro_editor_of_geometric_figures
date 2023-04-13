package xshape;

import xshape.model.Canvas;
import xshape.model.Shape;
import xshape.model.ShapeFactory;

import java.awt.geom.Point2D;

public abstract class XShape {
    protected ElementFactory _factory = null;

    protected ShapeFactory _shapefactory = null;

    protected Canvas canvas;

    protected Toolbar toolbar;

    // TODO : remove all of that to a Builder :
    private int BTN_SIZE = 40 ;
    private int BTN_MARGE = 25 ;
    private Element[] _element =null;

    public XShape(){
        this.canvas = new Canvas();
        toolbar = _factory.createToolbar(BTN_MARGE,BTN_MARGE+BTN_SIZE,400,100);
    }

    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract void createFactories();
    //Handler to start the GUI
    abstract void run();

    protected void createScene() {
        Shape shape = _shapefactory.createRectangle(100, 100, 50, 50);
        this.canvas.addShape(shape);
        Shape shape2 = _shapefactory.createRectangle(250, 250, 75, 20);
        this.canvas.addShape(shape2);
        shape.translate(new Point2D.Double(100, 50));
        Element saveBtn =  _factory.createButton(BTN_MARGE,20,BTN_SIZE,BTN_SIZE,"Save","save.png");
        Element doBtn =  _factory.createButton((2*BTN_MARGE)+BTN_SIZE,20,BTN_SIZE,BTN_SIZE,"do","redo.png");
    }

    public void draw() {
        if (canvas.isEmpty()) {
            createFactories();
            createScene();
        }

        for (Shape s: canvas.getShapes())
            s.draw();
    }

}
