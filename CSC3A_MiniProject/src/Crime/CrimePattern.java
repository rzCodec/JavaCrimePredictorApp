package Crime;
import CustomDataStructures.*;

public class CrimePattern {
	private int iTraffic;
	private int iMorning;
	private int iNight;
	private int iSleep;
	private int iDay;
	
	public CrimePattern(){
	}

	public boolean isOrganisedCrime(double stdDeviation, int iAvg){
		double dHalfAvg = iAvg / 2;
		if(stdDeviation <= dHalfAvg) return true;
		else return false;
	}
	
	/**
	 * Calculate the average time to be used in the standard devivation formula
	 * @param timeList
	 * @return
	 */
	public int calcAvgTime(CustomList<Integer> timeList){
		int iTotalTime = 0;
		for(int time : timeList){
			iTotalTime += time;
		}
		
		return iTotalTime / timeList.size();
	}
	
	/**
	 * Calculate the standard deviation to determine if the crimes occurring are organized and planned out or usually random based
	 * @param numList
	 * @return
	 */
	public double calcStandardDeviation(CustomList<Integer> numList, int iAvgTime)
	{
	    double variance = 0;
	    double stdDeviation = 0;
	    
	    for (int value : numList){
	        variance += ((value - iAvgTime) * (value - iAvgTime)) / numList.size();
	    }
	    
	    stdDeviation = Math.sqrt(variance);  
	    return stdDeviation;
	}
	
	/**
	 * Finds the pattern of crimes that will occur at a common time
	 * @param iMaxTime
	 * @return
	 */
	public String findCrimePattern(int iTime){
		if((iTime >= 22) || (iTime <= 6)) {
			iSleep += 1;
			return "\n Domestic Violence, Attempted Murder, Attempted Break-In, Kidnapping and Hi-jack crimes are high when people come home at night or while they are sleeping";
		}
		else if((iTime >= 6) && (iTime <= 9)){
			iMorning += 1;
			return "\n Assault, Hi-jack, Snatch & Grab is likely to occur in the morning hours";
		}
		else if((iTime >= 9) && (iTime <= 16)){
			iDay += 1;
			return "\n Robbery, Fraud, Attempted Break-In, Blackmail and Extortion crimes have a high chance of occurring during the day";
		}
		else if((iTime >= 16) && (iTime <= 22)){
			iTraffic += 1;
			return "\n Hi-jack, Assault, Snatch and Grab, Attempted Robbery and Kidnapping are likely due to rush hour traffic";
		}
		else return "\n Any crime can occur";
	}
	
	/**
	 * Finds the common time that a group of crimes occur
	 * @param timeList - A list of integer times that crimes have started at.
	 * @return
	 */
	public int findCommonTime(CustomList<Integer> timeList){
		int iMax = timeList.get(0);
		for(int time : timeList)
		{
			if(iMax <= time) iMax = time;
		}
		return iMax;
	}

	public int getiTraffic() {
		return iTraffic;
	}

	public void setiTraffic(int iTraffic) {
		this.iTraffic = iTraffic;
	}

	public int getiMorning() {
		return iMorning;
	}

	public void setiMorning(int iMorning) {
		this.iMorning = iMorning;
	}

	public int getiNight() {
		return iNight;
	}

	public void setiNight(int iNight) {
		this.iNight = iNight;
	}

	public int getiSleep() {
		return iSleep;
	}

	public void setiSleep(int iSleep) {
		this.iSleep = iSleep;
	}

	public int getiDay() {
		return iDay;
	}

	public void setiDay(int iDay) {
		this.iDay = iDay;
	}
}
