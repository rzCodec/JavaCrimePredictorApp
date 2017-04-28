package CustomDataStructures;

/*
 * Created by Ronald Lai, 201433999
 */

public interface iNode<T> 
{
	public void addData(Integer index, T anObject);
	public void addAndReplaceData(Integer index, T DataType);
	public cNode<T> addAfterNode(cNode<T> givenNode, T anItem);
	public cNode<T> addBeforeNode(cNode<T> givenNode, T anItem);
	public cNode<T> addFirst(T item);
	public cNode<T> addLast(T item);
	public cNode<T> searchNode(cNode<T> givenNode);
	public cNode<T> removeLast();
	public cNode<T> getFrontNode();
	public cNode<T> removeAfterNode(cNode<T> givenNode);
	public cNode<T> removeBeforeNode(cNode<T> givenNode);
	public cNode<T> returnNode(Integer iIndex);
	public boolean isEmpty();
	
	
}
