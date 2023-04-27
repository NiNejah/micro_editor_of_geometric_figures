package xshape;

import xshape.model.Rectangle;
import xshape.model.RectangleAwt;
import xshape.model.Shape;
import xshape.model.ShapeFactoryAwt;

import javax.swing.*;
import java.awt.geom.Point2D;

public class ElementFactoryAwt implements ElementFactory {
    private int BTN_SIZE = 20 ;
    private int BTN_MARGE = 20 ;
    public ElementFactoryAwt() {
    }
    public Rectangle createRectangle(double posX, double posY,
                                     double height, double width) {
        return new RectangleAwt(width, height, 0, 0, new Point2D.Double(posX, posY),
                0, 255, 0, 0);
    }

    @Override
    public Element createButton(double posX, double posY, double height, double width, String label,String icon ) {
        return new ButtonAwt(posX,posY,height,width,label,icon);
    }
    @Override
    public Toolbar createToolbar(double posX, double posY, double height, double width, ToolbarStyle style) {
        if(style == ToolbarStyle.VERTICAL){
            ToolbarAwt toolbarAwt =  new ToolbarAwt(width, height, new Point2D.Double(posX, posY),new ShapeFactoryAwt());
            Shape shape = createRectangle(0, 0, 50, 50);
            shape.translate(new Point2D.Double(100, 50));
            toolbarAwt.addShape(shape);
            Shape shape2 = createRectangle(0, 0, 70, 50);
            toolbarAwt.addShape(shape2);
            return toolbarAwt ;
        }else if (style == ToolbarStyle.HORIZONTAL){
            ButtonBarAwt btnAwt = new ButtonBarAwt(width, height, new Point2D.Double(posX, posY), new ShapeFactoryAwt());
            ButtonAwt saveBtn =  (ButtonAwt) createButton(BTN_MARGE,20,BTN_SIZE,BTN_SIZE,"Save","save.png");
            ButtonAwt unBtn =  (ButtonAwt) createButton((2*BTN_MARGE)+BTN_SIZE,20,BTN_SIZE,BTN_SIZE,"do","redo.png");
            ButtonAwt rnBtn =  (ButtonAwt) createButton((3*BTN_MARGE)+BTN_SIZE,20,BTN_SIZE,BTN_SIZE,"redo","load.png");
            btnAwt.addButton(saveBtn);
            btnAwt.addButton(unBtn);
            btnAwt.addButton(rnBtn);
            return btnAwt;
        }
        return null ;
    }

    @Override
    public Object createUI(Toolbar toolbarH, Toolbar toolbarV) {
        JPanel jp = new JPanel();
//        jp.setBounds(0,0,500,500);
        JPanel toolVJp = (JPanel) toolbarV.draw();
        jp.add(toolVJp);
        JPanel toolHJp = (JPanel) toolbarH.draw();
        jp.add(toolHJp);
        return jp;
    }
}
