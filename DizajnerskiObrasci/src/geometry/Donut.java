package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.dnd.DropTargetEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Float;
import java.io.Serializable;

public class Donut extends Circle implements Serializable{

	private int innerRadius;
	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius); 
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		setColor(color);
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}
	
	public Donut(Point center, Color borderColor, Color innerColor) {
		this.center = center;
		this.borderColor = borderColor;
		this.innerColor = innerColor;
		
	}
	
	public Donut clone(Donut donut) {
		donut.getCenter().setX(this.getCenter().getX());
		donut.getCenter().setY(this.getCenter().getY());
		return donut;
	}


	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocna = (Donut) obj;
			if (getCenter().equals(pomocna.getCenter()) && getRadius() == pomocna.getRadius()
					&& innerRadius == pomocna.getInnerRadius())
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean contains(int x, int y) {
		double dFromCenter = getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}

	public boolean contains(Point p) {
		double dFromCenter = getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}

	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - this.innerRadius,
					getCenter().getY() - this.innerRadius,
					this.innerRadius*2,
					this.innerRadius*2);
		this.fill(g);
	}
	
	public void fill(Graphics g) {
		Ellipse2D inner = new Ellipse2D.Float(center.getX() - innerRadius, center.getY() - innerRadius, 2 * innerRadius, 2 * innerRadius);
		Ellipse2D outer = new Ellipse2D.Float(center.getX() - getRadius(), center.getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
		
		Area outer2 = new Area(outer);
		Area inner2 = new Area(inner);
		
		outer2.subtract(inner2);
		g.setColor(getInnerColor());
		((Graphics2D)g).fill (outer2);
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Donut) {
			return (int)(this.area()-((Donut)o).area());
		}
		return 0;
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public String toString() {
		return "donut with center in point " + getCenter() + " and radius " + getRadius() + ", inner radius " + getInnerRadius()
		+ ", inner color (" + getInnerColor() + ") and edge color (" + getColor() + ")";
	}

}
