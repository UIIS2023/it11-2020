package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Circle extends SurfaceShape implements Serializable{

	protected Point center = new Point();
	private int radius;
	protected Color borderColor;
	protected Color innerColor;

	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		this.setSelected(selected);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		this.setColor(color);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		this.setInnerColor(innerColor);
	}
	
	public Circle(Point center, Color borderColor, Color innerColor) {
		this.center = center;
		this.borderColor = borderColor;
		this.innerColor = innerColor;
	}
	
	public Circle clone(Circle circle) {
		
		circle.getCenter().setX(this.getCenter().getX());
		circle.getCenter().setY(this.getCenter().getY());
		return circle;
	}
	

	public double area() {
		return radius * radius * Math.PI;
	}

	public double circumference() {
		return 2 * radius * Math.PI;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle pomocna = (Circle) obj;
			if (this.center.equals(pomocna.center) && this.radius == pomocna.radius)
				return true;
			else
				return false;

		} else
			return false;
	}

	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}

	public boolean contains(Point p) {
		return center.distance(p.getX(), p.getY()) <= radius;
	}

	@Override
	public void draw(Graphics g) {
		  g.setColor(getColor());
	      g.drawOval(center.getX()-radius, center.getY()-radius, 2*radius, 2*radius);	
	      this.fill(g);
	      if(selected == true) {
				g.setColor(Color.BLUE);
				g.drawRect(center.getX() - 2, center.getY() - 2, 4, 4);
				g.drawRect(center.getX() - radius - 2, center.getY() - 2, 4, 4);
				g.drawRect(center.getX() + radius - 2, center.getY() - 2, 4, 4);
				g.drawRect(center.getX() - 2, center.getY() - radius - 2, 4, 4);
				g.drawRect(center.getX() - 2, center.getY() + radius - 2, 4, 4);
			}
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - this.radius + 1, this.center.getY() - this.radius + 1,
				this.radius*2 - 2, this.radius*2 - 2);
		
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Circle) {
			return (int)(this.area()-((Circle)o).area());
			//jer area vraca double a compareTo vraca int
		}
		return 0;
	}
	

	@Override
	public void moveTo(int x, int y) {
	   center.moveTo(x, y);	
	}

	@Override
	public void moveBy(int x, int y) {
		   center.moveBy(x, y);	
	}

	public Point getCenter() {// vraca objekat klase Point
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return this.radius;
	}

	public void setRadius(int radius) throws Exception {
		if (radius > 0) {
			this.radius = radius;
		} else {
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
	}

	public String toString() {
		return "circle with center in point " + center + " and radius " + radius + " with inner color (" + getInnerColor()
		 + ") and edge color (" + getColor() +")";
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	
}  
