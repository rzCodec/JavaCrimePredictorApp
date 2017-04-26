package Crime;
public class Stats 
{
	private int iTotalCrimes;
	private int dAverageCrimes;
	private int iMaxCrimes;
	private String sMaxCrimeRegion;
	private int iThreshold;
	
	public Stats()
	{
		
	}
	
	
	
	public Stats(int iTotalCrimes, int dAverageCrimes, int iMaxCrimes, String sMaxCrimeRegion, int iThreshold) 
	{
		this.iTotalCrimes = iTotalCrimes;
		this.dAverageCrimes = dAverageCrimes;
		this.iMaxCrimes = iMaxCrimes;
		this.sMaxCrimeRegion = sMaxCrimeRegion;
		this.iThreshold = iThreshold;
	}



	public int calcAvgCrime(int iTotalCrimes, int iCrimesPerRegion)
	{
		return iTotalCrimes / iCrimesPerRegion;
	}
	
	//Getters and Setters

	public int getiTotalCrimes() {
		return iTotalCrimes;
	}

	public void setTotalCrimes(int iTotalCrimes) {
		this.iTotalCrimes = iTotalCrimes;
	}

	public int getdAverageCrimes() {
		return dAverageCrimes;
	}

	public void setdAverageCrimes(int dAverageCrimes) {
		this.dAverageCrimes = dAverageCrimes;
	}

	public int getiMaxCrimes() {
		return iMaxCrimes;
	}

	public void setiMaxCrimes(int iMaxCrimes) {
		this.iMaxCrimes = iMaxCrimes;
	}

	public String getsMaxCrimeRegion() {
		return sMaxCrimeRegion;
	}

	public void setsMaxCrimeRegion(String sMaxCrimeRegion) {
		this.sMaxCrimeRegion = sMaxCrimeRegion;
	}

	public int getiThreshold() {
		return iThreshold;
	}

	public void setiThreshold(int iThreshold) {
		this.iThreshold = iThreshold;
	}

	@Override
	public String toString() 
	{
		return "Statistics \n TotalCrimes :" + iTotalCrimes + ", \n AverageCrimes :" + dAverageCrimes + ", \n MaxCrimes :"
				+ iMaxCrimes + ", \n MaxCrimeRegion :" + sMaxCrimeRegion + ", Threshold :" + iThreshold;
	}
	
	
	

}
