package Crime;
public class CrimeFrequency {
	private String crime;
	private int iFreq;

	public CrimeFrequency(String sName, int iFreq) {
		this.crime = sName;
		this.iFreq = iFreq;
	}
	
	public String getsCrimeType() {
		return crime;
	}

	public void setsCrimeType(String crime) {
		this.crime = crime;
	}

	public int getiFreq() {
		return iFreq;
	}
	
	public void setiFreq(int iFreq) {
		this.iFreq = iFreq;
	}
	
	@Override
	public String toString() {
		return "Crime Type :" + crime + " :: Frequency :" + iFreq + "\n";
	}
}
