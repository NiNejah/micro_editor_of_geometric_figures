package xshape;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import xshape.model.*;
import xshape.model.Canvas;
import xshape.model.Polygon;
import xshape.model.Rectangle;
import xshape.model.Shape;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;

class GUIHelper {
    public static JFrame showOnFrame(JComponent component, String frameName) {
        JFrame frame = new JFrame(frameName);
        WindowAdapter wa = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        };
        frame.addWindowListener(wa);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
}

public class AwtApp extends XShape implements ActionListener {
    JPanel bars = null ;

    JCanvas jc;

    JPanel buttons;
    JButton saveButton;

    private HashMap<JPanel, Shape> graphToModel;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton){
            loadFile();
        }
    }

    class Whiteboard extends JPanel {
        public Whiteboard(){
            setBackground(Color.WHITE);
        }

        public Dimension getPreferredSize(){
            return isPreferredSizeSet() ?
                    super.getPreferredSize() : new Dimension(1000, 1000);
        }

        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            for (Shape shape : canvas.getShapes())
            {
                if(shape instanceof Rectangle){
                    RectangleAwt rectangle = (RectangleAwt) shape;
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(new Color((int) (rectangle.getRGB()[0]*255), (int) (rectangle.getRGB()[1]*255), (int) (rectangle.getRGB()[2]*255)));
                    System.out.println(rectangle.rotation());
                    g2.rotate(Math.toRadians(rectangle.rotation()), rectangle.rotationCenter().getX(), rectangle.rotationCenter().getY());
                    g2.fillRect((int) (rectangle.position().getX() - rectangle.size().getX()/2), (int) (rectangle.position().getY() - rectangle.size().getY()/2),
                            (int) rectangle.size().getX(), (int) rectangle.size().getY());
                    g2.rotate(Math.toRadians(-(rectangle.rotation())), rectangle.rotationCenter().getX(), rectangle.rotationCenter().getY());
                } else if(shape instanceof Polygon){
                    PolygonAwt polygon = (PolygonAwt) shape;
                    List<Point2D.Double> coordinates = polygon.sidesPosition();
                    int[] x = new int[polygon.getNbSides()];
                    int[] y = new int[polygon.getNbSides()];
                    int cpt = 0;
                    for(Point2D.Double coor: coordinates){
                        x[cpt] = (int) coor.getX();
                        y[cpt] = (int) coor.getY();
                        cpt++;
                    }
                    g.setColor(new Color((int) (polygon.getRGB()[0]*255), (int) (polygon.getRGB()[1]*255), (int) (polygon.getRGB()[2]*255)));
                    g.fillPolygon(x, y, polygon.getNbSides());
                } else if(shape instanceof ShapeGroup){
                    ShapeGroupAwt sg = (ShapeGroupAwt) shape;
                    drawShapeGroup(sg, g);
                }
            }
        }

        private void drawShapeGroup(ShapeGroup sg, Graphics g){
            for(Shape shape: sg.getChilds()){
                if(shape instanceof Rectangle){
                    RectangleAwt rectangle = (RectangleAwt) shape;
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(new Color((int) (rectangle.getRGB()[0]*255), (int) (rectangle.getRGB()[1]*255), (int) (rectangle.getRGB()[2]*255)));
                    g2.rotate(Math.toRadians(rectangle.rotation()), rectangle.rotationCenter().getX(), rectangle.rotationCenter().getY());
                    g2.fillRect((int) (rectangle.position().getX() - rectangle.size().getX()/2), (int) (rectangle.position().getY() - rectangle.size().getY()/2),
                            (int) rectangle.size().getX(), (int) rectangle.size().getY());
                    g2.rotate(Math.toRadians(-(rectangle.rotation())), rectangle.rotationCenter().getX(), rectangle.rotationCenter().getY());
                } else if(shape instanceof Polygon){
                    PolygonAwt polygon = (PolygonAwt) shape;
                    List<Point2D.Double> coordinates = polygon.sidesPosition();
                    int[] x = new int[polygon.getNbSides()];
                    int[] y = new int[polygon.getNbSides()];
                    int cpt = 0;
                    for(Point2D.Double coor: coordinates){
                        x[cpt] = (int) coor.getX();
                        y[cpt] = (int) coor.getY();
                        cpt++;
                    }
                    g.setColor(new Color((int) (polygon.getRGB()[0]*255), (int) (polygon.getRGB()[1]*255), (int) (polygon.getRGB()[2]*255)));
                    g.fillPolygon(x, y, polygon.getNbSides());
                } else if(shape instanceof ShapeGroup){
                    ShapeGroupAwt group = (ShapeGroupAwt) shape;
                    drawShapeGroup(group, g);
                }
            }
        }
    }
    class JCanvas extends JPanel {
        XShape _xshape = null;
        public JCanvas(XShape xs) {
            _xshape = xs;
            graphToModel = new HashMap<>();
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }

    private void loadFile(){
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if(result == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            ArrayList<Shape> newCanvas = deserialize(file.getAbsolutePath());
            Whiteboard whiteboard = new Whiteboard();
            this.canvas = new Canvas();
            for(Shape s: newCanvas){
                if(s instanceof Rectangle){
                    RectangleAwt rect = new RectangleAwt((Rectangle) s);
                    addShapeToCanvas(rect);
                } else if(s instanceof Polygon){
                    PolygonAwt poly = new PolygonAwt((Polygon) s);
                    addShapeToCanvas(poly);
                } else if(s instanceof ShapeGroup){
                    ShapeGroupAwt sg = new ShapeGroupAwt((ShapeGroup) s);
                    sg.removeGenericChilds();
                    addShapeToCanvas(sg);
                }
            }
            jc.add(whiteboard, BorderLayout.CENTER);
            frame.revalidate();
        }
    }

    private JFrame frame;
    
    @Override
    protected void createFactories() {
        this._factory = new ElementFactoryAwt();
        this._shapefactory = new ShapeFactoryAwt();
    }

    @Override
    protected void createUI() {
        jc = new JCanvas(this);
        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(500, 500));
        jc.setLayout(new BorderLayout());

        if(bars == null){
            UIBuilder builder = new UIBuilder(_factory) ;
            this.bars = (JPanel) builder.build() ;
            JPanel horizontal = (JPanel) bars.getComponent(1);
            saveButton = (JButton) horizontal.getComponent(0);
            saveButton.addActionListener(this);
        }
        jc.add(bars.getComponent(0),BorderLayout.WEST);
        jc.add(bars.getComponent(0),BorderLayout.NORTH);
        frame = GUIHelper.showOnFrame(jc, "XShape Swing/AWT Rendering");
        // TODO : add toolabar (v,h,canvas) panel here !

    }

    @Override
    protected void addShapeToCanvas(Shape shape) {
        canvas.addShape(shape);
    }

    @Override
    protected void addShapeToToolbar(Shape shape) {

    }

    @Override
    protected void removeShapeFromCanvas(Shape shape) {

    }

    @Override
    protected void removeShapeFromToolbar(Shape shape) {

    }

    @Override
    protected void createGroup() {

    }

    @Override
    protected void destroyGroup(ShapeGroup sg) {

    }
}

