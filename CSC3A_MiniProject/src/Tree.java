import CustomDataStructures.CustomList;



public class Tree 
{
	private int iNodes = 0;
	private int iNumCities = 0;
	private int[] iArySuburbs = null;
	private Node rootProvinceNode; 
		
	public Tree(String sRegionName, int iNumCities, int[] iArySuburbs)
	{		
		this.iNumCities = iNumCities;
		this.iArySuburbs = iArySuburbs;
		
		CrimeData provinceDataObj = new CrimeData(sRegionName, "", "", "", "", "", 0, 0);
		rootProvinceNode = new Node(null, null, provinceDataObj);
		CustomList<Node> cityNodeList = new CustomList<Node>();
		iNodes += 1;
		
		//Create the cities for the current province
		for(int i = 0; i < iNumCities; i++)
		{
			CrimeData cityDataObj = new CrimeData("City #" + i,  "", "", "", "", "", i, i);
			
			//Set each city to its parent node, which is the province
			Node cityNode = new Node(rootProvinceNode, null, cityDataObj);
			cityNodeList.addLast(cityNode);
			iNodes += 1;
		}
		
		for(int i = 0; i < iNumCities; i++) //For each city...
		{
			CustomList<Node> suburbNodeList = new CustomList<Node>();	
			for(int k = 0; k < iArySuburbs[i]; k++)
			{
				CrimeData suburbDataObj = new CrimeData("Suburb # " + k, "", "", "", "", "", k, k);
				Node cityNodeParent = cityNodeList.get(i);
				
				//Set each suburb to the current city
				//An "upward" reference because the children have reference to its parent
				Node suburbNode = new Node(cityNodeParent, null, suburbDataObj);
				suburbNodeList.addLast(suburbNode);
				iNodes += 1;
			}
			
			//Now that all of the suburbs have been iterated through and set to its city,
			//Lets set the city's children to the suburb list
			//This creates a "downward" reference; each parent now knows its children
			//Now I have created a double ended reference; from the children to its parent and its parent to its children.
			cityNodeList.get(i).setChildrenNodeList(suburbNodeList);
						
			//Now that the Tree has been created, I populate the entire Tree with randomly generated data
		}
		
		//I apply the same concept here
		rootProvinceNode.setChildrenNodeList(cityNodeList);	
	}
	
	//Populates the tree with randomized crime data
	public void populateTree() 
	{
		CustomList<Node> cityNodeList = rootProvinceNode.getChildrenNodeList();
		DataGeneration dgObj = new DataGeneration();
				
		for(Node cityNode : cityNodeList)
		{			
			CrimeData cdObj = new CrimeData(dgObj.generateRandomCity(), "", "", "", "", "", 1, 2);
			cityNode.setCrimeDataObj(cdObj);
			
			CustomList<Node> suburbNodeList = cityNode.getChildrenNodeList();
			for(Node suburbNode : suburbNodeList)
			{
				cdObj = new CrimeData("", "", "", "", "", "", 3, 4);
				suburbNode.setCrimeDataObj(cdObj);
			}
		}
	}
	
	
	
	public void displayTreeContents()
	{
		System.out.println("Province : " + rootProvinceNode.getCrimeDataObj().toString());
		
		for(Node cityNode : rootProvinceNode.getChildrenNodeList())
		{
			if(cityNode != null)
			{
				System.out.println("City : " + cityNode.getCrimeDataObj().toString());
				for(Node suburbNode : cityNode.getChildrenNodeList())
				{
					System.out.println("Suburb : " + suburbNode.getCrimeDataObj().toString());
				}
				System.out.println("\n");
			}
		
		}
		System.out.println("========================================================================");
	}
	
    //Getters and Setters
	public int getiNodes() 
	{
		return iNodes;
	}

	public void setiNodes(int iNodes)
	{
		this.iNodes = iNodes;
	}

	public int getiNumCities() 
	{
		return iNumCities;
	}

	public void setiNumCities(int iNumCities) 
	{
		this.iNumCities = iNumCities;
	}

	public int[] getiArySuburbs() 
	{
		return iArySuburbs;
	}

	public void setiArySuburbs(int[] iArySuburbs) 
	{
		this.iArySuburbs = iArySuburbs;
	}

	public Node getRootProvinceNode() 
	{
		return rootProvinceNode;
	}

	public void setRootProvinceNode(Node rootProvinceNode) 
	{
		this.rootProvinceNode = rootProvinceNode;
	}

} //end of class
