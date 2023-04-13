package xshape;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.stage.Stage;
import xshape.model.Shape;
import xshape.model.ShapeFactoryFx;

import java.awt.geom.Point2D;
import java.util.HashMap;

public class FxApp extends XShape {

    // TODO ajouter observateur dans cette classe (controller)
    private HashMap<Node, Shape> graphToModel = new HashMap<>();

    private Stage stage;


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
        //toolbar horizontale
        ToolbarFx toolbar2 = new ToolbarFx(_shapefactory, ToolbarStyle.HORIZONTAL);
        ToolBar tbFX2 = (ToolBar) toolbar2.draw();
        this.toolbarH = tbFX2;
        FxApplication.pane.setTop(tbFX2);
        //binEvents((ImageView) this.toolbarH.getItems().get(0));

        //toolbar verticale
        ToolbarFx toolbar = new ToolbarFx(_shapefactory, ToolbarStyle.VERTICAL);
        ToolBar tbFX = (ToolBar) toolbar.draw();
        this.toolbarV = tbFX;
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
        /*n.setOnDragDetected(event -> {
            Dragboard db = n.startDragAndDrop();
            ClipboardContent cc = new ClipboardContent();
            cc.put(DataFormat.IMAGE, graphToModel.get(n));
            db.setContent(cc);
            event.consume();
        });
        n.setOnDragDone(event -> {
            event.consume();
        });*/
        n.setOnMousePressed(e -> {
            System.out.println("drag start");
        });
        n.setOnMouseReleased(e -> {
            Shape shape = graphToModel.get(n);
            if(e.getSceneX() > 0 && e.getSceneY() > 0 && e.getSceneX() <= 40 && e.getSceneY() <= 40){
                removeShapeFromToolbar(n);
            } else {
                Shape newShape = shape.clone();
                newShape.setPosition(new Point2D.Double(e.getSceneX(), e.getSceneY()));
                System.out.println(e.getSceneX() + " " + e.getSceneY());
                if(e.getSceneX() > this.toolbarV.getWidth())
                    addShapeToCanvas(newShape);
            }

            System.out.println("drop");
        });

    }

    public void canvasShapeEvents(Node n){
        n.setOnMousePressed(e -> {
            System.out.println("drag start");
        });
        n.setOnMouseReleased(e -> {
            Shape shape = graphToModel.get(n);
            if(e.getSceneX() > 0 && e.getSceneY() > 0 && e.getSceneX() <= 40 && e.getSceneY() <= 40){
                removeShapeFromCanvas(n);
            } else {
                Shape newShape = shape.clone();
                newShape.setPosition(new Point2D.Double(e.getSceneX(), e.getSceneY()));
                System.out.println(e.getSceneX() + " " + e.getSceneY());
                if(e.getSceneX() < this.toolbarV.getWidth())
                    addShapeToToolbar(newShape);
            }
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
        //Element saveBtn =  _factory.createButton(BTN_MARGE,20,BTN_SIZE,BTN_SIZE,"Save","save.png");
        //Element doBtn =  _factory.createButton((2*BTN_MARGE)+BTN_SIZE,20,BTN_SIZE,BTN_SIZE,"do","redo.png");
    }

    public void addShapeToCanvas(Shape s){
        canvas.addShape(s);
        Node newNode = (Node) s.draw();
        ((Group)(FxApplication.pane.getCenter())).getChildren().add(newNode);
        canvasShapeEvents(newNode);
        graphToModel.put(newNode, s);
    }

    public void removeShapeFromToolbar(Node n){
        graphToModel.remove(n);
        toolbarV.getItems().remove(n);
    }

    public void removeShapeFromCanvas(Node n){
        Shape s = graphToModel.get(n);
        canvas.removeShape(s);
        graphToModel.remove(n);
        FxApplication._root.getChildren().remove(n);
    }

    public void addShapeToToolbar(Shape s){
        Node newNode = (Node) s.draw();
        graphToModel.put(newNode, s);
        toolbarV.getItems().add(newNode);
        toolbarShapeEvents(newNode);
    }

    private void binEvents(ImageView iv){
        iv.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if(db.hasContent(DataFormat.IMAGE)){
                success = true;
                Shape s = (Shape) db.getContent(DataFormat.IMAGE);
                Node n = (Node) s.draw();
                graphToModel.remove(n);
                toolbarV.getItems().remove(n);
                FxApplication._root.getChildren().remove(n);
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }
}