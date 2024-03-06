package command;

import java.io.Serializable;
import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class CompoundRemoveShapeCommand implements Command, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getCmdName() {
		return "compound remove shape command";
	}
	private DrawingModel model;
    private ArrayList<ShapesWithPosition> shapes;
    private String log;

    public CompoundRemoveShapeCommand(DrawingModel model, ArrayList<ShapesWithPosition> shapes, String log) {
    	this.model = model;
    	this.shapes = shapes;
    	this.log = log;
    }
	
	@Override
	public void execute() {
		for (ShapesWithPosition shape : shapes) {
            model.remove(shape.getShape());
        }
	}

	@Override
	public void unexecute() {
		for (int i = shapes.size() - 1; i >= 0; i--) {
	        ShapesWithPosition shape = shapes.get(i);
	        model.addShapeToPosition(shape.getPosition(), shape.getShape());
	    }
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return null;
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
