package CustomDataStructures;
/*
 * @Author Mr R. Lai
 */

public class cNode<T> implements iPosition<T>
{
	T element;
	cNode<T> nextLink;
	cNode<T> prevLink;
		
	public cNode()
	{
		
	}
	
	/**
	 * Default Constructor
	 */
	public cNode(cNode<T> nextLink, cNode<T> prevLink, T element)
	{
		this.nextLink = nextLink;
		this.prevLink = prevLink;
		this.element = element;
	}
	
	public cNode<T> getNode(iPosition<T> p)
	{
		cNode<T> currentNode = (cNode<T>) p;
		if(currentNode.getNextLink() == null) throw new IllegalArgumentException("P is no longer in the list");
	    return currentNode;
	}
	
	//Accessor and Mutator methods
	
	public void setNextLink(cNode<T> nextLink)
	{
		this.nextLink = nextLink;
	}
	
	public cNode<T> getNextLink()
	{
		return this.nextLink;
	}
	
	public void setPrevLink(cNode<T> prevLink)
	{
		this.prevLink = prevLink;
	}
	
	public cNode<T> getPrevLink()
	{
		return this.prevLink;
	}
	
	public void setElement(T element)
	{
		this.element = element;
	}
	
	public T getElement()
	{
		return this.element;
	}
	
	public void display()
	{
		if(this.element != null) System.out.println("Element's details : " + this.element.toString() + "\n");
		else System.out.println("No element was found! \n");
	}
}
