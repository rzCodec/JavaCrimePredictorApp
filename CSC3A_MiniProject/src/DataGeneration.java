import java.util.*;
import CustomDataStructures.CustomList;

public class DataGeneration extends CrimeData
{
	private int iRandomCities;
	private int[] iRandomArySuburbs = null;
	//====================================
	private String[] aryCity = {"Johannesburg", "Sandton", "Randburg", "Alberton"};
	private CustomList<String> aryListCity;
	
	public DataGeneration() 
	{
		aryListCity = new CustomList<String>();
	    aryListCity.addLast("Johannesburg");
	    aryListCity.addLast("Sandton");
	    aryListCity.addLast("Randburg");
	    aryListCity.addLast("Alberton");
	}

	
	public String generateRandomCity()
	{
		Random random = new Random();
		System.out.println(aryListCity.size());
		int iSelector = random.nextInt((aryListCity.size())) + 0;
		
		return aryListCity.removeElementAt(iSelector);
	}
	
	
	public void generateBaseData()
	{
		Random random = new Random();
		this.iRandomCities = random.nextInt(4) + 1;
		iRandomArySuburbs = new int[this.iRandomCities];
		
		for(int i = 0; i < iRandomCities; i++)
		{
			iRandomArySuburbs[i] = random.nextInt(3) + 1;
		}
	}

	//Getters and Setters
	public int getiRandomCities() 
	{
		return iRandomCities;
	}

	public void setiRandomCities(int iRandomCities) 
	{
		this.iRandomCities = iRandomCities;
	}

	public int[] getiRandomArySuburbs() 
	{
		return iRandomArySuburbs;
	}

	public void setiRandomArySuburbs(int[] iRandomArySuburbs) 
	{
		this.iRandomArySuburbs = iRandomArySuburbs;
	}

	
	
	
	
	
}
