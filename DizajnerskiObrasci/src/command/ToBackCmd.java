package command;

import java.io.Serializable;

import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command, Serializable{

	public String getCmdName() {
		return "to back command";
	}
	private DrawingModel model;
	private Shape shape;
	private int lastPosition;
	private String log;
	
	public ToBackCmd(DrawingModel model, Shape shape, String log) {
		super();
		this.model = model;
		this.shape = shape;
		this.lastPosition = model.getSelected();
		this.log = log;
	}
	
	@Override
	public void execute() {
//		if(!model.getUndoListCommand().contains(this)) {
//			model.getUndoListCommand().add(this);
//	        System.out.println("dodato u undo listu " + this.getCmdName());
//		}
		toBack(shape, lastPosition);
	}
	

	@Override
	public void unexecute() {
		unexecuteToBack(shape, lastPosition);
	}
	
	public void toBack(Shape shape, int lastPosition) {
		if(lastPosition > 0) {
			model.getShapes().set(lastPosition, model.getShapes().get(lastPosition-1));
			model.getShapes().set(lastPosition-1, shape);
		}
	}
	
	public void unexecuteToBack(Shape shape, int lastPosition) {
		if(lastPosition > 0) {
			model.getShapes().set(lastPosition-1, model.getShapes().get(lastPosition));
			model.getShapes().set(lastPosition, shape);	
		}
	}

	@Override
	public Shape getShape() {
		return shape;
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
