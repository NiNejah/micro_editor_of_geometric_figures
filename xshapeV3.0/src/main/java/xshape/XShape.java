package xshape;

import javafx.scene.control.ToolBar;
import xshape.model.Canvas;
import xshape.model.Shape;
import xshape.model.ShapeFactory;

import java.awt.geom.Point2D;

public abstract class XShape {
    protected ElementFactory _factory = null;

    protected ShapeFactory _shapefactory = null;

    protected Canvas canvas;

    protected ToolBar toolbarV;

    protected ToolBar toolbarH;

    // TODO : remove all of that to a Builder :
    private int BTN_SIZE = 40 ;
    private int BTN_MARGE = 25 ;

    public XShape(){
        this.canvas = new Canvas();
    }

    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract void createFactories();
    //Handler to start the GUI
    void run(){
        createFactories();
        createUI();
        draw();
    }

    protected void createScene() {
        Shape shape = _shapefactory.createRectangle(100, 100, 50, 50);
        shape.translate(new Point2D.Double(100, 50));
        addShapeToCanvas(shape);
        Shape shape2 = _shapefactory.createRectangle(250, 250, 75, 20);
        addShapeToCanvas(shape2);
        //Element saveBtn =  _factory.createButton(BTN_MARGE,20,BTN_SIZE,BTN_SIZE,"Save","save.png");
        //Element doBtn =  _factory.createButton((2*BTN_MARGE)+BTN_SIZE,20,BTN_SIZE,BTN_SIZE,"do","redo.png");
    }

    protected abstract void addShapeToCanvas(Shape shape);

    protected abstract void addShapeToToolbar(Shape shape);

    protected abstract void removeShapeFromCanvas(Shape shape);

    protected abstract void removeShapeFromToolbar(Shape shape);

    protected abstract void createUI();

    public void draw() {
        if (this.canvas.isEmpty()) {
            createScene();
        }
    }

}
