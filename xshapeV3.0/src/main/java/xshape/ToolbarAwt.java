package xshape;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class ToolbarAwt extends ElementAbstract implements xshape.Toolbar{

    private JPanel _adapted ;
    public ToolbarAwt(double posX, double posY, double height, double width ) {
        super.position(new Point2D.Double(posX, posY));
        super.size(new Point2D.Double(width, height));
        _adapted = new JPanel() ;
    }
    @Override
    public Object draw() {
        Graphics g = AwtContext.instance().graphics();
        Point2D pos = position();
        Point2D size = size();
        _adapted.setBackground(new Color(0x123456));
        _adapted.setBounds((int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY());
        _adapted.paint(g);
        return _adapted;
    }
}
