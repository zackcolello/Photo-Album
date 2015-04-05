package cs213.photoAlbum.GUIViewStates;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class MenuBarPanel extends JPanel{

	JButton logoutButton = new JButton("logout");
	
	public MenuBarPanel(){
	}
	
	public JPanel CreateMenuBarPanel(String location) {

		// Grab current JFrame
		Frame[] frames = Frame.getFrames();
		PhotoAlbum pa = (PhotoAlbum) frames[0];

		JPanel MenuBar = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		// gbc.insets = new Insets(0,200,0, 0);
		MenuBar.setLayout(gbl);

		JPanel whiteSpacePanel = new JPanel();
		// whiteSpacePanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		whiteSpacePanel.setPreferredSize(new Dimension(340, 2));
		whiteSpacePanel.setVisible(true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = .3;
		MenuBar.add(whiteSpacePanel, gbc);

		JLabel locationLabel = new JLabel();
		locationLabel.setText(location);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 0;

		MenuBar.add(locationLabel, gbc);

		String[] searchType = { "Search:", "By Date", "By Tag" };
		JComboBox<String> searchBox = new JComboBox<String>(searchType);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 0;

		MenuBar.add(searchBox, gbc);
		MenuBar.setBorder(new EtchedBorder(EtchedBorder.RAISED));

		

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 0;

		MenuBar.add(logoutButton, gbc);

		// Event listeners for buttons
		logoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				processEvent();
				
			}
		});

		return MenuBar;

	}

	public PhotoAlbumState processEvent() {

		if (logoutButton.getText().equals("logout")) {
			PhotoAlbumStore.login1State.enter();
			return PhotoAlbumStore.login1State;
		}

		return null;
	}

}
