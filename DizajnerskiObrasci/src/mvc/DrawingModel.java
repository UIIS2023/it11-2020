package mvc;

import java.io.Serializable;
import java.util.ArrayList;

import command.Command;
import geometry.Point;
import geometry.Shape;

public class DrawingModel implements Serializable{
	
	
	private int i;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	private ArrayList<Command> undoListCommand = new ArrayList<>();
	private ArrayList<Command> redoListCommand = new ArrayList<>();


	public ArrayList<Command> getRedoListCommand() {
		return redoListCommand;
	}

	public void setRedoListCommand(ArrayList<Command> redoListCommand) {
		this.redoListCommand = redoListCommand;
	}

	public void addToUndoListCommand (Command cmd) {
		System.out.println("add to undo" +cmd.getCmdName());
		if(!undoListCommand.contains(cmd)) {
			undoListCommand.add(cmd);
		}
		System.out.println("TRENUTNO STANJE UNDO LISTE");
		System.out.println("/n");
		for(int i=0; i<undoListCommand.size(); i++) {
			System.out.println(undoListCommand.get(i).getCmdName());
		}
		System.out.println("==============================================/n");
	}
	
	public void addToRedoListCommand (Command cmd) {
		System.out.println("add to redo" + cmd.getCmdName());
		if(!redoListCommand.contains(cmd)) {
			redoListCommand.add(cmd);
		}
		System.out.println("TRENUTNO STANJE REDO LISTE");
		System.out.println("/n");
		for(int i=0; i<redoListCommand.size(); i++) {
			System.out.println(redoListCommand.get(i).getCmdName());
		}
		System.out.println("==============================================/n");
	}
	
	public void deleteFromUndoListCommand (Command cmd) {
		System.out.println("delete from undo"+cmd.getCmdName());
		undoListCommand.remove(cmd);
		System.out.println("TRENUTNO STANJE UNDO LISTE");
		System.out.println("/n");
		for(int i=0; i<undoListCommand.size(); i++) {
			System.out.println(undoListCommand.get(i).getCmdName());
		}
		System.out.println("==============================================/n");
	}
	
	public void deleteFromUndoListCommand (int index) {
		undoListCommand.remove(index);
	}
	
	public void deleteFromRedoListCommand (Command cmd) {
		System.out.println("delete from redo "+ cmd.getCmdName());
		redoListCommand.remove(cmd);
		System.out.println("TRENUTNO STANJE REDO LISTE");
		System.out.println("/n");
		for(int i=0; i<redoListCommand.size(); i++) {
			System.out.println(redoListCommand.get(i).getCmdName());
		}
		System.out.println("==============================================/n");
	}


	public ArrayList<Command> getUndoListCommand() {
		return undoListCommand;
	}

	public void setUndoListCommand(ArrayList<Command> listCommand) {
		this.undoListCommand = listCommand;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public void add(Shape shape) {
		shapes.add(shape);
	}
	
	public void remove(Shape shape) {
		shapes.remove(shape);
	}

	public Shape getShape(int index) {
		return shapes.get(index);
	}
	
	public void setShape(int index, Shape shape) {
		shapes.set(index, shape);
	}
	
	public void addShapeToPosition(int index, Shape shape) {
		shapes.add(index, shape);
	}
	
	public void removeSelected() {
		shapes.removeIf(shape -> shape.isSelected());
	}
	
	public boolean isEmpty() {
		return shapes.isEmpty();
	}
	
	public int getSelected() {
		for (i = shapes.size()-1; i >= 0; i--) {
			if (shapes.get(i).isSelected()) {
				return i;
			}
		}
		return -1;
	}
	
	public ArrayList<Integer> getListOfSelectedIndexes(){
		ArrayList<Integer> listOfIndexesOfSelectedShapes = new ArrayList<>();
		for(int i=0; i<shapes.size(); i++) {
			if(shapes.get(i).isSelected()) {
				listOfIndexesOfSelectedShapes.add(i);
			}
		}
		return listOfIndexesOfSelectedShapes;
	}
	
	public int getNumberOfAllSelected() {
		int numberOfAllSelected = 0;
		for (i=0; i<shapes.size(); i++) {
			if (shapes.get(i).isSelected()) {
				numberOfAllSelected += 1;
			}
		}
		return numberOfAllSelected;
	}
	
	public void select(Point point) {
		for (i = shapes.size() - 1; i >= 0; i--) { 
			if (shapes.get(i).contains(point.getX(), point.getY())) {
				shapes.get(i).setSelected(true);
				return;
			}
		}
	}
	
	public void deselect() {
		shapes.forEach(shape -> shape.setSelected(false));
	}
	
	
	public void updateShape(Shape oldShape, Shape newShape) {
		if(this.getShapes().size()>0) {
			int index = getShapes().indexOf(oldShape);
			getShapes().set(index, newShape);
		}
	}
	
}
