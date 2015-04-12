package cs213.photoAlbum.GUIViewStates;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs213.photoAlbum.model.album;
import cs213.photoAlbum.model.photo;
import cs213.photoAlbum.model.tag;

public class Album5State extends PhotoAlbumState {

	static Album5State instance = null;

	@Override
	void enter() {

		// Grab current JFrame and remove all the things
		Frame[] frames = Frame.getFrames();
		Album5Store.pa = (PhotoAlbum) frames[0];
		Album5Store.pa.getContentPane().removeAll();
		Album5Store.pa.getContentPane().repaint();
		Album5Store.pa.getContentPane().revalidate();

		// Create constraints, add to main panel
		Album5Store.gbl = new GridBagLayout();
		Album5Store.gbc = new GridBagConstraints();
		Album5Store.MainPanel = new JPanel();
		Album5Store.MainPanel.setLayout(Album5Store.gbl);

		// Create top Menu Bar
		JPanel MenuBar = new MenuBarPanel();
		MenuBar = (JPanel) ((MenuBarPanel) MenuBar).CreateMenuBarPanel("Photo");
		Album5Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		Album5Store.gbc.gridx = 0;
		Album5Store.gbc.gridy = 0;
		Album5Store.gbc.weightx = 1;
		Album5Store.gbc.gridwidth = 2;
		Album5Store.MainPanel.add(MenuBar, Album5Store.gbc);

		// Add Outer Photo Panel in Main Panel
		Album5Store.OuterPhotoPanel = new JPanel();
		Album5Store.gbc.gridx = 0;
		Album5Store.gbc.gridy = 1;
		Album5Store.gbc.gridwidth = 1;
		Album5Store.gbc.weightx = .75;
		Album5Store.OuterPhotoPanel.setBorder(new EtchedBorder());
		Album5Store.OuterPhotoPanel.setVisible(true);
		Album5Store.OuterPhotoPanel.setPreferredSize(new Dimension(200, 545));
		Album5Store.MainPanel.add(Album5Store.OuterPhotoPanel, Album5Store.gbc);
		// Add tags panel to main panel
		Album5Store.TagsPanel = new JPanel();
		Album5Store.gbc.gridx = 1;
		Album5Store.gbc.gridy = 1;
		Album5Store.gbc.gridwidth = 1;
		Album5Store.gbc.weightx = .25;
		Album5Store.TagsPanel.setBorder(new EtchedBorder());
		Album5Store.TagsPanel.setVisible(true);
		Album5Store.TagsPanel.setPreferredSize(new Dimension(200, 545));
		Album5Store.MainPanel.add(Album5Store.TagsPanel, Album5Store.gbc);

		// Set Photo Panel grid constraints
		Album5Store.ppgbc = new GridBagConstraints();
		Album5Store.ppgbc.gridx = 0;
		Album5Store.ppgbc.gridy = 0;

		// Add left button to OuterPhotoPanel
		Album5Store.leftButton = new JButton("<<");
		Album5Store.leftButton.setPreferredSize(new Dimension(60, 160));
		Album5Store.OuterPhotoPanel.add(Album5Store.leftButton,
				Album5Store.ppgbc);

		// Store a current Photo, so we know when we are flipping through photos
		//System.out.println(Album5Store.currentPhoto);
		Album5Store.photo = Album5Store.currentPhoto.getPhoto();

		// Set image size to fit PhotoPanel if very large
		if (Album5Store.photo.getIconHeight() > 400
				|| Album5Store.photo.getIconWidth() > 400) {
			Image image = Album5Store.photo.getImage();
			Image newimg = image.getScaledInstance(400, 400,
					java.awt.Image.SCALE_SMOOTH);
			Album5Store.photo = new ImageIcon(newimg);
		}

		Album5Store.ImageLabel = new JLabel("", Album5Store.photo,
				JLabel.CENTER);

		// Create image panel with photo
		Album5Store.ImagePanel = new JPanel();
		Album5Store.ImagePanel.setLayout(new BorderLayout());
		Album5Store.ImagePanel.setPreferredSize(new Dimension(400, 400));
		Album5Store.ImagePanel.setBorder(new EtchedBorder());
		Album5Store.ImagePanel.add(Album5Store.ImageLabel, BorderLayout.CENTER);
		Album5Store.ppgbc.gridx = 1;
		Album5Store.ppgbc.gridy = 0;
		Album5Store.OuterPhotoPanel.add(Album5Store.ImagePanel,
				Album5Store.ppgbc);

		// Add rightButton to OuterPhotoPanel
		Album5Store.rightButton = new JButton(">>");
		Album5Store.ppgbc.gridx = 2;
		Album5Store.ppgbc.gridy = 0;
		Album5Store.rightButton.setPreferredSize(new Dimension(60, 160));
		Album5Store.OuterPhotoPanel.add(Album5Store.rightButton,
				Album5Store.ppgbc);

		// Set visibility of left and right buttons depending on where you are
		// in the album
		if (Album5Store.currentAlbum.getPhotos().indexOf(
				Album5Store.currentPhoto) == 0) {
			Album5Store.leftButton.setEnabled(false);
		} else if (Album5Store.currentAlbum.getPhotos().indexOf(
				Album5Store.currentPhoto) == Album5Store.currentAlbum
				.getPhotos().size() - 1) {
			Album5Store.rightButton.setEnabled(false);
		}

		// Add ImageInfoPanel underneath the photo
		Album5Store.ImageInfoPanel = new JPanel();
		Album5Store.ppgbc.gridx = 0;
		Album5Store.ppgbc.gridwidth = 3;
		Album5Store.ppgbc.gridy = 1;
		Album5Store.ImageInfoPanel.setPreferredSize(new Dimension(400, 127));
		Album5Store.ImageInfoPanel.setBorder(new EtchedBorder());
		Album5Store.OuterPhotoPanel.add(Album5Store.ImageInfoPanel,
				Album5Store.ppgbc);

		// Add content to ImageInfoPanel
		Album5Store.iigbl = new GridBagLayout();
		Album5Store.iigbc = new GridBagConstraints();
		Album5Store.iigbc.gridx = 0;
		Album5Store.iigbc.gridy = 0;
		Album5Store.iigbc.gridwidth = 1;
		Font font = new Font("Garamond", Font.BOLD, 15);
		Album5Store.caption = new JLabel(Album5Store.currentPhoto.getCaption(),
				SwingConstants.CENTER);
		// Album5Store.caption.setBorder(new EtchedBorder());
		Album5Store.caption.setPreferredSize(new Dimension(330, 25));
		Album5Store.caption.setFont(font);
		Album5Store.ImageInfoPanel.add(Album5Store.caption, Album5Store.iigbc);
		Album5Store.recaptionButton = new JButton("Recaption");
		Album5Store.iigbc.gridx = 0;
		Album5Store.iigbc.gridy = 1;
		Album5Store.iigbc.gridwidth = 2;

		Album5Store.ImageInfoPanel.add(Album5Store.recaptionButton,
				Album5Store.iigbc);
		Album5Store.iigbc.gridx = 0;
		Album5Store.iigbc.gridwidth = 2;
		Album5Store.iigbc.gridy = 2;
		Album5Store.date = new JLabel("Date: --", SwingConstants.CENTER);
		Album5Store.date.setPreferredSize(new Dimension(330, 25));
		Album5Store.ImageInfoPanel.add(Album5Store.date, Album5Store.iigbc);

		// Create JPanel for tags and constraints
		Album5Store.tgbl = new GridBagLayout();
		Album5Store.tgbc = new GridBagConstraints();
		Album5Store.TagsPanel.setLayout(Album5Store.tgbl);

		// Add tag label
		Album5Store.tagsLabel = new JLabel("Tags:", SwingConstants.CENTER);
		Album5Store.tgbc.gridx = 0;
		Album5Store.tgbc.gridy = 0;
		Album5Store.tgbc.gridwidth = 2;
		Album5Store.TagsPanel.add(Album5Store.tagsLabel, Album5Store.tgbc);

		// Add tag JScrollPane
		Album5Store.TagsArr = new ArrayList<String>();
		for (tag t : Album5Store.currentPhoto.getTag()) {
			Album5Store.TagsArr.add(t.getType() + ": " + t.getValue());
		}

		Album5Store.list = new JList(Album5Store.TagsArr.toArray());
		Album5Store.scrollpane = new JScrollPane(Album5Store.list);
		Album5Store.scrollpane.setPreferredSize(new Dimension(270, 200));
		Album5Store.scrollpane.setVisible(true);
		Album5Store.tags = new JPanel();
		// Album5Store.tags.setBorder(new EtchedBorder());
		Album5Store.tags.setPreferredSize(new Dimension(280, 200));
		Album5Store.tgbc.gridx = 0;
		Album5Store.tgbc.gridy = 1;
		Album5Store.tgbc.gridwidth = 2;
		Album5Store.scrollpane.revalidate();
		Album5Store.tags.add(Album5Store.scrollpane, BorderLayout.CENTER);
		Album5Store.TagsPanel.add(Album5Store.tags, Album5Store.tgbc);

		// Add the tag buttons below the scrollpane
		Album5Store.tagButtonPanel = new JPanel();
		// Album5Store.tagButtonPanel.setBorder(new EtchedBorder());

		Album5Store.addTagButton = new JButton("Add Tag");
		Album5Store.addTagButton.setPreferredSize(new Dimension(120, 30));
		Album5Store.tgbc.gridx = 0;
		Album5Store.tgbc.gridy = 2;
		Album5Store.tgbc.gridwidth = 1;

		Album5Store.tagButtonPanel.add(Album5Store.addTagButton);

		// Add the remove tag button
		Album5Store.removeTagButton = new JButton("Remove Tag");
		Album5Store.removeTagButton.setEnabled(false);
		Album5Store.removeTagButton.setPreferredSize(new Dimension(120, 30));
		Album5Store.tgbc.gridx = 0;
		Album5Store.tgbc.gridy = 2;
		Album5Store.tgbc.gridwidth = 2;

		Album5Store.tagButtonPanel.add(Album5Store.removeTagButton);

		Album5Store.TagsPanel.add(Album5Store.tagButtonPanel, Album5Store.tgbc);

		// Add filler panel under tag buttons
		Album5Store.fillerPanel = new JPanel();
		Album5Store.tgbc.gridx = 1;
		Album5Store.tgbc.gridy = 3;
		Album5Store.tgbc.gridwidth = 1;
		Album5Store.fillerPanel.setPreferredSize(new Dimension(270, 250));
		Album5Store.fillerPanel.setBorder(new EtchedBorder());
		Album5Store.TagsPanel.add(Album5Store.fillerPanel, Album5Store.tgbc);

		AddActionListeners();

		// Add the main panel to the Photo Album object
		Album5Store.pa.add(Album5Store.MainPanel, Album5Store.gbc);
		Album5Store.pa.revalidate();
		Album5Store.pa.repaint();

	}

	public void AddActionListeners() {

		Album5Store.saveButton = new JButton("Save");
		Album5Store.cancelButton = new JButton("Cancel");
		Album5Store.submitTag = new JButton("Submit");
		Album5Store.cancelTag = new JButton("Cancel");
		Album5Store.newCaptionField = new JTextField();

		// Recaption listener
		Album5Store.recaptionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Clear current things in info panel & disable buttons
				Album5Store.ImageInfoPanel.removeAll();
				Album5Store.leftButton.setEnabled(false);
				Album5Store.rightButton.setEnabled(false);

				// Create new text field for user to enter new name
				Album5Store.newCaptionField.setPreferredSize(new Dimension(330,
						25));
				Album5Store.iigbc.gridx = 0;
				Album5Store.iigbc.gridy = 0;
				Album5Store.ImageInfoPanel.add(Album5Store.newCaptionField,
						Album5Store.iigbc);

				// Add Save button to Info Panel
				Album5Store.iigbc.gridx = 1;
				Album5Store.iigbc.gridy = 0;
				Album5Store.saveButton.setEnabled(false);
				Album5Store.ImageInfoPanel.add(Album5Store.saveButton,
						Album5Store.iigbc);

				// Add cancel button to InfoPanel
				Album5Store.iigbc.gridx = 2;
				Album5Store.iigbc.gridy = 0;
				Album5Store.ImageInfoPanel.add(Album5Store.cancelButton,
						Album5Store.iigbc);

				Album5Store.iigbc.gridx = 0;
				Album5Store.iigbc.gridy = 1;
				Album5Store.date = new JLabel("Date: --", SwingConstants.CENTER);
				Album5Store.date.setPreferredSize(new Dimension(330, 25));
				Album5Store.ImageInfoPanel.add(Album5Store.date,
						Album5Store.iigbc);
				Album5Store.pa.revalidate();
				Album5Store.pa.repaint();
			}
		});

		// Add listener for cancel button. Goes back to original state
		Album5Store.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				instance = null;
				PhotoAlbumStore.album5State.enter();
			}
		});

		// Add listener for save button. Overwrites current caption, then resets
		Album5Store.saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				Album5Store.currentPhoto
				.setCaption(Album5Store.newCaptionField
						.getText());

				instance = null;
				PhotoAlbumStore.album5State.enter();
			}
		});

		// Action listener for when add Tag is clicked
		Album5Store.addTagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Set other buttons to not be enabled
				Album5Store.recaptionButton.setEnabled(false);
				Album5Store.addTagButton.setEnabled(false);
				Album5Store.removeTagButton.setEnabled(false);
				Album5Store.leftButton.setEnabled(false);
				Album5Store.rightButton.setEnabled(false);

				// Add tag type label
				Album5Store.fillerPanel.setLayout(new GridBagLayout());
				Album5Store.fgbc = new GridBagConstraints();
				Album5Store.fgbc.gridx = 0;
				Album5Store.fgbc.gridy = 0;
				Album5Store.tagType = new JLabel("Tag type:");
				Album5Store.fillerPanel.add(Album5Store.tagType,
						Album5Store.fgbc);

				// Add tag type field
				Album5Store.fgbc.gridx = 1;
				Album5Store.fgbc.gridy = 0;
				Album5Store.typeField = new JTextField();
				Album5Store.typeField.setPreferredSize(new Dimension(120, 30));
				Album5Store.fillerPanel.add(Album5Store.typeField,
						Album5Store.fgbc);

				// Add tag value label
				Album5Store.fgbc.gridx = 0;
				Album5Store.fgbc.gridy = 1;
				Album5Store.tagValue = new JLabel("Tag value:");
				Album5Store.fillerPanel.add(Album5Store.tagValue,
						Album5Store.fgbc);

				// Add tag value field
				Album5Store.fgbc.gridx = 1;
				Album5Store.fgbc.gridy = 1;
				Album5Store.valueField = new JTextField();
				Album5Store.valueField.setPreferredSize(new Dimension(120, 30));
				Album5Store.fillerPanel.add(Album5Store.valueField,
						Album5Store.fgbc);

				// Add submit button for tag
				Album5Store.submitTag = new JButton("Submit");
				Album5Store.fgbc.gridx = 0;
				Album5Store.fgbc.gridy = 2;
				Album5Store.fillerPanel.add(Album5Store.submitTag,
						Album5Store.fgbc);

				// Add cancel button for tag
				Album5Store.cancelTag = new JButton("Cancel");
				Album5Store.fgbc.gridx = 1;
				Album5Store.fgbc.gridy = 2;
				Album5Store.fillerPanel.add(Album5Store.cancelTag,
						Album5Store.fgbc);

				// Add listener for submitting Tag. Goes back to original state
				Album5Store.submitTag.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						tag t = new tag(Album5Store.typeField.getText(),
								Album5Store.valueField.getText());
						Album5Store.currentPhoto.getTag().add(t);

						instance = null;
						PhotoAlbumStore.album5State.enter();
					}
				});

				// Add listener for cancel Tag button.
				Album5Store.cancelTag.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						instance = null;
						PhotoAlbumStore.album5State.enter();
					}
				});

				Album5Store.pa.revalidate();
				Album5Store.pa.repaint();
			}
		});

		// Add listener for when when right button is pressed
		Album5Store.rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Create temp photo to find index of photo in currentAlbum

				int index = 0;

				for (photo p : Album5Store.currentAlbum.getPhotos()) {
					if (p.getFileName().equals(
							Album5Store.currentPhoto.getFileName())) {
						index = Album5Store.currentAlbum.getPhotos().indexOf(p);
					}
				}

				// Set current Photo to be the photo to the right of the current
				// Photo
				if (!(index + 1 > Album5Store.currentAlbum.getPhotos().size() - 1)) {
					Album5Store.currentPhoto = Album5Store.currentAlbum
							.getPhotos().get(index + 1);
				}

				instance = null;
				PhotoAlbumStore.album5State.enter();

			}
		});

		// Add listener for when when right button is pressed
		Album5Store.leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Create temp photo to find index of photo in currentAlbum

				int index = 0;

				for (photo p : Album5Store.currentAlbum.getPhotos()) {
					if (p.getFileName().equals(
							Album5Store.currentPhoto.getFileName())) {
						index = Album5Store.currentAlbum.getPhotos().indexOf(p);
					}
				}

				// Set current Photo to be the photo to the left of the current
				// Photo
				if (index - 1 >= 0) {
					Album5Store.currentPhoto = Album5Store.currentAlbum
							.getPhotos().get(index - 1);
				}

				instance = null;
				PhotoAlbumStore.album5State.enter();

			}
		});

		// Add listener for when removeTag is pressed
		Album5Store.removeTagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Album5Store.currentPhoto.getTag().remove(
						Album5Store.list.getSelectedIndex());

				instance = null;
				PhotoAlbumStore.album5State.enter();

			}
		});

		// Add listener for TagList to set removeTag button enabled or not
		Album5Store.list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting()) {

					if (Album5Store.list.isSelectionEmpty()) {
						Album5Store.removeTagButton.setEnabled(false);

					} else {
						Album5Store.removeTagButton.setEnabled(true);
					}
					return;
				}
			}
		});

		// Add key listener for newCaptionField for when text is entered
		Album5Store.newCaptionField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent event) {
				if (!Album5Store.newCaptionField.getText().equals("")) {
					Album5Store.saveButton.setEnabled(true);
				} else {
					Album5Store.saveButton.setEnabled(false);
				}
			}

			@Override
			public void keyReleased(KeyEvent event) {
				if (!Album5Store.newCaptionField.getText().equals("")) {
					Album5Store.saveButton.setEnabled(true);
				} else {
					Album5Store.saveButton.setEnabled(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent event) {
				if (!Album5Store.newCaptionField.getText().equals("")) {
					Album5Store.saveButton.setEnabled(true);
				} else {
					Album5Store.saveButton.setEnabled(false);
				}
			}
		});

	}

	public static Album5State getInstance() {
		if (instance == null) {
			instance = new Album5State();
		}
		return instance;
	}

	@Override
	PhotoAlbumState processEvent() {
		// TODO Auto-generated method stub
		return null;
	}

}