package command;

import java.io.Serializable;

import geometry.Donut;
import geometry.Shape;
import mvc.DrawingModel;

public class UpdateDonutCmd implements Command, Serializable{

	public String getCmdName() {
		return "update donut command";
	}
	private Donut oldDonut;
	private Donut newDonut;
	private Donut originalDonut = new Donut();
	private DrawingModel model;
	private String log;
	
	public UpdateDonutCmd(Donut oldDonut, Donut newDonut) {
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
	}
	
	public UpdateDonutCmd(Donut oldDonut, Donut newDonut, DrawingModel model, String log) {
		this.oldDonut = oldDonut;
		this.newDonut = newDonut;
		this.model = model;
		this.log = log;
	}
	
	@Override
	public void execute() {
		originalDonut = oldDonut.clone(originalDonut);
		model.updateShape(oldDonut, newDonut);
		model.addToUndoListCommand(this);
	}

	@Override
	public void unexecute() {
		oldDonut = originalDonut.clone(oldDonut);
		model.updateShape(newDonut, oldDonut);
	}

	@Override
	public Shape getShape() {
		return newDonut;
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
