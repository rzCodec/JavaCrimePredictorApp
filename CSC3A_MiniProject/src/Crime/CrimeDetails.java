package Crime;

public class CrimeDetails {
	private String sCrimeType;
	private int iStartTime;
	private String sDay;
	private String sMonth;
	private String sDate;
	
	public CrimeDetails(String sCrimeType, int iStartTime, String sDay, String sMonth, String sDate) {
		this.sCrimeType = sCrimeType;
		this.iStartTime = iStartTime;
		this.sDay = sDay;
		this.sMonth = sMonth;
		this.sDate = sDate;
	}
	
	@Override
	public String toString() {
		String sTimeType = "";
		int iTempTime = 0;
		
		if(iStartTime >= 12) {
			iTempTime = iStartTime - 12;
			sTimeType = "pm";
		}
		else sTimeType = "am";
		return "\n CrimeDetails \n The Crime Type :" + sCrimeType + "\n StartTime :" + iTempTime + sTimeType + "\n Date :" + sDate + "\n Day :" + sDay + "\n Month :"
				+ sMonth + "\n" ;
	}
	
	public String getsCrimeType() {
		return sCrimeType;
	}

	public void setsCrimeType(String sCrimeType) {
		this.sCrimeType = sCrimeType;
	}

	public int getiStartTime() {
		return iStartTime;
	}

	public void setiStartTime(int iStartTime) {
		this.iStartTime = iStartTime;
	}

	public String getsDay() {
		return sDay;
	}

	public void setsDay(String sDay) {
		this.sDay = sDay;
	}

	public String getsMonth() {
		return sMonth;
	}

	public void setsMonth(String sMonth) {
		this.sMonth = sMonth;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
}
