package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class DrawingFrame extends JFrame {
	
	
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	
	private JPanel contentPane;
	
	private ButtonGroup btnsShapes = new ButtonGroup();
	public JToggleButton tglbtnPoint = new JToggleButton("Point");
	public JToggleButton tglbtnLine = new JToggleButton("Line");
	public JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	public JToggleButton tglbtnCircle = new JToggleButton("Circle");
	public JToggleButton tglbtnDonut = new JToggleButton("Donut");
	public JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
	
	private JButton btnDrawing = new JButton("Draw");
	private JButton btnOperationSelect = new JButton("Select");
	private JButton btnActionEdit = new JButton("Modify");
	private JButton btnActionDelete = new JButton("Delete");
	public JButton btnUndo = new JButton("Undo");
	public JButton btnRedo = new JButton("Redo");	
	
	private JButton btnSaveLog = new JButton("Save log");
	private JButton btnImportFile = new JButton("Import log");
	private JButton btnLoadNext = new JButton("Load next");
	
	private JButton btnInnerColor = new JButton("Inner color");
	private JButton btnEdgeColor = new JButton("Edge color");
	
	private JButton btnToFront = new JButton("To front");
	private JButton btnToBack = new JButton("To back");
	private JButton btnBringToFront = new JButton("Bring to front");
	private JButton btnBringToBack = new JButton("Bring to back");
	
	public Color edgeColor = Color.BLACK;
	public Color innerColor = Color.WHITE;
	
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFile = new JMenu ("");
	
	public int clicked = 0;

	public DrawingView getView() {
		return view;
	}

	public void setDrawingController(DrawingController controller) {
		this.controller = controller;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public void setBtnEdgeColor(JButton btnEdgeColor) {
		this.btnEdgeColor = btnEdgeColor;
	}

	public DrawingFrame() {
		setTitle("IT 11-2020 Savic Nikolina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 800);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1100, 900));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setBackground(Color.WHITE);
		
		
		JPanel pnlBottom = new JPanel(new BorderLayout(15, 15));
		pnlBottom.setBorder(new EmptyBorder(15,15,15,15));
		pnlBottom.add(scrollPane, BorderLayout.NORTH);
		scrollPane.setPreferredSize(new Dimension(40,100));
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JPanel pnlEast = new JPanel();
		contentPane.add(pnlEast, BorderLayout.EAST);
		
		JPanel pnlWest = new JPanel();
		contentPane.add(pnlWest, BorderLayout.WEST);
		
		tglbtnPoint.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));      
		tglbtnPoint.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglbtnPoint);
		
		tglbtnLine.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));
		tglbtnLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglbtnLine);
		
		tglbtnRectangle.setFont(new Font("Tw Cen MT", Font.PLAIN, 17));
		tglbtnRectangle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglbtnRectangle);
		
		tglbtnCircle.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));
		tglbtnCircle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglbtnCircle);
		
		tglbtnDonut.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));
		tglbtnDonut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglbtnDonut);
		
		tglbtnHexagon.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));
		tglbtnHexagon.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglbtnHexagon);
		
		btnUndo.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));      
		btnUndo.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRedo.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));      
		btnRedo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.actionPerformedUndo(e);
			}
		});
		
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.actionPerformedRedo(e);
			}
		});
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		
		btnDrawing.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));
		btnDrawing.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.actionPerformedDrawing(e);
			}
		});
		
		btnOperationSelect.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));
		btnOperationSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(btnOperationSelect);
		btnOperationSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.actionPerformedEditDelete(e);
			}
		});
		
		btnActionEdit.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));
		btnActionEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnActionEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.actionPerformedModify(e);
			}
		});
		
		btnActionDelete.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));
		btnActionDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnActionDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.actionPerformedDelete(e);
			}
		});
		
		btnToFront.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));
		btnToFront.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		
		btnToBack.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));
		btnToBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		
		btnBringToFront.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));
		btnBringToFront.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		
		btnBringToBack.setFont(new Font("Tw Cen MT", Font.TRUETYPE_FONT, 17));
		btnBringToBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		
		setJMenuBar(menuBar);
		menuBar.add(menuFile);
		
		btnSaveLog.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
			}
		});
		
		btnImportFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					controller.importLog();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		btnLoadNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					controller.loadNextShape();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		btnInnerColor.setBackground(Color.WHITE);
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					controller.innerColorOfShapeChoose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		btnEdgeColor.setBackground(Color.BLACK);
		btnEdgeColor.setForeground(Color.WHITE);
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					controller.edgeColorOfShapeChoose();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		
		GroupLayout gl_pnlTop = new GroupLayout(pnlNorth);
		gl_pnlTop.setHorizontalGroup(
			gl_pnlTop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTop.createSequentialGroup()
					.addGroup(gl_pnlTop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_pnlTop.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnDrawing, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(btnOperationSelect, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(btnActionEdit, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(btnActionDelete, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(btnToFront, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(btnToBack, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(btnBringToFront, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(btnBringToBack, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						)
					.addGroup(gl_pnlTop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnlBottom, GroupLayout.PREFERRED_SIZE, 900, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addGap(49)
							.addGroup(gl_pnlTop.createParallelGroup(Alignment.LEADING, false)
								.addComponent(view, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_pnlTop.createSequentialGroup()
									.addComponent(tglbtnPoint, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(tglbtnLine, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(tglbtnRectangle, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(tglbtnHexagon, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_pnlTop.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLoadNext, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnImportFile, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSaveLog, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnInnerColor, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEdgeColor, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		
		
		gl_pnlTop.setVerticalGroup(
			gl_pnlTop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTop.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTop.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnPoint, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnLine, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnRectangle, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnHexagon))
					.addGap(18)
					.addGroup(gl_pnlTop.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addGroup(gl_pnlTop.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlTop.createSequentialGroup()
									.addComponent(btnDrawing, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(15)
									.addComponent(btnOperationSelect, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(15)
									.addComponent(btnActionEdit, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(15)
									.addComponent(btnActionDelete, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(40)
									.addComponent(btnToFront, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(15)
									.addComponent(btnToBack, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(15)
									.addComponent(btnBringToFront, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(15)
									.addComponent(btnBringToBack, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(40)
									.addComponent(btnUndo)
									.addGap(15)
									.addComponent(btnRedo))
								.addGroup(gl_pnlTop.createSequentialGroup()
									.addComponent(btnSaveLog)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnImportFile)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnLoadNext)
									.addGap(40)
									.addComponent(btnInnerColor)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnEdgeColor)))
							.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
							.addComponent(pnlBottom, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTop.createSequentialGroup()
							.addComponent(view, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
							.addGap(150))))
		);   
		pnlNorth.setLayout(gl_pnlTop);
	}	

	public void writeTextToTextArea (String text) {
		textArea.append(text);
		textArea.append("\n");
	}
	
	public void clearTextArea() {
		textArea.setText("");
	}

	public JButton getBtnActionDelete() {
		return btnActionDelete;
	}

	public void setBtnActionDelete(JButton btnActionDelete) {
		this.btnActionDelete = btnActionDelete;
	}

	public JButton getBtnActionEdit() {
		return btnActionEdit;
	}

	public void setBtnActionEdit(JButton btnActionEdit) {
		this.btnActionEdit = btnActionEdit;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}

	
	
}
