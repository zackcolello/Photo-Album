package cs213.photoAlbum.GUIViewStates;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import cs213.photoAlbum.control.Controller;

public class SearchDate6State extends PhotoAlbumState {

	static SearchDate6State instance = null;

	void enter() {

		// Grab current JFrame and remove all the things
		Frame[] frames = Frame.getFrames();
		SearchDate6Store.pa = (PhotoAlbum) frames[0];

		SearchDate6Store.pa.revalidate();
		SearchDate6Store.pa.repaint();

		SearchDate6Store.pa.getContentPane().removeAll();
		SearchDate6Store.pa.getContentPane().repaint();
		SearchDate6Store.pa.getContentPane().revalidate();

		// Create constraints, add to main panel
		SearchDate6Store.gbl = new GridBagLayout();
		SearchDate6Store.gbc = new GridBagConstraints();
		SearchDate6Store.MainPanel = new JPanel();
		SearchDate6Store.MainPanel.setLayout(SearchDate6Store.gbl);

		// Create top Menu Bar
		JPanel MenuBar = new MenuBarPanel();
		MenuBar = (JPanel) ((MenuBarPanel) MenuBar)
				.CreateMenuBarPanel("Search By Date");
		SearchDate6Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		SearchDate6Store.gbc.gridx = 0;
		SearchDate6Store.gbc.gridy = 0;
		SearchDate6Store.gbc.weightx = 1;
		SearchDate6Store.gbc.gridwidth = 2;
		SearchDate6Store.MainPanel.add(MenuBar, SearchDate6Store.gbc);

		// Add LowerPanel that will contain everything else in the view
		SearchDate6Store.LowerPanel = new JPanel();
		SearchDate6Store.LowerPanel.setPreferredSize(new Dimension(300, 545));
		SearchDate6Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		SearchDate6Store.gbc.gridx = 0;
		SearchDate6Store.gbc.gridy = 1;
		SearchDate6Store.gbc.weightx = 1;
		SearchDate6Store.gbc.gridwidth = 2;
		SearchDate6Store.MainPanel.add(SearchDate6Store.LowerPanel,
				SearchDate6Store.gbc);

		// Add DatesPanel to LowerPanel
		// Set up gbc and gbl for the lower panel
		SearchDate6Store.lpgbc = new GridBagConstraints();
		SearchDate6Store.lpgbl = new GridBagLayout();
		SearchDate6Store.LowerPanel.setLayout(SearchDate6Store.lpgbl);
		// SearchDate6Store.lpgbc.fill = GridBagConstraints.HORIZONTAL;
		SearchDate6Store.lpgbc.gridx = 0;
		SearchDate6Store.lpgbc.gridy = 0;
		SearchDate6Store.lpgbc.weightx = 1;
		SearchDate6Store.lpgbc.gridwidth = 2;

		// Create Dates Panel, add to Lower Panel
		SearchDate6Store.DatesPanel = new JPanel();
		SearchDate6Store.DatesPanel.setLayout(SearchDate6Store.dgbl);
		SearchDate6Store.DatesPanel.setBorder(new EtchedBorder());
		SearchDate6Store.DatesPanel.setPreferredSize(new Dimension(650, 70));
		SearchDate6Store.DatesPanel.setVisible(true);

		// Fill in Dates Panel with labels
		SearchDate6Store.dgbc = new GridBagConstraints();
		SearchDate6Store.dgbl = new GridBagLayout();
		SearchDate6Store.dgbc.gridx = 0;
		SearchDate6Store.dgbc.gridy = 0;
		SearchDate6Store.startDate = new JLabel("Start Date:");
		SearchDate6Store.startDate.setVisible(true);
		SearchDate6Store.DatesPanel.add(SearchDate6Store.startDate,
				SearchDate6Store.dgbc);

		// Create JComboBoxes
		String[] months = { "Month:", "January", "February", "March", "April",
				"May", "June", "July", "August", "September", "October",
				"November" };
		SearchDate6Store.startMonth = new JComboBox<String>(months);
		SearchDate6Store.dgbc.gridx = 1;
		SearchDate6Store.dgbc.gridy = 0;
		SearchDate6Store.startMonth.setVisible(true);
		SearchDate6Store.DatesPanel.add(SearchDate6Store.startMonth,
				SearchDate6Store.dgbc);
		String[] days = { "Day:", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30", "31" };
		SearchDate6Store.startDay = new JComboBox<String>(days);
		SearchDate6Store.dgbc.gridx = 2;
		SearchDate6Store.dgbc.gridy = 0;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.startDay,
				SearchDate6Store.dgbc);
		String[] years = { "Year:", "2015", "2014", "2013", "2012", "2011",
				"2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003",
				"2002", "2001", "2000" };
		SearchDate6Store.startYear = new JComboBox<String>(years);
		SearchDate6Store.dgbc.gridx = 3;
		SearchDate6Store.dgbc.gridy = 0;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.startYear,
				SearchDate6Store.dgbc);

		SearchDate6Store.endDate = new JLabel("End Date:");
		SearchDate6Store.dgbc.gridx = 0;
		SearchDate6Store.dgbc.gridy = 1;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.endDate,
				SearchDate6Store.dgbc);
		// Create JComboBoxes for end Date
		SearchDate6Store.endMonth = new JComboBox<String>(months);
		SearchDate6Store.dgbc.gridx = 1;
		SearchDate6Store.dgbc.gridy = 1;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.endMonth,
				SearchDate6Store.dgbc);
		SearchDate6Store.endDay = new JComboBox<String>(days);
		SearchDate6Store.dgbc.gridx = 2;
		SearchDate6Store.dgbc.gridy = 1;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.endDay,
				SearchDate6Store.dgbc);
		SearchDate6Store.endYear = new JComboBox<String>(years);
		SearchDate6Store.dgbc.gridx = 3;
		SearchDate6Store.dgbc.gridy = 1;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.endYear,
				SearchDate6Store.dgbc);

		SearchDate6Store.LowerPanel.add(SearchDate6Store.DatesPanel,
				SearchDate6Store.lpgbc);

		// Create buttons panel, add to Lower Panel
		SearchDate6Store.ButtonsPanel = new JPanel();
		// SearchDate6Store.ButtonsPanel.setBorder(new EtchedBorder());
		SearchDate6Store.ButtonsPanel.setPreferredSize(new Dimension(300, 100));
		SearchDate6Store.lpgbc.gridx = 0;
		SearchDate6Store.lpgbc.gridy = 1;
		SearchDate6Store.LowerPanel.add(SearchDate6Store.ButtonsPanel,
				SearchDate6Store.lpgbc);

		// Set up gbc and gbl for the buttons panel
		SearchDate6Store.bgbc = new GridBagConstraints();
		SearchDate6Store.bgbl = new GridBagLayout();
		SearchDate6Store.ButtonsPanel.setLayout(SearchDate6Store.bgbl);
		SearchDate6Store.bgbc.gridx = 0;
		SearchDate6Store.bgbc.gridy = 0;
		SearchDate6Store.bgbc.weightx = 1;

		// Add buttons to buttonsPanel
		SearchDate6Store.submitButton = new JButton("Submit");
		SearchDate6Store.submitButton.setPreferredSize(new Dimension(120, 50));
		SearchDate6Store.ButtonsPanel.add(SearchDate6Store.submitButton,
				SearchDate6Store.bgbc);
		SearchDate6Store.cancelButton = new JButton("Cancel");
		SearchDate6Store.cancelButton.setVisible(false);
		SearchDate6Store.cancelButton.setPreferredSize(new Dimension(120, 50));
		SearchDate6Store.bgbc.gridx = 1;
		SearchDate6Store.bgbc.gridy = 0;

		SearchDate6Store.ButtonsPanel.add(SearchDate6Store.cancelButton,
				SearchDate6Store.bgbc);

		// Add the main panel to the Photo Album object
		SearchDate6Store.pa.add(SearchDate6Store.MainPanel,
				SearchDate6Store.gbc);

		AddListeners();

		SearchDate6Store.pa.revalidate();
		SearchDate6Store.pa.repaint();

	}

	public void AddListeners() {

		SearchDate6Store.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
				}

			
		});

		SearchDate6Store.submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String month1=null;
				switch ((String)SearchDate6Store.startMonth.getSelectedItem()){
					case("January"): month1="01";
					case("Feburary"): month1="02";
					case("March"): month1="03";
					case("April"): month1="04";
					case("May"): month1="05";
					case("June"): month1="06";
					case("July"): month1="07";
					case("August"): month1="08";
					case("September"): month1="09";
					case("October"): month1="10";
					case("November"): month1="11";
					case("Deccember"): month1="12";
				}
				
				String month2=null;
				switch ((String)SearchDate6Store.startMonth.getSelectedItem()){
					case("January"): month2="01";
					case("Feburary"): month2="02";
					case("March"): month2="03";
					case("April"): month2="04";
					case("May"): month2="05";
					case("June"): month2="06";
					case("July"): month2="07";
					case("August"): month2="08";
					case("September"): month2="09";
					case("October"): month2="10";
					case("November"): month2="11";
					case("Deccember"): month2="12";
				}
					
				String startdate=month1+"\\"+SearchDate6Store.startDay+"\\"+SearchDate6Store.startYear+"-00:00:00";
				String enddate=month2+"\\"+SearchDate6Store.endDay+"\\"+SearchDate6Store.endYear+"-00:00:00";
				
				Results8Store.results=PhotoAlbum.controller.getPhotosByDate(Login1State.user, startdate, enddate);
				
	
				
				SearchDate6State.instance=null;
				PhotoAlbumStore.results8State.enter();
				
			}
		});

	}

	public static SearchDate6State getInstance() {
		if (instance == null) {
			instance = new SearchDate6State();
		}
		return instance;
	}

	PhotoAlbumState processEvent() {
		// TODO Auto-generated method stub
		return null;
	}

}