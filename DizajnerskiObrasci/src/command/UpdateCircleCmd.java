package command;

import java.io.Serializable;

import geometry.Circle;
import geometry.Shape;
import mvc.DrawingModel;

public class UpdateCircleCmd implements Command, Serializable{
	
	public String getCmdName() {
		return "update circle command";
	}
	private Circle oldCircle;
	private Circle newCircle;
	private Circle originalCircle = new Circle();
	private DrawingModel model;
	private String log;

	public UpdateCircleCmd (Circle oldCircle, Circle newCircle) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
	}
	
	public UpdateCircleCmd (Circle oldCircle, Circle newCircle, DrawingModel model, String log) {
		this.oldCircle = oldCircle;
		this.newCircle = newCircle;
		this.model = model;
		this.log = log;
	}
	
	@Override
	public void execute() {
		originalCircle = oldCircle.clone(originalCircle);
		model.updateShape(oldCircle, newCircle);
		//model.addToUndoListCommand(this);
	}

	@Override
	public void unexecute() {
		oldCircle = originalCircle.clone(oldCircle);
		model.updateShape(newCircle, oldCircle);
	}
	
	
	@Override
	public Shape getShape() {
		return newCircle;
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
