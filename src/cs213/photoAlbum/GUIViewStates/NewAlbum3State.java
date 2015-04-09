package cs213.photoAlbum.GUIViewStates;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EtchedBorder;

public class NewAlbum3State extends PhotoAlbumState {

	private static NewAlbum3State instance = null;

	public void enter() {

		// Grab current JFrame and remove all things
		Frame[] frames = Frame.getFrames();
		Album3Store.pa = (PhotoAlbum) frames[0];
		Album3Store.pa.getContentPane().removeAll();
		Album3Store.pa.getContentPane().repaint();
		Album3Store.pa.getContentPane().revalidate();

		// Create constraints, add to main panel

		Album3Store.gbl = new GridBagLayout();
		Album3Store.gbc = new GridBagConstraints();
		Album3Store.MainPanel = new JPanel();
		Album3Store.MainPanel.setLayout(Album3Store.gbl);

		// Create top Menu Bar
		JPanel MenuBar = new MenuBarPanel();
		MenuBar = (JPanel) ((MenuBarPanel) MenuBar)
				.CreateMenuBarPanel("Albums");
		Album3Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		Album3Store.gbc.gridx = 0;
		Album3Store.gbc.gridy = 0;
		Album3Store.gbc.weightx = 1;
		Album3Store.gbc.gridwidth = 2;
		Album3Store.MainPanel.add(MenuBar, Album3Store.gbc);

		// Create album panel of the screen
		Album3Store.albumPanel = new JPanel();
		Album3Store.innerPanel = new JPanel();

		// set up inner Panel of album panel
		Album3Store.innerPanel.setBorder(new EtchedBorder());
		Album3Store.innerPanel.setLayout(Album3Store.gbl);

		// Create jscrollpane for album panel
		Album3Store.albumScroll = new JScrollPane(Album3Store.innerPanel);
		Album3Store.albumScroll.setVisible(true);
		Album3Store.albumScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Album3Store.albumScroll.setLayout(new ScrollPaneLayout());
		Album3Store.albumScroll.setVisible(true);
		Album3Store.albumPanel.setLayout(new BorderLayout());
		Album3Store.albumPanel.setPreferredSize(new Dimension(300, 545));
		Album3Store.albumPanel
				.add(Album3Store.albumScroll, BorderLayout.CENTER);

		// Add albumPanel to mainPanel
		Album3Store.gbc.gridx = 0;
		Album3Store.gbc.gridy = 1;
		Album3Store.gbc.weightx = .8;
		Album3Store.gbc.gridwidth = 1;
		Album3Store.albumPanel.setBorder(new EtchedBorder());
		Album3Store.albumPanel.setVisible(true);

		Album3Store.MainPanel.add(Album3Store.albumPanel, Album3Store.gbc);

		// Create Buttons Panel
		Album3Store.ButtonsPanel = new JPanel();
		Album3Store.ButtonsPanel.setLayout(Album3Store.gbl);
		Album3Store.ButtonsPanel.setPreferredSize(new Dimension(300, 545));
		Album3Store.bgbc = new GridBagConstraints();
		Album3Store.bgbl = new GridBagLayout();
		Album3Store.ButtonsPanel.setLayout(Album3Store.bgbl);

		// Add Album Button
		Album3Store.AddAlbumButton = new JButton("Add Album");
		Album3Store.bgbc.gridx = 0;
		Album3Store.bgbc.gridy = 0;
		Album3Store.ButtonsPanel.add(Album3Store.AddAlbumButton,
				Album3Store.bgbc);

		// Rename Album Button
		Album3Store.RenameAlbumButton = new JButton("Rename Album");
		Album3Store.bgbc.gridx = 1;
		Album3Store.bgbc.gridy = 0;
		Album3Store.bgbc.weightx = 0.5;
		Album3Store.ButtonsPanel.add(Album3Store.RenameAlbumButton,
				Album3Store.bgbc);

		// Delete Album Button
		Album3Store.DeleteAlbumButton = new JButton("Delete Album");
		Album3Store.bgbc.gridx = 2;
		Album3Store.bgbc.gridy = 0;
		Album3Store.bgbc.weightx = 0.5;
		Album3Store.ButtonsPanel.add(Album3Store.DeleteAlbumButton,
				Album3Store.bgbc);

		// Add filler panel below buttons
		Album3Store.fillerPanel = new JPanel();
		Album3Store.bgbc.gridx = 0;
		Album3Store.bgbc.gridy = 1;
		Album3Store.bgbc.weighty = 1;
		Album3Store.bgbc.weightx = 1;
		Album3Store.bgbc.fill = GridBagConstraints.BOTH;
		Album3Store.bgbc.gridwidth = 3;
		Album3Store.ButtonsPanel.add(Album3Store.fillerPanel, Album3Store.bgbc);

		// Add buttons panel to main panel
		Album3Store.gbc.gridx = 1;
		Album3Store.gbc.gridy = 1;
		Album3Store.gbc.weightx = .2;
		Album3Store.gbc.weighty = 1;
		Album3Store.albumPanel.setBorder(new EtchedBorder());
		Album3Store.albumPanel.setVisible(true);
		Album3Store.MainPanel.add(Album3Store.ButtonsPanel, Album3Store.gbc);

		// Add MainPanel to pa
		Album3Store.pa.add(Album3Store.MainPanel, Album3Store.gbc);

		AddListeners();

	}

	public void AddListeners() {

		Album3Store.AddAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Create GBL & GBC for filler panel
				Album3Store.filgbl = new GridBagLayout();
				Album3Store.filgbc = new GridBagConstraints();
				Album3Store.fillerPanel.setLayout(Album3Store.filgbl);
				Album3Store.filgbc.insets = new Insets(10, 10, 10, 10);

				
				//Create AlbumName field
				Album3Store.AlbumName = new JLabel("Album Name:");
				Album3Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Album3Store.filgbc.gridx = 0;
				Album3Store.filgbc.gridy = 0;
				Album3Store.fillerPanel.add(Album3Store.AlbumName, Album3Store.filgbc);
				
				//Create AlbumName field
				Album3Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Album3Store.filgbc.weightx = .5;
				Album3Store.filgbc.gridx = 1;
				Album3Store.filgbc.gridy = 0;
				JTextField AlbumField = new JTextField();
				AlbumField.setSize(new Dimension(50, 20));
				Album3Store.fillerPanel.add(AlbumField, Album3Store.filgbc);
				
				//Create 'Create' Button
				
				
			}
		});

	}

	public static NewAlbum3State getInstance() {
		if (instance == null) {
			instance = new NewAlbum3State();
		}
		return instance;
	}

	@Override
	PhotoAlbumState processEvent(String button) {
		// TODO Auto-generated method stub
		return null;
	}

}