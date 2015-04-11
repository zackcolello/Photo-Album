package cs213.photoAlbum.GUIViewStates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
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

public class InAlbum4State extends PhotoAlbumState {

	static InAlbum4State instance = null;

	public void enter() {
		
		InAlbum4Store.PhotosArray = new ArrayList<JPanel>();
		
		System.out.println("album: " + InAlbum4Store.albumName);
		
		// Grab current JFrame and remove all the things
		Frame[] frames = Frame.getFrames();

		InAlbum4Store.pa = (PhotoAlbum) frames[0];

		System.out.println(Login1State.user);
		InAlbum4Store.DestAlbums = PhotoAlbum.backend.getUser(Login1State.user)
				.getAlbums();
		System.out.println(InAlbum4Store.DestAlbums);

		InAlbum4Store.pa.getContentPane().removeAll();
		InAlbum4Store.pa.getContentPane().repaint();
		InAlbum4Store.pa.getContentPane().revalidate();

		// Create constraints, add to main panel

		InAlbum4Store.gbl = new GridBagLayout();
		InAlbum4Store.gbc = new GridBagConstraints();
		InAlbum4Store.MainPanel = new JPanel();
		InAlbum4Store.MainPanel.setLayout(InAlbum4Store.gbl);

		// Create top Menu Bar
		JPanel MenuBar = new MenuBarPanel();
		MenuBar = (JPanel) ((MenuBarPanel) MenuBar).CreateMenuBarPanel("Photo");
		InAlbum4Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		InAlbum4Store.gbc.gridx = 0;
		InAlbum4Store.gbc.gridy = 0;
		InAlbum4Store.gbc.weightx = 1;
		InAlbum4Store.gbc.gridwidth = 2;
		InAlbum4Store.gbc.anchor = GridBagConstraints.NORTH;
		InAlbum4Store.MainPanel.add(MenuBar, InAlbum4Store.gbc);

		// Create album panel of the screen
		InAlbum4Store.photoPanel = new JPanel();
		InAlbum4Store.innerPanel = new JPanel();

		// set up inner Panel of album panel
		InAlbum4Store.innerPanel.setBorder(new EtchedBorder());
		InAlbum4Store.innerPanel.setLayout(InAlbum4Store.gbl);

		// Create jscrollpane for album panel
		InAlbum4Store.photoScroll = new JScrollPane(InAlbum4Store.innerPanel);
		InAlbum4Store.photoScroll.setVisible(true);
		InAlbum4Store.photoScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		InAlbum4Store.photoScroll.setLayout(new ScrollPaneLayout());
		InAlbum4Store.photoScroll.setVisible(true);
		InAlbum4Store.photoPanel.setLayout(new BorderLayout());
		InAlbum4Store.photoPanel.setPreferredSize(new Dimension(50, 545));
		InAlbum4Store.photoPanel.add(InAlbum4Store.photoScroll,
				BorderLayout.CENTER);

		// Add photoPanel to mainPanel
		InAlbum4Store.gbc.gridx = 0;
		InAlbum4Store.gbc.gridy = 1;
		InAlbum4Store.gbc.weightx = .67;
		InAlbum4Store.gbc.gridwidth = 1;
		InAlbum4Store.photoPanel.setBorder(new EtchedBorder());
		InAlbum4Store.photoPanel.setVisible(true);
		
		InAlbum4Store.MainPanel
				.add(InAlbum4Store.photoPanel, InAlbum4Store.gbc);

		// Create Buttons Panel
		InAlbum4Store.ButtonsPanel = new JPanel();
		InAlbum4Store.ButtonsPanel.setLayout(InAlbum4Store.gbl);
		InAlbum4Store.ButtonsPanel.setPreferredSize(new Dimension(50, 540));
		InAlbum4Store.bgbc = new GridBagConstraints();
		InAlbum4Store.bgbl = new GridBagLayout();
		InAlbum4Store.ButtonsPanel.setLayout(InAlbum4Store.bgbl);

		// Add Photo Button
		InAlbum4Store.AddPhotoButton = new JButton("Add Photo");
		InAlbum4Store.bgbc.gridx = 0;
		InAlbum4Store.bgbc.gridy = 0;
		InAlbum4Store.ButtonsPanel.add(InAlbum4Store.AddPhotoButton,
				InAlbum4Store.bgbc);

		// Move Photo Button
		InAlbum4Store.MovePhotoButton = new JButton("Move Photo");
		InAlbum4Store.bgbc.gridx = 1;
		InAlbum4Store.bgbc.gridy = 0;
		InAlbum4Store.bgbc.weightx = 0.6;
		InAlbum4Store.MovePhotoButton.setEnabled(false);
		InAlbum4Store.ButtonsPanel.add(InAlbum4Store.MovePhotoButton,
				InAlbum4Store.bgbc);

		// Delete Photo Button
		InAlbum4Store.DeletePhotoButton = new JButton("Delete Photo");
		InAlbum4Store.bgbc.gridx = 2;
		InAlbum4Store.bgbc.gridy = 0;
		InAlbum4Store.bgbc.weightx = 0.5;
		InAlbum4Store.DeletePhotoButton.setEnabled(false);
		InAlbum4Store.ButtonsPanel.add(InAlbum4Store.DeletePhotoButton,
				InAlbum4Store.bgbc);

		// Add filler panel below buttons
		InAlbum4Store.fillerPanel = new JPanel();
		InAlbum4Store.bgbc.gridx = 0;
		InAlbum4Store.bgbc.gridy = 1;
		InAlbum4Store.bgbc.weighty = 1;
		InAlbum4Store.bgbc.weightx = 1;
		InAlbum4Store.bgbc.fill = GridBagConstraints.BOTH;
		InAlbum4Store.bgbc.gridwidth = 3;
		InAlbum4Store.ButtonsPanel.add(InAlbum4Store.fillerPanel,
				InAlbum4Store.bgbc);

		// Add buttons panel to main panel
		InAlbum4Store.gbc.gridx = 1;
		InAlbum4Store.gbc.gridy = 1;
		InAlbum4Store.gbc.weightx = .33;
		InAlbum4Store.gbc.weighty = 1;
		InAlbum4Store.photoPanel.setBorder(new EtchedBorder());
		InAlbum4Store.photoPanel.setVisible(true);
		InAlbum4Store.MainPanel.add(InAlbum4Store.ButtonsPanel,
				InAlbum4Store.gbc);

		// Add MainPanel to pa
		InAlbum4Store.pa.add(InAlbum4Store.MainPanel, InAlbum4Store.gbc);

		// Build the list of photo Albums to put in the photoPanel list
		fillphotoPanel();

		// Add all the listeners
		addListeners();

	}

	public void fillphotoPanel() {

		InAlbum4Store.PhotosArray = new ArrayList<JPanel>();
		InAlbum4Store.phgbc = new GridBagConstraints();
		InAlbum4Store.phgbl = new GridBagLayout();
		InAlbum4Store.AddErrLabel = new JLabel(
				"Error: Cannot add Photo to this Album");
		InAlbum4Store.MoveErrLabel = new JLabel(
				"Error: Cannot move Photo to Destination Album");

		for (photo p : PhotoAlbum.backend.getUser(Login1State.user)
				.getAlbum(InAlbum4Store.albumName).getPhotos()) {

			// Create temp JPanel
			JPanel temp = new JPanel();
			temp.setLayout(InAlbum4Store.phgbl);
			temp.setBackground(Color.WHITE);
			temp.setPreferredSize(new Dimension(170, 200));
			temp.setBorder(new EtchedBorder());
			temp.setVisible(true);

			// Build temp's information

			ImageIcon icon;
			Image image = p.getPhoto().getImage();
			Image newimg = image.getScaledInstance(100, 100,
					java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);

			InAlbum4Store.phgbc.gridx = 0;
			InAlbum4Store.phgbc.gridy = 0;
			JLabel photo = new JLabel("", icon, JLabel.CENTER);
			temp.add(photo, InAlbum4Store.phgbc);

			// Add the path of the photo to the temp JPanel
			InAlbum4Store.PhotoName = new JLabel(p.getFileName());
			InAlbum4Store.phgbc.gridx = 0;
			InAlbum4Store.phgbc.gridy = 1;
			temp.add(InAlbum4Store.PhotoName, InAlbum4Store.phgbc);

			// Add the caption of the photo to the temp JPanel
			InAlbum4Store.photoCaption = new JLabel(p.getCaption());
			InAlbum4Store.phgbc.gridx = 0;
			InAlbum4Store.phgbc.gridy = 2;
			temp.add(InAlbum4Store.photoCaption, InAlbum4Store.phgbc);

			// Add the date of photos
			InAlbum4Store.Date = new JLabel(p.getCalendar());
			InAlbum4Store.phgbc.gridx = 0;
			InAlbum4Store.phgbc.gridy = 3;
			temp.add(InAlbum4Store.Date, InAlbum4Store.phgbc);

			// Add temp to the PhotosArray
			InAlbum4Store.PhotosArray.add(temp);

			// Organize albums in albumPanel: 3 albums per row
			InAlbum4Store.scrollConstraints = new GridBagConstraints();
			InAlbum4Store.rowCount = 0;
			InAlbum4Store.columnCount = 0;

			for (JPanel j : InAlbum4Store.PhotosArray) {

				InAlbum4Store.scrollConstraints.gridx = InAlbum4Store.columnCount;
				InAlbum4Store.scrollConstraints.gridy = InAlbum4Store.rowCount;

				if (InAlbum4Store.columnCount == 2) {
					InAlbum4Store.rowCount += 1;
					InAlbum4Store.columnCount = 0;
				} else {
					InAlbum4Store.columnCount++;
				}

				j.setBackground(Color.WHITE);
				j.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {

						for (JPanel f : InAlbum4Store.PhotosArray) {
							f.setBackground(Color.WHITE);
						}

						InAlbum4Store.MovePhotoButton.setEnabled(true);
						InAlbum4Store.DeletePhotoButton.setEnabled(true);
						j.setBackground(Color.LIGHT_GRAY);
						InAlbum4Store.pa.revalidate();
						InAlbum4Store.pa.repaint();
					}

				});

				InAlbum4Store.innerPanel
						.add(j, InAlbum4Store.scrollConstraints);
			}

			// Add albumScroll and albumPanel to mainpanel
			InAlbum4Store.photoPanel.add(InAlbum4Store.photoScroll,
					BorderLayout.CENTER);
			InAlbum4Store.gbc.gridx = 0;
			InAlbum4Store.gbc.gridy = 1;
			InAlbum4Store.gbc.weightx = .8;
			InAlbum4Store.gbc.weighty = 1;
			InAlbum4Store.gbc.gridwidth = 1;
			InAlbum4Store.photoPanel.setBorder(new EtchedBorder());
			InAlbum4Store.photoPanel.setVisible(true);
			InAlbum4Store.MainPanel.add(InAlbum4Store.photoPanel,
					InAlbum4Store.gbc);

			InAlbum4Store.pa.revalidate();
			InAlbum4Store.pa.repaint();

		}

	}

	public void addListeners() {

		InAlbum4Store.CancelPhoto = new JButton("Cancel");
		InAlbum4Store.AddPhoto = new JButton("Add");
		InAlbum4Store.CancelMove = new JButton("Cancel");
		InAlbum4Store.Move = new JButton("Move");
		InAlbum4Store.PhotoField = new JTextField();
		InAlbum4Store.CaptionField = new JTextField();
		// InAlbum4Store.DestAlbums = new JComboBox<album>((

		// Event listener for Add Photo Button
		InAlbum4Store.AddPhotoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Create GBL & GBC for filler panel
				InAlbum4Store.filgbl = new GridBagLayout();
				InAlbum4Store.filgbc = new GridBagConstraints();
				InAlbum4Store.fillerPanel.setLayout(InAlbum4Store.filgbl);
				InAlbum4Store.filgbc.insets = new Insets(10, 10, 10, 10);

				// Hide other buttons
				InAlbum4Store.MovePhotoButton.setEnabled(false);
				InAlbum4Store.DeletePhotoButton.setEnabled(false);
				InAlbum4Store.AddPhotoButton.setEnabled(false);

				// Create PhotoName field
				InAlbum4Store.PhotoName = new JLabel("Path to Photo:");
				InAlbum4Store.PhotoField.setVisible(true);
				InAlbum4Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				InAlbum4Store.filgbc.gridx = 0;
				InAlbum4Store.filgbc.gridy = 0;
				InAlbum4Store.fillerPanel.add(InAlbum4Store.PhotoName,
						InAlbum4Store.filgbc);

				// Create PhotoName field
				InAlbum4Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				InAlbum4Store.filgbc.weightx = .5;
				InAlbum4Store.filgbc.gridx = 1;
				InAlbum4Store.filgbc.gridy = 0;
				InAlbum4Store.PhotoField.setSize(new Dimension(50, 20));
				InAlbum4Store.fillerPanel.add(InAlbum4Store.PhotoField,
						InAlbum4Store.filgbc);

				// Create Caption field
				InAlbum4Store.Caption = new JLabel("Photo Caption:");
				InAlbum4Store.CaptionField.setVisible(true);
				InAlbum4Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				InAlbum4Store.filgbc.gridx = 0;
				InAlbum4Store.filgbc.gridy = 1;
				InAlbum4Store.fillerPanel.add(InAlbum4Store.Caption,
						InAlbum4Store.filgbc);

				// Create Caption field
				InAlbum4Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				InAlbum4Store.filgbc.weightx = .5;
				InAlbum4Store.filgbc.gridx = 1;
				InAlbum4Store.filgbc.gridy = 1;
				InAlbum4Store.CaptionField.setSize(new Dimension(50, 20));
				InAlbum4Store.fillerPanel.add(InAlbum4Store.CaptionField,
						InAlbum4Store.filgbc);

				// Create 'Add' Button
				InAlbum4Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				InAlbum4Store.filgbc.weightx = .5;
				InAlbum4Store.filgbc.gridwidth = 1;
				InAlbum4Store.filgbc.gridx = 0;
				InAlbum4Store.filgbc.gridy = 2;
				InAlbum4Store.AddPhoto.setEnabled(false);
				InAlbum4Store.AddPhoto.setVisible(true);
				InAlbum4Store.fillerPanel.add(InAlbum4Store.AddPhoto,
						InAlbum4Store.filgbc);

				// Create 'Cancel' Button
				InAlbum4Store.filgbc.weighty = 1;
				InAlbum4Store.filgbc.weightx = .5;
				InAlbum4Store.filgbc.gridx = 1;
				InAlbum4Store.filgbc.gridwidth = 1;
				InAlbum4Store.filgbc.gridy = 2;
				InAlbum4Store.CancelPhoto.setVisible(true);
				InAlbum4Store.fillerPanel.add(InAlbum4Store.CancelPhoto,
						InAlbum4Store.filgbc);

				// Create whitespace filler panel
				InAlbum4Store.fillerbottom = new JPanel();
				InAlbum4Store.filgbc.gridy = 3;
				InAlbum4Store.filgbc.gridx = 0;
				InAlbum4Store.filgbc.weightx = 1;
				InAlbum4Store.filgbc.weighty = 1;
				InAlbum4Store.filgbc.gridwidth = 2;
				InAlbum4Store.fillerbottom.setPreferredSize(new Dimension(190,
						350));
				InAlbum4Store.filgbc.fill = GridBagConstraints.BOTH;
				InAlbum4Store.fillerPanel.add(InAlbum4Store.fillerbottom,
						InAlbum4Store.filgbc);

				// Event listener for when text is entered into PhotoField
				InAlbum4Store.PhotoField.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent event) {
						if ((!InAlbum4Store.PhotoField.getText().equals(""))
								&& (!InAlbum4Store.CaptionField.getText()
										.equals(""))) {
							InAlbum4Store.AddPhoto.setEnabled(true);
						} else {
							InAlbum4Store.AddPhoto.setEnabled(false);
						}
					}

					@Override
					public void keyReleased(KeyEvent event) {
						if ((!InAlbum4Store.PhotoField.getText().equals(""))
								&& (!InAlbum4Store.CaptionField.getText()
										.equals(""))) {
							InAlbum4Store.AddPhoto.setEnabled(true);
						} else {
							InAlbum4Store.AddPhoto.setEnabled(false);
						}
					}

					@Override
					public void keyPressed(KeyEvent event) {
						if ((!InAlbum4Store.PhotoField.getText().equals(""))
								&& (!InAlbum4Store.CaptionField.getText()
										.equals(""))) {
							InAlbum4Store.AddPhoto.setEnabled(true);
						} else {
							InAlbum4Store.AddPhoto.setEnabled(false);
						}
					}
				});
				InAlbum4Store.CaptionField.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent event) {
						if ((!InAlbum4Store.PhotoField.getText().equals(""))
								&& (!InAlbum4Store.CaptionField.getText()
										.equals(""))) {
							InAlbum4Store.AddPhoto.setEnabled(true);
						} else {
							InAlbum4Store.AddPhoto.setEnabled(false);
						}
					}

					@Override
					public void keyReleased(KeyEvent event) {
						if ((!InAlbum4Store.PhotoField.getText().equals(""))
								&& (!InAlbum4Store.CaptionField.getText()
										.equals(""))) {
							InAlbum4Store.AddPhoto.setEnabled(true);
						} else {
							InAlbum4Store.AddPhoto.setEnabled(false);
						}
					}

					@Override
					public void keyPressed(KeyEvent event) {
						if ((!InAlbum4Store.PhotoField.getText().equals(""))
								&& (!InAlbum4Store.CaptionField.getText()
										.equals(""))) {
							InAlbum4Store.AddPhoto.setEnabled(true);
						} else {
							InAlbum4Store.AddPhoto.setEnabled(false);
						}
					}
				});

				InAlbum4Store.pa.revalidate();
				InAlbum4Store.pa.repaint();

			}
		});

		// Event listener for Cancel Photo Button
		InAlbum4Store.CancelPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Set buttons to be enabled if a panel is selected
				if (panelSelected()) {
					InAlbum4Store.MovePhotoButton.setEnabled(true);
					InAlbum4Store.DeletePhotoButton.setEnabled(true);
				} else {
					InAlbum4Store.MovePhotoButton.setEnabled(false);
					InAlbum4Store.DeletePhotoButton.setEnabled(false);
				}

				InAlbum4Store.PhotoField.setText("");
				InAlbum4Store.CaptionField.setText("");
				InAlbum4Store.AddErrLabel.setVisible(false);
				InAlbum4Store.PhotoField.setVisible(false);
				InAlbum4Store.PhotoName.setVisible(false);
				InAlbum4Store.CaptionField.setVisible(false);
				InAlbum4Store.Caption.setVisible(false);
				InAlbum4Store.CancelPhoto.setVisible(false);
				InAlbum4Store.AddPhoto.setVisible(false);
				InAlbum4Store.AddPhotoButton.setEnabled(true);
				InAlbum4Store.fillerbottom.setVisible(false);

			}
		});

		// Event listener for addPhoto Button
		InAlbum4Store.AddPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// InAlbum4Store.MoveErrLabel.setVisible(false);
				File f = new File(InAlbum4Store.PhotoField.getText());
				if (f.exists() && !f.isDirectory()) {

					if (PhotoAlbum.backend.getUser(Login1State.user)
							.getAlbum(InAlbum4Store.albumName)
							.getPhoto(InAlbum4Store.PhotoField.getText()) != null) {

						addPhotoError();

					} else {
						if (PhotoAlbum.backend
								.getUser(Login1State.user)
								.photoExists(InAlbum4Store.PhotoField.getText())) {

							PhotoAlbum.backend
									.getUser(Login1State.user)
									.getPhoto(
											InAlbum4Store.PhotoField.getText())
									.setCount(
											PhotoAlbum.backend
													.getUser(Login1State.user)
													.getPhoto(
															InAlbum4Store.PhotoField
																	.getText())
													.getCount() + 1);

							// add photo to album
							PhotoAlbum.backend
									.getUser(Login1State.user)
									.getAlbum(InAlbum4Store.albumName)
									.addPhoto(
											PhotoAlbum.backend.getUser(
													Login1State.user).getPhoto(
													InAlbum4Store.PhotoField
															.getText()));

						} else {
							photo newPhoto = new photo(InAlbum4Store.PhotoField
									.getText(), InAlbum4Store.CaptionField
									.getText());
							System.out.println(PhotoAlbum.backend.getUser(
									Login1State.user).addUserPhoto(
									InAlbum4Store.PhotoField.getText(),
									InAlbum4Store.CaptionField.getText()));
							PhotoAlbum.backend.getUser(Login1State.user)
									.getAlbum(InAlbum4Store.albumName)
									.addPhoto(newPhoto);
							PhotoAlbum.backend
									.getUser(Login1State.user)
									.getPhoto(
											InAlbum4Store.PhotoField.getText())
									.setCount(1);

							InAlbum4State.instance = null;
							PhotoAlbumStore.inalbum4State.enter();
						}

					}
				} else {
					addPhotoError();
				}
			}

		});

		// Event listener for Delete Photo Button
		InAlbum4Store.DeletePhotoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (photo p : PhotoAlbum.backend.getUser(Login1State.user)
						.getUserPhotos()) {
					System.out.println(p + " " + p.getCount());

				}

				PhotoAlbum.backend
						.getUser(Login1State.user)
						.getPhoto(getSelectedPhoto())
						.setCount(
								PhotoAlbum.backend.getUser(Login1State.user)
										.getPhoto(getSelectedPhoto())
										.getCount() - 1);

				System.out.println(PhotoAlbum.backend.getUser(Login1State.user)
						.getPhoto(getSelectedPhoto()));

				if (PhotoAlbum.backend.getUser(Login1State.user)
						.getPhoto(getSelectedPhoto()).getCount() == 0) {
					PhotoAlbum.backend
							.getUser(Login1State.user)
							.getUserPhotos()
							.remove(PhotoAlbum.backend
									.getUser(Login1State.user).getPhoto(
											getSelectedPhoto()));

				}

				PhotoAlbum.backend
						.getUser(Login1State.user)
						.getAlbum(InAlbum4Store.albumName)
						.removePhoto(
								PhotoAlbum.backend.getUser(Login1State.user)
										.getAlbum(InAlbum4Store.albumName)
										.getPhoto(getSelectedPhoto()));

				InAlbum4State.instance = null;
				PhotoAlbumStore.inalbum4State.enter();

			}

		});

		// Event listener for Move Photo Button
		InAlbum4Store.MovePhotoButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InAlbum4Store.MovePhotoButton.setEnabled(false);
				InAlbum4Store.DeletePhotoButton.setEnabled(false);
				InAlbum4Store.AddPhotoButton.setEnabled(false);

				InAlbum4Store.DestAlbums = PhotoAlbum.backend.getUser(
						Login1State.user).getAlbums();
				InAlbum4Store.DestAlbumsBox = new JComboBox<album>();

				if (!InAlbum4Store.DestAlbums.isEmpty()) {
					for (album a : InAlbum4Store.DestAlbums) {
						InAlbum4Store.DestAlbumsBox.addItem(a);
						;
					}
				}

				InAlbum4Store.filgbc = new GridBagConstraints();
				InAlbum4Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				InAlbum4Store.filgbc.weightx = 1;
				InAlbum4Store.filgbc.gridwidth = 2;
				InAlbum4Store.filgbc.gridx = 0;
				InAlbum4Store.filgbc.gridy = 0;
				InAlbum4Store.Dest = new JLabel("New Destination for photo:");
				InAlbum4Store.Dest.setVisible(true);
				InAlbum4Store.fillerPanel.add(InAlbum4Store.Dest,
						InAlbum4Store.filgbc);
				// Create 'ComboBox' Button

				// InAlbum4Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				InAlbum4Store.filgbc.weightx = 1;
				InAlbum4Store.filgbc.gridwidth = 2;
				InAlbum4Store.filgbc.gridx = 0;
				InAlbum4Store.filgbc.gridy = 1;
				InAlbum4Store.DestAlbumsBox.setSelectedIndex(-1);
				InAlbum4Store.DestAlbumsBox.setVisible(true);

				InAlbum4Store.fillerPanel.add(InAlbum4Store.DestAlbumsBox,
						InAlbum4Store.filgbc);

				// Create 'Move' Button
				InAlbum4Store.filgbc.weighty = 1;
				InAlbum4Store.filgbc.weightx = .5;
				InAlbum4Store.filgbc.gridx = 0;
				InAlbum4Store.filgbc.gridwidth = 1;
				InAlbum4Store.filgbc.gridy = 2;
				InAlbum4Store.Move.setVisible(true);
				InAlbum4Store.Move.setEnabled(false);
				InAlbum4Store.fillerPanel.add(InAlbum4Store.Move,
						InAlbum4Store.filgbc);

				// Create 'Cancel' Button
				InAlbum4Store.filgbc.weighty = 1;
				InAlbum4Store.filgbc.weightx = .5;
				InAlbum4Store.filgbc.gridx = 1;
				InAlbum4Store.filgbc.gridwidth = 1;
				InAlbum4Store.filgbc.gridy = 2;
				InAlbum4Store.CancelMove.setVisible(true);
				InAlbum4Store.fillerPanel.add(InAlbum4Store.CancelMove,
						InAlbum4Store.filgbc);

				// Create whitespace filler panel
				InAlbum4Store.fillerbottom = new JPanel();
				InAlbum4Store.filgbc.gridy = 2;
				InAlbum4Store.filgbc.gridx = 0;
				InAlbum4Store.filgbc.weightx = 1;
				InAlbum4Store.filgbc.weighty = 1;
				InAlbum4Store.filgbc.gridwidth = 2;
				InAlbum4Store.fillerbottom.setPreferredSize(new Dimension(190,
						350));
				InAlbum4Store.filgbc.fill = GridBagConstraints.BOTH;
				InAlbum4Store.fillerPanel.add(InAlbum4Store.fillerbottom,
						InAlbum4Store.filgbc);

				InAlbum4Store.DestAlbumsBox
						.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								InAlbum4Store.Move.setEnabled(true);
								InAlbum4Store.Destination = InAlbum4Store.DestAlbumsBox
										.getSelectedItem().toString();

							}

						});

				InAlbum4Store.Move.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (PhotoAlbum.backend.getUser(Login1State.user)
								.getAlbum(InAlbum4Store.Destination)
								.getPhoto(getSelectedPhoto()) == null) {

							PhotoAlbum.backend
									.getUser(Login1State.user)
									.getAlbum(InAlbum4Store.Destination)
									.addPhoto(
											PhotoAlbum.backend
													.getUser(Login1State.user)
													.getAlbum(
															InAlbum4Store.Destination)
													.getPhoto(
															getSelectedPhoto()));

							PhotoAlbum.backend
									.getUser(Login1State.user)
									.getAlbum(InAlbum4Store.albumName)
									.removePhoto(
											PhotoAlbum.backend
													.getUser(Login1State.user)
													.getAlbum(
															InAlbum4Store.albumName)
													.getPhoto(
															getSelectedPhoto()));
							InAlbum4State.instance = null;
							PhotoAlbumStore.inalbum4State.enter();

						} else {
							movePhotoError();

						}

					}

				});

				InAlbum4Store.CancelMove
						.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								if (panelSelected()) {
									InAlbum4Store.MovePhotoButton
											.setEnabled(true);
									InAlbum4Store.DeletePhotoButton
											.setEnabled(true);
								} else {
									InAlbum4Store.MovePhotoButton
											.setEnabled(false);
									InAlbum4Store.DeletePhotoButton
											.setEnabled(false);
								}

								InAlbum4Store.MoveErrLabel.setVisible(false);
								InAlbum4Store.DestAlbumsBox.setVisible(false);
								InAlbum4Store.Dest.setVisible(false);
								InAlbum4Store.CancelMove.setVisible(false);
								InAlbum4Store.Move.setVisible(false);
								InAlbum4Store.AddPhotoButton.setEnabled(true);
								InAlbum4Store.fillerbottom.setVisible(false);

							}

						});

				InAlbum4Store.pa.revalidate();
				InAlbum4Store.pa.repaint();

			}
		});

	}

	private void addPhotoError() {
		InAlbum4Store.AddErrLabel.setVisible(true);
		InAlbum4Store.AddErrLabel.setForeground(Color.red);
		InAlbum4Store.bgbc.gridy = 2;
		InAlbum4Store.bgbc.gridx = 0;
		InAlbum4Store.bgbc.weighty = 1;
		InAlbum4Store.bgbc.weightx = 1;
		InAlbum4Store.bgbc.fill = GridBagConstraints.BOTH;
		InAlbum4Store.bgbc.gridwidth = 3;
		InAlbum4Store.ButtonsPanel.add(InAlbum4Store.AddErrLabel,
				InAlbum4Store.bgbc);

		InAlbum4Store.pa.revalidate();
		InAlbum4Store.pa.repaint();
	}

	private void movePhotoError() {
		InAlbum4Store.MoveErrLabel.setVisible(true);
		InAlbum4Store.MoveErrLabel.setForeground(Color.red);
		InAlbum4Store.bgbc.gridy = 2;
		InAlbum4Store.bgbc.gridx = 0;
		InAlbum4Store.bgbc.weighty = 1;
		InAlbum4Store.bgbc.weightx = 1;
		InAlbum4Store.bgbc.fill = GridBagConstraints.BOTH;
		InAlbum4Store.bgbc.gridwidth = 3;
		InAlbum4Store.ButtonsPanel.add(InAlbum4Store.MoveErrLabel,
				InAlbum4Store.bgbc);

		InAlbum4Store.pa.revalidate();
		InAlbum4Store.pa.repaint();
	}

	public boolean panelSelected() {

		for (JPanel j : InAlbum4Store.PhotosArray) {
			if (j.getBackground() == Color.LIGHT_GRAY) {
				return true;
			}
		}

		return false;
	}

	private String getSelectedPhoto() {
		for (JPanel j : InAlbum4Store.PhotosArray) {
			if (j.getBackground() == Color.LIGHT_GRAY) {
				System.out.println();
				return ((JLabel) j.getComponent(1)).getText();
			}
		}
		;
		return null;
	}

	public static InAlbum4State getInstance() {
		if (instance == null) {
			instance = new InAlbum4State();
		}
		return instance;
	}

	PhotoAlbumState processEvent() {
		// TODO Auto-generated method stub
		return null;
	}
}
