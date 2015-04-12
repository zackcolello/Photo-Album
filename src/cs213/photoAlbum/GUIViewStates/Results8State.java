package cs213.photoAlbum.GUIViewStates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EtchedBorder;

import cs213.photoAlbum.model.album;
import cs213.photoAlbum.model.photo;

public class Results8State extends PhotoAlbumState {
	
	static Results8State instance = null;

	@Override
	void enter() {
		// Grab current JFrame and remove all the things
		Frame[] frames = Frame.getFrames();

		Results8Store.pa = (PhotoAlbum) frames[0];
		Results8Store.PhotosArray=new ArrayList<JPanel>();
		Results8Store.pa.getContentPane().removeAll();
		Results8Store.pa.getContentPane().repaint();
		Results8Store.pa.getContentPane().revalidate();

		// Create constraints, add to main panel

		Results8Store.gbl = new GridBagLayout();
		Results8Store.gbc = new GridBagConstraints();
		Results8Store.MainPanel = new JPanel();
		Results8Store.MainPanel.setLayout(Results8Store.gbl);

		// Create top Menu Bar
		JPanel MenuBar = new MenuBarPanel();
		MenuBar = (JPanel) ((MenuBarPanel) MenuBar).CreateMenuBarPanel("Photo");
		Results8Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		Results8Store.gbc.gridx = 0;
		Results8Store.gbc.gridy = 0;
		Results8Store.gbc.weightx = 1;
		Results8Store.gbc.gridwidth = 2;
		Results8Store.gbc.anchor = GridBagConstraints.NORTH;
		Results8Store.MainPanel.add(MenuBar, Results8Store.gbc);

		// Create album panel of the screen
		Results8Store.photoPanel = new JPanel();
		Results8Store.innerPanel = new JPanel();

		// set up inner Panel of album panel
		Results8Store.innerPanel.setBorder(new EtchedBorder());
		Results8Store.innerPanel.setLayout(Results8Store.gbl);

		// Create jscrollpane for album panel
		Results8Store.photoScroll = new JScrollPane(Results8Store.innerPanel);
		Results8Store.photoScroll.setVisible(true);
		Results8Store.photoScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Results8Store.photoScroll.setLayout(new ScrollPaneLayout());
		Results8Store.photoScroll.setVisible(true);
		Results8Store.photoPanel.setLayout(new BorderLayout());
		Results8Store.photoPanel.setPreferredSize(new Dimension(50, 540));
		Results8Store.photoPanel.add(Results8Store.photoScroll,
				BorderLayout.CENTER);

		// Add photoPanel to mainPanel
		Results8Store.gbc.gridx = 0;
		Results8Store.gbc.gridy = 1;
		Results8Store.gbc.weightx = .67;
		Results8Store.gbc.gridwidth = 1;
		Results8Store.photoPanel.setBorder(new EtchedBorder());
		Results8Store.photoPanel.setVisible(true);

		Results8Store.MainPanel
				.add(Results8Store.photoPanel, Results8Store.gbc);

		// Create Buttons Panel
		Results8Store.ButtonsPanel = new JPanel();
		Results8Store.ButtonsPanel.setLayout(Results8Store.gbl);
		Results8Store.ButtonsPanel.setPreferredSize(new Dimension(50, 540));
		Results8Store.bgbc = new GridBagConstraints();
		Results8Store.bgbl = new GridBagLayout();
		Results8Store.ButtonsPanel.setLayout(Results8Store.bgbl);

		// Add Photo Button
		Results8Store.NewResultsAlbum = new JButton("Create Album From Results");
		Results8Store.bgbc.gridx = 0;
		Results8Store.bgbc.gridy = 0;
		Results8Store.ButtonsPanel.add(Results8Store.NewResultsAlbum,
				Results8Store.bgbc);

		// Add filler panel below buttons
		Results8Store.fillerPanel = new JPanel();
		Results8Store.bgbc.gridx = 0;
		Results8Store.bgbc.gridy = 1;
		Results8Store.bgbc.weighty = 1;
		Results8Store.bgbc.weightx = 1;
		Results8Store.bgbc.fill = GridBagConstraints.BOTH;
		Results8Store.bgbc.gridwidth = 3;
		Results8Store.ButtonsPanel.add(Results8Store.fillerPanel,
				Results8Store.bgbc);

		// Add buttons panel to main panel
		Results8Store.gbc.gridx = 1;
		Results8Store.gbc.gridy = 1;
		Results8Store.gbc.weightx = .33;
		Results8Store.gbc.weighty = 1;
		Results8Store.photoPanel.setBorder(new EtchedBorder());
		Results8Store.photoPanel.setVisible(true);
		Results8Store.MainPanel.add(Results8Store.ButtonsPanel,
				Results8Store.gbc);

		// Add MainPanel to pa
		Results8Store.pa.add(Results8Store.MainPanel, Results8Store.gbc);

		// Build the list of photo Albums to put in the photoPanel list
		fillphotoPanel();

		// Add all the listeners
		addListeners();

	}

	public void fillphotoPanel() {

		Results8Store.phgbc = new GridBagConstraints();
		Results8Store.phgbl = new GridBagLayout();

		Results8Store.CreateErrLabel = new JLabel(
				"Error: Unable to Create New Albume with this Name");
		
		if (!Results8Store.results.isEmpty()) {

			for (photo p : Results8Store.results) {
				JPanel temp = new JPanel();
				temp.setLayout(Results8Store.phgbl);
				temp.setBackground(Color.WHITE);
				temp.setPreferredSize(new Dimension(170, 200));
				temp.setBorder(new EtchedBorder());
				temp.setVisible(true);

				ImageIcon icon;
				Image image = p.getPhoto().getImage();
				Image newimg = image.getScaledInstance(125, 125,
						java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(newimg);

				Results8Store.phgbc.gridx = 0;
				Results8Store.phgbc.gridy = 0;
				Results8Store.phgbc.gridheight = 1;
				Results8Store.phgbc.gridwidth = 1;
				JLabel photo = new JLabel("", icon, JLabel.CENTER);
				temp.add(photo, Results8Store.phgbc);

				// Add the path of the photo to the temp JPanel
				Results8Store.PhotoName = new JLabel(p.getFileName());
				Results8Store.phgbc.gridx = 0;
				Results8Store.phgbc.gridy = 1;
				temp.add(Results8Store.PhotoName, Results8Store.phgbc);

				// Add the caption of the photo to the temp JPanel
				Results8Store.photoCaption = new JLabel(p.getCaption());
				Results8Store.phgbc.gridx = 0;
				Results8Store.phgbc.gridy = 2;
				temp.add(Results8Store.photoCaption, Results8Store.phgbc);

				// Add the date of photos
				Results8Store.Date = new JLabel(p.getCalendar());
				Results8Store.phgbc.gridx = 0;
				Results8Store.phgbc.gridy = 3;
				temp.add(Results8Store.Date, Results8Store.phgbc);

				// Add temp to the PhotosArray
				Results8Store.PhotosArray.add(temp);

				// Organize albums in albumPanel: 3 albums per row
				Results8Store.scrollConstraints = new GridBagConstraints();
				Results8Store.rowCount = 0;
				Results8Store.columnCount = 0;

				for (JPanel j : Results8Store.PhotosArray) {

					Results8Store.scrollConstraints.gridx = Results8Store.columnCount;
					Results8Store.scrollConstraints.gridy = Results8Store.rowCount;

					if (Results8Store.columnCount == 2) {
						Results8Store.rowCount += 1;
						Results8Store.columnCount = 0;
					} else {
						Results8Store.columnCount++;
					}

					j.setBackground(Color.WHITE);
					j.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {

							for (JPanel f : Results8Store.PhotosArray) {
								f.setBackground(Color.WHITE);
							}

							j.setBackground(Color.LIGHT_GRAY);
							Results8Store.pa.revalidate();
							Results8Store.pa.repaint();

						}

					});

					Results8Store.innerPanel.add(j,
							Results8Store.scrollConstraints);
				}

				// Add albumScroll and albumPanel to mainpanel
				Results8Store.photoPanel.add(Results8Store.photoScroll,
						BorderLayout.CENTER);
				Results8Store.gbc.gridx = 0;
				Results8Store.gbc.gridy = 1;
				Results8Store.gbc.weightx = .8;
				Results8Store.gbc.weighty = 1;
				Results8Store.gbc.gridwidth = 1;
				Results8Store.photoPanel.setBorder(new EtchedBorder());
				Results8Store.photoPanel.setVisible(true);
				Results8Store.MainPanel.add(Results8Store.photoPanel,
						Results8Store.gbc);

				Results8Store.pa.revalidate();
				Results8Store.pa.repaint();

			}

		}else{
			Results8Store.NewResultsAlbum.setEnabled(false);
		}
	}

	public void addListeners() {

		Results8Store.CreateAlbum = new JButton("Create");
		Results8Store.CancelAlbum = new JButton("Cancel");
		Results8Store.AlbumName = new JTextField();

		// Event listener for Add Photo Button
		Results8Store.NewResultsAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Create GBL & GBC for filler panel
				Results8Store.filgbl = new GridBagLayout();
				Results8Store.filgbc = new GridBagConstraints();
				Results8Store.fillerPanel.setLayout(Results8Store.filgbl);
				Results8Store.filgbc.insets = new Insets(10, 10, 10, 10);

				// Hide other buttons
				Results8Store.NewResultsAlbum.setEnabled(false);

				// Create PhotoName field
				Results8Store.NewAlbum = new JLabel("Name of Album:");
				Results8Store.NewAlbum.setVisible(true);
				Results8Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Results8Store.filgbc.gridx = 0;
				Results8Store.filgbc.gridy = 0;
				Results8Store.fillerPanel.add(Results8Store.NewAlbum,
						Results8Store.filgbc);

				// Create PhotoName field
				Results8Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Results8Store.filgbc.gridx = 0;
				Results8Store.filgbc.gridy = 1;
				Results8Store.fillerPanel.add(Results8Store.AlbumName,
						Results8Store.filgbc);

				// Create 'Create' field
				Results8Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Results8Store.filgbc.weightx = .5;
				Results8Store.filgbc.gridwidth = 1;
				Results8Store.filgbc.gridx = 0;
				Results8Store.filgbc.gridy = 2;
				Results8Store.CreateAlbum.setVisible(true);
				Results8Store.CreateAlbum.setEnabled(false);
				Results8Store.fillerPanel.add(Results8Store.CreateAlbum,
						Results8Store.filgbc);

				// Create 'cancel' Button
				Results8Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Results8Store.filgbc.weightx = .5;
				Results8Store.filgbc.gridwidth = 1;
				Results8Store.filgbc.gridx = 1;
				Results8Store.filgbc.gridy = 2;
				Results8Store.CancelAlbum.setVisible(true);
				Results8Store.fillerPanel.add(Results8Store.CancelAlbum,
						Results8Store.filgbc);

				// Create whitespace filler panel
				Results8Store.fillerbottom = new JPanel();
				Results8Store.filgbc.gridy = 3;
				Results8Store.filgbc.gridx = 0;
				Results8Store.filgbc.weightx = 1;
				Results8Store.filgbc.weighty = 1;
				Results8Store.filgbc.gridwidth = 2;
				Results8Store.fillerbottom.setPreferredSize(new Dimension(190,
						300));
				Results8Store.filgbc.fill = GridBagConstraints.BOTH;
				Results8Store.fillerPanel.add(Results8Store.fillerbottom,
						Results8Store.filgbc);
				
				Results8Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Results8Store.filgbc.weightx = .5;
				Results8Store.filgbc.gridwidth = 1;
				Results8Store.filgbc.gridx = 0;
				Results8Store.filgbc.gridy = 4;
				
				Results8Store.CreateErrLabel.setVisible(false);
				
				Results8Store.fillerPanel.add(Results8Store.CreateErrLabel,
						Results8Store.filgbc);

				// Event listener for when text is entered into PhotoField
				Results8Store.AlbumName.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent event) {
						if ((!Results8Store.AlbumName.getText().equals(""))) {
							Results8Store.CreateAlbum.setEnabled(true);
						} else {
							Results8Store.CreateAlbum.setEnabled(false);
						}
					}

					@Override
					public void keyReleased(KeyEvent event) {
						if ((!Results8Store.AlbumName.getText().equals(""))) {
							Results8Store.CreateAlbum.setEnabled(true);
						} else {
							Results8Store.CreateAlbum.setEnabled(false);
						}
					}

					@Override
					public void keyPressed(KeyEvent event) {
						if ((!Results8Store.AlbumName.getText().equals(""))) {
							Results8Store.CreateAlbum.setEnabled(true);
						} else {
							Results8Store.CreateAlbum.setEnabled(false);
						}
					}
				});

				Results8Store.pa.revalidate();
				Results8Store.pa.repaint();

			}
		});

		// Event listener for Cancel Photo Button
		Results8Store.CancelAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Set buttons to be enabled if a panel is selected
				if (panelSelected()) {
					Results8Store.NewResultsAlbum.setEnabled(true);
				} else {
					Results8Store.NewResultsAlbum.setEnabled(false);
				}

				Results8Store.AlbumName.setText("");
				Results8Store.AlbumName.setVisible(false);
				Results8Store.CreateErrLabel.setVisible(false);

				Results8Store.CancelAlbum.setVisible(false);
				Results8Store.CreateAlbum.setVisible(false);
				Results8Store.NewAlbum.setVisible(false);
				Results8Store.NewResultsAlbum.setEnabled(true);
				Results8Store.fillerbottom.setVisible(false);

			}
		});

		// Event listener for addPhoto Button
		Results8Store.CreateAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PhotoAlbum.backend.getUser(Login1State.user).albumExists(
						Results8Store.AlbumName.getText())) {
					CreateError();
				} else if(Results8Store.results.isEmpty()){
					CreateError();
				}else{
					PhotoAlbum.backend.getUser(Login1State.user).addAlbum(
							Results8Store.AlbumName.getText());
					for(photo p: Results8Store.results){
						PhotoAlbum.backend.getUser(Login1State.user).getAlbum(Results8Store.AlbumName.getText()).addPhoto(p);
					}
					Results8Store.CancelAlbum.setVisible(false);
					Results8Store.CreateAlbum.setVisible(false);
					Results8Store.AlbumName.setText("");
					Results8Store.AlbumName.setVisible(false);
					Results8Store.CreateErrLabel.setVisible(false);
					Results8Store.NewAlbum.setVisible(false);
					Results8Store.fillerbottom.setVisible(false);
				}
				
				

			}
		});

	}

	private void CreateError() {
		Results8Store.CreateErrLabel.setVisible(true);
		Results8Store.CreateErrLabel.setForeground(Color.red);
		

		//Results8Store.pa.revalidate();

		//Results8Store.pa.repaint();
	}

	public boolean panelSelected() {

		for (JPanel j : Results8Store.PhotosArray) {
			if (j.getBackground() == Color.LIGHT_GRAY) {
				return true;
			}
		}

		return false;
	}
	
	public static Results8State getInstance() {
		if (instance == null) {
			instance = new Results8State();
		}
		return instance;
	}

	@Override
	PhotoAlbumState processEvent() {
		// TODO Auto-generated method stub
		return null;
	}

}
