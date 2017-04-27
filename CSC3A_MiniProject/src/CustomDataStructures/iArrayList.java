package CustomDataStructures;

/*
 *
 * @Author Mr R. Lai
 */


public interface iArrayList<T> 
{
	public void addListElements(CustomList<T> cusList);
	public void addArrayElements(T[] aryData);
	public void addFirst(T Data);
	public void addLast(T Data);
	public void insertData(int iIndex, T Data);
	public void insertReplace(int index, T Data);
	public T removeElementAt(int index);
	public T removeFirst();
	public T removeLast();
	public T get(int iIndex);
	public int size();

}
