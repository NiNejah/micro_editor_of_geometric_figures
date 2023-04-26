package xshape;

import javafx.scene.control.ToolBar;
import xshape.model.*;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class XShape {
    protected ElementFactory _factory = null;

    protected ShapeFactory _shapefactory = null;

    protected Canvas canvas;

    protected ToolBar toolbarV;

    protected ToolBar toolbarH;

    protected List<Shape> currentSelection = new ArrayList<>();

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
        /*Shape shape = _shapefactory.createRectangle(100, 100, 50, 50);
        shape.translate(new Point2D.Double(100, 50));
        addShapeToCanvas(shape);
        Shape shape2 = _shapefactory.createRectangle(250, 250, 75, 20);
        addShapeToCanvas(shape2);*/
        //Element saveBtn =  _factory.createButton(BTN_MARGE,20,BTN_SIZE,BTN_SIZE,"Save","save.png");
        //Element doBtn =  _factory.createButton((2*BTN_MARGE)+BTN_SIZE,20,BTN_SIZE,BTN_SIZE,"do","redo.png");
    }

    protected abstract void addShapeToCanvas(Shape shape);

    protected abstract void addShapeToToolbar(Shape shape);

    protected abstract void removeShapeFromCanvas(Shape shape);

    protected abstract void removeShapeFromToolbar(Shape shape);

    protected abstract void createUI();

    protected abstract void createGroup();

    protected abstract void destroyGroup(ShapeGroup sg);

    public void draw() {
        if (this.canvas.isEmpty()) {
            createScene();
        }
    }

    protected void serialize(String filename) {
        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            ArrayList<Shape> toSerialize = new ArrayList<>();

            for(Shape s: canvas.getShapes()){
                if(s instanceof Rectangle){
                    Rectangle rect = new Rectangle((Rectangle) s);
                    toSerialize.add(rect);
                } else if(s instanceof Polygon){
                    Polygon poly = new Polygon((Polygon) s);
                    toSerialize.add(poly);
                } else if(s instanceof ShapeGroup){
                    ShapeGroup sg = new ShapeGroup((ShapeGroup) s);
                    sg.setGenericChilds();
                    toSerialize.add(sg);
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

    protected ArrayList deserialize(String filename){
        try{
            System.out.println(filename);
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            ArrayList<Shape> shapes = (ArrayList<Shape>) in.readObject();
            in.close();
            return shapes;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
