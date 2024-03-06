package command;

import java.io.Serializable;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class UpdatePointCmd implements Command, Serializable{
	
	public String getCmdName() {
		return "update point command";
	}

	private Point oldPoint;
	private Point newPoint;
	private Point original = new Point();
	private DrawingModel model;
	private String log;
	
	public UpdatePointCmd(Point oldPoint) {
		super();
		this.oldPoint = oldPoint;
	}
	
	public UpdatePointCmd(Point oldPoint, Point newPoint) {
		super();
		this.oldPoint = oldPoint;
		this.newPoint = newPoint;
		this.original = new Point();
	}
	
	public UpdatePointCmd(Point oldPoint, Point newPoint, DrawingModel model, String log) {
		super();
		this.oldPoint = oldPoint;
		this.newPoint = newPoint;
		this.original = new Point();
		this.model = model;
		this.log = log;
	}
	
	@Override
	public void execute() {
		original = oldPoint.clone(original);
		model.updateShape(oldPoint, newPoint);
		//model.addToUndoListCommand(this);
	}

	@Override
	public void unexecute() {
		oldPoint = original.clone(oldPoint);
		model.updateShape(newPoint, oldPoint);
	}

	@Override
	public Shape getShape() {
		return newPoint;
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
