package command;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.JColorChooser;

import geometry.Shape;
import geometry.SurfaceShape;
import mvc.DrawingModel;

public class UpdateEdgeColorCmd implements Command, Serializable{
	
	public String getCmdName() {
		return "UpdateEdgeColorCmd";
	}
	
	private DrawingModel model;
	private Shape selectedShape;
	private Color oldColor;
	private Color newColor;
	private SurfaceShape newShape;
	private String log;
	
	public UpdateEdgeColorCmd(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.selectedShape = shape;
	}
	
	public UpdateEdgeColorCmd(DrawingModel model, Shape shape, Color oldC, Color newC, String log) {
		super();
		this.model = model;
		this.selectedShape = shape;
		this.oldColor = oldC;
		this.newColor = newC;
		this.log = log;
	}

	@Override
	public void execute() {
		try {
			newShape = (SurfaceShape) selectedShape.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		newShape.setColor(newColor);
		model.updateShape(selectedShape, newShape);
	}

	@Override
	public void unexecute() {
		model.updateShape(newShape, selectedShape);
	}

	@Override
	public Shape getShape() {
		return selectedShape;
	}

	@Override
	public void setModel(DrawingModel model) {
		this.model = model;
	}

	@Override
	public String getLog() {
		// TODO Auto-generated method stub
		return log;
	}

}
