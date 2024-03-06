package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import geometry.Line;
import geometry.Point;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DlgLine extends JDialog {
	private JTextField txtFirstX;
	private JTextField txtFirstY;
	private JTextField txtSecondX;
	private JTextField txtSecondY;
	
	private Line line = null;
	private Color edgeColor = null;
	private JLabel lblFirstX;
	private JLabel lblFirstY;
	private JLabel lblSecondX;
	private JLabel lblSecondY;
	private JButton btnColor;

	public DlgLine() {
		setResizable(false);
		setTitle("IT 11-2020 Savic Nikolina");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 300, 231);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			{
				lblFirstX = new JLabel("1. X coordinate", SwingConstants.CENTER);
			}
			{
				txtFirstX = new JTextField();
				txtFirstX.setColumns(10);
			}
			{
				lblFirstY = new JLabel("1. Y coordinate");
				lblFirstY.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				txtFirstY = new JTextField();
				txtFirstY.setColumns(10);
			}
			{
				lblSecondX = new JLabel("2. X coordinate");
				lblSecondX.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				txtSecondX = new JTextField();
				txtSecondX.setColumns(10);
			}
			{
				lblSecondY = new JLabel("2. Y coordinate");
				lblSecondY.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				txtSecondY = new JTextField();
				txtSecondY.setColumns(10);
			}
			{
				btnColor = new JButton("Choose a color:");
				btnColor.setHorizontalAlignment(SwingConstants.CENTER);
				btnColor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						edgeColor = JColorChooser.showDialog(null, "Choose edge color:", edgeColor);
						if (edgeColor == null)
							edgeColor = Color.BLACK;
					}
				});
			}
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(lblFirstX, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFirstX, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(lblFirstY, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFirstY, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(lblSecondX, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSecondX, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(lblSecondY, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSecondY, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
						.addContainerGap(76, Short.MAX_VALUE)
						.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addGap(71))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(1)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblFirstX, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtFirstX, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblFirstY, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtFirstY, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblSecondX, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtSecondX, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblSecondY, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtSecondY, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			panel.setLayout(gl_panel);
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.SOUTH);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							int newFirstX = Integer.parseInt(txtFirstX.getText());
							int newFirstY = Integer.parseInt(txtFirstY.getText());
							int newSecondX = Integer.parseInt(txtSecondX.getText());
							int newSecondY = Integer.parseInt(txtSecondY.getText());

							if(newFirstX < 0 || newFirstY < 0 || newSecondX < 0 || newSecondY < 0) {
								JOptionPane.showMessageDialog(null, "You have entered incorrect information!", "ERROR", JOptionPane.ERROR_MESSAGE);
								return;
							}
							line = new Line(new Point(newFirstX, newFirstY), new Point(newSecondX, newSecondY),false, edgeColor);
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "You have entered incorrect information!", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				panel.add(btnOk);
			}
			{
				JButton btnExit = new JButton("EXIT");
				btnExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				panel.add(btnExit);
			}
		}
	}
	

	public Line getLine() {
		return line;
	}
	
	public void firstPoint (Point point) {
		txtFirstX.setText("" + point.getX());
		txtFirstY.setText("" + point.getY());
	}
	
	public void secondPoint (Point point) {
		txtSecondX.setText("" + point.getX());
		txtSecondY.setText("" + point.getY());
	}
	
	public void setColors(Color edgeColor) {
		this.edgeColor = edgeColor;
	}
	
	public void setLine(Line line) {
		txtFirstX.setText("" + line.getStartPoint().getX());
		txtFirstY.setText("" + line.getStartPoint().getY());
		txtSecondX.setText("" + line.getEndPoint().getX());
		txtSecondY.setText("" + line.getEndPoint().getY());
		edgeColor = line.getColor();
	}
}
