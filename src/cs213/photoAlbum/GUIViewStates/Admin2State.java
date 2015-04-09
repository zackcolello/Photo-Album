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

public class Admin2State extends PhotoAlbumState {
	
	private static Admin2State instance = null;
	
	public Admin2State(){
		
	}

	public void enter(){
		System.out.println("Admin state");
		
		// Grab current JFrame
		Frame[] frames = Frame.getFrames();
		Admin2Store.pa = (PhotoAlbum) frames[0];

		// Clear items from that state
		Admin2Store.pa.getContentPane().removeAll();
		Admin2Store.pa.getContentPane().repaint();
		Admin2Store.pa.getContentPane().revalidate();
		
	
		Admin2Store.gbl = new GridBagLayout();
		Admin2Store.gbc = new GridBagConstraints();
		Admin2Store.MainPanel = new JPanel();
		Admin2Store.MainPanel.setLayout(Admin2Store.gbl);
		
		JPanel AdminBar = new AdminBarPanel();
		AdminBar= (JPanel)((AdminBarPanel)AdminBar).createAdminBar();

		Admin2Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		Admin2Store.gbc.gridx = 0;
		Admin2Store.gbc.gridy = 0;
		Admin2Store.gbc.weightx = 1;
		Admin2Store.gbc.gridwidth = 2;
		Admin2Store.MainPanel.add(AdminBar, Admin2Store.gbc);
		
		Admin2Store.UserPanel = new JPanel();
		Admin2Store.innerPanel = new JPanel();
		
		Admin2Store.UserLabel = new JLabel();
		Admin2Store.UserLabel.setText("Users");
		Admin2Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		Admin2Store.gbc.gridx = 0;
		Admin2Store.gbc.gridy = 0;
		Admin2Store.gbc.weightx = 1;
		Admin2Store.gbc.gridwidth = 2;
		Admin2Store.UserPanel
			.add(Admin2Store.UserLabel,Admin2Store.gbc);

		
		Admin2Store.innerPanel.setBorder(new EtchedBorder());;
		Admin2Store.innerPanel.setLayout(Album3Store.gbl);
		
		Admin2Store.UserScroll = new JScrollPane(Admin2Store.innerPanel);
		Admin2Store.UserScroll.setVisible(true);
		Admin2Store.UserScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Admin2Store.UserScroll.setLayout(new ScrollPaneLayout());
		Admin2Store.UserScroll.setVisible(true);
		
		Admin2Store.UserPanel.setLayout(Admin2Store.gbl);
		Admin2Store.UserPanel.setPreferredSize(new Dimension(300, 545));
		
		Admin2Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		Admin2Store.gbc.gridx = 0;
		Admin2Store.gbc.gridy = 1;
		Admin2Store.gbc.weightx = 1;
		Admin2Store.gbc.gridwidth = 2;
		
		Admin2Store.UserPanel
				.add(Admin2Store.UserScroll, Admin2Store.gbc);
		
		
		
		Admin2Store.gbc.gridx = 0;
		Admin2Store.gbc.gridy = 1;
		Admin2Store.gbc.weightx = .8;
		Admin2Store.gbc.gridwidth = 1;
		Admin2Store.UserPanel.setBorder(new EtchedBorder());
		Admin2Store.UserPanel.setVisible(true);
		
		Admin2Store.MainPanel.add(Admin2Store.UserPanel, Admin2Store.gbc);
		
		
		Admin2Store.ButtonsPanel = new JPanel();
		Admin2Store.ButtonsPanel.setLayout(Admin2Store.gbl);
		Admin2Store.ButtonsPanel.setPreferredSize(new Dimension(300, 545));
		Admin2Store.bgbc = new GridBagConstraints();
		Admin2Store.bgbl = new GridBagLayout();
		Admin2Store.ButtonsPanel.setLayout(Admin2Store.bgbl);
		
		Admin2Store.AddUserButton = new JButton("Add User");
		Admin2Store.bgbc.gridx = 0;
		Admin2Store.bgbc.gridy = 0;
		Admin2Store.bgbc.weightx=.05;
		Admin2Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		Admin2Store.ButtonsPanel.add(Admin2Store.AddUserButton, Admin2Store.bgbc);
		
		Admin2Store.RemoveUserButton = new JButton("Remove User");
		Admin2Store.bgbc.gridx = 1;
		Admin2Store.bgbc.gridy = 0;
		Admin2Store.bgbc.weightx=.05;
		Admin2Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		Admin2Store.ButtonsPanel.add(Admin2Store.RemoveUserButton, Admin2Store.bgbc);
		
		Admin2Store.fillerPanel = new JPanel();
		Admin2Store.bgbc.gridx = 0;
		Admin2Store.bgbc.gridy = 1;
		Admin2Store.bgbc.weighty = 1;
		Admin2Store.bgbc.weightx = 1;
		Admin2Store.bgbc.fill = GridBagConstraints.BOTH;
		Admin2Store.bgbc.gridwidth = 3;
		Admin2Store.ButtonsPanel.add(Admin2Store.fillerPanel, Admin2Store.bgbc);
		
		Admin2Store.gbc.gridx = 1;
		Admin2Store.gbc.gridy = 1;
		Admin2Store.gbc.weightx = .2;
		Admin2Store.gbc.weighty = 1;
		Admin2Store.UserPanel.setBorder(new EtchedBorder());
		Admin2Store.UserPanel.setVisible(true);
		Admin2Store.MainPanel.add(Admin2Store.ButtonsPanel, Admin2Store.gbc);

		
		
		
		

		
		
		
		
		
		
		
		Admin2Store.pa.add(Admin2Store.MainPanel, Admin2Store.gbc);

		
		
		//Admin2Store.pa.add(Admin2Store.MainPanel, Admin2Store.gbc);


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


	
	
	
	
	@Override
	PhotoAlbumState processEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Admin2State getInstance() {
		if (instance == null) {
			instance = new Admin2State();
		}
		return instance;
	}
	
}
