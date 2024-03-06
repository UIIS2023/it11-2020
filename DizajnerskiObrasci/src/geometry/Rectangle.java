package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Rectangle extends SurfaceShape implements Serializable{

	private Point upperLeftPoint = new Point();
	private int width;
	private int height;
	private Color edgeColor;
	private Color innerColor;

	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color) {
		this(upperLeftPoint, width, height, selected);
		setColor(color);
	}
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, width, height, selected, color);
		setInnerColor(innerColor);
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {
		this(upperLeftPoint, width, height);
		setSelected(selected);
	}
	
	public Rectangle(Point upperLefPoint, Color borderColor, Color innerColor) {
		this.upperLeftPoint = upperLefPoint;
		this.edgeColor = borderColor;
		this.innerColor = innerColor;
	}
	
	public Rectangle clone(Rectangle rectangle) {
		rectangle.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
		return rectangle;
	}
	
 
	public int area() {
		return this.width * this.height;
	}

	public int circumference() {
		return this.width *2 +this.height * 2;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle)
		{
			Rectangle pomocna = (Rectangle) obj;
			if(this.upperLeftPoint.equals(pomocna.upperLeftPoint)&& this.width==pomocna.width &&this.height==pomocna.height)
				return true;
			else 
				return false;
		}else 
			return false;
	}
	public boolean contains (int x, int y) {
		return (upperLeftPoint.getX()<=x && x<= upperLeftPoint.getX()+width && upperLeftPoint.getY()<=y && y<=upperLeftPoint.getY()+height);
			
		
	}
	public boolean contains (Point p) {
		if(upperLeftPoint.getX()<=p.getX() && p.getX()<= upperLeftPoint.getX()+width && upperLeftPoint.getY()<=p.getY() && p.getY()<=upperLeftPoint.getY()+height)
			return true;
		return false;
	}
	
	

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		this.fill(g);
		
		if (selected) {
			g.setColor(Color.blue);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width - 2, upperLeftPoint.getY() - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g.drawRect(upperLeftPoint.getX() + width  - 2, upperLeftPoint.getY() + height - 2, 4, 4);
		}
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX() + 1, this.upperLeftPoint.getY() + 1, this.width-1, this.height-1);
		
	}
		
    
	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
	
	}

	@Override
	public void moveBy(int x, int y) {
		upperLeftPoint.moveBy(x, y);
		
	}
	

	@Override
	public int compareTo(Object o) {
		if(o instanceof Rectangle) {
			return (this.area()-((Rectangle)o).area());
		}
		return 0;
	}
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Color getBorderColor() {
		return edgeColor;
	}

	public void setBorderColor(Color borderColor) {
		this.edgeColor = borderColor;
	}
	
	 public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public String toString() {
		return "rectangle with upperleft point "+ upperLeftPoint +" and width = "+width+", height = "+ height
				+ ", inner color (" + getInnerColor() + ") and edge color (" + getColor() + ")";
	}

	

}
