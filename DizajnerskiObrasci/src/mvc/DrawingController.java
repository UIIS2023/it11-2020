package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import adapter.HexagonAdapter;
import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.CommandExecutor;
import command.CompoundRemoveShapeCommand;
import command.RedoShapeCmd;
import command.RemoveShapeCmd;
import command.ShapesWithPosition;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UndoShapeCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateEdgeColorCmd;
import command.UpdateHexagonCmd;
import command.UpdateInnerColorCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.SurfaceShape;
import observer.SelectedShapes;
import observer.SelectedShapesUpdate;
import strategy.SaveCommands;

import strategy.SaveLog;
import strategy.SaveManager;

public class DrawingController {
	
	private final int OPERATION_DRAWING = 1;      
	private final int OPERATION_EDIT_DELETE = 0;   
	private int activeOperation = OPERATION_DRAWING;
	
	private boolean lineWaitingForSecondPoint = false;
	private Point lineFirstPoint;
	 
	private DrawingModel model;
	private DrawingFrame frame;
	
	private Command cmd;
	private CommandExecutor commandExecutor;
	private SelectedShapes selectedShapes;
	private SelectedShapesUpdate selectedShapesUpdate;
	
	
	
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		super();
		this.model=model;
		this.frame=frame;
		this.selectedShapes = new SelectedShapes();
		this.selectedShapesUpdate = new SelectedShapesUpdate(frame);
		this.selectedShapes.addObservers(selectedShapesUpdate);
		this.commandExecutor = new CommandExecutor();
	}
			
			public void mouseClicked(MouseEvent e) {
				
				frame.getBtnActionDelete().setEnabled(false);
				frame.getBtnActionEdit().setEnabled(false);
				
				Point mouseClick = new Point(e.getX(), e.getY());
				if (!frame.tglbtnLine.isSelected()) 
					lineWaitingForSecondPoint = false;
				
				if (activeOperation == OPERATION_EDIT_DELETE) {
					model.select(mouseClick);
					this.selectedShapes.setSelectedShapes(model.getNumberOfAllSelected());
					edgeColorOfShape();
					if(!frame.tglbtnPoint.isSelected() && !frame.tglbtnLine.isSelected()) {
						try {
							innerColorOfShape();
						}
						catch(ClassCastException ex){
							ex.printStackTrace();
							System.out.println(ex.getMessage());
						}
					}
					frame.repaint();
					return;
				}
				if (frame.tglbtnPoint.isSelected()) {
					DlgPoint dlgPoint = new DlgPoint();
					dlgPoint.setPoint(mouseClick);
					dlgPoint.setColors(frame.edgeColor);
					dlgPoint.setVisible(true);
					
					if(dlgPoint.getPoint() != null) {
						addShapeSupport(dlgPoint.getPoint()); 
					}
					return;
					
				} else if (frame.tglbtnLine.isSelected()) {
					DlgLine dlgLine = new DlgLine();
					dlgLine.firstPoint(mouseClick);
					dlgLine.setColors(frame.edgeColor);
					dlgLine.setVisible(true);
					if(dlgLine.getLine() != null) {
						addShapeSupport(dlgLine.getLine());
					}
					return;
				} else if (frame.tglbtnRectangle.isSelected()) {
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.setPoint(mouseClick);
					dlgRectangle.setColors(frame.edgeColor, frame.innerColor);
					dlgRectangle.setVisible(true);
					
					if(dlgRectangle.getRectangle() != null) {
						addShapeSupport(dlgRectangle.getRectangle());
					}
					return;
				} else if (frame.tglbtnCircle.isSelected()) {
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.setPoint(mouseClick);
					dlgCircle.setColors(frame.edgeColor, frame.innerColor);
					dlgCircle.setVisible(true);
					
					if(dlgCircle.getCircle() != null) {
						addShapeSupport(dlgCircle.getCircle());
					}
					return;
				} else if (frame.tglbtnDonut.isSelected()) {
					DlgDonut dlgDonut = new DlgDonut();
					dlgDonut.setPoint(mouseClick);
					dlgDonut.setColors(frame.edgeColor, frame.innerColor);
					dlgDonut.setVisible(true);
					
					if(dlgDonut.getDonut() != null) {
						addShapeSupport(dlgDonut.getDonut());
					}
					return;
				} else if (frame.tglbtnHexagon.isSelected()) {
					DlgHexagon dlgHexagon = new DlgHexagon();
					dlgHexagon.setPoint(mouseClick);
					dlgHexagon.setColors(frame.edgeColor, frame.innerColor);
					dlgHexagon.setVisible(true);
					
					if(dlgHexagon.getHexagonAdapter() != null) {
						addShapeSupport(dlgHexagon.getHexagonAdapter());
					}
					return;
				}
			}
			
			private void commandGeneral() {
				commandExecutor.executeCommand(cmd);
				frame.writeTextToTextArea(cmd.getLog());
				if(!(cmd instanceof UndoShapeCmd || cmd instanceof RedoShapeCmd)) {
					model.addToUndoListCommand(cmd);
				}
				frame.repaint();
			}

			private void addShapeSupport( Shape shape) {
				String log = "Added " + shape.toString();
				cmd = new AddShapeCmd(model, shape, log);
				commandGeneral();
				model.setRedoListCommand(new ArrayList<Command>());
			}
		
			private void updateShapeSupport(Shape shape) {
				commandGeneral();
				shape.setSelected(true);
			}
			
			public void actionPerformedDrawing(ActionEvent e) {
				activeOperation=OPERATION_DRAWING;
				model.deselect();
				frame.repaint();
			}
			
			public void actionPerformedEditDelete(ActionEvent e) {  
				activeOperation=OPERATION_EDIT_DELETE;
			}
			
		
			public void actionPerformedModify(ActionEvent e) {
				int index=model.getSelected();
				if(index==-1) 
					return;
				
				Shape shape = model.getShape(index);
				
				if (shape instanceof Point) {
					DlgPoint dlgPoint = new DlgPoint();
					dlgPoint.setPoint((Point)shape);
					dlgPoint.setVisible(true);
					
					if(dlgPoint.getPoint() != null) {
						int originalPointIndex = model.getSelected();
						frame.writeTextToTextArea("Selected " + dlgPoint.getPoint().toString());
						Point newP = dlgPoint.getPoint();
						Point oldP = (Point) model.getShapes().get(originalPointIndex);
						String log = "Updated " + dlgPoint.getPoint().toString();
						cmd = new UpdatePointCmd(oldP, newP, model, log);
						updateShapeSupport(newP);
					}
				} else if (shape instanceof Line) {
					DlgLine dlgLine = new DlgLine();
					dlgLine.setLine((Line)shape);
					dlgLine.setVisible(true);
					
					if(dlgLine.getLine() != null) {
						int originalLineIndex = model.getSelected();
						frame.writeTextToTextArea("Selected " + dlgLine.getLine().toString());
						Line oldL = (Line) model.getShapes().get(originalLineIndex);
						Line newL = dlgLine.getLine();
						String log = "Updated " + dlgLine.getLine().toString();
						cmd = new UpdateLineCmd(oldL, newL, model, log);
						updateShapeSupport(newL);
					}
				} else if (shape instanceof Rectangle) {
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.setRectangle((Rectangle)shape);
					dlgRectangle.setVisible(true);
					
					if(dlgRectangle.getRectangle() != null) {
						int originalRectIndex = model.getSelected();
						frame.writeTextToTextArea("Selected " + dlgRectangle.getRectangle().toString());
						Rectangle oldR = (Rectangle) model.getShapes().get(originalRectIndex);
						Rectangle newR = dlgRectangle.getRectangle();
						String log = "Updated " + dlgRectangle.getRectangle().toString();
						cmd = new UpdateRectangleCmd(oldR, newR, model, log);
						updateShapeSupport(newR);
					}
				
				}else if (shape instanceof Donut) {
						DlgDonut dlgDonut = new DlgDonut();
						dlgDonut.setDonut((Donut)shape);
						dlgDonut.setVisible(true);
						
						if(dlgDonut.getDonut() != null) {
							int originalDonutIndex = model.getSelected();
							frame.writeTextToTextArea("Selected " + dlgDonut.getDonut().toString());
							Donut newD = dlgDonut.getDonut();
							Donut oldD = (Donut) model.getShapes().get(originalDonutIndex);
							String log = "Updated " + dlgDonut.getDonut().toString();
							cmd = new UpdateDonutCmd(oldD, newD, model, log);
							updateShapeSupport(newD);
						}
				} else if (shape instanceof Circle) {
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.setCircle((Circle)shape);
					dlgCircle.setVisible(true);
					
					if(dlgCircle.getCircle() != null) {
						int originalCircleIndex = model.getSelected();
						frame.writeTextToTextArea("Selected "  + dlgCircle.getCircle().toString());
						Circle newC = dlgCircle.getCircle();
						Circle oldC = (Circle) model.getShapes().get(originalCircleIndex);
						String log =  "Updated "  + dlgCircle.getCircle().toString();
						cmd = new UpdateCircleCmd(oldC, newC, model, log);
						updateShapeSupport(newC);
					}
				} else if (shape instanceof HexagonAdapter) {
					DlgHexagon dlgHexagon = new DlgHexagon();
					dlgHexagon.setHexagonAdapter((HexagonAdapter)shape);
					dlgHexagon.setVisible(true);
					
					if(dlgHexagon.getHexagonAdapter() != null) {
						int originalHexagonIndex = model.getSelected();
						frame.writeTextToTextArea("Selected ");
						HexagonAdapter newH = dlgHexagon.getHexagonAdapter();
						HexagonAdapter oldH = (HexagonAdapter) model.getShapes().get(originalHexagonIndex);
						String log = "Updated " + dlgHexagon.getHexagonAdapter().toString();
						cmd = new UpdateHexagonCmd(oldH, newH, model, log);
						updateShapeSupport(newH);
					}
				} 
			}

		
			public void actionPerformedDelete(ActionEvent e) {
				if (model.getShapes().size() == 0) {
			        JOptionPane.showMessageDialog(frame, "There are no shapes that can be deleted.");
			        return;
			    }
			    if (JOptionPane.showConfirmDialog(null, "Do you really want to delete the selected shape(s)?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
			        ArrayList<Integer> listOfSelectedShapes = model.getListOfSelectedIndexes();
			        ArrayList<ShapesWithPosition> selectedShapes = new ArrayList<>();
			        String log = "Deleted shapes: ";
			        for (int i = listOfSelectedShapes.size() - 1; i >= 0; i--) {
			            Shape selectedShape = model.getShapes().get(listOfSelectedShapes.get(i));
			            ShapesWithPosition helperShape = new ShapesWithPosition(listOfSelectedShapes.get(i), selectedShape);
			            selectedShapes.add(helperShape);
			            log += helperShape.getShape().toString() + "; ";
			        }
			        cmd = new CompoundRemoveShapeCommand(model, selectedShapes, log);
			        commandGeneral();
			    }
			}
			
			
			public void actionPerformedUndo(ActionEvent e) {
				if(model.getUndoListCommand().size()==0) {
					JOptionPane.showMessageDialog(frame, "There is no commands that could be undid.");
				} 
				else {
					String log = "Undo command has been executed for ";
					cmd = new UndoShapeCmd(model, log);
					commandExecutor.executeCommand(cmd);
					frame.writeTextToTextArea(cmd.getLog());
					frame.repaint();
				}
			}
			
			public void actionPerformedRedo(ActionEvent e) {
				if(model.getRedoListCommand().size()==0) {
					JOptionPane.showMessageDialog(frame, "There is no commands that could be redid.");
				} else {
					String log = "Redo command has been executed for ";
					cmd = new RedoShapeCmd(model, log);
					commandExecutor.executeCommand(cmd);
					frame.writeTextToTextArea(cmd.getLog());
					frame.repaint();
				}
			}
			
			public void saveLog() {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt","txt");
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setFileFilter(filter);
				
				if (fileChooser.showSaveDialog(frame.getParent()) == JFileChooser.APPROVE_OPTION) 
				{	
					try {
						File file = fileChooser.getSelectedFile();
						String filePath;
						filePath = file.getCanonicalPath();
						
						String path1 = "";
						String path2 = "";
						if(!filePath.contains(".")) {
							path1 = filePath + ".txt";
							//path2 = filePath + "_shapes.txt";	
						} else {
							path1 = filePath;
							//path2 = filePath.replace(".", "_shapes.");
						}
						
						File logToSave = new File(path1);
						File commandsToSave = new File(path1);
						//File commandsToSave = new File(path2);
						
						SaveManager manager = new SaveManager(new SaveLog());
						manager.save(frame, logToSave);
						SaveManager manager2 = new SaveManager(new SaveCommands());
						manager2.save(commandExecutor, commandsToSave);
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
					
				}
				frame.repaint();
			}
			
			public void importLog() throws IOException{
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt","txt");
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setFileFilter(filter);

				if(isDeleted == false) {
					frame.clearTextArea();
					for(int i=0; i<model.getShapes().size(); i++) {
						model.remove(model.getShape(i));
						frame.repaint();
					}
				}
				fileChooser.showOpenDialog(null);
				File drawingToLoad = fileChooser.getSelectedFile();
				loadDrawing(drawingToLoad);
			}
			
			ArrayList<Command> helperCommands = new ArrayList<Command>();
			
			public void loadDrawing(File drawingToLoad) throws IOException {
				
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(drawingToLoad));
				try {
					helperCommands = (ArrayList<Command>) ois.readObject();
					clicked = 0;
					
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				ois.close();
			}
			
			boolean isDeleted = false;
			int counter = 0;
			int clicked = 0;
			
			public void loadNextShape() {
				if(clicked < helperCommands.size()) {
					cmd = helperCommands.get(clicked);
					cmd.setModel(model);
					commandGeneral();
					clicked+=1;
					System.out.println("trenutni klik je " + clicked);
				} 
			}
			
			
			public void toFront() {
				if(model.getShapes().size()>0) {
					Shape selectedShape = model.getShapes().get(model.getSelected());
					String log = "The command to front has been invoked for " + selectedShape.toString();
					cmd = new ToFrontCmd(model, selectedShape, log);
					commandGeneral();
				}
			}
			
			public void toBack() {
				if(model.getShapes().size()>0) {
					Shape selectedShape = model.getShapes().get(model.getSelected());
					String log = "The command to back has been invoked for " + selectedShape.toString();
					cmd = new ToBackCmd(model, selectedShape, log);
					commandGeneral();
				}
			}
			
			public void bringToFront() {
				if(model.getShapes().size()>0) {
					Shape selectedShape = model.getShapes().get(model.getSelected());
					String log = "The command bring to front has been invoked for " + selectedShape.toString();
					cmd = new BringToFrontCmd(model, selectedShape, log);
					commandGeneral();
				}
			}
			
			public void bringToBack() {
				if(model.getShapes().size()>0) {
					Shape selectedShape = model.getShapes().get(model.getSelected());
					String log = "The command bring to back has been invoked for " + selectedShape.toString();
					cmd = new BringToBackCmd(model, selectedShape, log);
					commandGeneral();
				}
			}
			
			public void edgeColorOfShape() {
				if(model.getShapes().size()>0) {
					int index = model.getSelected();
					if(index>=0) {
						Shape selectedShape = model.getShapes().get(index);
						frame.getBtnEdgeColor().setBackground(selectedShape.getColor());
						if(selectedShape.getColor() == Color.BLACK) {
							frame.getBtnEdgeColor().setForeground(Color.WHITE);
						}
					}
				}
			}
			
			public void edgeColorOfShapeChoose() {
				int index = model.getSelected();
				if(index>=0) {
					Shape selectedShape = model.getShapes().get(index);
					Color oldColor = selectedShape.getColor();
					Color newColor = JColorChooser.showDialog(null, "Choose edge color:", oldColor);
					if (newColor == null) {
						newColor = Color.BLACK;
					}
					String log = "The command to change the edge color has been invoked for " + selectedShape.toString();
					cmd = new UpdateEdgeColorCmd(model, selectedShape, oldColor, newColor, log);
					frame.getBtnEdgeColor().setBackground(cmd.getShape().getColor());
					commandGeneral();
				} else {
					JOptionPane.showMessageDialog(frame, "There is no shapes.");
					return;
				}
			}
			
			public void innerColorOfShape() {
				int index = model.getSelected();
				if(index>=0) {
					SurfaceShape selectedShape = (SurfaceShape)model.getShapes().get(index);
					frame.getBtnInnerColor().setBackground(selectedShape.getInnerColor());
				} else {
					JOptionPane.showMessageDialog(frame, "There is no shapes.");
					return;
				}
			}
		
			public void innerColorOfShapeChoose() {
				int index = model.getSelected();
				if(index>=0) {
					try {
						SurfaceShape selectedShape = (SurfaceShape)model.getShapes().get(index);
						Color oldColor = selectedShape.getInnerColor();
						Color newColor = JColorChooser.showDialog(null, "Choose inner color:", oldColor);
						if (newColor == null) {
							newColor = Color.WHITE;
						}
						String log = "The command to change the inner color has been invoked for " + selectedShape.toString();
						cmd = new UpdateInnerColorCmd(model, selectedShape, oldColor, newColor, log);
						if(cmd instanceof UpdateInnerColorCmd) {
							frame.getBtnInnerColor().setBackground(((UpdateInnerColorCmd) cmd).getSelectedShape().getInnerColor());
						}
						commandGeneral();
					} catch (Exception ex) {
						ex.printStackTrace();
						System.out.println(ex.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(frame, "There is no shapes.");
					return;
				}
			}
			
}
