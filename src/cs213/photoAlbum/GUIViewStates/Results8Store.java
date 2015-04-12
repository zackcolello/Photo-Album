package cs213.photoAlbum.GUIViewStates;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cs213.photoAlbum.model.album;
import cs213.photoAlbum.model.photo;

/**
 * 
 * @author Anna Genke
 *
 */
public class Results8Store {
	
static PhotoAlbum pa;

	//album reference to know where it is in
	static ArrayList<photo> results;
	
	
	static JPanel MainPanel;
	//JPanels for the results Panel
	static JPanel photoPanel;
	static JPanel innerPanel;
	static JScrollPane photoScroll;
	
	//JPanels for the button Panel
		static JPanel ButtonsPanel;
		static JPanel fillerPanel;
		
		static GridBagLayout gbl;
		static GridBagConstraints gbc;
		static GridBagLayout bgbl;
		static GridBagConstraints bgbc;
		
		//For when Add Button is clicked
		static GridBagLayout filgbl;
		static GridBagConstraints filgbc;
		static JLabel NewAlbum;
		static JPanel filler;
		static JTextField AlbumName;
		static JButton NewResultsAlbum;
		static JButton CreateAlbum;
		static JButton CancelAlbum;
		
		static JPanel fillerbottom;
		//For When MovePhot is clicked
		
		
		
		//For filling in the album Panel
		static ArrayList<JPanel> PhotosArray;
		static JPanel temp;
		static GridBagLayout phgbl;
		static GridBagConstraints phgbc;
		static JLabel PhotoName;
		
		
		//For getting the dates of the photos
		//static ArrayList<photo> photoList;
		static JLabel photoCaption;
		static JLabel Date;
		static GridBagConstraints scrollConstraints;
		static int rowCount;
		static int columnCount;
		

		static JLabel CreateErrLabel;
		
		
		static ArrayList<photo> Photos;
		

}
