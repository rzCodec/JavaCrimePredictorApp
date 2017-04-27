package graphicInterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Crime.CrimeData;
import CustomDataStructures.CrimeTree;


public class CustomFrame extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3644110547115789312L;

	private JPanel panelObj;	
	private JTextArea txtAreaDisplayStats;
	private JScrollPane scrollPane;
	private JButton btnGenerateStats;
	private CrimeTree crimeTreeObj;
	
	public CustomFrame()
	{
		setTitle("Crime Patterns in 2017");
		createCrimeTree();
		JPanel BasePanel = new JPanel();
	    JPanel buttonPanel = new JPanel();
	    JPanel statsPanel = new JPanel();
	    
	    BasePanel.setLayout(new FlowLayout());
	    btnGenerateStats = new JButton("Generate and View Statistics");
	    btnGenerateStats.setPreferredSize(new Dimension(200, 100));
	    btnGenerateStats.addActionListener(this);
	    //buttonPanel.add(btnGenerateStats);
	    
	    txtAreaDisplayStats = new JTextArea(30,75);
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
