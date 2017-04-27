import java.util.*;
import CustomDataStructures.CustomList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import graphicInterface.*;
import CustomDataStructures.*;

public class Main {

	static int iRandomCities;
	static int[] iRandomArySuburbs = null;
	
	public static void main(String[] args)
	{
		/*
		CustomList<Integer> intList = new CustomList<Integer>();
		intList.addFirst(5);
		intList.addFirst(3);
		
		for(int i : intList)
		{
			System.out.println(i);
		}
		
		CustomList<CrimeTree> myTreeList = new CustomList<CrimeTree>();
		
		generateBaseData();
		CrimeTree treeObj = new CrimeTree("Gauteng", iRandomCities, iRandomArySuburbs);
		myTreeList.addLast(treeObj);
		treeObj.displayTreeContents();
		
		
		generateBaseData();
		Tree treeObj2 = new Tree("Limpopo", iRandomCities, iRandomArySuburbs);
		myTreeList.addLast(treeObj2);
		
		for(Tree tempTree : myTreeList)
		{
			tempTree.displayTreeContents();
		}
		
		
		treeObj.populateTree();
		treeObj.crunchCrimeStats();
		treeObj.displayTreeContents();
		System.out.println("Located Region : " + treeObj.searchTreeForSuburb("Rosebank").toString());
		
		//treeObj.getRootProvinceNode().getChildrenNodeList().removeLast();
		//treeObj.displayTreeContents();
		 */
		
		CustomFrame Frame = new CustomFrame();
	    Frame.setVisible(true);
	    Frame.pack();
	    Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Frame.setLocationRelativeTo(null);
	    Frame.setResizable(false);
	    Frame.setSize(900, 800);
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
