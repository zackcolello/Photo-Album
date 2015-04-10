package cs213.photoAlbum.GUIViewStates;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cs213.photoAlbum.model.album;

public class Album5Store  {
	
	//PhotoAlbum
	static PhotoAlbum pa;
	//Store album and photo we are looking at
	static cs213.photoAlbum.model.photo currentPhoto;
	static album currentAlbum;
	
	
	//MainPanel and constraints
	static GridBagLayout gbl;
	static GridBagConstraints gbc;
	static JPanel MainPanel;
	
	//Photo Panel for Image
	static JPanel OuterPhotoPanel;
	static JLabel ImageLabel;
	static JPanel ImagePanel;
	static ImageIcon photo;
	static JButton leftButton;
	static JButton rightButton;
	static JPanel ImageInfoPanel;
	static GridBagLayout ppgbl;
	static GridBagConstraints ppgbc;
	
	//ImageInfoPanel buttons and labels
	static JLabel caption;
	static JButton recaptionButton;
	static JTextField newCaptionField;
	static JButton saveButton;
	static JButton cancelButton;
	static JLabel date;
	static GridBagLayout iigbl;
	static GridBagConstraints iigbc;
	
	//TagsPanel
	static JPanel TagsPanel;
	static JLabel tagsLabel;
	static JPanel tags;
	static JList list;
	static ArrayList<String> TagsArr;
	static JScrollPane scrollpane;
	static JButton addTagButton;
	static JButton removeTagButton;
	static JPanel fillerPanel;
	static JPanel tagButtonPanel;
	static GridBagLayout tgbl;
	static GridBagConstraints tgbc;
	
	//Filler Panel for adding a tag
	static GridBagConstraints fgbc;
	static JLabel tagType;
	static JLabel tagValue;
	static JTextField typeField;
	static JTextField valueField;
	static JButton submitTag;
	static JButton cancelTag;
	
}