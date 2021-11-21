package Crime;

public class Stats {
	private int iTotalCrimes;
	private int dAverageCrimes;
	private int iMaxCrimes;
	private String sMaxCrimeRegion;
	private int iThreshold;
	private int iAvgTime; 
	
	public Stats(){
		
	}
	
	public Stats(int iTotalCrimes, int dAverageCrimes, int iMaxCrimes, String sMaxCrimeRegion, int iThreshold) {
		this.iTotalCrimes = iTotalCrimes;
		this.dAverageCrimes = dAverageCrimes;
		this.iMaxCrimes = iMaxCrimes;
		this.sMaxCrimeRegion = sMaxCrimeRegion;
		this.iThreshold = iThreshold;
	}

	public int getiTotalCrimes() {
		return iTotalCrimes;
	}

	public void setTotalCrimes(int iTotalCrimes) {
		this.iTotalCrimes = iTotalCrimes;
	}

	public int getAverageCrimes() {
		return dAverageCrimes;
	}

	public void setAverageCrimes(int dAverageCrimes) {
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
	public String toString() {
		return "" + iTotalCrimes + ", \n Average Number of Crimes :" + dAverageCrimes + " \n Max Number of Crimes :"
				+ iMaxCrimes + " \n MaxCrimeRegion :" + sMaxCrimeRegion + " \n Threshold :" + iThreshold;
	}
}
