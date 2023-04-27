package xshape.model;

import xshape.AwtContext;
import xshape.Observateur;
import xshape.model.Rectangle;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.awt.*;
import java.io.Serializable;

public class RectangleAwt extends Rectangle implements Serializable {
	private double _rotation;
	private Point2D _centerOfRotation = new Point2D.Double(position().getX() + (size().getX()/2), position().getY() + (size().getY()/2));
	public RectangleAwt(double width, double height, double arcWidth, double arcHeight,
						Point2D.Double pos, double rot, int red, int green, int blue) {
		super(width, height, arcWidth, arcHeight, pos, rot, red, green, blue);
	}

	public RectangleAwt(Rectangle r){
		super(r);
	}
	public Object draw2() {
		Point2D pos = position();
		Point2D size = size();
		JPanel jp = new JPanel();
		double[] rgb = getRGB();
		jp.setBackground(new Color((int) rgb[0], (int) rgb[1], (int) rgb[2]));
		jp.setBounds((int) pos.getX(),(int)  pos.getY(),(int) size.getX(),(int) size.getY());

//        Graphics g = AwtContext.instance().graphics();
//        Color c = g.getColor();

//        g.setColor(Color.BLUE);
//        g.fillRect((int)(pos.getX() - size.getX()/2),
//        (int)(pos.getY() - size.getY()/2),
//        (int)(size.getX()),
//        (int)(size.getY()));
//        g.setColor(c);
		// TODO
		return jp;
	}
    @Override
    public Object draw() {
        Point2D pos = position();
        Point2D size = size();
        JPanel jp = new JPanel();
//		{
//            @Override
//            protected void paintComponent(Graphics g) {
//                Graphics2D g2d = (Graphics2D) g;
//                g2d.rotate(Math.toRadians(_rotation), _centerOfRotation.getX(), _centerOfRotation.getY());
//                super.paintComponent(g);
//                g2d.fillRect(0, 0, (int) size.getX(), (int) size.getY());
//            }
//        };
        double[] rgb = getRGB();
        jp.setBackground(new Color((int) rgb[0], (int) rgb[1], (int) rgb[2]));
        jp.setBounds((int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY());
		jp.setPreferredSize(new Dimension((int) size.getX(), (int) size.getY()));
		jp.addMouseListener(new Observateur());
        return jp;
    }
	@Override
	public double rotation(){return _rotation;};
	@Override
	public void rotation(double angle){_rotation = angle;};
	public Point2D rotationCenter(){return _centerOfRotation;}
	public void rotationCenter(Point2D centerOfRotation){ _centerOfRotation = centerOfRotation;}

	public Shape clone() {
		return new RectangleAwt(this);
	}

	public boolean clicked(double mx,double my){
		Point2D pos = position();
		Point2D size = size();
		return  mx<=pos.getX() && my>pos.getY() &&
				mx <= size.getX() && mx <= size.getY()  ;
	}
}
