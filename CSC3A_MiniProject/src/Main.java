import java.util.*;
import CustomDataStructures.CustomList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	static int iRandomCities;
	static int[] iRandomArySuburbs = null;
	
	public static void main(String[] args)
	{
		/*
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(2017, 0, 15, 22, 30, 15);
		Date date = cal.getTime();
		String tempDate = date.toString();
		System.out.println("Date is " + tempDate + "\n");
		*/
		
		CustomList<Integer> intList = new CustomList<Integer>();
		intList.addFirst(5);
		intList.addFirst(3);
		
		for(int i : intList)
		{
			System.out.println(i);
		}
		
		CustomList<Tree> myTreeList = new CustomList<Tree>();
		
		generateBaseData();
		Tree treeObj = new Tree("Gauteng", iRandomCities, iRandomArySuburbs);
		myTreeList.addLast(treeObj);
		treeObj.displayTreeContents();
		
		/*
		generateBaseData();
		Tree treeObj2 = new Tree("Limpopo", iRandomCities, iRandomArySuburbs);
		myTreeList.addLast(treeObj2);
		
		for(Tree tempTree : myTreeList)
		{
			tempTree.displayTreeContents();
		}
		*/
		
		treeObj.populateTree();
		treeObj.crunchCrimeStats();
		treeObj.displayTreeContents();
		System.out.println("Located Region : " + treeObj.searchTreeForSuburb("Rosebank").toString());
		//treeObj.getRootProvinceNode().getChildrenNodeList().removeLast();
		//treeObj.displayTreeContents();
	}
	
	public static void generateBaseData()
	{
		Random random = new Random();
		//iRandomCities = random.nextInt(4) + 1;
		iRandomCities = 4;
		iRandomArySuburbs = new int[iRandomCities];
		
		for(int i = 0; i < iRandomCities; i++)
		{
			iRandomArySuburbs[i] = random.nextInt(5) + 2;
		}
	}

}
