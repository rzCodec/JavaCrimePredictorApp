package Crime;

public class CrimeFrequency 
{
	private String sCrimeType;
	private int iFreq;

	public CrimeFrequency(String sName, int iFreq) 
	{
		this.sCrimeType = sName;
		this.iFreq = iFreq;
	}
	
	public String getsCrimeType() {
		return sCrimeType;
	}

	public void setsCrimeType(String sCrimeType) {
		this.sCrimeType = sCrimeType;
	}

	public int getiFreq() 
	{
		return iFreq;
	}
	public void setiFreq(int iFreq) 
	{
		this.iFreq = iFreq;
	}
	@Override
	public String toString() 
	{
		return "Crime Type :" + sCrimeType + " :: Frequency :" + iFreq + "\n";
	}
 
	
	

}
