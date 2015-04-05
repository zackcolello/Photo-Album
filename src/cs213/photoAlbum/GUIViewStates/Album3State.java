package cs213.photoAlbum.GUIViewStates;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import cs213.photoAlbum.model.album;

public class Album3State extends PhotoAlbumState {

	/**
	 * Singleton instance
	 */
	private static Album3State instance = null;

	JPanel MainPanel = new JPanel();
	JPanel LeftPanel = new JPanel();
	JPanel RightPanel = new JPanel();
	
	private GridBagLayout gbl = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();

	// Constructor
	public Album3State() {
		
	}
	
	// Called when state is entered, build state
	public void enter() {

		// Grab current JFrame
		Frame[] frames = Frame.getFrames();
		PhotoAlbum pa = (PhotoAlbum) frames[0];

		// Clear items from that state
		pa.getContentPane().removeAll();
		pa.getContentPane().repaint();
		
		pa.setLayout(gbl);

		MainPanel.setLayout(gbl);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;

		JPanel MenuBar = new MenuBarPanel();
		MenuBar = (JPanel) ((MenuBarPanel) MenuBar)
				.CreateMenuBarPanel("albums");

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		MainPanel.add(MenuBar, gbc);

		// Set up album panel with album scroll pane

		JPanel albumPanel = new JPanel();
		JScrollPane albumScroll = new JScrollPane();
		albumScroll.setVisible(true);
		albumScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		albumPanel.setLayout(new BorderLayout());
		albumPanel.setPreferredSize(new Dimension(300, 545));

		albumPanel.add(albumScroll, BorderLayout.CENTER);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = .8;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		albumPanel.setBorder(new EtchedBorder());
		albumPanel.setVisible(true);
		MainPanel.add(albumPanel, gbc);

		// Set up button panel on the right
		JPanel ButtonsPanel = new JPanel();
		GridBagConstraints buttongbc = new GridBagConstraints();
		GridBagLayout buttongbl = new GridBagLayout();

		ButtonsPanel.setLayout(buttongbl);

		// ButtonsPanel.setBorder(new EtchedBorder());
		ButtonsPanel.setPreferredSize(new Dimension(300, 545));

		JButton AddAlbumButton = new JButton("Add Album");
		AddAlbumButton.setVisible(true);
		// AddAlbumButton.setPreferredSize(new Dimension(140, 40));
		buttongbc.gridx = 0;
		ButtonsPanel.add(AddAlbumButton, buttongbc);

		JButton RenameAlbumButton = new JButton("Rename Album");
		RenameAlbumButton.setVisible(true);
		RenameAlbumButton.setEnabled(false);
		// RenameAlbumButton.setPreferredSize(new Dimension(140, 40));
		buttongbc.gridx = 1;
		buttongbc.weightx = 0.5;
		ButtonsPanel.add(RenameAlbumButton, buttongbc);

		JButton DeleteAlbumButton = new JButton("Delete Album");
		DeleteAlbumButton.setVisible(true);
		DeleteAlbumButton.setEnabled(false);
		// DeleteAlbumButton.setPreferredSize(new Dimension(140, 40));
		buttongbc.gridx = 2;
		buttongbc.weightx = 0.5;
		ButtonsPanel.add(DeleteAlbumButton, buttongbc);

		// add filler for under the buttons
		JPanel fillerPanel = new JPanel();
		buttongbc.gridx = 0;
		buttongbc.gridy = 1;
		buttongbc.weighty = 1;
		buttongbc.weightx = 1;
		buttongbc.fill = GridBagConstraints.BOTH;
		// buttongbc.gridheight = 20;
		buttongbc.gridwidth = 3;
		// fillerPanel.setBorder(new EtchedBorder());
		ButtonsPanel.add(fillerPanel, buttongbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = .2;
		gbc.weighty = 1;
		albumPanel.setBorder(new EtchedBorder());
		albumPanel.setVisible(true);

		MainPanel.add(ButtonsPanel, gbc);

		// Put main panel in jframe
		MainPanel.setVisible(true);
		pa.add(MainPanel, gbc);
		pa.setVisible(true);

		// Check for when AddAlbumButton selected
		
		AddAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GridBagLayout fillergbl = new GridBagLayout();
				GridBagConstraints fillergbc = new GridBagConstraints();
				fillerPanel.setLayout(fillergbl);
				fillergbc.insets = new Insets(10, 10, 10, 10);

				fillergbc.fill = GridBagConstraints.HORIZONTAL;
				fillergbc.gridx = 0;
				fillergbc.gridy = 0;

				// Adding album
				JLabel AlbumName = new JLabel("Album Name:");
				fillerPanel.add(AlbumName, fillergbc);

				fillergbc.fill = GridBagConstraints.HORIZONTAL;
				fillergbc.weightx = .5;
				fillergbc.gridx = 1;
				fillergbc.gridy = 0;
				JTextField AlbumField = new JTextField();
				AlbumField.setSize(new Dimension(50, 20));
				fillerPanel.add(AlbumField, fillergbc);

				fillergbc.fill = GridBagConstraints.HORIZONTAL;
				fillergbc.weightx = .5;
				fillergbc.gridwidth = 1;
				fillergbc.gridx = 0;
				fillergbc.gridy = 1;
				buttongbc.fill = GridBagConstraints.HORIZONTAL;
				JButton CreateAlbum = new JButton("Create");
				CreateAlbum.setEnabled(false);
				fillerPanel.add(CreateAlbum, fillergbc);

				buttongbc.fill = GridBagConstraints.HORIZONTAL;
				fillergbc.weighty = 1;
				fillergbc.weightx = .5;
				fillergbc.gridx = 1;
				fillergbc.gridwidth = 1;
				fillergbc.gridy = 1;
				JButton CancelAlbum = new JButton("Cancel");
				fillerPanel.add(CancelAlbum, fillergbc);

				JPanel fillerbottom = new JPanel();

				fillergbc.gridy = 2;
				fillergbc.gridx = 0;
				fillergbc.weightx = 1;
				fillergbc.weighty = 1;
				fillergbc.gridwidth = 2;
				fillerbottom.setPreferredSize(new Dimension(190, 350));
				fillergbc.fill = GridBagConstraints.BOTH;
				fillerPanel.add(fillerbottom, fillergbc);

				AddAlbumButton.setEnabled(false);
				pa.revalidate();

				
				// add event listener for when text is entered into label
				AlbumField.addKeyListener(new KeyListener() {

					@Override
					public void keyTyped(KeyEvent event) {
						if (!AlbumField.getText().equals("")) {
							CreateAlbum.setEnabled(true);
						} else {
							CreateAlbum.setEnabled(false);
						}
					}

					@Override
					public void keyReleased(KeyEvent event) {
						if (!AlbumField.getText().equals("")) {
							CreateAlbum.setEnabled(true);
						} else {
							CreateAlbum.setEnabled(false);
						}
					}

					@Override
					public void keyPressed(KeyEvent event) {
						if (!AlbumField.getText().equals("")) {
							CreateAlbum.setEnabled(true);
						} else {
							CreateAlbum.setEnabled(false);
						}
					}
				});
				
				
				// Check for when Create is clicked

				CreateAlbum.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						PhotoAlbum.backend.getUser(Login1State.user).addAlbum(AlbumField.getText());
						
						AlbumField.setVisible(false);
						AlbumName.setVisible(false);
						CancelAlbum.setVisible(false);
						CreateAlbum.setVisible(false);
						AddAlbumButton.setEnabled(true);
						fillerbottom.setVisible(false);
						
						for(album a: PhotoAlbum.backend.getUser(Login1State.user).getAlbums()){
							System.out.println(a.getName());
						}
					}
				});
				
				// Check for when Cancel is clicked
				CancelAlbum.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						AlbumField.setVisible(false);
						AlbumName.setVisible(false);
						CancelAlbum.setVisible(false);
						CreateAlbum.setVisible(false);
						AddAlbumButton.setEnabled(true);
						fillerbottom.setVisible(false);
						
					}
				});
			}
		});

	}

	// Processes events to move to other states
	public PhotoAlbumState processEvent(String button) {

		instance = null;
		
		if (button.equals("create")) {
			
			PhotoAlbumStore.album3State.enter();
			return PhotoAlbumStore.album3State;
		}
		
		if(button.equals("cancel")){
			PhotoAlbumStore.album3State.enter();
			return PhotoAlbumStore.album3State;
		}

		return null;
	}

	public static Album3State getInstance() {
		if (instance == null) {
			instance = new Album3State();
		}
		return instance;
	}

}
