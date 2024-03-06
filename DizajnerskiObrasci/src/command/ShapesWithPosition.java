package command;

import java.awt.Graphics;
import java.io.Serializable;

import geometry.Shape;

public class ShapesWithPosition implements Serializable{
	
	private Shape shape;
	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	private int position;
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public ShapesWithPosition(int position, Shape shape) {
		this.position = position;
		this.shape = shape;
	}
}