package cs213.photoAlbum.GUIViewStates;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Anna Genke
 *
 */
public class SearchDate6Store {

	// PhotoAlbum
	static PhotoAlbum pa;
	
	//MainPanel and constraints
	static GridBagLayout gbl;
	static GridBagConstraints gbc;
	static JPanel MainPanel;
	
	//LowerPanel with Dates and Buttons
	static JPanel LowerPanel;
	static JPanel DatesPanel;
	static JPanel ButtonsPanel;
	static JButton submitButton;
	static JButton cancelButton;
	
	//Add fields that will go in the Dates Panel
	static JLabel startDate;
	static JLabel endDate;
	static JComboBox<String> startMonth;
	static JComboBox<String> endMonth;
	static JComboBox<String> startDay;
	static JComboBox<String> endDay;
	static JComboBox<String> startYear;
	static JComboBox<String> endYear;
	static JLabel dash;
	static JComboBox<String> startHour;
	static JComboBox<String> startMinute;
	static JComboBox<String> startSecond;
	static JComboBox<String> endHour;
	static JComboBox<String> endMinute;
	static JComboBox<String> endSecond;
	static JLabel errLabel;
	
	//lowerPanel grid bag
	static GridBagLayout lpgbl;
	static GridBagConstraints lpgbc;
	
	//Dates grid bag 
	static GridBagLayout dgbl;
	static GridBagConstraints dgbc;
	
	//Buttons grid bag
	static GridBagLayout bgbl;
	static GridBagConstraints bgbc
	;
	
}