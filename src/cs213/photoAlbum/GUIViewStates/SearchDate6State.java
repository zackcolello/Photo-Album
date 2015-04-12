package cs213.photoAlbum.GUIViewStates;

import java.awt.Color;
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
import javax.swing.SwingConstants;
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
		SearchDate6Store.DatesPanel.setPreferredSize(new Dimension(650, 120));
		SearchDate6Store.DatesPanel.setVisible(true);

		// Fill in Dates Panel with labels
		SearchDate6Store.dgbc = new GridBagConstraints();
		SearchDate6Store.dgbl = new GridBagLayout();

		JLabel infoLabel = new JLabel(
				"          Month             Day          Year               Hour            Minute         Second");
		SearchDate6Store.dgbc.gridx = 1;
		SearchDate6Store.dgbc.gridy = 0;
		SearchDate6Store.dgbc.gridwidth = 7;
		infoLabel.setVisible(true);
		SearchDate6Store.DatesPanel.add(infoLabel, SearchDate6Store.dgbc);

		// Fill in top row
		SearchDate6Store.dgbc.gridx = 0;
		SearchDate6Store.dgbc.gridy = 1;
		SearchDate6Store.dgbc.gridwidth = 1;
		SearchDate6Store.startDate = new JLabel("Start Date:");
		SearchDate6Store.startDate.setVisible(true);
		SearchDate6Store.DatesPanel.add(SearchDate6Store.startDate,
				SearchDate6Store.dgbc);

		// Create JComboBoxes
		String[] months = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November" };
		SearchDate6Store.startMonth = new JComboBox<String>(months);
		SearchDate6Store.dgbc.gridx = 1;
		SearchDate6Store.dgbc.gridy = 1;
		SearchDate6Store.startMonth.setVisible(true);
		SearchDate6Store.DatesPanel.add(SearchDate6Store.startMonth,
				SearchDate6Store.dgbc);
		String[] days = { "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30", "31" };
		SearchDate6Store.startDay = new JComboBox<String>(days);
		SearchDate6Store.dgbc.gridx = 2;
		SearchDate6Store.dgbc.gridy = 1;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.startDay,
				SearchDate6Store.dgbc);
		String[] years = { "2015", "2014", "2013", "2012", "2011", "2010",
				"2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002",
				"2001", "2000" };
		SearchDate6Store.startYear = new JComboBox<String>(years);
		SearchDate6Store.dgbc.gridx = 3;
		SearchDate6Store.dgbc.gridy = 1;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.startYear,
				SearchDate6Store.dgbc);
		SearchDate6Store.dash = new JLabel("-");
		SearchDate6Store.dgbc.gridx = 4;
		SearchDate6Store.dgbc.gridy = 1;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.dash,
				SearchDate6Store.dgbc);
		String[] hours = { "00", "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23" };
		SearchDate6Store.startHour = new JComboBox<String>(hours);
		SearchDate6Store.dgbc.gridx = 5;
		SearchDate6Store.dgbc.gridy = 1;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.startHour,
				SearchDate6Store.dgbc);
		String[] minute = { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
				"19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
				"29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
				"39", "40", "44", "42", "43", "44", "45", "46", "47", "48",
				"49", "50", "51", "52", "53", "54", "55", "56", "57", "58",
				"59", "60" };
		SearchDate6Store.startMinute = new JComboBox<String>(minute);
		SearchDate6Store.dgbc.gridx = 6;
		SearchDate6Store.dgbc.gridy = 1;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.startMinute,
				SearchDate6Store.dgbc);
		String[] second = { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
				"19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
				"29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
				"39", "40", "44", "42", "43", "44", "45", "46", "47", "48",
				"49", "50", "51", "52", "53", "54", "55", "56", "57", "58",
				"59", "60" };
		SearchDate6Store.startSecond = new JComboBox<String>(second);
		SearchDate6Store.dgbc.gridx = 7;
		SearchDate6Store.dgbc.gridy = 1;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.startSecond,
				SearchDate6Store.dgbc);

		SearchDate6Store.endDate = new JLabel("End Date:");
		SearchDate6Store.dgbc.gridx = 0;
		SearchDate6Store.dgbc.gridy = 2;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.endDate,
				SearchDate6Store.dgbc);

		// Create JComboBoxes for end Date
		SearchDate6Store.endMonth = new JComboBox<String>(months);
		SearchDate6Store.dgbc.gridx = 1;
		SearchDate6Store.dgbc.gridy = 2;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.endMonth,
				SearchDate6Store.dgbc);
		SearchDate6Store.endDay = new JComboBox<String>(days);
		SearchDate6Store.dgbc.gridx = 2;
		SearchDate6Store.dgbc.gridy = 2;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.endDay,
				SearchDate6Store.dgbc);
		SearchDate6Store.endYear = new JComboBox<String>(years);
		SearchDate6Store.dgbc.gridx = 3;
		SearchDate6Store.dgbc.gridy = 2;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.endYear,
				SearchDate6Store.dgbc);

		SearchDate6Store.dash = new JLabel("-");
		SearchDate6Store.dgbc.gridx = 4;
		SearchDate6Store.dgbc.gridy = 2;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.dash,
				SearchDate6Store.dgbc);

		SearchDate6Store.endHour = new JComboBox<String>(hours);
		SearchDate6Store.dgbc.gridx = 5;
		SearchDate6Store.dgbc.gridy = 2;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.endHour,
				SearchDate6Store.dgbc);

		SearchDate6Store.endMinute = new JComboBox<String>(minute);
		SearchDate6Store.dgbc.gridx = 6;
		SearchDate6Store.dgbc.gridy = 2;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.endMinute,
				SearchDate6Store.dgbc);

		SearchDate6Store.endSecond = new JComboBox<String>(second);
		SearchDate6Store.dgbc.gridx = 7;
		SearchDate6Store.dgbc.gridy = 2;
		SearchDate6Store.DatesPanel.add(SearchDate6Store.endSecond,
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
				String month = (String) SearchDate6Store.startMonth
						.getSelectedItem();
				String month1 = null;
				System.out.println(month);
				switch ((String) SearchDate6Store.startMonth.getSelectedItem()) {
				case ("January"):
					month1 = "01";
					break;
				case ("February"):
					month1 = "02";
					break;
				case ("March"):
					month1 = "03";
					break;
				case ("April"):
					month1 = "04";
					break;
				case ("May"):
					month1 = "05";
					break;
				case ("June"):
					month1 = "06";
					break;
				case ("July"):
					month1 = "07";
					break;
				case ("August"):
					month1 = "08";
					break;
				case ("September"):
					month1 = "09";
					break;
				case ("October"):
					month1 = "10";
					break;
				case ("November"):
					month1 = "11";
					break;
				case ("December"):
					month1 = "12";
					break;
				}

				String month2 = null;
				month = (String) SearchDate6Store.endMonth.getSelectedItem();
				System.out.println(month);
				switch (month) {
				case ("January"):
					month2 = "01";
					break;
				case ("February"):
					month2 = "02";
					break;
				case ("March"):
					month2 = "03";
					break;
				case ("April"):
					month2 = "04";
					break;
				case ("May"):
					month2 = "05";
					break;
				case ("June"):
					month2 = "06";
					break;
				case ("July"):
					month2 = "07";
					break;
				case ("August"):
					month2 = "08";
					break;
				case ("September"):
					month2 = "09";
					break;
				case ("October"):
					month2 = "10";
					break;
				case ("November"):
					month2 = "11";
					break;
				case ("December"):
					month2 = "12";
					break;

				}

				String startdate = month1 + "/"
						+ SearchDate6Store.startDay.getSelectedItem() + "/"
						+ SearchDate6Store.startYear.getSelectedItem() + "-"
						+ SearchDate6Store.startHour.getSelectedItem() + ":"
						+ SearchDate6Store.startMinute.getSelectedItem() + ":"
						+ SearchDate6Store.startSecond.getSelectedItem();

				String enddate = month2 + "/"
						+ SearchDate6Store.endDay.getSelectedItem() + "/"
						+ SearchDate6Store.endYear.getSelectedItem() + "-"
						+ SearchDate6Store.endHour.getSelectedItem() + ":"
						+ SearchDate6Store.endMinute.getSelectedItem() + ":"
						+ SearchDate6Store.endSecond.getSelectedItem();

				System.out.println(startdate);
				System.out.println(enddate);

				// Sanity check for month
				if (!validDateCheck()) {
					// put error label
					SearchDate6Store.errLabel = new JLabel("Error: Invalid Date.", SwingConstants.CENTER);
					SearchDate6Store.errLabel.setForeground(Color.red);
					SearchDate6Store.errLabel.setPreferredSize(new Dimension(200, 20));
					SearchDate6Store.dgbc.gridx = 0;
					SearchDate6Store.dgbc.gridy = 3;
					SearchDate6Store.dgbc.gridwidth = 7;
					SearchDate6Store.errLabel.setVisible(true);
					SearchDate6Store.DatesPanel.add(
							SearchDate6Store.errLabel, SearchDate6Store.dgbc);

					SearchDate6Store.pa.revalidate();
					SearchDate6Store.pa.repaint();
					
				} else {

					Results8Store.results = PhotoAlbum.controller
							.getPhotosByDate(Login1State.user, startdate,
									enddate);

					PhotoAlbum.controller.getPhotosByDate(Login1State.user,
							startdate, enddate);

					SearchDate6State.instance = null;
					PhotoAlbumStore.results8State.enter();
				}
			}
		});

	}

	public boolean validDateCheck() {

		switch (SearchDate6Store.startMonth.getSelectedItem().toString()) {
		case ("January"):
			return true;
		case ("February"):
			System.out.println(Integer.parseInt(SearchDate6Store.startDay.getSelectedItem()
					.toString()));
			if (Integer.parseInt(SearchDate6Store.startDay.getSelectedItem()
					.toString()) > 28) {
				return false;
			}
			break;
		case ("March"):
			return true;
		case ("April"):
			if (Integer.parseInt(SearchDate6Store.startDay.getSelectedItem()
					.toString()) > 30) {
				return false;
			}
		case ("May"):

			return true;
		case ("June"):
			if (Integer.parseInt(SearchDate6Store.startDay.getSelectedItem()
					.toString()) > 30) {
				return false;
			}
		case ("July"):
			return true;
		case ("August"):
			return true;
		case ("September"):
			if (Integer.parseInt(SearchDate6Store.startDay.getSelectedItem()
					.toString()) > 30) {
				return false;
			}
		case ("October"):
			return true;
		case ("November"):
			if (Integer.parseInt(SearchDate6Store.startDay.getSelectedItem()
					.toString()) > 30) {
				return false;
			}
		case ("December"):
			return true;
		}
		
		switch (SearchDate6Store.endMonth.getSelectedItem().toString()) {
		case ("January"):
			return true;
		case ("February"):
			System.out.println(Integer.parseInt(SearchDate6Store.endDay.getSelectedItem()
					.toString()));
			if (Integer.parseInt(SearchDate6Store.endDay.getSelectedItem()
					.toString()) > 28) {
				return false;
			}
			break;
		case ("March"):
			return true;
		case ("April"):
			if (Integer.parseInt(SearchDate6Store.endDay.getSelectedItem()
					.toString()) > 30) {
				return false;
			}
		case ("May"):

			return true;
		case ("June"):
			if (Integer.parseInt(SearchDate6Store.endDay.getSelectedItem()
					.toString()) > 30) {
				return false;
			}
		case ("July"):
			return true;
		case ("August"):
			return true;
		case ("September"):
			if (Integer.parseInt(SearchDate6Store.endDay.getSelectedItem()
					.toString()) > 30) {
				return false;
			}
		case ("October"):
			return true;
		case ("November"):
			if (Integer.parseInt(SearchDate6Store.endDay.getSelectedItem()
					.toString()) > 30) {
				return false;
			}
		case ("December"):
			return true;
		}

		return true;

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