package xshape;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import xshape.model.Canvas;
import xshape.model.Shape;
import xshape.model.ShapeFactoryFx;

import java.awt.geom.Point2D;
import java.util.HashMap;

public class FxApp extends XShape {

    // TODO ajouter observateur dans cette classe (controller)
    private HashMap<Node, Shape> graphToModel = new HashMap<>();

    private Stage stage;
    private int BTN_MARGE = 20 ;
    private int BTN_SIZE = 40 ;

    public FxApp(Stage stage){
        super();
        this.stage = stage;
    }
    @Override
    protected void createFactories() {
        this._shapefactory = new ShapeFactoryFx();
        this._factory = new ElementFactoryFx();
    }
    @Override
    void run(){
        createFactories();
        createUI();
        draw();
    }

    void createUI(){
        ToolbarFx toolbar = new ToolbarFx(_shapefactory);
        ToolBar tbFX = (ToolBar) toolbar.draw();
        this.toolbar = tbFX;
        ObservableList<Node> nodes = tbFX.getItems();
        graphToModel.put(nodes.get(0), toolbar.rectangle);
        for(Node n: nodes){
            toolbarShapeEvents(n);
        }
        FxApplication.pane.setLeft(tbFX);
        FxApplication.pane.setCenter(FxApplication._root);
    }
    public void draw(){
        if (this.canvas.isEmpty()) {
            createFactories();
            createScene();
        }
    }

    public void toolbarShapeEvents(Node n){
        n.setOnMousePressed(e -> {
            System.out.println("drag start");
        });
        n.setOnMouseReleased(e -> {
            Shape shape = graphToModel.get(n);
            Shape newShape = shape.clone();
            newShape.setPosition(new Point2D.Double(e.getSceneX(), e.getSceneY()));
            System.out.println(e.getSceneX() + " " + e.getSceneY());
            if(e.getSceneX() > this.toolbar.getWidth())
                addShapeToCanvas(newShape);
            System.out.println("drop");
        });
    }

    public void canvasShapeEvents(Node n){
        n.setOnMousePressed(e -> {
            System.out.println("drag start");
        });
        n.setOnMouseReleased(e -> {
            Shape shape = graphToModel.get(n);
            Shape newShape = shape.clone();
            newShape.setPosition(new Point2D.Double(e.getSceneX(), e.getSceneY()));
            System.out.println(e.getSceneX() + " " + e.getSceneY());
            if(e.getSceneX() < this.toolbar.getWidth())
                addShapeToToolbar(newShape);
            System.out.println("drop");
        });
    }

    @Override
    protected void createScene() {
        Shape shape = _shapefactory.createRectangle(100, 100, 50, 50);
        shape.translate(new Point2D.Double(100, 50));
        addShapeToCanvas(shape);
        Shape shape2 = _shapefactory.createRectangle(250, 250, 75, 20);
        addShapeToCanvas(shape2);
        Element saveBtn =  _factory.createButton(BTN_MARGE,20,BTN_SIZE,BTN_SIZE,"Save","save.png");
        //Element doBtn =  _factory.createButton((2*BTN_MARGE)+BTN_SIZE,20,BTN_SIZE,BTN_SIZE,"do","redo.png");
    }

    public void addShapeToCanvas(Shape s){
        canvas.addShape(s);
        Node newNode = (Node) s.draw();
        ((Group)(FxApplication.pane.getCenter())).getChildren().add(newNode);
        canvasShapeEvents(newNode);
        graphToModel.put(newNode, s);
    }

    public void addShapeToToolbar(Shape s){
        Node newNode = (Node) s.draw();
        graphToModel.put(newNode, s);
        toolbar.getItems().add(newNode);
        toolbarShapeEvents(newNode);
    }
    public void  update(Shape s ){
         s.draw();
    }
}