package CustomDataStructures;
import java.util.Random;

import javax.swing.JTextArea;

import Crime.*;

/*
 * Created By Ronald Lai, 201433999
 */
public class CrimeTree 
{
	private int iNodes = 0;
	private int iSuburbNodes = 0;
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
		
		CrimeData provinceDataObj = new CrimeData(sRegionName, tempList, "", "", "", 0, 0);
		rootProvinceNode = new Node(null, null, provinceDataObj);
		CustomList<Node> cityNodeList = new CustomList<Node>();
		iNodes += 1;
		
		//Create the cities for the current province
		for(int i = 0; i < iNumCities; i++)
		{
			CrimeData cityDataObj = new CrimeData("City #" + i, tempList, "", "", "", i, i); //Set each city to its parent node, which is the province
			Node cityNode = new Node(rootProvinceNode, null, cityDataObj);
			cityNodeList.addLast(cityNode);
			iNodes += 1;
		}
		
		for(int i = 0; i < iNumCities; i++) //For each city...
		{
			CustomList<Node> suburbNodeList = new CustomList<Node>();	
			for(int k = 0; k < iArySuburbs[i]; k++)
			{
				CrimeData suburbDataObj = new CrimeData("Suburb # " + k, tempList, "", "", "", k, k);
				suburbDataObj.setIsSuburb(true); //Make each object aware of its type of region
				Node cityNodeParent = cityNodeList.get(i);
				
				//Set each suburb to the current city
				//An "upward" reference because the children have reference to its parent
				Node suburbNode = new Node(cityNodeParent, null, suburbDataObj);
				suburbNodeList.addLast(suburbNode);
				iSuburbNodes += 1;
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
			CrimeData cdObj = new CrimeData(dgObj.generateRandomRegion("City"), tempList, dgObj.getsDay(), dgObj.getsMonth(), dgObj.getsDate(), dgObj.getiStartTime(), dgObj.getiNumCrimesCommitted());
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
				
				cdObj = new CrimeData(dgObj.generateRandomRegion("Suburb"), tempList, dgObj.getsDay(), dgObj.getsMonth(), dgObj.getsDate(), dgObj.getiStartTime(), dgObj.getiNumCrimesCommitted());				
				cdObj.makeCrimeList();
				suburbNode.setCrimeDataObj(cdObj);
			} //end for each suburb loop
		}//end for each city loop	
	}
	
	//A function to calculate the different statistics
	public void crunchCrimeStats() 
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
				cityNode.getCrimeDataObj().setiTotalCrimes(iTotalNumCrimes); //Set the total crimes per city
				//numCrimeList.addLast(suburbNode.getCrimeDataObj().getiNumCrimesCommitted()); //Add the number of crimes to
				suburbNode.getCrimeDataObj().setiTotalCrimes(suburbNode.getCrimeDataObj().getiNumCrimesCommitted());
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
			//Calcuate the average crimes per city by diving the total crimes across all cities and suburbs by the # of citites
			int iAvgCrimesPerCity = calcAvgCrimes(iTotalNumCrimes, cityNode.getChildrenNodeList().size());
			cityNode.getCrimeDataObj().setiAverageCrimes(iAvgCrimesPerCity);
			iGrandTotal += cityNode.getCrimeDataObj().getiTotalCrimes(); //The total crimes that occurred for Gauteng
			iTotalNumCrimes = 0;
		}
		
		int iMaxCities = rootProvinceNode.getChildrenNodeList().get(0).getCrimeDataObj().getiTotalCrimes();
		int iCityCrime = 0;
		String sMaxCity = "";
		
		for(Node cityNode : rootProvinceNode.getChildrenNodeList())
		{
			iCityCrime = cityNode.getCrimeDataObj().getiTotalCrimes();
			//System.out.println("# Crimes" + iCityCrime);
			if(iMaxCities <= iCityCrime)
			{
				iMaxCities = cityNode.getCrimeDataObj().getiTotalCrimes();
				sMaxCity = cityNode.getCrimeDataObj().getsRegion();
				//System.out.println("City " + sMaxCity);
			}
		}
	
		rootProvinceNode.getCrimeDataObj().setiTotalCrimes(iGrandTotal);
		int iProvinceAvgCrimes = calcAvgCrime(iGrandTotal, rootProvinceNode.getChildrenNodeList().size());
		rootProvinceNode.getCrimeDataObj().setiAverageCrimes(iProvinceAvgCrimes);
		rootProvinceNode.getCrimeDataObj().setiMaxCrimes(iMaxCities);
		rootProvinceNode.getCrimeDataObj().setsMaxCrimeRegion(sMaxCity);
		rootProvinceNode.setChildrenNodeList(cityNodeList);
	}
	
	private int calcAvgCrime(int iTotalCrimes, int iCrimesPerRegion)
	{
		return iTotalCrimes / iCrimesPerRegion;
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
    
    /**
     * Determines if crime usually occurs at night or during the day.
     * @param iNight
     * @param iDay
     * @return
     */
    private boolean atDark(int iNight, int iDay)
    {
    	if(iNight > iDay) return true;
    	else return false;
    }
   
    //Determines if the crimes occur at dark (5pm - 6am) or during the day
    private boolean isNight(int iTime)
    {
    	if((iTime > 17) || (iTime < 6))  
    	{
    		return true;
    	}
    	else return false;
    }
    
    /**
     * This function determines which type of crime has occurred the most in the entire province
     * It also determines if crimes are planned or not based on the standard deviation of the start times of the crimes
     * It also lists a summary of all the crimes
     * @param txtStatsArea
     */
  
    private void detFreq(JTextArea txtStatsArea)
	{		
    	int iNightCount = 0;
    	int iDayCount = 0;
    	
    	//I get all the of crimes that been committed in all the suburbs of each city
    	CustomList<String> crimeList = new CustomList<String>();
    	CrimePattern crimePatternObj = new CrimePattern();
    	
    	for(Node cityNode : rootProvinceNode.getChildrenNodeList())
    	{
    		CustomList<Integer> suburbTimeList = new CustomList<Integer>();
    		for(Node suburbNode : cityNode.getChildrenNodeList())
    		{
    			//System.out.println("Time is : " + suburbNode.getCrimeDataObj().getiStartTime());
    			CustomList<Integer> timeList = 	new CustomList<Integer>();
    			
    			for(CrimeDetails cdObj: suburbNode.getCrimeDataObj().getCrimeDetailsList())
    			{
    				timeList.addFirst(cdObj.getiStartTime());
    				if(isNight(cdObj.getiStartTime())) iNightCount += 1;
    				else iDayCount += 1;
    			}
    			
    			int iAvgTime = crimePatternObj.calcAvgTime(timeList);
    			double stdDeviation = crimePatternObj.calcStandardDeviation(timeList, iAvgTime);	
    			suburbNode.getCrimeDataObj().setIsCrimeOrganised(crimePatternObj.isOrganisedCrime(stdDeviation, iAvgTime));
    			
    			int iCommonTime = (int) (((int) iAvgTime * 0.5) + (stdDeviation));
    			String sCrimePattern = crimePatternObj.findCrimePattern(iCommonTime);
    			suburbNode.getCrimeDataObj().setiPatternTime(iCommonTime);
    			suburbNode.getCrimeDataObj().setsPatternCrime(sCrimePattern);
    			suburbTimeList.addFirst(iCommonTime);
    			
    			//Get each crime type from the suburb
    			for(String sCrime : suburbNode.getCrimeDataObj().getsCrimeTypeList())
    			{
    				crimeList.addFirst(sCrime);
    			} // end for each crime type
    		} // end for each suburb node
    		
    		int iCityAvgTime = crimePatternObj.calcAvgTime(suburbTimeList);
			double cityStdDeviation = crimePatternObj.calcStandardDeviation(suburbTimeList, iCityAvgTime);
			int iCommonTime = (int) (((int) iCityAvgTime * 0.25) + (cityStdDeviation));
			cityNode.getCrimeDataObj().setiPatternTime(iCommonTime);
			cityNode.getCrimeDataObj().setsPatternCrime(crimePatternObj.findCrimePattern(iCommonTime));
			cityNode.getCrimeDataObj().setIsCrimeOrganised(crimePatternObj.isOrganisedCrime(cityStdDeviation, iCityAvgTime));
			
    	} // end for each city node
    	
    	if(atDark(iNightCount, iDayCount) == true)
    	{
    		txtStatsArea.append("Crimes likely occur at night, please be careful, especially when going home! \n\n");
    	}
    	else txtStatsArea.append("Crimes usually occur during the day, be vigilant! \n\n");
    	
		int count = 0;
		
		//A unique list to keep track of all the crimes committed by counting each occurrence
		CustomList<CrimeFrequency> freqCountList = new CustomList<CrimeFrequency>(); 
		for(String sCrimeType : DataGeneration.aryListCrimeType)
		{
			freqCountList.addFirst(new CrimeFrequency(sCrimeType, 0));
		}
		
		//Now I compare each crime that has occurred with the unique crimes stored
		//Now I can determine which crime has occurred the most
		for(CrimeFrequency fc : freqCountList)
		{
			for(String currCrime : crimeList)
			{
				if(currCrime.equals(fc.getsCrimeType()))
				{
					count += 1;
					fc.setiFreq(count);
				}
			}
			count = 0;
		}
		
		int iMaxFreq = freqCountList.get(0).getiFreq();
		int iFreqValue = 0;
		String sCrimeName = "";
		
		for(CrimeFrequency fc : freqCountList)
		{
			iFreqValue = fc.getiFreq();
			if(iMaxFreq <= iFreqValue)
			{
				iMaxFreq = iFreqValue;
				sCrimeName = fc.getsCrimeType();
			}
			//System.out.println(fc.toString());
			txtStatsArea.append(fc.toString());
		}
		
		txtStatsArea.append("Crime Type :" + sCrimeName + " occurred the most, " + iMaxFreq + " times in this province");			
	}
	
	public void displayTreeContents(JTextArea txtStatsArea)
	{	
		txtStatsArea.append("\n *** Province *** " + rootProvinceNode.getCrimeDataObj().getsRegion() + "\n");
		txtStatsArea.append("Total Crimes : " + rootProvinceNode.getCrimeDataObj().getiTotalCrimes() + "\n");
		txtStatsArea.append("Number of Cities :" + rootProvinceNode.getChildrenNodeList().size() + "\n");
		txtStatsArea.append("Average Number of Crimes Per City :" + rootProvinceNode.getCrimeDataObj().getiAverageCrimes() + "\n");
		String sMaxCrimeRegion = rootProvinceNode.getCrimeDataObj().getsMaxCrimeRegion();
		txtStatsArea.append("Highest Number of Crimes :" + rootProvinceNode.getCrimeDataObj().getiMaxCrimes() + " in " + sMaxCrimeRegion + "\n \n");
		txtStatsArea.append("\nCrime Frequency for Gauteng \n\n");
		detFreq(txtStatsArea);	
		
		for(Node cityNode : rootProvinceNode.getChildrenNodeList())
		{
			if(cityNode != null)
			{
				txtStatsArea.append("\n\n === City === " + cityNode.getCrimeDataObj().toString() + "Total Number of Suburbs :" + cityNode.getChildrenNodeList().size() + "\n");
				txtStatsArea.append(" Average number of crimes per suburb is : " + cityNode.getCrimeDataObj().getiAverageCrimes() + "\n \n");
				txtStatsArea.append("\n");
				
				for(Node suburbNode : cityNode.getChildrenNodeList())
				{
					txtStatsArea.append(" Suburb : " + suburbNode.getCrimeDataObj().toString());
				}
				txtStatsArea.append("\n");
			}
		
		}
		txtStatsArea.setCaretPosition(0);
	}
	
	/**
	 * Calculate average crimes for a region
	 * @param iTotCrimes
	 * @param iNum
	 * @return
	 */
	private int calcAvgCrimes(int iTotCrimes, int iNum)
	{
		return iTotCrimes / iNum;
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
