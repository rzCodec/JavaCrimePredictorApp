import java.util.*;
import CustomDataStructures.CustomList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;
import graphicInterface.*;
import CustomDataStructures.*;

public class CrimeInformation_Main {
	static int iRandomCities;
	static int[] iRandomArySuburbs = null;
	
	public static void main(String[] args) {	
	    CustomFrame Frame = new CustomFrame();
	    Frame.setVisible(true);
	    Frame.pack();
	    Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Frame.setLocationRelativeTo(null);
	    Frame.setResizable(false);
	    Frame.setSize(1000, 800);
	}
}
