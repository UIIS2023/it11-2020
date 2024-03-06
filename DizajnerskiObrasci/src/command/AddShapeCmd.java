package command;

import java.io.Serializable;

import geometry.Shape;
import mvc.DrawingModel;

public class AddShapeCmd implements Command, Serializable{
	
	private DrawingModel model;
	private Shape shape;
	private String log;

	public AddShapeCmd(DrawingModel model, Shape shape, String log) {
		super();
		this.model = model;
		this.shape = shape;
		this.log = log;
	}
	

	@Override
	public void execute() {
		model.add(shape);
	}

	@Override
	public void unexecute() {
		model.remove(shape);
	}

	@Override
	public Shape getShape() {
		return shape;
	}

	@Override
	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public String getLog() {
		return log;
	}
	
	public String getCmdName() {
		return "add shape cmd";
	}
	

}
