package xshape;

import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import xshape.model.*;

import java.awt.geom.Point2D;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class FxApp extends XShape {

    // TODO ajouter observateur dans cette classe (controller)
    private HashMap<Node, Shape> graphToModel = new HashMap<>();

    private Stage stage;

    private MenuBar menu;


    public FxApp(Stage stage){
        super();
        this.stage = stage;
        stage.setOnCloseRequest(e -> {
            File file = new File(this.getClass().getResource(".").getFile() + "/toolbar");
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            serializeToolbar(file.getAbsolutePath());

        });
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

        MenuBar menu = new MenuBar();
        Menu file = new Menu("File");
        MenuItem save = new MenuItem("Save");
        MenuItem load = new MenuItem("Load");
        file.getItems().addAll(load, save);
        menu.getMenus().add(file);
        load.setOnAction(e -> {
            loadFile();
        });
        save.setOnAction(e -> {
            fileChooserEvent();
        });
        this.menu = menu;

        VBox verticalTop = new VBox(menu, toolbarH);
        pane.setTop(verticalTop);

        this.stage.getScene().setRoot(pane);
        FxApplication.pane = pane;

        loadToolbar();

        if(toolbarV.getItems().isEmpty()){
            Rectangle rectangle = this._shapefactory.createRectangle(0, 0, 50, 50);
            rectangle.setColor(0, 0, 1.0);
            Node rectNode = (javafx.scene.shape.Rectangle) rectangle.draw();
            toolbarV.getItems().add(rectNode);
            ObservableList<Node> nodes = toolbarV.getItems();
            graphToModel.put(rectNode, rectangle);
            for(Node n: nodes){
                toolbarShapeEvents(n);
            }
        }
    }

    private void fileChooserEvent(){
        Stage dialog = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save file");
        File file = fileChooser.showSaveDialog(dialog);
        if (file != null){
            serialize(file.getAbsolutePath());
        }
    }

    private void loadFile(){
        Stage dialog = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file");
        File file = fileChooser.showOpenDialog(dialog);
        if (file != null){
            ArrayList<Shape> newCanvas = deserialize(file.getAbsolutePath());
            FxApplication._root.getChildren().clear();
            graphToModel.clear();

            this.canvas = new Canvas();
            for(Shape s: newCanvas){
                if(s instanceof Rectangle){
                    RectangleFx rect = new RectangleFx((Rectangle) s);
                    addShapeToCanvas(rect);
                }
            }
        }
    }

    private void loadToolbar(){
        URL resource = this.getClass().getResource("toolbar");
        if(resource != null){
            File toolbarFile = null;
            try {
                toolbarFile = Paths.get(resource.toURI()).toFile();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            if(toolbarFile.exists()){
                String toolbarPath = toolbarFile.getAbsolutePath();
                ArrayList<Shape> toolbarShapes = deserialize(toolbarPath);
                for(Shape s: toolbarShapes){
                    if(s instanceof Rectangle){
                        RectangleFx rect = new RectangleFx((Rectangle) s);
                        addShapeToToolbar(rect);
                    }
                }
            }
        }
    }

    private void serializeToolbar(String filename){
        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            ArrayList<Shape> toSerialize = new ArrayList<>();

            ObservableList<Node> toolbarElements = toolbarV.getItems();
            for(Node node: toolbarElements){
                Shape s = graphToModel.get(node);
                if(s instanceof Rectangle){
                    Rectangle rect = new Rectangle((Rectangle) s);
                    toSerialize.add(rect);
                }
            }
            out.writeObject(toSerialize);

            out.flush();
            out.close();
            file.close();
            System.out.println("Serialization complete");
        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("The file " + filename + " doesn't exist.");
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void toolbarShapeEvents(Node n){
        n.setOnMouseReleased(e -> {
            Shape shape = graphToModel.get(n);
            if(e.getSceneX() > 0 && e.getSceneY() > menu.getHeight() && e.getSceneX() <= 40 && e.getSceneY() <= menu.getHeight() + toolbarH.getHeight()){
                removeShapeFromToolbar(shape);
            } else {
                if(e.getSceneX() > this.toolbarV.getWidth() && e.getSceneY() > menu.getHeight() + toolbarH.getHeight()){
                    Shape newShape = shape.clone();
                    newShape.setPosition(new Point2D.Double(e.getSceneX(), e.getSceneY()));
                    newShape.rotationCenter(new Point2D.Double(e.getSceneX(), e.getSceneY()));
                    addShapeToCanvas(newShape);
                }
            }
        });

    }

    public void canvasShapeEvents(Node n){
        n.setOnMousePressed(e -> {
            if(e.getButton() == MouseButton.PRIMARY && e.isControlDown()){
                Shape shape = graphToModel.get(n);
                this.currentSelection.add(shape);
                selectShape(graphToModel.get(n));
            }
            if(e.getButton() == MouseButton.SECONDARY)
                contextMenu(n);
        });
        n.setOnMouseReleased(e -> {
            Shape shape = graphToModel.get(n);
            if(e.getSceneX() > 0 && e.getSceneY() > menu.getHeight() && e.getSceneX() <= 40 && e.getSceneY() <= menu.getHeight() + toolbarH.getHeight()){
                removeShapeFromCanvas(shape);
            } else {
                System.out.println(menu.getHeight() + toolbarH.getHeight());
                if(e.getSceneX() < this.toolbarV.getWidth() && e.getSceneY() > menu.getHeight() + toolbarH.getHeight()){
                    Shape newShape = shape.clone();
                    addShapeToToolbar(newShape);
                }
            }
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
        /*if (newNode instanceof Group){
            for(Node node: ((Group) newNode).getChildren()){
                System.out.println(node);
            }
        }*/
        graphToModel.put(newNode, s);
        toolbarV.getItems().add(newNode);
        toolbarShapeEvents(newNode);
    }

    public void contextMenu(Node n){
        // TODO actions pour chaque option
        ContextMenu menu = new ContextMenu();
        MenuItem item1 = new MenuItem("Group");
        MenuItem item2 = new MenuItem("Degroup");
        if(!(n instanceof Group)) item2.setDisable(true);
        Menu editMenu = new Menu("Edit");
        for(String parameter: graphToModel.get(n).editableParameters()){
            MenuItem edit = new MenuItem(parameter);
            edit.setOnAction(e -> {
                editShape(graphToModel.get(n), parameter);
            });
            editMenu.getItems().add(edit);
        }

        item1.setOnAction(e -> {
            createGroup();
        });
        item2.setOnAction(e -> {
            destroyGroup((ShapeGroup) graphToModel.get(n));
        });

        menu.getItems().addAll(item1, item2, editMenu);
        menu.show(n, Side.BOTTOM, 0, 0);
    }

    protected void createGroup(){
        if(currentSelection.size() > 1){
            ShapeGroup sg = _shapefactory.createShapeGroup();
            for(Shape s: currentSelection){
                unselectShape(s);
                removeShapeFromCanvas(s);
                Shape newS = s.clone();
                sg.add(newS);
            }
            for(Shape shape: sg.getChilds()){
                System.out.println(shape);
            }
            addShapeToCanvas(sg);
        }
        currentSelection.clear();
    }

    protected void destroyGroup(ShapeGroup sg){
        for(Shape shape : sg.getChilds()){
            Shape newShape = shape.clone();
            addShapeToCanvas(newShape);
        }
        removeShapeFromCanvas(sg);
    }

    protected void selectShape(Shape s){
        if(s instanceof ShapeGroup){

        } else {
            javafx.scene.shape.Shape shape = (javafx.scene.shape.Shape) s.draw();
            shape.setStrokeWidth(3);
            shape.setStroke(Color.LIGHTBLUE);
        }
    }

    protected void unselectShape(Shape s){
        if(s instanceof ShapeGroup){

        } else {
            javafx.scene.shape.Shape shape = (javafx.scene.shape.Shape) s.draw();
            shape.setStrokeWidth(0);
            shape.setStroke(Color.TRANSPARENT);
        }
    }

    protected void editShape(Shape s, String parameter){
        TextInputDialog dialog;
        switch (parameter){
            case "Width":
                dialog = new TextInputDialog(String.valueOf(s.size().getX()));
                dialog.setTitle("Set width");
                dialog.setContentText("Width :");
                Optional<String> result = dialog.showAndWait();

                if(result.isPresent() && isNumeric(result.get())){
                    ((Rectangle) s).setWidth(Double.parseDouble(result.get()));
                }
                break;
            case "Height":
                dialog = new TextInputDialog(String.valueOf(s.size().getY()));
                dialog.setTitle("Set height");
                dialog.setContentText("Height :");
                result = dialog.showAndWait();

                if(result.isPresent() && isNumeric(result.get())){
                    ((Rectangle) s).setHeight(Double.parseDouble(result.get()));
                }
                break;
            case "Rotation":
                dialog = new TextInputDialog(String.valueOf(s.rotation()));
                dialog.setTitle("Set rotation");
                dialog.setContentText("Rotation :");
                result = dialog.showAndWait();

                if(result.isPresent() && isNumeric(result.get())){
                    s.rotation(Double.parseDouble(result.get()));
                }
                break;
            case "Color":
                double[] rgb = s.getRGB();
                ColorPicker colorPicker;
                if(rgb.length > 0) colorPicker = new ColorPicker(Color.color(rgb[0], rgb[1], rgb[2]));
                else colorPicker = new ColorPicker();
                Stage stage = new Stage();
                GridPane gp = new GridPane();
                stage.setScene(new Scene(gp, 100, 100));
                gp.add(colorPicker, 0, 0);
                Button btn = new Button("OK");
                btn.setOnAction(e -> {
                    Color chosenColor = colorPicker.getValue();
                    System.out.println(chosenColor.getRed() + " " + chosenColor.getGreen() + " " + chosenColor.getBlue());

                    s.setColor(chosenColor.getRed(), chosenColor.getGreen(), chosenColor.getBlue());
                    stage.close();
                });
                gp.add(btn, 0, 1);
                stage.showAndWait();
        }
        s.update();
    }

    private boolean isNumeric(String txt){
        if (txt == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(txt);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}