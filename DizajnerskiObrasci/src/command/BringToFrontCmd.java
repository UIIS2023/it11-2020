package command;

import java.io.Serializable;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command, Serializable{

	public String getCmdName() {
		return "bring to front cmd";
	}
	private DrawingModel model;
	private Shape shape;
	private int lastPosition = 0;
	private String log;
	
	public BringToFrontCmd(DrawingModel model, Shape shape, String log) {
		super();
		this.model = model;
		this.shape = shape;
		this.log = log;
	}
	
	@Override
	public void execute() {
		bringToFront(shape, lastPosition);
	}
	
	public void bringToFront(Shape shape, int lastPosition) {
		shape = model.getShapes().get(lastPosition);
		
		for(int i=lastPosition; i<model.getShapes().size()-1; i++) {
			model.getShapes().set(i, model.getShapes().get(i+1));		
		}
		model.getShapes().set(model.getShapes().size()-1, shape);
//		model.addToUndoListCommand(this);
	}

	@Override
	public void unexecute() {
		unexecuteBringToFront(shape, lastPosition);
	}
	
	public void unexecuteBringToFront(Shape shape, int lastPosition){
		for(int i=model.getShapes().size()-1; i>lastPosition; i--) {
			model.getShapes().set(i, model.getShapes().get(i-1));
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
		// TODO Auto-generated method stub
		return log;
	}

}
