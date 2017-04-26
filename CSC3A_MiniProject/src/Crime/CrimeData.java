package Crime;
import java.util.HashMap;
import java.util.StringTokenizer;

import CustomDataStructures.CustomList;

public class CrimeData 
{
	private CustomList<CrimeDetails> crimeDetailsList;
	protected String sRegion;
	protected CustomList<String> sCrimeTypeList;  
	protected String sDay;
	protected String sMonth;
	protected String sDate;
	
	protected int iStartTime;
	protected int iNumCrimesCommitted;
	
	private Boolean isProvince = false;
	private Boolean isCity = false;
	private Boolean isSuburb = false;
	
	private Stats RegionStats;
	
	private int iTotalCrimes;
	private double dAverageCrimes;
	private int iMaxCrimes;
	private String sMaxCrimeRegion;
	private int iLowestCrimes;
	private String sMinCrimeRegion;
	
	public Boolean getIsProvince() 
	{
		return isProvince;
	}



	public void setIsProvince(Boolean isProvince) 
	{
		this.isProvince = isProvince;
	}



	public Boolean getIsCity() 
	{
		return isCity;
	}



	public void setIsCity(Boolean isCity) 
	{
		this.isCity = isCity;
	}



	public Boolean getIsSuburb() {
		return isSuburb;
	}



	public void setIsSuburb(Boolean isSuburb) {
		this.isSuburb = isSuburb;
	}



	public CrimeData()
	{
	
	}
	
	

	public CrimeData(String sRegion, CustomList<String> sCrimeTypeList, String sDay, String sMonth, String sDate,
			int iStartTime, int iNumCrimesCommitted, Stats statsObj)
	{
		crimeDetailsList = new CustomList<CrimeDetails>();
		this.sRegion = sRegion;
	    this.sCrimeTypeList = sCrimeTypeList;
		this.sDay = sDay;
		this.sMonth = sMonth;
		this.sDate = sDate;
		this.iStartTime = iStartTime;
		this.iNumCrimesCommitted = iNumCrimesCommitted;
		this.RegionStats = statsObj;
	}

	//Setters and Getters

	public void makeCrimeList()
	{
		
		if(this.sCrimeTypeList != null)
		{
			for(String s : this.sCrimeTypeList)
			{
				DataGeneration dg = new DataGeneration();
				dg.generateRandomDate();
				CrimeDetails cdObj = new CrimeDetails(s, dg.getiStartTime(), dg.getsDay(), dg.getsMonth(), dg.getsDate());
				crimeDetailsList.addFirst(cdObj);
			}
		} 
		else
			try 
		    {
				throw new Exception("CustomList is currently null. \n The contents will still be added later.");
			} 
		    catch (Exception e) 
		    {
				e.printStackTrace();
			}
	}
	

	public String getsDate() 
	{
		return sDate;
	}
	
	public String displayDetails()
	{
		return "Total number of suburbs is : ";
	}

	@Override
	public String toString() 
	{
		String sTimeType = "";
		String sListOfCrimes = "";

		
		if(iStartTime >= 12)
		{
			iStartTime = iStartTime - 12;
			sTimeType = "pm";
		}
		else sTimeType = "am";

		if(sCrimeTypeList != null)
		{
			for(CrimeDetails cdObj : crimeDetailsList) 
			{
				sListOfCrimes += cdObj.toString() + "\n";
			}
		}
		
		String sType = "";
		String sMaxCrime = "";
		String sMinCrime = "";
		
		int iTotCrimes = 0;
		if(isProvince == true) 
		{
			sType = "Province";
			sMaxCrime = "\n Total Number Crimes is :" + iTotalCrimes;
		}
		else if(isCity == true)
		{
			sType = "City ";
			iTotCrimes = iTotalCrimes;
			sMaxCrime = "\n Highest Number of Crimes is :" + iMaxCrimes + " in " + sMaxCrimeRegion;
		}
		else
		{
			sType = "Suburb";
			iTotCrimes = crimeDetailsList.size(); 
		}
				
		//return sRegion + "\n The total number of crimes in this " + sType + ": " + iTotCrimes + sMaxCrime + "\n " + sListOfCrimes;
		return sRegion + "\n The total number of crimes in this " + sType + ": " + RegionStats.getiTotalCrimes() + sMaxCrime + "\n " + sListOfCrimes;
	}



	public void setsDate(String sDate) 
	{
		this.sDate = sDate;
	}

	public String getsDay() 
	{
		return sDay;
	}
	
	public void setsDay(String sDay)
	{
		this.sDay = sDay;
	}

	public String getsMonth() 
	{
		return sMonth;
	}

	public void setsMonth(String sMonth) 
	{
		this.sMonth = sMonth;
	}

	public String getsRegion()
	{
		return sRegion;
	}

	public void setsRegion(String sRegion) 
	{
		this.sRegion = sRegion;
	}

	public int getiStartTime() 
	{
		return iStartTime;
	}

	public void setiStartTime(int iStartTime) 
	{
		this.iStartTime = iStartTime;
	}

	public int getiNumCrimesCommitted() 
	{
		return iNumCrimesCommitted;
	}

	public void setiNumCrimesCommitted(int iNumCrimesCommitted) 
	{
		this.iNumCrimesCommitted = iNumCrimesCommitted;
	}

	public int getiTotalCrimes() 
	{
		return iTotalCrimes;
	}

	public void setiTotalCrimes(int iTotalCrimes) 
	{
		this.iTotalCrimes = iTotalCrimes;
	}

	public double getdAverageCrimes() 
	{
		return dAverageCrimes;
	}

	public void setdAverageCrimes(double dAverageCrimes) 
	{
		this.dAverageCrimes = dAverageCrimes;
	}

	public CustomList<String> getsCrimeTypeList() 
	{
		return sCrimeTypeList;
	}

	public void setsCrimeTypeList(CustomList<String> sCrimeTypeList) 
	{
		this.sCrimeTypeList = sCrimeTypeList;
	}



	public int getiMaxCrimes() {
		return iMaxCrimes;
	}



	public void setiMaxCrimes(int iMaxCrimes) {
		this.iMaxCrimes = iMaxCrimes;
	}



	public int getiLowestCrimes() {
		return iLowestCrimes;
	}



	public void setiLowestCrimes(int iLowestCrimes) {
		this.iLowestCrimes = iLowestCrimes;
	}



	public String getsMaxCrimeRegion() {
		return sMaxCrimeRegion;
	}



	public void setsMaxCrimeRegion(String sMaxCrimeRegion) {
		this.sMaxCrimeRegion = sMaxCrimeRegion;
	}

	public void setMaxCrimeData(int iMaxCrimes, String sMaxRegion)
	{
		this.iMaxCrimes = iMaxCrimes;
		this.sMaxCrimeRegion = sMaxRegion;
	}



	public String getsMinCrimeRegion() {
		return sMinCrimeRegion;
	}



	public void setsMinCrimeRegion(String sMinCrimeRegion) {
		this.sMinCrimeRegion = sMinCrimeRegion;
	}



	/**
	 * @return the regionStats
	 */
	public Stats getRegionStats() {
		return RegionStats;
	}



	/**
	 * @param regionStats the regionStats to set
	 */
	public void setRegionStats(Stats regionStats) {
		RegionStats = regionStats;
	}

	
}
