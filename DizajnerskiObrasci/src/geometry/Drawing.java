package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		JFrame frame = new JFrame("Drawings");
		frame.setSize(800, 600);
		frame.setVisible(true);
		Drawing drawing = new Drawing(); // prosiruje JPanel
		frame.getContentPane().add(drawing); // poziv metode paint(g)
	}

	// redefinise metodu iz klase JComponent
	@Override
	public void paint(Graphics g) {
		
		//radi za ceo graficki kontekst a ne za oblik pojedinacno
		g.setColor(Color.GREEN);

		Point p = new Point(50, 50);
		p.draw(g);
		//p.draw(g);

		Line l1 = new Line(new Point(100, 100), new Point(200, 200));
		l1.draw(g);
		//l1.draw(g);

		Rectangle r1 = new Rectangle(l1.getEndPoint(), 50, 50);
		r1.draw(g);
		//r1.draw(g);

		Circle c1 = new Circle(new Point(500, 100), 80);
		c1.draw(g);
		//c1.draw(g);

		Donut d1 = new Donut(new Point(800, 100), 50, 25);
		d1.draw(g);
		//d1.draw(g);

		Rectangle k1 = new Rectangle(new Point(500, 500), 50, 50);
		k1.draw(g);
		//k1.draw(g);

		// 5. zadatak
		int innerR = (int) (k1.getHeight() * Math.sqrt(2) / 2);
		Donut d2 = new Donut(new Point(k1.getUpperLeftPoint().getX() + k1.getHeight() / 2,
				k1.getUpperLeftPoint().getY() + k1.getHeight() / 2), 80, innerR);
		d2.draw(g);
		//d2.draw(g);
		
		

		// Vezbe 8
		
		// Zadatak 1
		ArrayList<Shape> shapes = new ArrayList<>();
		shapes.add(p);
		shapes.add(l1);
		shapes.add(c1);
		shapes.add(d1);
		shapes.add(k1);

		Iterator<Shape> it=shapes.iterator();

		while(it.hasNext()) {
			it.next().moveBy(10, 0);
		}

		// Zadatak 2
		g.setColor(Color.BLACK);
		shapes.get(3).draw(g);
		shapes.get(shapes.size() - 1).draw(g);
		shapes.remove(1);
		shapes.get(1).draw(g);
		// preklopice se sa prethodnim kvadratom koji smo iscrtali
		shapes.get(3).draw(g);
		shapes.add(3, l1);

		it = shapes.iterator();
		while (it.hasNext()) {
			Shape s = it.next();
			if (s instanceof Circle || s instanceof Rectangle) {
				s.draw(g);
			}
		}

		// Zadatak 3
		try {
			c1.setRadius(-10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// nije se prekinulo iyvrasavanje prog i nije setovan radius
		System.out.println(c1);

		// Zadatak 4
		it = shapes.iterator();
		while (it.hasNext()) {
			Shape s = it.next();
			s.setSelected(true);
			s.draw(g);
		}

		// Zadatak 5
		HashMap<String, Shape> hmShapes = new HashMap<String, Shape>();
		hmShapes.put("point", p);
		hmShapes.put("line", l1);
		System.out.print(hmShapes.get("line"));

	}

}
