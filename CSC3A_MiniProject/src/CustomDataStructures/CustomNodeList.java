package CustomDataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomNodeList<T> implements iNode<T>, Iterable<T>, Cloneable {
	private CustomNodeList<T> copylist; 
	private cNode<T> header = null;
	private cNode<T> trailer = null;
	private Integer size;
	private Integer j = 0;
	

	public CustomNodeList() {
		trailer = new cNode<T>(null, null, null);
		header = new cNode<T>(trailer, null, null); //Set the header's next link to point to the trailer
		trailer.setPrevLink(header); //Set the trailer's prev link to point to the header.
		size = 0;
	}
	
	/**
	 * Adds data to the list after a specified index in the array list
	 * @see iNode#add(java.lang.Integer, java.lang.Comparable)
	 */
	@Override
	public void addData(Integer index, T DataType) {
		if(index == 0) {
			addFirst(DataType); //Zero index means add the data to the front of the list
		}
		else {
			cNode<T> getNode = getFrontNode(); //Get the front node of the list
			for(int i = 0; i < (index - 1); i++) {
				getNode = getNode.getNextLink(); //Traverse through the list
			}
			
			//After the node is obtained from the index, add the data.
			addAfterNode(getNode, DataType);
		}
		
	}
	
	/**
	 * Adds data to the list after a specified index in the array list. This time it replaces any data that was there before.
	 * @see iNode#add(java.lang.Integer, java.lang.Comparable)
	 */
	@Override
	public void addAndReplaceData(Integer index, T DataType) {
		if(index == 0) {
			addFirst(DataType); //Zero index means add the data to the front of the list
			removeAfterNode(header.getNextLink());
		}
		else {
			cNode<T> getNode = getFrontNode();
			for(int i = 0; i < (index - 1); i++) {
				getNode = getNode.getNextLink(); //Traverse through the list
			}
			
			//After the node is obtained from the index, add the data.
			addAfterNode(getNode, DataType);
			removeAfterNode(getNode.getNextLink());
		}
	}
	
	/**
	 * Add an object after a specified node in the array list
	 * @see iNode#add(java.lang.Integer, java.lang.Comparable)
	 */
	@Override
	public cNode<T> addAfterNode(cNode<T> givenNode, T DataType) {
		cNode<T> newNode = new cNode<T>(null, null, DataType);
		cNode<T> nextNode = givenNode.getNextLink();
			
		givenNode.setNextLink(newNode);
		newNode.setPrevLink(givenNode);
		newNode.setNextLink(nextNode);
		nextNode.setPrevLink(newNode);
		
		size += 1;
		return newNode;
	}
	
	/**
	 * Add an object before a specified node in the array list
	 * @see iNode#add(java.lang.Integer, java.lang.Comparable)
	 */
	@Override
	public cNode<T> addBeforeNode(cNode<T> givenNode, T DataType) {
		cNode<T> newNode = new cNode<T>(null, null, DataType);
		cNode<T> prevNode = givenNode.getPrevLink();
		prevNode.setNextLink(newNode);
		newNode.setNextLink(givenNode);
		newNode.setPrevLink(prevNode);
		givenNode.setPrevLink(newNode);
		size += 1;
		return newNode;
	}
	
	/**
	 * Removes a node after a specified node.
	 * @see iNode#returnNode(java.lang.Integer)
	 */
	@Override
	public cNode<T> removeAfterNode(cNode<T> givenNode) {
		cNode<T> removeNode = givenNode.getNextLink();
		cNode<T> nextNode = removeNode.getNextLink();
		givenNode.setNextLink(nextNode);
		nextNode.setPrevLink(givenNode);
		size--;
		return removeNode;
	}
	
	/**
	 * Add an object to the front of the array list
	 * @see iNode#addFirst(T)
	 */
	@Override
	public cNode<T> addFirst(T ItemType) {
		return addAfterNode(header, ItemType);
	}
	
	/**
	 * Add an object to the end of the array list
	 * @see iNode#addFirst(T)
	 */
	@Override
	public cNode<T> addLast(T ItemType) {
		return addBeforeNode(trailer, ItemType);
	}
	
	/**
	 * Search for a given node
	 * @see iNode#addFirst(T)
	 */
	@Override
	public cNode<T> searchNode(cNode<T> givenNode) {
		cNode<T> currentNode = header.getNextLink();
		while (currentNode != trailer) {
			if (currentNode.getElement().equals(givenNode)) {
				return currentNode;
			}
			currentNode = currentNode.getNextLink();
		}
		return null;
	}
	
	/** Removes the last node in the array list and returns the data that was removed.
	 * 
	 * @see iNode#removeLast()
	 */
	@Override
	public cNode<T> removeLast() {	
		return removeBeforeNode(trailer);
	}
	
	/**
	 * Checks if the list is empty
	 * @see iNode#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		if(size == 0) return true;
		else return false;
	}
	
	/**
	 * Returns the front element in the array list AFTER the header
	 * @see iNode#isEmpty()
	 */
	@Override
	public cNode<T> getFrontNode() {
		if(header.getNextLink() == trailer) {
			System.out.println("List is empty!");
			System.exit(-1);
		}
		
		cNode<T> frontNode = header.getNextLink();
		return frontNode;
	}
	
	/**
	 * Returns the data type at the specified location / index in the array list
	 * @see iNode#returnNode(java.lang.Integer)
	 */
	@Override
	public cNode<T> returnNode(Integer iIndex) {
		if((iIndex == 0) && (iIndex <= size)) {
			return getFrontNode(); //Simply return the first element in the list
		}
		else if(iIndex <= size)
		{
			cNode<T> newNode = getFrontNode();
			for(int i = 0; i < iIndex; i++) {		
				newNode = newNode.getNextLink();
			}	
		
			return newNode;
		}
		else {
			System.out.println("The index specified is greater than the list size!");
			return null;			
		}
	}
	
	/**
	 * Returns a node before a given one
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public cNode<T> removeBeforeNode(cNode<T> givenNode) {
		cNode<T> removeNode = givenNode.getPrevLink();
		cNode<T> prevNode = removeNode.getPrevLink();
		givenNode.setPrevLink(prevNode);
		prevNode.setNextLink(givenNode);
		
		removeNode.setPrevLink(null);
		removeNode.setNextLink(null);
		return removeNode;
	}
	
	//===================== Iterable Interface Methods =============================
	
	/**
	 * Returns an iterator
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		Iterator<T> iterator = new Iterator<T>() {
			private Integer iIndex = 0;
		
			/**
			 * Check if there is another element in the list
			 * @see java.util.Iterator#hasNext()
			 */
			@Override
			public boolean hasNext() {
				if(!(isEmpty())) //If the list contains something...
				{
					if(iIndex < size) return true; //If the current is more than the size of the list, it does not have a next element
					else return false;
				}
				else return false; //Otherwise an empty list has no next element.
			}

			/**
			 * Get the next element in the array list / list
			 * Once the element has been obtained, it can be worked with such as displaying, adding, etc
			 * @see java.util.Iterator#next()
			 */
			@Override
			public T next() {
				if(iIndex == 0) {
					//System.out.println("Index # " + iIndex);
					iIndex += 1;
					return getFrontNode().getElement();
				} 
				else {
					//System.out.println("Index # " + iIndex);
					T currentElement = null;
					cNode<T> newNode = getFrontNode();
					
					for(int i = 0; i < iIndex; i++) {		
						newNode = newNode.getNextLink();
						currentElement = newNode.getElement();
					}				
					iIndex += 1;
					return currentElement;
				}	
			}
		}; 
		return iterator;
	}
	
	/**
	 * @return returns the header
	 */
	public cNode<T> getHeader() {
		return header;
	}

	/**
	 * @param header, set the value of the header
	 */
	public void setHeader(cNode<T> header) {
		this.header = header;
	}

	/**
	 * @return returns the trailer
	 */
	public cNode<T> getTrailer() {
		return trailer;
	}

	/**
	 * @param trailer, set the value of the trailer
	 */
	public void setTrailer(cNode<T> trailer) {
		this.trailer = trailer;
	}

	/**
	 * @return returns the size of the array list which is the number of elements inside it
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @param size, sets the size of the array list
	 */
	public void setSize(int size) {
		this.size = size;
	}
} 
