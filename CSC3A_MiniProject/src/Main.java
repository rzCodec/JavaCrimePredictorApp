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
		
		CustomList<String> stringList = new CustomList<String>();
		
		stringList.addLast("Bob"); //index 0
		stringList.addLast("John"); //index 1
		stringList.addLast("Tom"); //index 2
		
		System.out.println(stringList.removeElementAt(2));
		
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
		treeObj.displayTreeContents();
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
			iRandomArySuburbs[i] = random.nextInt(20) + 1;
		}
	}

}
