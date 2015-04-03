package cs213.photoAlbum.GUIViewStates;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PhotoAlbum extends JFrame {

	// Create panels for default login state
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JLabel welcomeLabel = new JLabel();
	private JLabel usernameLabel = new JLabel();
	private JButton submitButton = new JButton("login");
	private JTextField usernameField = new JTextField();

	private GridBagLayout gbLayout = new GridBagLayout();
	private GridBagConstraints gbConstraints = new GridBagConstraints();
	private ActionListener listener = new PhotoAlbumController();

	public void buildLayout() {

		setLayout(gbLayout);

		
		//set borders for 2 panels
		TitledBorder title = BorderFactory.createTitledBorder("what the fuck");
		
		//topPanel.setBorder(title);
		
		// Add components to mainPanel, add mainPanel
		
		bottomPanel.setLayout(new BorderLayout());
		welcomeLabel.setText("Welcome to PhotoAlbum34");
		welcomeLabel.setFont(new Font("Serif", Font.ITALIC, 50));
		topPanel.add(welcomeLabel, BorderLayout.CENTER);
		
		// Set up bottomPanel with labels and buttons

		usernameField.setPreferredSize(new Dimension(130, 20));
		submitButton.setPreferredSize(new Dimension(110, 20));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		buttonPanel.setBorder(new EmptyBorder(10, 0, 30, 0));
		
		usernameLabel.setText("Username:");
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.add(usernameLabel, BorderLayout.EAST);
		usernamePanel.add(usernameField, BorderLayout.WEST);
		usernamePanel.setBorder(new EmptyBorder(30, 0, 0, 0));
		
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(usernamePanel, BorderLayout.CENTER);
		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
		bottomPanel.setBorder( new EmptyBorder(0, 330, 0, 330 ) );
		
		//add top panel to main panel
		
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.weightx = 1;
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 1;
		gbConstraints.anchor = GridBagConstraints.NORTHWEST;
		add(topPanel, gbConstraints);
		
		
		//add bottom panel to main panel
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.anchor = GridBagConstraints.SOUTHWEST;
		gbConstraints.weightx = 1;
		gbConstraints.gridy = 2;
		gbConstraints.gridx = 1;
		add(bottomPanel, gbConstraints);
		
		
	}

	public PhotoAlbum() {
		// give title to JFrame
		super("Photo Album");
		this.setPreferredSize(new Dimension(900, 600));
		buildLayout();

	}

	public static void main(String[] args) {

		PhotoAlbum photoAlbumGUI = new PhotoAlbum();
		photoAlbumGUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
		photoAlbumGUI.setResizable(false);
		photoAlbumGUI.pack();
		photoAlbumGUI.setLocationRelativeTo(null);
		photoAlbumGUI.setVisible(true);

	}
}
