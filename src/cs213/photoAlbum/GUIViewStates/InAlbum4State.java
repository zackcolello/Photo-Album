package cs213.photoAlbum.GUIViewStates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EtchedBorder;

import cs213.photoAlbum.model.album;

public class InAlbum4State extends PhotoAlbumState {

	static InAlbum4State instance = null;



	public void enter() {
		// Grab current JFrame and remove all the things
		Frame[] frames = Frame.getFrames();
		
		InAlbum4Store.pa = (PhotoAlbum) frames[0];
		
		System.out.println(Login1State.user);
		InAlbum4Store.DestAlbums = PhotoAlbum.backend.getUser(Login1State.user).getAlbums();

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
		MenuBar =(JPanel) ((MenuBarPanel) MenuBar)
				.CreateMenuBarPanel("Photo");
		InAlbum4Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		InAlbum4Store.gbc.gridx = 0;
		InAlbum4Store.gbc.gridy = 0;
		InAlbum4Store.gbc.weightx = 1;
		InAlbum4Store.gbc.gridwidth = 2;
		InAlbum4Store.gbc.anchor=GridBagConstraints.NORTH;
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
		InAlbum4Store.photoPanel.setPreferredSize(new Dimension(50, 540));
		InAlbum4Store.photoPanel
		.add(InAlbum4Store.photoScroll, BorderLayout.CENTER);

		// Add photoPanel to mainPanel
		InAlbum4Store.gbc.gridx = 0;
		InAlbum4Store.gbc.gridy = 1;
		InAlbum4Store.gbc.weightx = .67;
		InAlbum4Store.gbc.gridwidth = 1;
		InAlbum4Store.photoPanel.setBorder(new EtchedBorder());
		InAlbum4Store.photoPanel.setVisible(true);

		InAlbum4Store.MainPanel.add(InAlbum4Store.photoPanel, InAlbum4Store.gbc);

		// Create Buttons Panel
		InAlbum4Store.ButtonsPanel = new JPanel();
		InAlbum4Store.ButtonsPanel.setLayout(InAlbum4Store.gbl);
		InAlbum4Store.ButtonsPanel.setPreferredSize(new Dimension(50, 540));
		InAlbum4Store.bgbc = new GridBagConstraints();
		InAlbum4Store.bgbl = new GridBagLayout();
		InAlbum4Store.ButtonsPanel.setLayout(InAlbum4Store.bgbl);

		// Add Album Button
		InAlbum4Store.AddPhotoButton = new JButton("Add Photo");
		InAlbum4Store.bgbc.gridx = 0;
		InAlbum4Store.bgbc.gridy = 0;
		InAlbum4Store.ButtonsPanel.add(InAlbum4Store.AddPhotoButton,
				InAlbum4Store.bgbc);

		// Rename Album Button
		InAlbum4Store.MovePhotoButton = new JButton("MOve Album");
		InAlbum4Store.bgbc.gridx = 1;
		InAlbum4Store.bgbc.gridy = 0;
		InAlbum4Store.bgbc.weightx = 0.6;
		InAlbum4Store.MovePhotoButton.setEnabled(true);
		InAlbum4Store.ButtonsPanel.add(InAlbum4Store.MovePhotoButton,
				InAlbum4Store.bgbc);

		// Delete Album Button
		InAlbum4Store.DeletePhotoButton = new JButton("Delete Album");
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
		InAlbum4Store.ButtonsPanel.add(InAlbum4Store.fillerPanel, InAlbum4Store.bgbc);

		// Add buttons panel to main panel
		InAlbum4Store.gbc.gridx = 1;
		InAlbum4Store.gbc.gridy = 1;
		InAlbum4Store.gbc.weightx = .33;
		InAlbum4Store.gbc.weighty = 1;
		InAlbum4Store.photoPanel.setBorder(new EtchedBorder());
		InAlbum4Store.photoPanel.setVisible(true);
		InAlbum4Store.MainPanel.add(InAlbum4Store.ButtonsPanel, InAlbum4Store.gbc);

		// Add MainPanel to pa
		InAlbum4Store.pa.add(InAlbum4Store.MainPanel, InAlbum4Store.gbc);

		// Build the list of photo Albums to put in the photoPanel list
		//fillphotoPanel();

		// Add all the listeners
		addListeners();

	}



	public void addListeners(){

		InAlbum4Store.CancelPhoto = new JButton("Cancel");
		InAlbum4Store.AddPhoto = new JButton("Add");
		InAlbum4Store.CancelMove = new JButton("Cancel");
		InAlbum4Store.Move = new JButton("Move");
		InAlbum4Store.PhotoField = new JTextField();
		InAlbum4Store.CaptionField = new JTextField();
		//InAlbum4Store.DestAlbums = new JComboBox<album>((

		// Event listener for Add Album Button
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
				InAlbum4Store.PhotoField.setVisible(true);
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
				InAlbum4Store.PhotoField.setSize(new Dimension(50, 20));
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

				// Event listener for when text is entered into AlbumField
				InAlbum4Store.PhotoField.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent event) {
						if ((!InAlbum4Store.PhotoField.getText().equals(""))&&(!InAlbum4Store.CaptionField.getText().equals(""))) {
							InAlbum4Store.AddPhoto.setEnabled(true);
						} else {
							InAlbum4Store.AddPhoto.setEnabled(false);
						}
					}

					@Override
					public void keyReleased(KeyEvent event) {
						if ((!InAlbum4Store.PhotoField.getText().equals(""))&&(!InAlbum4Store.CaptionField.getText().equals(""))) {
							InAlbum4Store.AddPhoto.setEnabled(true);
						} else {
							InAlbum4Store.AddPhoto.setEnabled(false);
						}
					}

					@Override
					public void keyPressed(KeyEvent event) {
						if ((!InAlbum4Store.PhotoField.getText().equals(""))&&(!InAlbum4Store.CaptionField.getText().equals(""))) {
							InAlbum4Store.AddPhoto.setEnabled(true);
						} else {
							InAlbum4Store.AddPhoto.setEnabled(false);
						}
					}
				});
				InAlbum4Store.CaptionField.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent event) {
						if ((!InAlbum4Store.PhotoField.getText().equals(""))&&(!InAlbum4Store.CaptionField.getText().equals(""))) {
							InAlbum4Store.AddPhoto.setEnabled(true);
						} else {
							InAlbum4Store.AddPhoto.setEnabled(false);
						}
					}

					@Override
					public void keyReleased(KeyEvent event) {
						if ((!InAlbum4Store.PhotoField.getText().equals(""))&&(!InAlbum4Store.CaptionField.getText().equals(""))) {
							InAlbum4Store.AddPhoto.setEnabled(true);
						} else {
							InAlbum4Store.AddPhoto.setEnabled(false);
						}
					}

					@Override
					public void keyPressed(KeyEvent event) {
						if ((!InAlbum4Store.PhotoField.getText().equals(""))&&(!InAlbum4Store.CaptionField.getText().equals(""))) {
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

		// Event listener for Cancel Album Button
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
				InAlbum4Store.AddErrLabel.setVisible(false);
				InAlbum4Store.PhotoField.setVisible(false);
				InAlbum4Store.PhotoName.setVisible(false);
				InAlbum4Store.CancelPhoto.setVisible(false);
				InAlbum4Store.AddPhoto.setVisible(false);
				InAlbum4Store.AddPhotoButton.setEnabled(true);
				InAlbum4Store.fillerbottom.setVisible(false);

			}
		});

		// Event listener for Create Album Button
		InAlbum4Store.AddPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*InAlbum4Store.MoveErrLabel.setVisible(false);

					if (PhotoAlbum.backend.getUser(Login1State.user).getAlbum(
							InAlbum4Store.AlbumField.getText()) != null) {

						addAlbumError();

					} else {

						PhotoAlbum.backend.getUser(Login1State.user).addAlbum(
								InAlbum4Store.AlbumField.getText());

						Album3State.instance = null;
						PhotoAlbumStore.album3State.enter();
					}
				 */
				System.out.println("click");

			}
		});

		/*		// Event listener for Delete Album Button
			InAlbum4Store.DeleteAlbumButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					PhotoAlbum.backend.getUser(Login1State.user).removeAlbum(
							getSelectedAlbum());

					Album3State.instance = null;
					PhotoAlbumStore.album3State.enter();

				}
			});
		 */
		// Event listener for Rename Album Button
		InAlbum4Store.MovePhotoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InAlbum4Store.DestAlbums= PhotoAlbum.backend.getUser(Login1State.user).getAlbums();
				InAlbum4Store.DestAlbumsBox = new JComboBox<album>();
				
				for(album a: InAlbum4Store.DestAlbums){
					InAlbum4Store.DestAlbumsBox.addItem(a);
				}
				InAlbum4Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				InAlbum4Store.filgbc.weightx = .5;
				InAlbum4Store.filgbc.gridwidth = 2;
				InAlbum4Store.filgbc.gridx = 0;
				InAlbum4Store.filgbc.gridy = 0;
				InAlbum4Store.Dest = new JLabel("New Destination for photo:");
				InAlbum4Store.Dest.setVisible(true);
				InAlbum4Store.fillerPanel.add(InAlbum4Store.Dest,
						InAlbum4Store.filgbc);
				// Create 'ComboBox' Button

				InAlbum4Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				InAlbum4Store.filgbc.weightx = .5;
				InAlbum4Store.filgbc.gridwidth = 2;
				InAlbum4Store.filgbc.gridx = 0;
				InAlbum4Store.filgbc.gridy = 1;
				InAlbum4Store.DestAlbumsBox.setVisible(true);

				InAlbum4Store.fillerPanel.add(InAlbum4Store.DestAlbumsBox,
						InAlbum4Store.filgbc);

				// Create 'Move' Button
				InAlbum4Store.filgbc.weighty = 1;
				InAlbum4Store.filgbc.weightx = .5;
				InAlbum4Store.filgbc.gridx = 0;
				InAlbum4Store.filgbc.gridwidth = 1;
				InAlbum4Store.filgbc.gridy = 1;
				InAlbum4Store.Move.setVisible(true);
				InAlbum4Store.fillerPanel.add(InAlbum4Store.Move,
						InAlbum4Store.filgbc);

				// Create 'Cancel' Button
				InAlbum4Store.filgbc.weighty = 1;
				InAlbum4Store.filgbc.weightx = .5;
				InAlbum4Store.filgbc.gridx = 1;
				InAlbum4Store.filgbc.gridwidth = 1;
				InAlbum4Store.filgbc.gridy = 1;
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
			}


		});
	}


public boolean panelSelected() {

	for (JPanel j : InAlbum4Store.PhotosArray) {
		if (j.getBackground() == Color.LIGHT_GRAY) {
			return true;
		}
	}

	return false;
}

public static InAlbum4State getInstance() {
	if (instance == null) {
		instance = new InAlbum4State();
	}
	return instance;
}



PhotoAlbumState processEvent() {
	if (instance == null) {
		instance = new InAlbum4State();
	}
	return instance;
}

}

