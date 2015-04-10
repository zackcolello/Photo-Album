package cs213.photoAlbum.GUIViewStates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EtchedBorder;

import cs213.photoAlbum.model.album;
import cs213.photoAlbum.model.photo;

//make the album jpanels an instance with getInstance.

public class NewAlbum3State extends PhotoAlbumState {

	public ArrayList<photo> photoList;

	static NewAlbum3State instance = null;

	public void enter() {

		Album3Store.albumsArray = new ArrayList<JPanel>();

		// Grab current JFrame and remove all the things
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
		Album3Store.RenameAlbumButton.setEnabled(false);
		Album3Store.ButtonsPanel.add(Album3Store.RenameAlbumButton,
				Album3Store.bgbc);

		// Delete Album Button
		Album3Store.DeleteAlbumButton = new JButton("Delete Album");
		Album3Store.bgbc.gridx = 2;
		Album3Store.bgbc.gridy = 0;
		Album3Store.bgbc.weightx = 0.5;
		Album3Store.DeleteAlbumButton.setEnabled(false);
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

		// Build the list of photo Albums to put in the AlbumPanel list
		fillAlbumPanel();

		// Add all the listeners
		AddListeners();

	}

	public void fillAlbumPanel() {

		Album3Store.albumsArray = new ArrayList<>();
		Album3Store.algbc = new GridBagConstraints();
		Album3Store.algbl = new GridBagLayout();
		Album3Store.errLabel = new JLabel(
				"Error: Album with that name already exists.");

		for (album a : PhotoAlbum.backend.getUser(Login1State.user).getAlbums()) {

			// Create temp JPanel
			JPanel temp = new JPanel();
			temp.setLayout(Album3Store.algbl);
			temp.setBackground(Color.WHITE);
			temp.setPreferredSize(new Dimension(170, 200));
			temp.setBorder(new EtchedBorder());
			temp.setVisible(true);

			// Build temp's information
			if (a.getPhotos().size() == 0) {
				ImageIcon noPhoto = new ImageIcon("docs/NoPhotos.png");
				Album3Store.algbc.gridx = 0;
				Album3Store.algbc.gridy = 0;
				JLabel photo = new JLabel("", noPhoto, JLabel.CENTER);
				temp.add(photo, Album3Store.algbc);
			} else {
				// here we would read in the actual thumb nail
			}

			// Add the name of the album to the temp JPanel
			Album3Store.albumName = new JLabel(a.getName());
			Album3Store.algbc.gridx = 0;
			Album3Store.algbc.gridy = 1;
			temp.add(Album3Store.albumName, Album3Store.algbc);

			// Add the number of photos
			Album3Store.numPhotos = new JLabel("Number of photos: "
					+ Integer.toString(a.getPhotos().size()));
			Album3Store.algbc.gridx = 0;
			Album3Store.algbc.gridy = 2;
			temp.add(Album3Store.numPhotos, Album3Store.algbc);

			// Get the earliest and latest date, add to temp jpanel
			photoList = a.getPhotos();
			Album3Store.dates = new JLabel();

			if (photoList.size() == 0) {
				Album3Store.dates.setText("From: -- To: --");
			} else {

				String earliest = photoList.get(0).getCalendar();
				String latest = photoList.get(0).getCalendar();
				for (photo p : photoList) {
					if (p.getCalendar().compareTo(earliest) < 0) {
						earliest = p.getCalendar();
					}
					if (p.getCalendar().compareTo(latest) > 0) {
						latest = p.getCalendar();
					}

				}
				Album3Store.dates.setText("From: " + earliest + " To: "
						+ latest);
			}

			// Add dates to temp
			Album3Store.algbc.gridx = 0;
			Album3Store.algbc.gridy = 3;
			temp.add(Album3Store.dates, Album3Store.algbc);

			// Add temp to the albumsArray
			Album3Store.albumsArray.add(temp);

			// Organize albums in albumPanel: 3 albums per row
			Album3Store.scrollConstraints = new GridBagConstraints();
			Album3Store.rowCount = 0;
			Album3Store.columnCount = 0;

			for (final JPanel j : Album3Store.albumsArray) {

				Album3Store.scrollConstraints.gridx = Album3Store.columnCount;
				Album3Store.scrollConstraints.gridy = Album3Store.rowCount;

				if (Album3Store.columnCount == 2) {
					Album3Store.rowCount += 1;
					Album3Store.columnCount = 0;
				} else {
					Album3Store.columnCount++;
				}

				j.setBackground(Color.WHITE);
				j.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {

						for (JPanel f : Album3Store.albumsArray) {
							f.setBackground(Color.WHITE);
						}

						Album3Store.RenameAlbumButton.setEnabled(true);
						Album3Store.DeleteAlbumButton.setEnabled(true);
						j.setBackground(Color.LIGHT_GRAY);
						Album3Store.pa.revalidate();
						Album3Store.pa.repaint();
					}

				});

				Album3Store.innerPanel.add(j, Album3Store.scrollConstraints);
			}

			// Add albumScroll and albumPanel to mainpanel
			Album3Store.albumPanel.add(Album3Store.albumScroll,
					BorderLayout.CENTER);
			Album3Store.gbc.gridx = 0;
			Album3Store.gbc.gridy = 1;
			Album3Store.gbc.weightx = .8;
			Album3Store.gbc.weighty = 1;
			Album3Store.gbc.gridwidth = 1;
			Album3Store.albumPanel.setBorder(new EtchedBorder());
			Album3Store.albumPanel.setVisible(true);
			Album3Store.MainPanel.add(Album3Store.albumPanel, Album3Store.gbc);

			Album3Store.pa.revalidate();
			Album3Store.pa.repaint();

		}

	}

	public void AddListeners() {

		Album3Store.CancelAlbum = new JButton("Cancel");
		Album3Store.CreateAlbum = new JButton("Create");
		Album3Store.RenameAlbum = new JButton("Rename");
		Album3Store.RenameCancel = new JButton("Cancel");
		Album3Store.AlbumField = new JTextField();

		// Event listener for Add Album Button
		Album3Store.AddAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Create GBL & GBC for filler panel
				Album3Store.filgbl = new GridBagLayout();
				Album3Store.filgbc = new GridBagConstraints();
				Album3Store.fillerPanel.setLayout(Album3Store.filgbl);
				Album3Store.filgbc.insets = new Insets(10, 10, 10, 10);

				// Hide other buttons
				Album3Store.RenameAlbumButton.setEnabled(false);
				Album3Store.DeleteAlbumButton.setEnabled(false);

				// Create AlbumName field
				Album3Store.AlbumName = new JLabel("Album Name:");
				Album3Store.AlbumField.setVisible(true);
				Album3Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Album3Store.filgbc.gridx = 0;
				Album3Store.filgbc.gridy = 0;
				Album3Store.fillerPanel.add(Album3Store.AlbumName,
						Album3Store.filgbc);

				// Create AlbumName field
				Album3Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Album3Store.filgbc.weightx = .5;
				Album3Store.filgbc.gridx = 1;
				Album3Store.filgbc.gridy = 0;
				Album3Store.AlbumField.setSize(new Dimension(50, 20));
				Album3Store.fillerPanel.add(Album3Store.AlbumField,
						Album3Store.filgbc);

				// Create 'Create' Button
				Album3Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Album3Store.filgbc.weightx = .5;
				Album3Store.filgbc.gridwidth = 1;
				Album3Store.filgbc.gridx = 0;
				Album3Store.filgbc.gridy = 1;
				Album3Store.CreateAlbum.setEnabled(false);
				Album3Store.CreateAlbum.setVisible(true);
				Album3Store.fillerPanel.add(Album3Store.CreateAlbum,
						Album3Store.filgbc);

				// Create 'Cancel' Button
				Album3Store.filgbc.weighty = 1;
				Album3Store.filgbc.weightx = .5;
				Album3Store.filgbc.gridx = 1;
				Album3Store.filgbc.gridwidth = 1;
				Album3Store.filgbc.gridy = 1;
				Album3Store.CancelAlbum.setVisible(true);
				Album3Store.fillerPanel.add(Album3Store.CancelAlbum,
						Album3Store.filgbc);

				// Create whitespace filler panel
				Album3Store.fillerbottom = new JPanel();
				Album3Store.filgbc.gridy = 2;
				Album3Store.filgbc.gridx = 0;
				Album3Store.filgbc.weightx = 1;
				Album3Store.filgbc.weighty = 1;
				Album3Store.filgbc.gridwidth = 2;
				Album3Store.fillerbottom.setPreferredSize(new Dimension(190,
						350));
				Album3Store.filgbc.fill = GridBagConstraints.BOTH;
				Album3Store.fillerPanel.add(Album3Store.fillerbottom,
						Album3Store.filgbc);

				// Event listener for when text is entered into AlbumField
				Album3Store.AlbumField.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent event) {
						if (!Album3Store.AlbumField.getText().equals("")) {
							Album3Store.CreateAlbum.setEnabled(true);
						} else {
							Album3Store.CreateAlbum.setEnabled(false);
						}
					}

					@Override
					public void keyReleased(KeyEvent event) {
						if (!Album3Store.AlbumField.getText().equals("")) {
							Album3Store.CreateAlbum.setEnabled(true);
						} else {
							Album3Store.CreateAlbum.setEnabled(false);
						}
					}

					@Override
					public void keyPressed(KeyEvent event) {
						if (!Album3Store.AlbumField.getText().equals("")) {
							Album3Store.CreateAlbum.setEnabled(true);
						} else {
							Album3Store.CreateAlbum.setEnabled(false);
						}
					}
				});

				Album3Store.pa.revalidate();
				Album3Store.pa.repaint();

			}
		});

		// Event listener for Cancel Album Button
		Album3Store.CancelAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Set buttons to be enabled if a panel is selected
				if (panelSelected()) {
					Album3Store.RenameAlbumButton.setEnabled(true);
					Album3Store.DeleteAlbumButton.setEnabled(true);
				} else {
					Album3Store.RenameAlbumButton.setEnabled(false);
					Album3Store.DeleteAlbumButton.setEnabled(false);
				}

				Album3Store.AlbumField.setText("");
				Album3Store.errLabel.setVisible(false);
				Album3Store.AlbumField.setVisible(false);
				Album3Store.AlbumName.setVisible(false);
				Album3Store.CancelAlbum.setVisible(false);
				Album3Store.CreateAlbum.setVisible(false);
				Album3Store.AddAlbumButton.setEnabled(true);
				Album3Store.fillerbottom.setVisible(false);

			}
		});

		// Event listener for Create Album Button
		Album3Store.CreateAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Album3Store.errLabel.setVisible(false);

				if (PhotoAlbum.backend.getUser(Login1State.user).getAlbum(
						Album3Store.AlbumField.getText()) != null) {

					addAlbumError();

				} else {

					PhotoAlbum.backend.getUser(Login1State.user).addAlbum(
							Album3Store.AlbumField.getText());

					NewAlbum3State.instance = null;
					PhotoAlbumStore.newAlbum3State.enter();
				}

			}
		});

		// Event listener for Delete Album Button
		Album3Store.DeleteAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PhotoAlbum.backend.getUser(Login1State.user).removeAlbum(
						getSelectedAlbum());

				NewAlbum3State.instance = null;
				PhotoAlbumStore.newAlbum3State.enter();

			}
		});

		// Event listener for Rename Album Button
		Album3Store.RenameAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// PhotoAlbum.backend.getUser(Login1State.user).getAlbum(Album3Store.AlbumField.getText()).;
				// Create GBL & GBC for filler panel
				Album3Store.filgbl = new GridBagLayout();
				Album3Store.filgbc = new GridBagConstraints();
				Album3Store.fillerPanel.setLayout(Album3Store.filgbl);
				Album3Store.filgbc.insets = new Insets(10, 10, 10, 10);
				Album3Store.AlbumField.setVisible(true);

				// Hide add album
				Album3Store.AddAlbumButton.setEnabled(false);

				// Create AlbumName field
				Album3Store.AlbumName = new JLabel("New Album Name:");
				Album3Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Album3Store.filgbc.gridx = 0;
				Album3Store.filgbc.gridy = 0;
				Album3Store.fillerPanel.add(Album3Store.AlbumName,
						Album3Store.filgbc);

				// Create AlbumName field
				Album3Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Album3Store.filgbc.weightx = .5;
				Album3Store.filgbc.gridx = 1;
				Album3Store.filgbc.gridy = 0;
				Album3Store.AlbumField.setSize(new Dimension(50, 20));
				Album3Store.fillerPanel.add(Album3Store.AlbumField,
						Album3Store.filgbc);

				// Create 'Rename' Button
				Album3Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Album3Store.filgbc.weightx = .5;
				Album3Store.filgbc.gridwidth = 1;
				Album3Store.filgbc.gridx = 0;
				Album3Store.filgbc.gridy = 1;
				Album3Store.RenameAlbum.setEnabled(false);
				Album3Store.RenameAlbum.setText("Rename");
				Album3Store.RenameAlbum.setVisible(true);
				Album3Store.fillerPanel.add(Album3Store.RenameAlbum,
						Album3Store.filgbc);

				// Create 'Cancel' Button
				Album3Store.filgbc.weighty = 1;
				Album3Store.filgbc.weightx = .5;
				Album3Store.filgbc.gridx = 1;
				Album3Store.filgbc.gridwidth = 1;
				Album3Store.filgbc.gridy = 1;
				Album3Store.RenameCancel.setVisible(true);
				Album3Store.fillerPanel.add(Album3Store.RenameCancel,
						Album3Store.filgbc);

				// Create whitespace filler panel
				Album3Store.fillerbottom = new JPanel();
				Album3Store.filgbc.gridy = 2;
				Album3Store.filgbc.gridx = 0;
				Album3Store.filgbc.weightx = 1;
				Album3Store.filgbc.weighty = 1;
				Album3Store.filgbc.gridwidth = 2;
				Album3Store.fillerbottom.setPreferredSize(new Dimension(190,
						350));
				Album3Store.filgbc.fill = GridBagConstraints.BOTH;
				Album3Store.fillerPanel.add(Album3Store.fillerbottom,
						Album3Store.filgbc);

				// Event listener for when text is entered into AlbumField
				Album3Store.AlbumField.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent event) {
						if (!Album3Store.AlbumField.getText().equals("")) {
							Album3Store.RenameAlbum.setEnabled(true);
						} else {
							Album3Store.RenameAlbum.setEnabled(false);
						}
					}

					@Override
					public void keyReleased(KeyEvent event) {
						if (!Album3Store.AlbumField.getText().equals("")) {
							Album3Store.RenameAlbum.setEnabled(true);
						} else {
							Album3Store.RenameAlbum.setEnabled(false);
						}
					}

					@Override
					public void keyPressed(KeyEvent event) {
						if (!Album3Store.AlbumField.getText().equals("")) {
							Album3Store.RenameAlbum.setEnabled(true);
						} else {
							Album3Store.RenameAlbum.setEnabled(false);
						}
					}
				});

				Album3Store.pa.revalidate();
				Album3Store.pa.repaint();
			}
		});

		// Event listener for RenameAlbum Button
		Album3Store.RenameAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PhotoAlbum.backend.getUser(Login1State.user).renameAlbum(
						getSelectedAlbum(), Album3Store.AlbumField.getText());

				NewAlbum3State.instance = null;
				PhotoAlbumStore.newAlbum3State.enter();

			}
		});

		// Event listener for RenameCancel Button
		Album3Store.RenameCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Album3Store.RenameAlbumButton.setEnabled(true);
				Album3Store.DeleteAlbumButton.setEnabled(true);

				NewAlbum3State.instance = null;
				PhotoAlbumStore.newAlbum3State.enter();

			}
		});

	}

	public String getSelectedAlbum() {

		for (JPanel j : Album3Store.albumsArray) {
			if (j.getBackground() == Color.LIGHT_GRAY) {
				return ((JLabel) j.getComponent(1)).getText();
			}
		}

		return null;
	}

	// Puts an error when trying to add an album that already exists
	public void addAlbumError() {

		Album3Store.errLabel.setVisible(true);
		Album3Store.errLabel.setForeground(Color.red);
		Album3Store.bgbc.gridy = 2;
		Album3Store.bgbc.gridx = 0;
		Album3Store.bgbc.weighty = 1;
		Album3Store.bgbc.weightx = 1;
		Album3Store.bgbc.fill = GridBagConstraints.BOTH;
		Album3Store.bgbc.gridwidth = 3;
		Album3Store.ButtonsPanel.add(Album3Store.errLabel, Album3Store.bgbc);

		Album3Store.pa.revalidate();
		Album3Store.pa.repaint();
	}

	public static NewAlbum3State getInstance() {
		if (instance == null) {
			instance = new NewAlbum3State();
		}
		return instance;
	}

	public boolean panelSelected() {

		for (JPanel j : Album3Store.albumsArray) {
			if (j.getBackground() == Color.LIGHT_GRAY) {
				return true;
			}
		}

		return false;
	}

	@Override
	PhotoAlbumState processEvent() {
		// TODO Auto-generated method stub
		return null;
	}

}