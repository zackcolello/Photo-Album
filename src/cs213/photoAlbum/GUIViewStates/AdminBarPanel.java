package cs213.photoAlbum.GUIViewStates;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * 
 * @author Anna Genke
 *
 */
public class AdminBarPanel extends JPanel {
	

	JButton logoutButton = new JButton("logout");
	
	public AdminBarPanel(){
	}
	
	public JPanel createAdminBar(){
		Frame[] frames = Frame.getFrames();
		PhotoAlbum pa = (PhotoAlbum) frames[0];
		
		JPanel topBar=new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		topBar.setLayout(gbl);
		
		
		JPanel whiteSpacePanel = new JPanel();
		// whiteSpacePanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		whiteSpacePanel.setPreferredSize(new Dimension(380, 2));
		whiteSpacePanel.setVisible(true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = .3;
		
		
		topBar.add(whiteSpacePanel, gbc);
		
		
		JLabel locationLabel = new JLabel();
		locationLabel.setText("Admin");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 0;

		
		
		topBar.add(locationLabel);
		topBar.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		
		
		JPanel whiteSpacePanel2 = new JPanel();
		// whiteSpacePanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		whiteSpacePanel2.setPreferredSize(new Dimension(340, 2));
		whiteSpacePanel2.setVisible(true);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.weightx = .3;
		
		
		topBar.add(whiteSpacePanel2, gbc);


		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 4;
		gbc.gridy = 0;
		
		topBar.add(logoutButton, gbc);

		// Event listeners for buttons
		logoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				processEvent();
				
			}
		});
		
		return topBar;
	}
	
	public PhotoAlbumState processEvent() {

		if (logoutButton.getText().equals("logout")) {
			PhotoAlbumStore.login1State.enter();
			return PhotoAlbumStore.login1State;
		}

		return null;
	}

}
