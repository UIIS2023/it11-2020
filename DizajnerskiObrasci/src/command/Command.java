package command;

import geometry.Shape;
import mvc.DrawingModel;

public interface Command {
	
	void execute();
	
	void unexecute();
	
	Shape getShape();
	
	void setModel(DrawingModel model);
	
	String getCmdName();
	
	String getLog();
}
