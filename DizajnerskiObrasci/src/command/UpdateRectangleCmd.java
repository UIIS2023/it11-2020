package command;

import java.io.Serializable;

import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingModel;

public class UpdateRectangleCmd implements Command, Serializable{
	
	public String getCmdName() {
		return "update rectangle command";
	}

	private Rectangle oldRectangle;
	private Rectangle newRectangle;
	private Rectangle originalRectangle = new Rectangle();
	private DrawingModel model;
	private String log;
	
	public UpdateRectangleCmd (Rectangle oldRectangle, Rectangle newRectangle) {
		super();
		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
	}
	
	public UpdateRectangleCmd (Rectangle oldRectangle, Rectangle newRectangle, DrawingModel model, String log) {
		super();
		this.oldRectangle = oldRectangle;
		this.newRectangle = newRectangle;
		this.model = model;
		this.log = log;
	}
	
	@Override
	public void execute() {
		originalRectangle = oldRectangle.clone(originalRectangle);
		model.updateShape(oldRectangle, newRectangle);
		//model.addToUndoListCommand(this);
	}

	@Override
	public void unexecute() {
		oldRectangle = originalRectangle.clone(oldRectangle);
		model.updateShape(newRectangle, oldRectangle);
	}

	@Override
	public Shape getShape() {
		return newRectangle;
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
