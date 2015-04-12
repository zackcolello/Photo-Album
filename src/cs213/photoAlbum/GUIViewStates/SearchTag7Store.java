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
import cs213.photoAlbum.model.tag;

/**
 * 
 * @author Anna Genke
 *
 */
public class SearchTag7Store {

	static PhotoAlbum pa;

	static JPanel MainPanel;

	// JPanels for the list Tags Panel
	static JPanel TagLabelPanel;
	static JPanel TagListPanel;
	static JLabel TagLabel;
	static JScrollPane TagScroll;
	static DefaultListModel<String> listModel;
	static JList<String> TagList;
	static ArrayList<String> TagArrList;
	static JPanel innerPanel;

	// JPanels for the button Panel
	static JPanel ButtonsPanel;
	static JPanel fillerPanel;
	static JButton removeTagButton;
	static JButton addTagButton;
	static JPanel QueryPanel;

	static GridBagLayout gbl;
	static GridBagConstraints gbc;
	static GridBagLayout bgbl;
	static GridBagConstraints bgbc;

	// When AddTag is selected
	static GridBagLayout filgbl;
	static GridBagConstraints filgbc;
	static JLabel typeLabel;
	static JLabel valueLabel;
	static JPanel fillerbottom;
	static JTextField typeField;
	static JTextField valueField;
	static JButton SearchButton;
	static JButton submitButton;
	static JButton cancelButton;

	static JLabel errLabel;
}
