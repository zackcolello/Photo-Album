package cs213.photoAlbum.GUIViewStates;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Admin2Store {
	
	static PhotoAlbum pa;
	
	static JPanel MainPanel;
	
	//JPanels for the list Users Panel
	static JPanel UserPanel;
	static JLabel UserLabel;
	static JScrollPane UserScroll;
	static JPanel innerPanel;
	
	
	//JPanels for the button Panel
	static JPanel ButtonsPanel;
	static JPanel fillerPanel;
	static JButton RemoveUserButton;
	static JButton AddUserButton;
	
	static GridBagLayout gbl;
	static GridBagConstraints gbc;
	static GridBagLayout bgbl;
	static GridBagConstraints bgbc;
}
