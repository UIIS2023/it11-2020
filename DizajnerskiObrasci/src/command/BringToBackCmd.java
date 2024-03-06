package command;

import java.io.Serializable;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command, Serializable{
	
	public String getCmdName() {
		return "bring to back cmd";
	}
	
	private DrawingModel model;
	private Shape shape;
	private int lastPosition = 0;
	private String log;

	public BringToBackCmd(DrawingModel model, Shape shape, String log) {
		super();
		this.model = model;
		this.shape = shape;
		this.log = log;
	}

	@Override
	public void execute() {
		
		lastPosition = model.getSelected();
		shape = model.getShapes().get(lastPosition);
		
//		if(model.getShapes().size()>0) {
//			for(int i=lastPosition; i>0; i--) {
//				model.getShapes().set(i, model.getShapes().get(i-1));
//			}
//			model.getShapes().set(0, shape);
//			model.addToUndoListCommand(this);
//		}
		bringToBack(shape, lastPosition);
	}
	
	public void bringToBack(Shape shape, int lastPosition) {
		shape = model.getShapes().get(lastPosition);
		
		for(int i=lastPosition; i>0; i--) {
			model.getShapes().set(i, model.getShapes().get(i-1));
		}
		model.getShapes().set(0, shape);
		model.addToUndoListCommand(this);
	}

	@Override
	public void unexecute() {
		for(int i=0; i<lastPosition; i++) {
			model.getShapes().set(i, model.getShapes().get(i+1));
		}
		model.getShapes().set(lastPosition, shape);
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
		return log;
	}

	
}
