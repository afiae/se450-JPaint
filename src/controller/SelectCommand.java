package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.interfaces.IShapeDrawer;
import model.interfaces.IShapeList;
import model.interfaces.IShapes;
import point.Point;
import shapes.OutlineSelectedShape;
import shapes.SelectedShapeList;
import view.interfaces.PaintCanvasBase;


public class SelectCommand implements ICommand{

	Point _start, _end;
	IShapeList shapeList;
	IShapeList selectedShapeList;	
	int minX, maxX, minY, maxY;
	PaintCanvasBase paintCanvas;

	public SelectCommand(Point start, Point end, IShapeList shape_list, PaintCanvasBase pcb) {
		_start = start;
		_end = end;
		shapeList = shape_list; 	
		minX = Math.min(start.getX(), end.getX());
		maxX = Math.max(start.getX(), end.getX());
		minY = Math.min(start.getY(), end.getY());
		maxY = Math.max(start.getY(), end.getY());		
		paintCanvas = pcb;
		for(IShapes s : shapeList.getShapeList()) { s.deselect(); }
		shapeList.notifyObservers();
	}

	public void run() {
		selectedShapeList = new SelectedShapeList(shapeList, paintCanvas);	
		for(IShapes shape : shapeList.getShapeList()) {
			if(isSelected(shape)) {
				shape.select();
				selectedShapeList.add(shape);
			}
		}	
		return;
	}

	private boolean isSelected(IShapes shape) {
		int shapeMinX = Math.min(shape.getStartX(), shape.getEndX());
		int shapeMinY = Math.min(shape.getStartY(), shape.getEndY());
		int shapeMaxX = Math.max(shape.getStartX(), shape.getEndX());
		int shapeMaxY = Math.max(shape.getStartY(), shape.getEndY());
		if(shapeMinX <= maxX 
				&& shapeMaxX >= minX
				&& shapeMinY <= maxY 
				&& shapeMaxY >= minY
				) return true;

		return false;
	}
}