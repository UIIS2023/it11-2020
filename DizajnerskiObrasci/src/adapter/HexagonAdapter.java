package adapter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.Serializable;

import geometry.Moveable;
import geometry.Point;
import geometry.SurfaceShape;
import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape implements Moveable, Serializable{
	
	private Point center;
	private int radius;
	private Hexagon hexagon = new Hexagon(0, 0, 0);
	Color borderColor;
	Color innerColor;
	
	public Color getBorderColor() {
		return borderColor;
	}


	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	
	public HexagonAdapter() {
		//this.hexagon = new Hexagon(0,0,0);
	}

//	public HexagonAdapter(Point center, int radius, boolean selected, Color color, Color innerColor) {
//		this.center = center;
//		this.radius = radius;
//		this.setSelected(selected);
//		hexagon.setAreaColor(innerColor);
//		hexagon.setBorderColor(color);
//		this.hexagon = new Hexagon(center.getX(), center.getY(), radius);
//	}
	
	public HexagonAdapter(Point center, int r, boolean selected, Color color, Color innerColor) {
		this.hexagon = new Hexagon(center.getX(), center.getY(), r);
		this.setSelected(selected);
		hexagon.setAreaColor(innerColor);
		hexagon.setBorderColor(color);
	}
	
	public HexagonAdapter clone(HexagonAdapter hexagon) {
		
		hexagon.getHexagon().setX(this.getHexagon().getX());
		hexagon.getHexagon().setY(this.getHexagon().getY());
		
		hexagon.setColor(this.getColor());
		hexagon.setInnerColor(this.getInnerColor());
		
		return hexagon;
		
	}
	

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	
	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}
	
	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		super.setSelected(selected);
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof HexagonAdapter) {
			return hexagon.getR() - ((HexagonAdapter) o).getHexagon().getR();
		}
		return 0;
	}

	@Override
	public boolean contains(Point p) {
		return hexagon.doesContain(hexagon.getX(), hexagon.getY());
	}


	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			Hexagon hexaFromObj = ((HexagonAdapter) obj).getHexagon();
			return hexagon.getX() == hexaFromObj.getX() && hexagon.getY() == hexaFromObj.getY()
					&& hexagon.getR() == hexaFromObj.getR();
		}
		return false;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		hexagon.paint(g);
		this.fill(g);
		
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
	}
	
	//***************************************
	
	@Override
	public void setInnerColor(Color innerColor) {
		hexagon.setAreaColor(innerColor);
		super.setInnerColor(innerColor);
	}

	@Override
	public Color getInnerColor() {
		return hexagon.getAreaColor();
	}

	@Override
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
		super.setColor(color);
	}

	@Override
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public String toString() {
		return "hexagon with center in point (" + hexagon.getX() + ", " + hexagon.getY() + ")" + " and radius " + hexagon.getR() + " and inner color " + hexagon.getAreaColor() + " and edge color " + hexagon.getBorderColor();	
	}


	public Point getCenter() {
		return center;
	}


	public void setCenter(Point center) {
		this.center = center;
	}


	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
	

}
