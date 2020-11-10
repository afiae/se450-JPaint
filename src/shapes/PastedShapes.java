package shapes;

import java.util.ArrayList;

import model.interfaces.IShapeList;
import model.interfaces.IShapes;

public class PastedShapes implements IShapeList {

	private ArrayList<IShapes> pastedShapes;
	IShapeList masterList;

	public PastedShapes(IShapeList masterList) {
		pastedShapes = new ArrayList<>();
		this.masterList = masterList;		
	}

	@Override
	public void add(IShapes shape) { 
		pastedShapes.add(shape); 
		notifyObservers();
	}

	@Override
	public void remove(IShapes shape) { 
		pastedShapes.remove(shape); 
		notifyObservers();
	}

	@Override
	public void notifyObservers() { 
		masterList.notifyObservers();
	}

	@Override
	public void emptyList() { pastedShapes.clear(); }

	@Override
	public ArrayList<IShapes> getShapeList() { return pastedShapes; }

}
