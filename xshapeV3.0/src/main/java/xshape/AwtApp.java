package xshape;

import xshape.model.Shape;
import xshape.model.ShapeFactoryAwt;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;

class GUIHelper {
    public static void showOnFrame(JComponent component, String frameName) {
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
    }
}

public class AwtApp extends XShape {
    JPanel bars = null ;
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
    
    @Override
    protected void createFactories() {
        this._factory = new ElementFactoryAwt();
        this._shapefactory = new ShapeFactoryAwt();
    }

    @Override
    void run() {

        JCanvas jc = new JCanvas(this);
        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(500, 500));
        jc.setLayout(new BorderLayout());

        if(bars == null){
            createUI();
        }
        jc.add(bars.getComponent(0),BorderLayout.WEST);
        jc.add(bars.getComponent(0),BorderLayout.NORTH);
        // TODO : add toolabar (v,h,canvas) panel here !
        GUIHelper.showOnFrame(jc, "XShape Swing/AWT Rendering");

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
    protected void createUI() {
        createFactories();
        UIBuilder builder = new UIBuilder(_factory) ;
        this.bars = (JPanel) builder.build() ;
    }
}

