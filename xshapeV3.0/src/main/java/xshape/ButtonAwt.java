package xshape;

import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.*;
import java.net.URL;
public class ButtonAwt extends ElementAbstract implements xshape.Button {
    private JButton _adapted ;
    private String _label  ;
    public ButtonAwt (double posX, double posY, double height, double width ,String label,String icon ) {
        super.position(new Point2D.Double(posX, posY));
        super.size(new Point2D.Double(width, height));

        String resourcePath = "/images/" + icon;
        URL resourceUrl = ButtonAwt.class.getResource(resourcePath);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }
        ImageIcon imageIcon = new ImageIcon(resourceUrl);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance((int) width, (int) height,Image.SCALE_SMOOTH));
        _adapted = new JButton(imageIcon);
        label(label);
    }
    @Override
    public void label(String label) {
        _label = label ;
//        _adapted.setText(_label); // Update the text of the JButton
    }

    @Override
    public String label() {
        return _label;
    }


    @Override
    public void draw() {
        Graphics g = AwtContext.instance().graphics();
//        Color c = g.getColor();
        Point2D pos = position();
        Point2D size = size();
//        g.setColor(Color.BLUE); // Set the color to the background color of the JPanel
//        g.fillRect((int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY()); // Fill the background with the same color
        _adapted.setBackground(Color.GRAY);
        _adapted.setBounds((int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY());
        _adapted.setVisible(true);
//        _adapted.paint(g); // Draw the JButton on the Graphics object
        _adapted.paint(g.create((int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY()));
//        g.setColor(c);
    }
}
