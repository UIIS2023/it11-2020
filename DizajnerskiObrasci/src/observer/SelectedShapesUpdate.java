package observer;

import mvc.DrawingFrame;

public class SelectedShapesUpdate implements Observer{

	int selectedShapes;
	DrawingFrame frame;
	
	public SelectedShapesUpdate(DrawingFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void update(int countSelectedShape) {
		this.selectedShapes = countSelectedShape;
		updateButtons();
	}		
	
	void updateButtons() {
		if(selectedShapes==0) {
			frame.getBtnActionDelete().setEnabled(false);
			frame.getBtnActionEdit().setEnabled(false);
		}
		else if(selectedShapes == 1){
			frame.getBtnActionDelete().setEnabled(true);
			frame.getBtnActionEdit().setEnabled(true);
		} 
		else if(selectedShapes > 1) {
			frame.getBtnActionDelete().setEnabled(true);
			frame.getBtnActionEdit().setEnabled(false);
		}
	}
		
}
