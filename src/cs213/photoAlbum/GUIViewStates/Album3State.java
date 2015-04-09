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
import java.util.List;

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

public class Album3State extends PhotoAlbumState {

	/**
	 * Singleton instance
	 */
	private static Album3State instance = null;

	JPanel MainPanel = new JPanel();
	JPanel LeftPanel = new JPanel();
	JPanel RightPanel = new JPanel();

	PhotoAlbum pa = new PhotoAlbum();

	boolean panelSelected = false;

	private GridBagLayout gbl = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();

	int columnCount = 0;
	int rowCount = 0;

	// Constructor
	public Album3State() {

	}

	// Called when state is entered, build state
	public void enter() {

		System.out.println("hi");
		
		// Grab current JFrame
		Frame[] frames = Frame.getFrames();
		pa = (PhotoAlbum) frames[0];

		// Clear items from that state
		pa.getContentPane().removeAll();
		pa.getContentPane().repaint();
		pa.getContentPane().revalidate();

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

		Album3Store.DeleteAlbumButton = new JButton("Delete Album");
		Album3Store.RenameAlbumButton = new JButton("Rename Album");

		// Set up album panel with album scroll pane

		JPanel albumPanel = new JPanel();

		GridBagLayout scrollL = new GridBagLayout();
		GridBagConstraints scrollc = new GridBagConstraints();

			JPanel innerPanel = new JPanel();

		innerPanel.setBorder(new EtchedBorder());
		innerPanel.setLayout(scrollL);
		JScrollPane albumScroll = new JScrollPane(innerPanel);
		albumScroll.setVisible(true);
		albumScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		albumScroll.setLayout(new ScrollPaneLayout());
		albumPanel.setLayout(new BorderLayout());
		albumPanel.setPreferredSize(new Dimension(300, 545));

		// Add elements 		
		List<FancyPanel> albumThumbnails = new ArrayList<FancyPanel>();



		for (album a : PhotoAlbum.backend.getUser(Login1State.user).getAlbums()) {

			FancyPanel temp = new FancyPanel();
			temp.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();

			temp.setPreferredSize(new Dimension(170, 200));
			temp.setBorder(new EtchedBorder());
			temp.setVisible(true);

			// mice
			temp.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {

					// resetColors();
					for (FancyPanel f : albumThumbnails) {

						f.setBackground(Color.WHITE);
						innerPanel.revalidate();
						innerPanel.repaint();
						
					}
					Album3Store.RenameAlbumButton.setEnabled(true);
					Album3Store.DeleteAlbumButton.setEnabled(true);


					temp.setBackground(Color.LIGHT_GRAY);
					innerPanel.revalidate();
					innerPanel.repaint();

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// setBackground(background);
				}
			});

			JLabel albumN = new JLabel(a.getName());

			// set photo in album
			if (a.getPhotos().size() == 0) {
				ImageIcon noPhoto = new ImageIcon("docs/NoPhotos.png");
				gbc.gridx = 0;
				gbc.gridy = 0;

				JLabel label = new JLabel("", noPhoto, JLabel.CENTER);
				temp.add(label, gbc);
			}
			gbc.gridx = 0;
			gbc.gridy = 1;
			temp.add(albumN, gbc);

			JLabel numPhotos = new JLabel("Number of photos: "
					+ Integer.toString(a.getPhotos().size()));
			gbc.gridx = 0;
			gbc.gridy = 2;
			temp.add(numPhotos, gbc);

			// Find earliest and latest
			ArrayList<photo> photoList = a.getPhotos();
			JLabel dates = new JLabel();

			if (photoList.size() == 0) {
				dates.setText("From: -- To: --");
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
				dates.setText("From: " + earliest + " To: " + latest);
			}

			gbc.gridx = 0;
			gbc.gridy = 3;
			temp.add(dates, gbc);

			albumThumbnails.add((FancyPanel) temp);
		}

		// makes sure we lay out 3 thumbnails per row

		for (JPanel j : albumThumbnails) {

			scrollc.gridx = columnCount;
			scrollc.gridy = rowCount;

			if (columnCount == 2) {
				rowCount += 1;
				columnCount = 0;
			} else {
				columnCount++;
			}
			innerPanel.add(j, scrollc);
		}

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
		Album3Store.AddAlbumButton = new JButton("Add Album");
		Album3Store.AddAlbumButton.setVisible(true);

		// AddAlbumButton.setPreferredSize(new Dimension(140, 40));
		buttongbc.gridx = 0;
		ButtonsPanel.add(AddAlbumButton, buttongbc);

		RenameAlbumButton.setVisible(true);
		RenameAlbumButton.setEnabled(false);
		// RenameAlbumButton.setPreferredSize(new Dimension(140, 40));
		buttongbc.gridx = 1;
		buttongbc.weightx = 0.5;
		ButtonsPanel.add(RenameAlbumButton, buttongbc);

		DeleteAlbumButton.setVisible(true);
		DeleteAlbumButton.setEnabled(false);

		// DeleteAlbumButton listener

		DeleteAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FancyPanel temp = new FancyPanel();

				for (FancyPanel f : albumThumbnails) {

					if (f.getBackground().equals(Color.LIGHT_GRAY)) {
						temp = f;
						break;
					}
				}

				

				// get jlabel with albumname
				Component[] components = temp.getComponents();		
					String text = ((JLabel) components[1]).getText();
				PhotoAlbum.backend.getUser(Login1State.user).removeAlbum(text);
				
				/////////////////////////////////////////////////////
				innerPanel.remove(temp);
				
				//////////////////////////////////////////////////////////////////////

				pa.revalidate();
				pa.repaint();

			}
		});

		// DeleteAlbumButton.setPreferredSize(new Dimension(140, 40));
		buttongbc.gridx = 2;
		buttongbc.weightx = 0.5;
		ButtonsPanel.add(DeleteAlbumButton, buttongbc);

		// add filler for under the buttons
		final JPanel fillerPanel = new JPanel();
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
				final JLabel AlbumName = new JLabel("Album Name:");
				fillerPanel.add(AlbumName, fillergbc);

				fillergbc.fill = GridBagConstraints.HORIZONTAL;
				fillergbc.weightx = .5;
				fillergbc.gridx = 1;
				fillergbc.gridy = 0;
				final JTextField AlbumField = new JTextField();
				AlbumField.setSize(new Dimension(50, 20));
				fillerPanel.add(AlbumField, fillergbc);

				fillergbc.fill = GridBagConstraints.HORIZONTAL;
				fillergbc.weightx = .5;
				fillergbc.gridwidth = 1;
				fillergbc.gridx = 0;
				fillergbc.gridy = 1;
				buttongbc.fill = GridBagConstraints.HORIZONTAL;
				final JButton CreateAlbum = new JButton("Create");
				CreateAlbum.setEnabled(false);
				fillerPanel.add(CreateAlbum, fillergbc);

				buttongbc.fill = GridBagConstraints.HORIZONTAL;
				fillergbc.weighty = 1;
				fillergbc.weightx = .5;
				fillergbc.gridx = 1;
				fillergbc.gridwidth = 1;
				fillergbc.gridy = 1;
				final JButton CancelAlbum = new JButton("Cancel");
				fillerPanel.add(CancelAlbum, fillergbc);

				final JPanel fillerbottom = new JPanel();

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

						PhotoAlbum.backend.getUser(Login1State.user).addAlbum(
								AlbumField.getText());

						AlbumField.setVisible(false);
						AlbumName.setVisible(false);
						CancelAlbum.setVisible(false);
						CreateAlbum.setVisible(false);
						AddAlbumButton.setEnabled(true);
						fillerbottom.setVisible(false);

						// Add new album to scroll pane to jpanel

						// Add elements to albumPanel
						final List<FancyPanel> albumThumbnails = new ArrayList<FancyPanel>();

						for (album a : PhotoAlbum.backend.getUser(
								Login1State.user).getAlbums()) {

							final FancyPanel temp = new FancyPanel();
							temp.setLayout(new GridBagLayout());
							GridBagConstraints gbc = new GridBagConstraints();

							temp.setPreferredSize(new Dimension(170, 200));
							temp.setBorder(new EtchedBorder());
							temp.setVisible(true);

							// mice
							temp.addMouseListener(new MouseAdapter() {

								@Override
								public void mousePressed(MouseEvent e) {

									// resetColors();
									for (FancyPanel f : albumThumbnails) {

										f.setBackground(Color.WHITE);
										innerPanel.revalidate();
										innerPanel.repaint();
									}

									RenameAlbumButton.setEnabled(true);
									DeleteAlbumButton.setEnabled(true);

									temp.setBackground(Color.LIGHT_GRAY);
									innerPanel.revalidate();
									innerPanel.repaint();

								}

								@Override
								public void mouseReleased(MouseEvent e) {
									// setBackground(background);
								}
							});

							JLabel albumN = new JLabel(a.getName());

							// set photo in album
							if (a.getPhotos().size() == 0) {
								ImageIcon noPhoto = new ImageIcon("docs/NoPhotos.png");
								gbc.gridx = 0;
								gbc.gridy = 0;

								JLabel label = new JLabel("", noPhoto, JLabel.CENTER);
								temp.add(label, gbc);
							}
							gbc.gridx = 0;
							gbc.gridy = 1;
							temp.add(albumN, gbc);

							JLabel numPhotos = new JLabel("Number of photos: "
									+ Integer.toString(a.getPhotos().size()));
							gbc.gridx = 0;
							gbc.gridy = 2;
							temp.add(numPhotos, gbc);

							// Find earliest and latest
							ArrayList<photo> photoList = a.getPhotos();
							JLabel dates = new JLabel();

							if (photoList.size() == 0) {
								dates.setText("From: -- To: --");
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
								dates.setText("From: " + earliest + " To: " + latest);
							}

							gbc.gridx = 0;
							gbc.gridy = 3;
							temp.add(dates, gbc);

							albumThumbnails.add((FancyPanel) temp);
						}

						innerPanel.removeAll();
						rowCount = 0;
						columnCount = 0;

						for (JPanel j : albumThumbnails) {

							scrollc.gridx = columnCount;
							scrollc.gridy = rowCount;

							if (columnCount == 2) {
								rowCount += 1;
								columnCount = 0;
							} else {
								columnCount++;
							}
							innerPanel.add(j, scrollc);
						}
						pa.revalidate();

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
	public PhotoAlbumState processEvent() {

		instance = null;

		

		return null;
	}

	class FancyPanel extends JPanel {

		FancyPanel() {

			this.setBackground(Color.white);

		}
	}

	public static Album3State getInstance() {
		if (instance == null) {
			instance = new Album3State();
		}
		return instance;
	}

}
