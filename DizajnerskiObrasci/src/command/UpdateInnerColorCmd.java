package command;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.JColorChooser;

import geometry.Shape;
import geometry.SurfaceShape;
import mvc.DrawingModel;

public class UpdateInnerColorCmd implements Command, Serializable{
	
	public String getCmdName() {
		return "UpdateInnerColorCmd";
	}
	
	private DrawingModel model;
	private SurfaceShape selectedShape;
	private Color oldInnerColor;
	private Color newInnerColor;
	private SurfaceShape newShape;
	private String log;
	
	public UpdateInnerColorCmd(DrawingModel model, SurfaceShape shape) {
		super();
		this.model = model;
		this.selectedShape = shape;
	}
	
	public UpdateInnerColorCmd(DrawingModel model, SurfaceShape shape, Color oldC, Color newC, String log) {
		super();
		this.model = model;
		this.selectedShape = shape;
		this.oldInnerColor = oldC;
		this.newInnerColor = newC;
		this.log = log;
	}
	
	@Override
	public void execute() {
		try {
			newShape = (SurfaceShape) selectedShape.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		newShape.setInnerColor(newInnerColor);
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

	public SurfaceShape getSelectedShape() {
		return selectedShape;
	}

	@Override
	public String getLog() {
		// TODO Auto-generated method stub
		return log;
	}

}
