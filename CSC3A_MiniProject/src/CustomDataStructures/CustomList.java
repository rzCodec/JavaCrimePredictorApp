package CustomDataStructures;

import java.util.Iterator;

/*
 * Created by Ronald Lai, 201433999
 */

public class CustomList<T> implements iArrayList<T>, Iterable<T>
{
	//My CustomList has functionality from both ArrayLists and Queues
	//This class uses the Adapter Design Pattern to hide and abstract the functionality of the CustomNodeList
	
	private CustomNodeList<T> nodeList;
	
	public CustomList()
	{
		nodeList = new CustomNodeList<T>();
	}
	
	/**
     * Add an array list of data to the list in one go
     * @see iArrayList#removeLast()
     */
	@Override
	public void addListElements(CustomList<T> cusList)
	{
		for(T elem : cusList)
		{
			nodeList.addFirst(elem);
		}
	}
	
	/**
     * Add an array of data to the list in one go
     * @see iArrayList#removeLast()
     */
	@Override
	public void addArrayElements(T[] aryData)
	{
		for(T elem : aryData)
		{
			nodeList.addFirst(elem);
		}
	}
	
	/**
     * Add data to the start of the list
     * @see iArrayList#removeLast()
     */
	@Override
	public void addFirst(T Data)
	{
		nodeList.addFirst(Data);
	}
	
	/**
     * Add data to the end of the list
     * @see iArrayList#removeLast()
     */
	@Override
	public void addLast(T Data)
	{
		nodeList.addLast(Data);
	}
	
	
	/**
     * Add data to specified location within the list
     * @see iArrayList#removeLast()
     */
	@Override
	public void insertData(int iIndex, T Data)
	{
		nodeList.addData(iIndex, Data);
	}
	
	
	
	/**
     * Inserts new data at the specified index but replaces the existing data at that location
     * 
     */
	@Override
	public void insertReplace(int index, T Data)
	{
		nodeList.addAndReplaceData(index, Data);
	}
	
	/**
     * Remove the first element in the list
     * @see iArrayList#removeLast()
     */
	@Override
	public T removeFirst()
	{
		return nodeList.removeAfterNode(nodeList.getHeader()).getElement();
	}
	
    /**
     * Remove the last element in the list
     * @see iArrayList#removeLast()
     */
	@Override
	public T removeLast()
	{
		return nodeList.removeLast().getElement();
	}
	
	/**
     * Return and remove the element from a specified index in the list
     * @see iArrayList#removeLast()
     */
	@Override
	public T removeElementAt(int index)
	{
		cNode<T> getNode = nodeList.returnNode(index);
		return nodeList.removeAfterNode(getNode.getPrevLink()).getElement();
	}
	
   /**
     * Return the data in the list
     * @see iArrayList#removeLast()
     */
	@Override
	public T get(int index)
	{
		return nodeList.returnNode(index).getElement();
	}
	
   /**
	 * Returns an iterator
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator()
	{
		Iterator<T> iterator = new Iterator<T>(){
			
			/**
			 * Check if there is another element in the list
			 * @see java.util.Iterator#hasNext()
			 */
			@Override
			public boolean hasNext() 
			{
				if(!(nodeList.isEmpty())) //If the list contains something...
				{
					if(iIndex < nodeList.getSize()) return true; //If the current is more than the size of the list, it does not have a next element
					else return false;
				}
				else return false;	
			}
			
			private int iIndex = 0;
			/**
			 * Get the next element in the array list / list
			 * Once the element has been obtained, it can be worked with such as displaying, adding, etc
			 * @see java.util.Iterator#next()
			 */
			@Override
			public T next() 
			{
				if(iIndex == 0) 
				{
					iIndex += 1; //Move the index to the next element
					return nodeList.getFrontNode().getElement();
				} 
				else
				{
					T currentElement = null;
					cNode<T> newNode = nodeList.getFrontNode();
					
					for(int i = 0; i < iIndex; i++)
					{		
						newNode = newNode.getNextLink();
						currentElement = newNode.getElement();
					}				
					iIndex += 1; //Move the index to the next element
					return currentElement;
				}	
			}
			
		};		

	    return iterator;	
	}
	
	/**
	 * Return the size of the list
	 * Once the element has been obtained, it can be worked with such as displaying, adding, etc
	 * @see java.util.Iterator#next()
	 */
	@Override
	public int size()
	{
		return nodeList.getSize();
	}

} //end of class
