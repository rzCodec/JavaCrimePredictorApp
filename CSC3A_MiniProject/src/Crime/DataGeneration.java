package Crime;
import java.util.*;
import CustomDataStructures.CustomList;

public class DataGeneration extends CrimeData {
	private Random random = null;
	private int iRandomCities;
	private int[] iRandomArySuburbs = null;
	private CustomList<String> aryListCity;
	private CustomList<String> aryListSuburb;
	public static CustomList<String> aryListCrimeType;
	
	public DataGeneration() {
		random = new Random();
		initialiseData();
	}

	private void initialiseData(){
	    aryListCity = new CustomList<String>();
	    aryListCity.addLast("Johannesburg");
	    aryListCity.addLast("Sandton");
	    aryListCity.addLast("Benoni");
	    aryListCity.addLast("Alberton");
	    aryListCity.addLast("Midrand");
	    aryListCity.addLast("Pretoria");
	    
	    aryListSuburb = new CustomList<String>();
	    aryListSuburb.addLast("Rosebank");
	    aryListSuburb.addLast("Parktown");
	    aryListSuburb.addLast("Auckland Park");
	    aryListSuburb.addLast("Houghton");
	    aryListSuburb.addLast("Linden");
	    aryListSuburb.addLast("Edenvale");
	    aryListSuburb.addLast("Glenvista");
	    aryListSuburb.addLast("Midrand");
	    aryListSuburb.addLast("Fairvale");
	    aryListSuburb.addLast("HoneyDew");
	    aryListSuburb.addLast("Brooklyn");
	    aryListSuburb.addLast("Observatory");
	    aryListSuburb.addLast("Parkwood");
	    aryListSuburb.addLast("Maryvale");
	    aryListSuburb.addLast("Corlett Gardens");
	    aryListSuburb.addLast("Arcadia");
	    
	    aryListCrimeType = new CustomList<String>();
	    aryListCrimeType.addLast("Homocide");
	    aryListCrimeType.addLast("Snatch and Grab");
	    aryListCrimeType.addLast("Robbery");
	    aryListCrimeType.addLast("Kidnapping");
	    aryListCrimeType.addLast("Attempted Break-In");
	    aryListCrimeType.addLast("Domestic Violence");
	    aryListCrimeType.addLast("Fraud");
	    aryListCrimeType.addLast("Extortion");
	    aryListCrimeType.addLast("Double Homocide");
	    aryListCrimeType.addLast("Blackmail");
	    aryListCrimeType.addLast("Attempted Murder");
	    aryListCrimeType.addLast("Assault");
	    aryListCrimeType.addLast("Hi-jack");
	    		
	}
	
	public String generateRandomRegion(String sRegionType){
		if(sRegionType.equals("City")){
			int iCitySelector = random.nextInt((aryListCity.size())) + 0;		
			return aryListCity.get(iCitySelector);
		}
		else
		{
			//System.out.println("ArrayList Size: " + aryListSuburb.size());
			if(aryListSuburb.size() > 0){
				int iSuburbSelector = random.nextInt((aryListSuburb.size())) + 0;		
				return aryListSuburb.removeElementAt(iSuburbSelector);
			}
			else return "Hydepark";
		}
	}
	
	public String generateRandomCrime(){
		int iCrimeSelector = random.nextInt((aryListCrimeType.size())) + 0;		
		return aryListCrimeType.get(iCrimeSelector);
	}
	
	public void generateRandomDate(){	
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(2017, random.nextInt(12) + 0, random.nextInt(30) + 1, 22, 30, 15);
		Date date = cal.getTime();
		String tempDate = date.toString();
		StringTokenizer ST = new StringTokenizer(tempDate);
		super.setsDay(ST.nextToken());
		super.setsMonth(ST.nextToken());
		super.setsDate(ST.nextToken());
		super.setiNumCrimesCommitted(random.nextInt(CrimeData.CRIMES_COMMITED) + 3);
		super.setiStartTime(random.nextInt(24) + 0);
	}

	public void generateBaseData(){
		Random random = new Random();
		this.iRandomCities = random.nextInt(4) + 1;
		iRandomArySuburbs = new int[this.iRandomCities];
		
		for(int i = 0; i < iRandomCities; i++)
		{
			iRandomArySuburbs[i] = random.nextInt(3) + 1;
		}
	}

	public int getiRandomCities() {
		return iRandomCities;
	}

	public void setiRandomCities(int iRandomCities) {
		this.iRandomCities = iRandomCities;
	}

	public int[] getiRandomArySuburbs() {
		return iRandomArySuburbs;
	}

	public void setiRandomArySuburbs(int[] iRandomArySuburbs) {
		this.iRandomArySuburbs = iRandomArySuburbs;
	}	
}
