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

public class InAlbum4Store {

static PhotoAlbum pa;
	
	static JPanel MainPanel;
	
	//JPanels for the album Panel
	static JPanel photoPanel;
	static JPanel innerPanel;
	static JScrollPane photoScroll;
	
	//JPanels for the button Panel
	static JPanel ButtonsPanel;
	static JPanel fillerPanel;
	static JButton DeletePhotoButton;
	static JButton MovePhotoButton;
	static JButton AddPhotoButton;
	
	static GridBagLayout gbl;
	static GridBagConstraints gbc;
	static GridBagLayout bgbl;
	static GridBagConstraints bgbc;
	
	//For when AddAlbum Button is clicked
	static GridBagLayout filgbl;
	static GridBagConstraints filgbc;
	static JLabel PhotoPath;
	static JPanel filler;
	static JTextField PhotoField;
	static JLabel Caption;
	static JTextField CaptionField;
	static JButton AddPhoto;
	static JButton CancelPhoto;
	
	static JPanel fillerbottom;
	//For When MovePhot is clicked
	static JLabel Dest;
	static ArrayList<album> DestAlbums;
	static JComboBox<album> DestAlbumsBox;
	static JButton Move;
	static JButton CancelMove;
	static String Album;
	
	
	//For filling in the album Panel
	static ArrayList<JPanel> PhotosArray;
	static JPanel temp;
	static GridBagLayout phgbl;
	static GridBagConstraints phgbc;
	static JLabel PhotoName;
	static String Destination;
	
	
	//For getting the dates of the photos
	//static ArrayList<photo> photoList;
	static JLabel photoCaption;
	static JLabel Date;
	static GridBagConstraints scrollConstraints;
	static int rowCount;
	static int columnCount;
	

	static JLabel MoveErrLabel;
	static JLabel AddErrLabel;
	
	static ArrayList<photo> Photos;
	

}
