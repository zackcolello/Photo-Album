package cs213.photoAlbum.GUIViewStates;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Album3Store{
	
	static PhotoAlbum pa;
	
	static JPanel MainPanel;
	
	//JPanels for the album Panel
	static JPanel albumPanel;
	static JPanel innerPanel;
	static JScrollPane albumScroll;
	
	//JPanels for the button Panel
	static JPanel ButtonsPanel;
	static JPanel fillerPanel;
	static JButton DeleteAlbumButton;
	static JButton RenameAlbumButton;
	static JButton AddAlbumButton;
	
	static GridBagLayout gbl;
	static GridBagConstraints gbc;
	static GridBagLayout bgbl;
	static GridBagConstraints bgbc;
	
	//For when AddAlbum Button is clicked
	static GridBagLayout filgbl;
	static GridBagConstraints filgbc;
	static JLabel AlbumName;
	
	
}