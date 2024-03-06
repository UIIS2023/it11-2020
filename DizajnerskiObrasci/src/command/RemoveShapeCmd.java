package command;

import java.io.Serializable;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command, Serializable{

	public String getCmdName() {
		return "RemoveShapeCmd";
	}
	private DrawingModel model;
	private Shape shape;
	private String log;
	
	public RemoveShapeCmd(DrawingModel model, Shape shape, String log) {
		super();
		this.model = model;
		this.shape = shape;
		this.log = log;
	}

	@Override
	public void execute() {
		model.remove(shape);	
		model.addToUndoListCommand(this);
	}
	
	@Override
	public void unexecute() {
		model.add(shape);
		//model.addToHistoryOfCommands(this);
	}
	
	@Override
	public Shape getShape() {
		return shape;
	}
	
	public void setModel(DrawingModel model) {
		this.model = model;
	}

	@Override
	public String getLog() {
		// TODO Auto-generated method stub
		return log;
	}
	
	
}
