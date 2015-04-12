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
import java.io.File;
import java.io.IOException;

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

public class Results8State extends PhotoAlbumState{

	@Override
	void enter() {
		// Grab current JFrame and remove all the things
				Frame[] frames = Frame.getFrames();

				Results8Store.pa = (PhotoAlbum) frames[0];

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
			Results8Store.filgbc.gridy = 1;
			Results8Store.fillerPanel.add(Results8Store.NewAlbum,
					Results8Store.filgbc);

			// Create PhotoName field
			Results8Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
			Results8Store.filgbc.gridx = 0;
			Results8Store.filgbc.gridy = 1;
			Results8Store.AlbumName.setSize(new Dimension(50, 20));
			Results8Store.fillerPanel.add(Results8Store.AlbumName,
					Results8Store.filgbc);

			// Create 'Create' field
			Results8Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
			Results8Store.filgbc.weightx = .5;
			Results8Store.filgbc.gridwidth = 1;
			Results8Store.filgbc.gridx = 0;
			Results8Store.filgbc.gridy = 2;
			Results8Store.CreateAlbum.setVisible(true);
			Results8Store.fillerPanel.add(Results8Store.CreateAlbum,
					Results8Store.filgbc);

			// Create 'cancle' Button
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
					350));
			Results8Store.filgbc.fill = GridBagConstraints.BOTH;
			Results8Store.fillerPanel.add(Results8Store.fillerbottom,
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
			
			Results8Store.CancelPhoto.setVisible(false);
			Results8Store.AddPhoto.setVisible(false);
			Results8Store.AddPhotoButton.setEnabled(true);
			Results8Store.fillerbottom.setVisible(false);

		}
	});

	// Event listener for addPhoto Button
	Results8Store.AddPhoto.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			// Results8Store.MoveErrLabel.setVisible(false);
			File f = new File(Results8Store.PhotoField.getText());
			if (f.exists() && !f.isDirectory()) {
			

				try {
					if (PhotoAlbum.backend.getUser(Login1State.user)
							.getAlbum(Results8Store.albumName)
							.getPhoto(f.getCanonicalPath()) != null) {

						addPhotoError();

					} else {
						if (PhotoAlbum.backend
								.getUser(Login1State.user)
								.photoExists(f.getCanonicalPath())) {

							PhotoAlbum.backend
									.getUser(Login1State.user)
									.getPhoto(
											f.getCanonicalPath())
									.setCount(
											PhotoAlbum.backend
													.getUser(Login1State.user)
													.getPhoto(
															f.getCanonicalPath())
													.getCount() + 1);

							// add photo to album
							PhotoAlbum.backend
									.getUser(Login1State.user)
									.getAlbum(Results8Store.albumName)
									.addPhoto(
											PhotoAlbum.backend.getUser(
													Login1State.user).getPhoto(
													Results8Store.PhotoField
															.getText()));
							InAlbum4State.instance = null;
							PhotoAlbumStore.inalbum4State.enter();

						} else {
							photo newPhoto = new photo(f.getCanonicalPath(), Results8Store.CaptionField
									.getText());
							System.out.println(PhotoAlbum.backend.getUser(
									Login1State.user).addUserPhoto(
									f.getCanonicalPath(),
									Results8Store.CaptionField.getText()));
							PhotoAlbum.backend.getUser(Login1State.user)
									.getAlbum(Results8Store.albumName).addPhoto(newPhoto);
							PhotoAlbum.backend
							.getUser(Login1State.user)
							.getPhoto(
									f.getCanonicalPath())
							.setCount(1);

							InAlbum4State.instance = null;
							PhotoAlbumStore.inalbum4State.enter();
						}

					}
				} catch (IOException e1) {
					addPhotoError();
					e1.printStackTrace();
				}
			}else{
				addPhotoError();
			}
		}

	});

	// Event listener for Delete Photo Button
	Results8Store.DeletePhotoButton.addActionListener(new ActionListener() {
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
					.getAlbum(Results8Store.albumName)
					.removePhoto(
							PhotoAlbum.backend.getUser(Login1State.user)
									.getAlbum(Results8Store.albumName)
									.getPhoto(getSelectedPhoto()));

			InAlbum4State.instance = null;
			PhotoAlbumStore.inalbum4State.enter();

		}

	});

	// Event listener for Move Photo Button
	Results8Store.MovePhotoButton.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			Results8Store.MovePhotoButton.setEnabled(false);
			Results8Store.DeletePhotoButton.setEnabled(false);
			Results8Store.AddPhotoButton.setEnabled(false);
			
			Results8Store.DestAlbums = PhotoAlbum.backend.getUser(
					Login1State.user).getAlbums();
			Results8Store.DestAlbumsBox = new JComboBox<album>();

			if (!Results8Store.DestAlbums.isEmpty()) {
				for (album a : Results8Store.DestAlbums) {
					Results8Store.DestAlbumsBox.addItem(a);
					;
				}
			}

			Results8Store.filgbc = new GridBagConstraints();
			Results8Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
			Results8Store.filgbc.weightx = 1;
			Results8Store.filgbc.gridwidth = 2;
			Results8Store.filgbc.gridx = 0;
			Results8Store.filgbc.gridy = 0;
			Results8Store.Dest = new JLabel("New Destination for photo:");
			Results8Store.Dest.setVisible(true);
			Results8Store.fillerPanel.add(Results8Store.Dest,
					Results8Store.filgbc);
			// Create 'ComboBox' Button

			// Results8Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
			Results8Store.filgbc.weightx = 1;
			Results8Store.filgbc.gridwidth = 2;
			Results8Store.filgbc.gridx = 0;
			Results8Store.filgbc.gridy = 1;
			Results8Store.DestAlbumsBox.setSelectedIndex(-1);
			Results8Store.DestAlbumsBox.setVisible(true);

			Results8Store.fillerPanel.add(Results8Store.DestAlbumsBox,
					Results8Store.filgbc);

			// Create 'Move' Button
			Results8Store.filgbc.weighty = 1;
			Results8Store.filgbc.weightx = .5;
			Results8Store.filgbc.gridx = 0;
			Results8Store.filgbc.gridwidth = 1;
			Results8Store.filgbc.gridy = 2;
			Results8Store.Move.setVisible(true);
			Results8Store.Move.setEnabled(false);
			Results8Store.fillerPanel.add(Results8Store.Move,
					Results8Store.filgbc);

			// Create 'Cancel' Button
			Results8Store.filgbc.weighty = 1;
			Results8Store.filgbc.weightx = .5;
			Results8Store.filgbc.gridx = 1;
			Results8Store.filgbc.gridwidth = 1;
			Results8Store.filgbc.gridy = 2;
			Results8Store.CancelMove.setVisible(true);
			Results8Store.fillerPanel.add(Results8Store.CancelMove,
					Results8Store.filgbc);

			// Create whitespace filler panel
			Results8Store.fillerbottom = new JPanel();
			Results8Store.filgbc.gridy = 2;
			Results8Store.filgbc.gridx = 0;
			Results8Store.filgbc.weightx = 1;
			Results8Store.filgbc.weighty = 1;
			Results8Store.filgbc.gridwidth = 2;
			Results8Store.fillerbottom.setPreferredSize(new Dimension(190,
					350));
			Results8Store.filgbc.fill = GridBagConstraints.BOTH;
			Results8Store.fillerPanel.add(Results8Store.fillerbottom,
					Results8Store.filgbc);

			Results8Store.DestAlbumsBox
					.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Results8Store.Move.setEnabled(true);
							Results8Store.Destination = Results8Store.DestAlbumsBox
									.getSelectedItem().toString();

						}

					});

			Results8Store.Move.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (PhotoAlbum.backend.getUser(Login1State.user)
							.getAlbum(Results8Store.Destination)
							.getPhoto(getSelectedPhoto()) == null) {

						PhotoAlbum.backend
								.getUser(Login1State.user)
								.getAlbum(Results8Store.Destination)
								.addPhoto(
										PhotoAlbum.backend
												.getUser(Login1State.user)
												.getAlbum(
														Results8Store.albumName)
												.getPhoto(
														getSelectedPhoto()));

						PhotoAlbum.backend
								.getUser(Login1State.user)
								.getAlbum(Results8Store.albumName)
								.removePhoto(
										PhotoAlbum.backend
												.getUser(Login1State.user)
												.getAlbum(Results8Store.albumName)
												.getPhoto(
														getSelectedPhoto()));
						InAlbum4State.instance = null;
						PhotoAlbumStore.inalbum4State.enter();

					} else {
						movePhotoError();

					}
					
				
				}

			});

			Results8Store.CancelMove
					.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							if (panelSelected()) {
								Results8Store.MovePhotoButton
										.setEnabled(true);
								Results8Store.DeletePhotoButton
										.setEnabled(true);
							} else {
								Results8Store.MovePhotoButton
										.setEnabled(false);
								Results8Store.DeletePhotoButton
										.setEnabled(false);
							}

							Results8Store.MoveErrLabel.setVisible(false);
							Results8Store.DestAlbumsBox.setVisible(false);
							Results8Store.Dest.setVisible(false);
							Results8Store.CancelMove.setVisible(false);
							Results8Store.Move.setVisible(false);
							Results8Store.AddPhotoButton.setEnabled(true);
							Results8Store.fillerbottom.setVisible(false);

						}

					});

			Results8Store.pa.revalidate();
			Results8Store.pa.repaint();

		}
	});

}

	@Override
	PhotoAlbumState processEvent() {
		// TODO Auto-generated method stub
		return null;
	}

}
