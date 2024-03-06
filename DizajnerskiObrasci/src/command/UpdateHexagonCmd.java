package command;

import java.io.Serializable;

import adapter.HexagonAdapter;
import geometry.Shape;
import mvc.DrawingModel;

public class UpdateHexagonCmd implements Command, Serializable{
	
	public String getCmdName() {
		return "update hexagon command";
	}
	
	private HexagonAdapter oldHexagon;
	private HexagonAdapter newHexagon;
	private HexagonAdapter originalHexagon = new HexagonAdapter();
	private DrawingModel model;
	private String log;
	
	public UpdateHexagonCmd(HexagonAdapter oldHexagon, HexagonAdapter newHexagon) {
		this.oldHexagon = oldHexagon;
		this.newHexagon = newHexagon;
	}
	
	public UpdateHexagonCmd(HexagonAdapter oldHexagon, HexagonAdapter newHexagon, DrawingModel model, String log) {
		this.oldHexagon = oldHexagon;
		this.newHexagon = newHexagon;
		this.model = model;
		this.log = log;
	}

	@Override
	public void execute() {
		originalHexagon = oldHexagon.clone(originalHexagon);
		model.updateShape(oldHexagon, newHexagon);
	}

	@Override
	public void unexecute() {
		oldHexagon = originalHexagon.clone(oldHexagon);
		model.updateShape(newHexagon, oldHexagon);
	}

	@Override
	public Shape getShape() {
		return newHexagon;
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
