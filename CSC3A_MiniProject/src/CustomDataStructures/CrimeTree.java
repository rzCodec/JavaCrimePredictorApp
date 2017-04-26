package CustomDataStructures;
import javax.swing.JTextArea;

import Crime.*;

public class CrimeTree 
{
	private int iNodes = 0;
	private int iNumCities = 0;
	private int[] iArySuburbs = null;
	private Node rootProvinceNode; 
	
	String[] tempAry = {"NoCrime#1", "NoCrime#2"};
	CustomList<String> tempList = new CustomList<String>();
		
	public CrimeTree(String sRegionName, int iNumCities, int[] iArySuburbs)
	{		
		Stats statsObj = new Stats();
		this.iNumCities = iNumCities;
		this.iArySuburbs = iArySuburbs;
	
		tempList.addArrayElements(tempAry); //A temporary list to hold no crimes for the moment instead of holding null values
		
		CrimeData provinceDataObj = new CrimeData(sRegionName, tempList, "", "", "", 0, 0, statsObj);
		rootProvinceNode = new Node(null, null, provinceDataObj);
		CustomList<Node> cityNodeList = new CustomList<Node>();
		iNodes += 1;
		
		//Create the cities for the current province
		for(int i = 0; i < iNumCities; i++)
		{
			CrimeData cityDataObj = new CrimeData("City #" + i, tempList, "", "", "", i, i, statsObj); //Set each city to its parent node, which is the province
			//cityDataObj.setRegionStats(new Stats(-1, -1, -1, "", -1));
			Node cityNode = new Node(rootProvinceNode, null, cityDataObj);
			cityNodeList.addLast(cityNode);
			iNodes += 1;
		}
		
		for(int i = 0; i < iNumCities; i++) //For each city...
		{
			CustomList<Node> suburbNodeList = new CustomList<Node>();	
			for(int k = 0; k < iArySuburbs[i]; k++)
			{
				CrimeData suburbDataObj = new CrimeData("Suburb # " + k, tempList, "", "", "", k, k, statsObj);
				suburbDataObj.setIsSuburb(true); //Make each object aware of its type of region
				suburbDataObj.setRegionStats(new Stats(-1, -1, -1, "", -1));
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
		rootProvinceNode.getCrimeDataObj().setRegionStats(new Stats());
		rootProvinceNode.setChildrenNodeList(cityNodeList);
		rootProvinceNode.getCrimeDataObj().setIsProvince(true);
	}
	
	//Populates the tree with randomized crime data
	public void populateTree() 
	{
		CustomList<Integer> intList = new CustomList<Integer>();
		CustomList<Node> cityNodeList = rootProvinceNode.getChildrenNodeList();
		DataGeneration dgObj = new DataGeneration();
			
		for(Node cityNode : cityNodeList)
		{			
			dgObj.generateRandomDate();	
			CrimeData cdObj = new CrimeData(dgObj.generateRandomRegion("City"), tempList, dgObj.getsDay(), dgObj.getsMonth(), dgObj.getsDate(), dgObj.getiStartTime(), dgObj.getiNumCrimesCommitted(), new Stats());
			cdObj.setIsCity(true);
			cityNode.setCrimeDataObj(cdObj);
			
			CustomList<Node> suburbNodeList = cityNode.getChildrenNodeList();
			for(Node suburbNode : suburbNodeList)
			{
				dgObj.generateRandomDate();	
				CustomList<String> tempList = new CustomList<String>();
				
				for(int i = 0; i < dgObj.getiNumCrimesCommitted(); i++)
				{			
					tempList.addFirst(dgObj.generateRandomCrime());
				}		
				intList.addLast(dgObj.getiNumCrimesCommitted());
				//System.out.println(dgObj.getiNumCrimesCommitted());
				
				cdObj = new CrimeData(dgObj.generateRandomRegion("Suburb"), tempList, dgObj.getsDay(), dgObj.getsMonth(), dgObj.getsDate(), dgObj.getiStartTime(), dgObj.getiNumCrimesCommitted(), new Stats());				
				cdObj.makeCrimeList();
				suburbNode.setCrimeDataObj(cdObj);
			} //end for each suburb loop
		}//end for each city loop
		
		
	}
	
	/**
	 * 
	 */
	public void crunchCrimeStats() //A function to calculate the different statistics
	{
		int iTotalNumCrimes = 0;
		int iGrandTotal = 0;
		CustomList<Node> cityNodeList = rootProvinceNode.getChildrenNodeList();
		//CustomList<Integer> numCrimeList = new CustomList<Integer>();
		int iMax = 0; 
		int iMin = 0;
		int iCrimes = 0;
		
		for(Node cityNode : cityNodeList)
		{
			CustomList<Node> suburbList = cityNode.getChildrenNodeList();
			iMax = suburbList.get(0).getCrimeDataObj().getiNumCrimesCommitted();
			for(Node suburbNode : suburbList)
			{
				iTotalNumCrimes += suburbNode.getCrimeDataObj().getsCrimeTypeList().size(); //Total the number of crimes
				cityNode.getCrimeDataObj().getRegionStats().setTotalCrimes(iTotalNumCrimes); //Set the total crimes per city
				//numCrimeList.addLast(suburbNode.getCrimeDataObj().getiNumCrimesCommitted()); //Add the number of crimes to
				suburbNode.getCrimeDataObj().getRegionStats().setTotalCrimes(suburbNode.getCrimeDataObj().getiNumCrimesCommitted());
				String sCrimeRegion = suburbNode.getCrimeDataObj().getsRegion(); //Get the region name with the highest crime
				
				//Finding the Max Crimes within a region
				iCrimes = suburbNode.getCrimeDataObj().getiNumCrimesCommitted();
				if(iMax <= iCrimes)
				{
					iMax = iCrimes;
					suburbNode.getNodeParent().getCrimeDataObj().setiMaxCrimes(iMax);
					suburbNode.getNodeParent().getCrimeDataObj().setsMaxCrimeRegion(sCrimeRegion);
				}
			}
			//CrimeData cityData = cityNode.getCrimeDataObj();
			//cityData.setiMaxCrimes(findMaxCrimes(numCrimeList));
			//cityNode.getCrimeDataObj().getRegionStats().setTotalCrimes(iTotalNumCrimes);
			
			iTotalNumCrimes = 0;
			iGrandTotal += cityNode.getCrimeDataObj().getiTotalCrimes();
		}
			
		rootProvinceNode.getCrimeDataObj().getRegionStats().setTotalCrimes(iGrandTotal);
		int iProvinceAvgCrimes = rootProvinceNode.getCrimeDataObj().getRegionStats().calcAvgCrime(iGrandTotal, rootProvinceNode.getChildrenNodeList().size());
		rootProvinceNode.getCrimeDataObj().getRegionStats().setdAverageCrimes(iProvinceAvgCrimes);
		rootProvinceNode.setChildrenNodeList(cityNodeList);
	}
	
	/**
	 * Search for a particular suburb, Complexity Time is O(n)
	 * @param sSuburb - The suburb's name
	 * @return CrimeData - The node to be returned, a toString method displays the details
	 */
    public CrimeData searchTreeForSuburb(String sSuburb)
    {
    	for(Node cityNode : rootProvinceNode.getChildrenNodeList())
    	{
    		for(Node suburbNode : cityNode.getChildrenNodeList())
        	{
        		if(suburbNode.getCrimeDataObj().getsRegion().equals(sSuburb))
        		{
        			return suburbNode.getCrimeDataObj();
        		}
        	}	
    	}	
    	return null;
    }
	
	public void displayTreeContents(JTextArea txtStatsArea)
	{
		//System.out.println("Province : " + rootProvinceNode.getCrimeDataObj().toString());
		txtStatsArea.append("Province : " + rootProvinceNode.getCrimeDataObj().toString());
		
		for(Node cityNode : rootProvinceNode.getChildrenNodeList())
		{
			if(cityNode != null)
			{
				//System.out.println("City : " + cityNode.getCrimeDataObj().toString() + "Total Number of Suburbs :" + cityNode.getChildrenNodeList().size());
				//System.out.println(" Average number of crimes per suburb is : " + calcAvgCrimes(cityNode.getCrimeDataObj().getiTotalCrimes(), cityNode.getChildrenNodeList().size()) + "\n");
				txtStatsArea.append("City : " + cityNode.getCrimeDataObj().toString() + "Total Number of Suburbs :" + cityNode.getChildrenNodeList().size());
				txtStatsArea.append(" Average number of crimes per suburb is : " + calcAvgCrimes(cityNode.getCrimeDataObj().getiTotalCrimes(), cityNode.getChildrenNodeList().size()) + "\n");
				
				for(Node suburbNode : cityNode.getChildrenNodeList())
				{
					//System.out.println("Suburb : " + suburbNode.getCrimeDataObj().toString());
					txtStatsArea.append("Suburb : " + suburbNode.getCrimeDataObj().toString());
				}
				//System.out.println("\n");
				txtStatsArea.append("\n");
			}
		
		}
		System.out.println("========================================================================");
	}
	
	private int calcAvgCrimes(int iTotCrimes, int iSuburbs)
	{
		return iTotCrimes / iSuburbs;
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
