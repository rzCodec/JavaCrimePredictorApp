package graphicInterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import Crime.CrimeData;
import CustomDataStructures.CrimeTree;

/*
 * Created by Ronald Lai, 201433999
 */

public class CustomFrame extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 3644110547115789312L;
	
	private JTextArea txtAreaDisplayStats;
	private JScrollPane scrollPane;
	private JButton btnGenerateStats;
	private CrimeTree crimeTreeObj;
	
	public CustomFrame()
	{
		setTitle("Crime Patterns in 2017");
		createCrimeTree();
		JPanel BasePanel = new JPanel();
	    
	    BasePanel.setLayout(new FlowLayout());
	    btnGenerateStats = new JButton("Generate and View Statistics");
	    btnGenerateStats.setPreferredSize(new Dimension(250, 100));
	    btnGenerateStats.addActionListener(this);
	    btnGenerateStats.setFont(btnGenerateStats.getFont().deriveFont(14.0f));
	    
	    txtAreaDisplayStats = new JTextArea(50,75);
	    txtAreaDisplayStats.setLineWrap(true);
	    txtAreaDisplayStats.setWrapStyleWord(true);
	    txtAreaDisplayStats.setFont(txtAreaDisplayStats.getFont().deriveFont(16.0f));
	    txtAreaDisplayStats.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(txtAreaDisplayStats);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 675));
	 
	    //statsPanel.add(scrollPane);
	    BasePanel.add(btnGenerateStats);
	    BasePanel.add(scrollPane);
	    add(BasePanel);
	}

    private void createCrimeTree()
    {
    	Random rand = new Random();
    	int iNumCities = rand.nextInt(6) + 2;
    	int[] iArySuburbs = new int[iNumCities];
    	
    	for(int i = 0; i < iNumCities; i++)
		{
			iArySuburbs[i] = rand.nextInt(CrimeData.CRIMES_COMMITED) + 3; //Generate a random number of crimes per suburb
		}
    	
    	crimeTreeObj = new CrimeTree("Gauteng", iNumCities, iArySuburbs);
    }
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == btnGenerateStats)
		{
			createCrimeTree();
			txtAreaDisplayStats.setText(""); //Refresh the information displayed after each click
			crimeTreeObj.populateTree(); //Populate the empty tree with random data
			crimeTreeObj.crunchCrimeStats(); //Calculate and set the statistics
			crimeTreeObj.displayTreeContents(txtAreaDisplayStats); //Display the 
		}
	}

}
