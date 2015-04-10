package cs213.photoAlbum.GUIViewStates;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cs213.photoAlbum.model.User;

public class Admin2Store {
	
	static PhotoAlbum pa;
	
	static JPanel MainPanel;
	
	//JPanels for the list Users Panel
	static JPanel UserLabelPanel;
	static JPanel UserPanel;
	static JLabel UserLabel;
	static JScrollPane UserScroll;
	static DefaultListModel<User> listModel;
	static JList<User> UserList;
	static ArrayList<User> database;
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
	
	
	//When AddUser is selected
	static GridBagLayout filgbl;
	static GridBagConstraints filgbc;
	static JLabel UserName;
	static JLabel UserID;
	static JPanel fillerbottom;
	static JTextField NameField;
	static JTextField IDField;
	static JButton Add;
	static JButton Cancel;
	
	static JLabel errLabel;
	
	
}
