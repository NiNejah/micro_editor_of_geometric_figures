package xshape;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import xshape.model.Rectangle;
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

    protected void createUI(){
        UIBuilder builder = new UIBuilder(this._factory);
        BorderPane pane = (BorderPane) builder.build();
        this.toolbarH = (ToolBar) pane.getTop();
        this.toolbarV = (ToolBar) pane.getLeft();
        Rectangle rectangle = this._shapefactory.createRectangle(0, 0, 50, 50);
        rectangle.setColor(0, 0, 255);
        Node rectNode = (javafx.scene.shape.Rectangle) rectangle.draw();
        toolbarV.getItems().add(rectNode);
        ObservableList<Node> nodes = toolbarV.getItems();
        graphToModel.put(rectNode, rectangle);
        for(Node n: nodes){
            toolbarShapeEvents(n);
        }

        this.stage.getScene().setRoot(pane);
        FxApplication.pane = pane;
    }

    public void toolbarShapeEvents(Node n){
        n.setOnMousePressed(e -> {
            System.out.println("drag start");
        });
        n.setOnMouseReleased(e -> {
            Shape shape = graphToModel.get(n);
            if(e.getSceneX() > 0 && e.getSceneY() > 0 && e.getSceneX() <= 40 && e.getSceneY() <= 40){
                removeShapeFromToolbar(shape);
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
                removeShapeFromCanvas(shape);
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

    public void addShapeToCanvas(Shape s){
        canvas.addShape(s);
        Node newNode = (Node) s.draw();
        ((Group)(FxApplication.pane.getCenter())).getChildren().add(newNode);
        canvasShapeEvents(newNode);
        graphToModel.put(newNode, s);
    }

    public void removeShapeFromToolbar(Shape s){
        Node n = (Node) s.draw();
        graphToModel.remove(n);
        toolbarV.getItems().remove(n);
    }

    public void removeShapeFromCanvas(Shape s){
        canvas.removeShape(s);
        Node n = (Node) s.draw();
        graphToModel.remove(n);
        FxApplication._root.getChildren().remove(n);
    }

    public void addShapeToToolbar(Shape s){
        Node newNode = (Node) s.draw();
        graphToModel.put(newNode, s);
        toolbarV.getItems().add(newNode);
        toolbarShapeEvents(newNode);
    }
}