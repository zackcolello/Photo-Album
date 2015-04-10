package cs213.photoAlbum.GUIViewStates;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class InAlbum4Store {

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
	static JPanel fillerbottom;
	static JTextField AlbumField;
	static JButton CreateAlbum;
	static JButton CancelAlbum;
	
	//For filling in the album Panel
	static ArrayList<JPanel> albumsArray;
	static JPanel temp;
	static GridBagLayout algbl;
	static GridBagConstraints algbc;
	static JLabel albumName;
	
	
	//For getting the dates of the photos
	//static ArrayList<photo> photoList;
	static JLabel numPhotos;
	static JLabel dates;
	static GridBagConstraints scrollConstraints;
	static int rowCount;
	static int columnCount;
	
	//For renameAlbumButton
	static JButton RenameAlbum;
	static JButton RenameCancel;
	static JLabel errLabel;
	

}
