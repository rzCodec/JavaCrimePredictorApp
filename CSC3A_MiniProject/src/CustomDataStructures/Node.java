package CustomDataStructures;
import Crime.*;

public class Node {
	private Node nodeParent;
    private CustomList<Node> childrenNodeList;
    private CrimeData crimeDataObj;
    
    public Node(Node nodeParent, CustomList<Node> childrenNodeList, CrimeData crimeDataObj) {
    	this.nodeParent = nodeParent;
    	this.childrenNodeList = new CustomList<Node>();
    	this.crimeDataObj = crimeDataObj;
    }

	public Node getNodeParent() {
		return nodeParent;
	}

	public void setNodeParent(Node nodeParent) {
		this.nodeParent = nodeParent;
	}

	public CustomList<Node> getChildrenNodeList() {
		return childrenNodeList;
	}

	public void setChildrenNodeList(CustomList<Node> childrenNodeList) {
		this.childrenNodeList = childrenNodeList;
	}

	public CrimeData getCrimeDataObj() {
		return crimeDataObj;
	}

	public void setCrimeDataObj(CrimeData crimeDataObj) {
		this.crimeDataObj = crimeDataObj;
	}
     
}
