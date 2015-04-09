package cs213.photoAlbum.GUIViewStates;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class Admin2State extends PhotoAlbumState {
	
	private static Admin2State instance = null;

	JPanel MainPanel = new JPanel();
	JPanel LeftPanel = new JPanel();
	JPanel RightPanel = new JPanel();
	
	PhotoAlbum pa = new PhotoAlbum();
	
	private GridBagLayout gbl = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();
	
	int columnCount = 0;
	int rowCount = 0;
	

	public Admin2State(){
		
	}

	public void enter(){
		System.out.println("Admin state");
		
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
		
		JPanel AdminBar = new AdminBarPanel();
		AdminBar= (JPanel)((AdminBarPanel)AdminBar).createAdminBar();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		
		MainPanel.add(AdminBar,gbc);
		

		
		pa.add(MainPanel, gbc);


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
