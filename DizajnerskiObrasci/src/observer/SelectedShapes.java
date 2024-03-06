package observer;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectedShapes implements Observable {
	
	private int selectedShapes;
	private List<Observer> observers = new ArrayList<>();
	

	@Override
	public void addObservers(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObservers(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		Iterator<Observer> it = observers.iterator();
		while(it.hasNext()) {
			it.next().update(selectedShapes);
		}
	}
	
	public int getSelectedShapes() {
		return selectedShapes;
	}

	public void setSelectedShapes(int selectedShapes) {
		this.selectedShapes = selectedShapes;
		notifyObservers();
	}


}
