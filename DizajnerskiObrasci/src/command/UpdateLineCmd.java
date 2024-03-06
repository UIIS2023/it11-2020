package command;

import java.io.Serializable;

import geometry.Line;
import geometry.Shape;
import mvc.DrawingModel;

public class UpdateLineCmd implements Command, Serializable{
	
	public String getCmdName() {
		return "update line command";
	}
	
	private Line oldLine;
	private Line newLine;
	private Line original = new Line();
	private DrawingModel model;
	private String log;

	public UpdateLineCmd(Line oldLine, Line newLine) {
		this.oldLine = oldLine;
		this.newLine = newLine;
	}
	
	public UpdateLineCmd(Line oldLine, Line newLine, DrawingModel model, String log) {
		this.oldLine = oldLine;
		this.newLine = newLine;
		this.model = model;
		this.log = log;
	}
	
	public Line getOldLine() {
		return oldLine;
	}


	public void setOldLine(Line oldLine) {
		this.oldLine = oldLine;
	}


	@Override
	public void execute() {
		original = oldLine.clone(original);
		model.updateShape(oldLine, newLine);
		//model.addToUndoListCommand(this);
	}

	@Override
	public void unexecute() {
		oldLine = original.clone(oldLine);
		model.updateShape(newLine, oldLine);
	}


	@Override
	public Shape getShape() {
		return newLine;
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
