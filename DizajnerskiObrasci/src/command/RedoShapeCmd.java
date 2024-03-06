package command;

import java.io.Serializable;

import geometry.Shape;
import mvc.DrawingModel;

public class RedoShapeCmd implements Command, Serializable{

	public String getCmdName() {
		return "redo shape command";
	}
	private DrawingModel model;
	private String log;

	public RedoShapeCmd (DrawingModel model, String log) {
		super();
		this.model = model;
		this.log = log;
	}
	
	@Override
	public void execute() {
		int size = model.getRedoListCommand().size();
		if(size>0) {
			Command command = model.getRedoListCommand().get(size - 1);
			model.deleteFromRedoListCommand(command);
			model.addToUndoListCommand(command);
			command.execute();
			log += command.getCmdName();
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
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
