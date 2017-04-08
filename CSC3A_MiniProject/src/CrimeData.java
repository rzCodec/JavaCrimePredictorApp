
public class CrimeData 
{
	protected String sRegion;
	protected String sCrimeType;
	protected String sSecurityLevel;
    protected String sDate;
	protected String sDay;
	protected String sMonth;
	protected int iStartTime;
	protected int iReponseTime;
	protected int iTotalCrimes;
	
	public CrimeData()
	{
	
	}
	
	public CrimeData(String sRegion, String sCrimeType, String sSecurityLevel, String sDate, String sDay, String sMonth, int iStartTime, int iResponseTime) 
	{	
		this.sRegion = sRegion;
		this.sCrimeType = sCrimeType;
		this.sSecurityLevel = sSecurityLevel;
		this.sDate = sDate;
		this.sDay = sDay;
		this.sMonth = sMonth;
		this.iStartTime = iStartTime;
		this.iReponseTime = iResponseTime;
	}

	public void generateData()
	{
		
	}

	//Setters and Getters

	@Override
	public String toString() 
	{
		return "CrimeData [sRegion: " + sRegion + ", sCrimeType: " + sCrimeType + ", sSecurityLevel: " + sSecurityLevel
				+ ", sDate: " + sDate + ", sDay: " + sDay + ", sMonth: " + sMonth + ", iStartTime: " + iStartTime
				+ ", iReponseTime: " + iReponseTime + "]";
	}

	public String getsDate() 
	{
		return sDate;
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

	public int getiResponseTime() 
	{
		return iReponseTime;
	}

	public void setiResponseTime(int iReponseTime) {
		this.iReponseTime = iReponseTime;
	}

	public String getsCrimeType() 
	{
		return sCrimeType;
	}

	public void setsCrimeType(String sCrimeType) 
	{
		this.sCrimeType = sCrimeType;
	}

	public String getsSecurityLevel() 
	{
		return sSecurityLevel;
	}

	public void setsSecurityLevel(String sSecurityLevel) 
	{
		this.sSecurityLevel = sSecurityLevel;
	}

	
}
