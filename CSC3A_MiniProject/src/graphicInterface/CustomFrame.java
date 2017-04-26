package graphicInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import CustomDataStructures.CrimeTree;


public class CustomFrame extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3644110547115789312L;

	private CustomPanel cusPanelObj;
	
	private JTextArea txtStatsArea;
	private JButton btnGenerateStats;
	private CrimeTree crimeTreeObj;
	
	public CustomFrame()
	{
		setTitle("Crime Inspector");
		cusPanelObj = new CustomPanel();
		cusPanelObj.setBorder(new TitledBorder(new EtchedBorder(), "Crime Statistics"));
		
		createCrimeTree();
		//Text area to display the results
		txtStatsArea = new JTextArea("View Statistics");
		txtStatsArea.setPreferredSize(new Dimension(400, 500));
		
		//Button generates and show stats
		btnGenerateStats = new JButton("Generate Stats");
		btnGenerateStats.setPreferredSize(new Dimension(200, 400));
		btnGenerateStats.addActionListener(this);
		
		cusPanelObj.add(txtStatsArea);
		cusPanelObj.add(btnGenerateStats);
		add(cusPanelObj);
	}

    private void createCrimeTree()
    {
    	Random rand = new Random();
    	int iNumCities = rand.nextInt(4) + 1;
    	int[] iArySuburbs = new int[iNumCities];
    	
    	for(int i = 0; i < iNumCities; i++)
		{
			iArySuburbs[i] = rand.nextInt(6) + 2;
		}
    	
    	crimeTreeObj = new CrimeTree("Gauteng", iNumCities, iArySuburbs);
    }
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == btnGenerateStats)
		{
			crimeTreeObj.populateTree(); //Populate the empty tree with random data
			crimeTreeObj.crunchCrimeStats(); //Calculate and set the statistics
			crimeTreeObj.displayTreeContents(txtStatsArea); //Display the results
		}
	}

}
