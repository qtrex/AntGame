package com.mycompany.a3;

import java.util.Vector;

public class GameObjectCollection implements ICollection {

	private Vector theCollection;
	
	public GameObjectCollection() {
		theCollection = new Vector();
	}

	@Override
	public void add(GameObject newObject) {      //Function to add more elements to the collection
		// TODO Auto-generated method stub
		theCollection.addElement(newObject);
	}

	@Override
	public IIterator getIterator() {
		// TODO Auto-generated method stub
		return new GameVectorIterator();
	}
	
	//Iterator for the collection
	private class GameVectorIterator implements IIterator {

		private int currElementIndex;
		
		public GameVectorIterator() {
			currElementIndex = -1;
		}
		
		@Override
		public boolean hasNext() {               //Checks to see if there is another element
			// TODO Auto-generated method stub
			if (theCollection.size() <= 0) return false;
			if (currElementIndex == theCollection.size()-1) return false;
			return true;
		}

		@Override
		public Object getNext() {                //Returns the next element
			// TODO Auto-generated method stub
			currElementIndex++;
			return (theCollection.elementAt(currElementIndex));
		}
		
	}
	
}
