package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;

public class DrawingView extends JPanel{
	
	private DrawingModel model = new DrawingModel();
	
	public DrawingView() {
		setBackground(Color.WHITE);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean doAction = true;
				ArrayList<Shape> shapes = model.getShapes();
				for (int i = 0; i < shapes.size(); i++) {   
					if (shapes.get(i).contains(e.getPoint().x, e.getPoint().y)) {
						doAction = false;
					}
				}
				if(doAction) {
					for (int i = 0; i < shapes.size(); i++) {
						shapes.get(i).setSelected(false);
					}
				}
			}
		});
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext())
			it.next().draw(g);
	}
	
}
