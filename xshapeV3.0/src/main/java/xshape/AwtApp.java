package xshape;

import xshape.model.Shape;
import xshape.model.ShapeFactoryAwt;
import xshape.model.ShapeGroup;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;

class GUIHelper {
    public static JFrame showOnFrame(JComponent component, String frameName) {
        JFrame frame = new JFrame(frameName);
        WindowAdapter wa = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(wa);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
}

public class AwtApp extends XShape {
    class JCanvas extends JPanel {
        XShape _xshape = null;
        public JCanvas(XShape xs) {
            _xshape = xs;
        }
        public void paint(Graphics g) {
            super.paint(g);
            AwtContext.instance().graphics(g);
            _xshape.draw();
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

    }

    @Override
    public void run() {
        JCanvas jc = new JCanvas(this);
        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(500, 500));
        this.frame = GUIHelper.showOnFrame(jc, "XShape Swing/AWT Rendering");
        createFactories();
        createUI();
        draw();
    }

    @Override
    protected void addShapeToCanvas(Shape shape) {

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

