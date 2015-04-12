package cs213.photoAlbum.GUIViewStates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs213.photoAlbum.model.tag;

public class SearchTag7State extends PhotoAlbumState {

	private static SearchTag7State instance = null;

	public SearchTag7State() {

	}

	public void enter() {

		// Grab current JFrame
		Frame[] frames = Frame.getFrames();
		SearchTag7Store.pa = (PhotoAlbum) frames[0];

		// Clear items from that state
		SearchTag7Store.pa.getContentPane().removeAll();
		SearchTag7Store.pa.getContentPane().repaint();
		SearchTag7Store.pa.getContentPane().revalidate();

		SearchTag7Store.gbl = new GridBagLayout();
		SearchTag7Store.gbc = new GridBagConstraints();
		SearchTag7Store.MainPanel = new JPanel();
		SearchTag7Store.MainPanel.setLayout(SearchTag7Store.gbl);

		// Create top Menu Bar
		JPanel MenuBar = new MenuBarPanel();
		MenuBar = (JPanel) ((MenuBarPanel) MenuBar)
				.CreateMenuBarPanel("Albums");
		SearchTag7Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		SearchTag7Store.gbc.gridx = 0;
		SearchTag7Store.gbc.gridy = 0;
		SearchTag7Store.gbc.weightx = 1;
		SearchTag7Store.gbc.gridwidth = 2;
		SearchTag7Store.MainPanel.add(MenuBar, SearchTag7Store.gbc);

		// Label for Tags
		SearchTag7Store.TagLabelPanel = new JPanel();
		SearchTag7Store.TagLabelPanel.setBorder(new EtchedBorder());

		SearchTag7Store.TagLabel = new JLabel();
		SearchTag7Store.TagLabel.setText("Tags");
		SearchTag7Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		SearchTag7Store.gbc.gridx = 0;
		SearchTag7Store.gbc.gridy = 0;
		SearchTag7Store.gbc.weightx = 1;
		SearchTag7Store.gbc.gridwidth = 1;

		SearchTag7Store.TagLabelPanel.add(SearchTag7Store.TagLabel);

		SearchTag7Store.gbc.gridx = 0;
		SearchTag7Store.gbc.gridy = 1;
		SearchTag7Store.gbc.weightx = 1;
		SearchTag7Store.gbc.gridwidth = 1;
		SearchTag7Store.MainPanel.add(SearchTag7Store.TagLabelPanel, SearchTag7Store.gbc);

		SearchTag7Store.TagListPanel = new JPanel();
		SearchTag7Store.listModel = new DefaultListModel<String>();
		SearchTag7Store.TagArrList = new ArrayList<String>();
		
		for (String s : SearchTag7Store.TagArrList) {
			
				SearchTag7Store.listModel.addElement(s);
		}
		SearchTag7Store.TagList = new JList<String>(SearchTag7Store.listModel);

		SearchTag7Store.TagList
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		SearchTag7Store.TagList.setLayoutOrientation(JList.VERTICAL);
		SearchTag7Store.TagList.setVisibleRowCount(-1);

		SearchTag7Store.TagScroll = new JScrollPane(SearchTag7Store.TagList);
		SearchTag7Store.TagScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//SearchTag7Store.TagScroll.setLayout(new ScrollPaneLayout());
		SearchTag7Store.TagScroll.setPreferredSize(new Dimension(450, 410));
		SearchTag7Store.TagScroll.setVisible(true);
		SearchTag7Store.TagScroll.revalidate();
		//SearchTag7Store.TagListPanel.setLayout(new BorderLayout());
		SearchTag7Store.TagListPanel.setPreferredSize(new Dimension(450, 421));

		SearchTag7Store.TagListPanel.add(SearchTag7Store.TagScroll, BorderLayout.CENTER);
		
	//	SearchTag7Store.gbc.gridheight = GridBagConstraints.REMAINDER;
		SearchTag7Store.gbc.gridx = 0;
		SearchTag7Store.gbc.gridy = 2;
		SearchTag7Store.gbc.weightx = .8;
		SearchTag7Store.gbc.gridwidth = 1;
		SearchTag7Store.TagListPanel.setBorder(new EtchedBorder());
		SearchTag7Store.TagListPanel.setVisible(true);
		
		SearchTag7Store.MainPanel.add(SearchTag7Store.TagListPanel, SearchTag7Store.gbc);

		SearchTag7Store.QueryPanel = new JPanel();
		//SearchTag7Store.QueryPanel.setBorder(new EtchedBorder());
		SearchTag7Store.QueryPanel.setLayout(new GridBagLayout());
		SearchTag7Store.QueryPanel.setPreferredSize(new Dimension(300, 90));
		
		GridBagConstraints qgbc = new GridBagConstraints();
		
		SearchTag7Store.addTagButton = new JButton("Add New Tag to Query");
		qgbc.gridx = 0;
		qgbc.gridy = 0;
		SearchTag7Store.QueryPanel.add(SearchTag7Store.addTagButton, qgbc);
		
		SearchTag7Store.removeTagButton = new JButton("Remove Tag from Query");		
		SearchTag7Store.removeTagButton.setEnabled(false);
		qgbc.gridx = 1;
		qgbc.gridy = 0;
		SearchTag7Store.QueryPanel.add(SearchTag7Store.removeTagButton, qgbc);
		
		SearchTag7Store.gbc.gridx = 0;
		SearchTag7Store.gbc.gridy = 3;
		SearchTag7Store.QueryPanel.setVisible(true);
		SearchTag7Store.MainPanel.add(SearchTag7Store.QueryPanel, SearchTag7Store.gbc);
		
		SearchTag7Store.ButtonsPanel = new JPanel();
		SearchTag7Store.ButtonsPanel.setLayout(SearchTag7Store.gbl);
		SearchTag7Store.ButtonsPanel.setPreferredSize(new Dimension(300, 540));
		SearchTag7Store.bgbc = new GridBagConstraints();
		SearchTag7Store.ButtonsPanel.setBorder(new EtchedBorder());
		SearchTag7Store.bgbl = new GridBagLayout();
		SearchTag7Store.ButtonsPanel.setLayout(SearchTag7Store.bgbl);

		SearchTag7Store.SearchButton = new JButton("Search Tags");
		SearchTag7Store.bgbc.gridx = 0;
		SearchTag7Store.bgbc.gridy = 0;
		SearchTag7Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		SearchTag7Store.gbc.fill = GridBagConstraints.VERTICAL;
		SearchTag7Store.ButtonsPanel.add(SearchTag7Store.SearchButton,
				SearchTag7Store.bgbc);

		SearchTag7Store.cancelButton = new JButton("Cancel");
		SearchTag7Store.cancelButton.setEnabled(false);
		SearchTag7Store.bgbc.gridx = 0;
		SearchTag7Store.bgbc.gridy = 0;
		SearchTag7Store.bgbc.weightx = .05;
		SearchTag7Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		

		SearchTag7Store.fillerPanel = new JPanel();
		SearchTag7Store.bgbc.gridx = 0;
		SearchTag7Store.bgbc.gridy = 1;
		SearchTag7Store.bgbc.weighty = 1;
		SearchTag7Store.bgbc.weightx = 1;
		SearchTag7Store.bgbc.fill = GridBagConstraints.BOTH;
		SearchTag7Store.bgbc.gridwidth = 3;
		SearchTag7Store.ButtonsPanel.add(SearchTag7Store.fillerPanel, SearchTag7Store.bgbc);

		SearchTag7Store.gbc.gridx = 1;
		SearchTag7Store.gbc.gridy = 1;
		SearchTag7Store.gbc.weightx = .2;
		SearchTag7Store.gbc.weighty = 1;
		SearchTag7Store.ButtonsPanel.setBorder(new EtchedBorder());
		SearchTag7Store.ButtonsPanel.setVisible(true);
		SearchTag7Store.MainPanel.setBorder(new EtchedBorder());
		SearchTag7Store.gbc.gridheight = GridBagConstraints.REMAINDER;
		
		SearchTag7Store.MainPanel.add(SearchTag7Store.ButtonsPanel, SearchTag7Store.gbc);
		SearchTag7Store.gbc.gridheight = GridBagConstraints.REMAINDER;
		SearchTag7Store.pa.add(SearchTag7Store.MainPanel, SearchTag7Store.gbc);

		// fillTagList;

		AddListeners();

	}

	public void fillTagList() {

	}

	public void AddListeners() {

		SearchTag7Store.submitButton = new JButton("Submit");
		//SearchTag7Store.cancelButton = new JButton("Cancel");

		// Even listener for list selection

		SearchTag7Store.TagList
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						if (SearchTag7Store.TagList.getSelectedIndex() != -1) {
							SearchTag7Store.removeTagButton.setEnabled(true);
						} else {
							SearchTag7Store.removeTagButton.setEnabled(false);
						}
					}

				});

		// Event listener for addTagButton Button
		SearchTag7Store.addTagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SearchTag7Store.addTagButton.setEnabled(false);
				SearchTag7Store.removeTagButton.setEnabled(false);
				SearchTag7Store.cancelButton.setEnabled(true);
				// Create GBL & GBC for filler panel
				SearchTag7Store.filgbl = new GridBagLayout();
				SearchTag7Store.filgbc = new GridBagConstraints();
				SearchTag7Store.fillerPanel.setLayout(SearchTag7Store.filgbl);
				SearchTag7Store.fillerPanel.setBorder(new EtchedBorder());
				SearchTag7Store.filgbc.insets = new Insets(10, 10, 10, 10);

				// Create Name field
				SearchTag7Store.typeLabel = new JLabel("Tag Type:");
				SearchTag7Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				SearchTag7Store.filgbc.gridx = 0;
				SearchTag7Store.filgbc.gridy = 0;
				SearchTag7Store.fillerPanel.add(SearchTag7Store.typeLabel,
						SearchTag7Store.filgbc);

				// Create value field
				SearchTag7Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				SearchTag7Store.filgbc.weightx = .5;
				SearchTag7Store.filgbc.gridx = 1;
				SearchTag7Store.filgbc.gridy = 0;
				SearchTag7Store.typeField = new JTextField();
				SearchTag7Store.typeField.setSize(new Dimension(50, 20));
				SearchTag7Store.fillerPanel.add(SearchTag7Store.typeField,
						SearchTag7Store.filgbc);

				// Create type field
				SearchTag7Store.valueLabel = new JLabel("Tag Value:*");
				SearchTag7Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				SearchTag7Store.filgbc.gridx = 0;
				SearchTag7Store.filgbc.gridy = 1;
				SearchTag7Store.fillerPanel.add(SearchTag7Store.valueLabel,
						SearchTag7Store.filgbc);

				// Create ID field
				SearchTag7Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				SearchTag7Store.filgbc.weightx = .5;
				SearchTag7Store.filgbc.gridx = 1;
				SearchTag7Store.filgbc.gridy = 1;
				SearchTag7Store.valueField = new JTextField();
				SearchTag7Store.valueField.setSize(new Dimension(50, 20));
				SearchTag7Store.fillerPanel.add(SearchTag7Store.valueField,
						SearchTag7Store.filgbc);

				// Create 'submit' Button
				SearchTag7Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				SearchTag7Store.filgbc.weightx = .5;
				SearchTag7Store.filgbc.gridwidth = 1;
				SearchTag7Store.filgbc.gridx = 0;
				SearchTag7Store.filgbc.gridy = 2;
				SearchTag7Store.SearchButton.setEnabled(false);
				SearchTag7Store.SearchButton.setVisible(true);
				SearchTag7Store.submitButton.setEnabled(false);
				SearchTag7Store.fillerPanel
						.add(SearchTag7Store.submitButton, SearchTag7Store.filgbc);

				// Create 'cancelButton' Button
				SearchTag7Store.filgbc.weighty = 1;
				SearchTag7Store.filgbc.weightx = .5;
				SearchTag7Store.filgbc.gridx = 1;
				SearchTag7Store.filgbc.gridwidth = 1;
				SearchTag7Store.filgbc.gridy = 2;
				SearchTag7Store.cancelButton.setVisible(true);
				SearchTag7Store.fillerPanel
				.add(SearchTag7Store.cancelButton, SearchTag7Store.filgbc);

				// Create whitespace filler panel
				SearchTag7Store.fillerbottom = new JPanel();
				SearchTag7Store.filgbc.gridy = 2;
				SearchTag7Store.filgbc.gridx = 0;
				SearchTag7Store.filgbc.weightx = 1;
				SearchTag7Store.filgbc.weighty = 1;
				SearchTag7Store.filgbc.gridwidth = 2;
				SearchTag7Store.fillerbottom.setPreferredSize(new Dimension(100,
						350));
				SearchTag7Store.filgbc.fill = GridBagConstraints.BOTH;
				SearchTag7Store.fillerPanel.add(SearchTag7Store.fillerbottom,
						SearchTag7Store.filgbc);

				SearchTag7Store.errLabel = new JLabel(
						"Error, User already Exists with that ID");

				// Event listener for when text is entered into valueField
				SearchTag7Store.valueField.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent event) {
						if (!SearchTag7Store.valueField.getText().equals("")) {
							System.out.println("here");
							SearchTag7Store.submitButton.setEnabled(true);
						} else {
							SearchTag7Store.submitButton.setEnabled(false);
						}
					}
					@Override
					public void keyReleased(KeyEvent event) {
						if (!SearchTag7Store.valueField.getText().equals("")) {
							SearchTag7Store.submitButton.setEnabled(true);
						} else {
							SearchTag7Store.submitButton.setEnabled(false);
						}
					}

					@Override
					public void keyPressed(KeyEvent event) {
						if (!SearchTag7Store.valueField.getText().equals("")){
							SearchTag7Store.submitButton.setEnabled(true);
						} else {
							SearchTag7Store.submitButton.setEnabled(false);
						}
					}
				});

				SearchTag7Store.pa.revalidate();
				SearchTag7Store.pa.repaint();

			}
		});

		// Event listener for cancelButton Album Button
		SearchTag7Store.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SearchTag7State.instance = null;
				PhotoAlbumStore.searchTag7State.enter();
				
			}
		});

		// Event listener for Create Album Button
		SearchTag7Store.SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (PhotoAlbum.backend.containsUser(SearchTag7Store.typeField
						.getText())) {
					addDubError();

				} else {

					PhotoAlbum.backend.addUser(SearchTag7Store.typeField.getText(),
							SearchTag7Store.valueField.getText());
					SearchTag7State.instance = null;
					PhotoAlbumStore.searchTag7State.enter();
				}

			}
		});

		// Event listener for Delete Album Button
		SearchTag7Store.removeTagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//remove the tag here
				
				PhotoAlbumStore.searchTag7State.enter();
			}
		});
		
		// Add listener for TagList to set removeTag button enabled or not
		SearchTag7Store.TagList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting()) {

					if (SearchTag7Store.TagList.isSelectionEmpty()) {
						SearchTag7Store.removeTagButton.setEnabled(false);

					} else {
						SearchTag7Store.removeTagButton.setEnabled(true);
					}
					return;
				}
			}
		});

	}

	public void addDubError() {

		SearchTag7Store.errLabel.setVisible(true);
		SearchTag7Store.errLabel.setForeground(Color.red);
		SearchTag7Store.bgbc.gridy = 2;
		SearchTag7Store.bgbc.gridx = 0;
		SearchTag7Store.bgbc.weighty = 1;
		SearchTag7Store.bgbc.weightx = 1;
		SearchTag7Store.bgbc.fill = GridBagConstraints.BOTH;
		SearchTag7Store.bgbc.gridwidth = 3;
		SearchTag7Store.ButtonsPanel.add(SearchTag7Store.errLabel, SearchTag7Store.bgbc);

		SearchTag7Store.pa.revalidate();
		SearchTag7Store.pa.repaint();
	}

	@Override
	PhotoAlbumState processEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	public static SearchTag7State getInstance() {
		if (instance == null) {
			instance = new SearchTag7State();
		}
		return instance;
	}

}
