package cs213.photoAlbum.GUIViewStates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cs213.photoAlbum.model.User;

public class Login1State extends PhotoAlbumState {

	/**
	 * Singleton instance
	 */
	private static Login1State instance = null;

	
	//Holds who is logged in
	public static String user = null;
	
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

	// Constructor
	public Login1State() {
	}

	// Called when state is entered, build state
	public void enter() {

		// Grab current JFrame
		Frame[] frames = Frame.getFrames();
		final PhotoAlbum pa = (PhotoAlbum) frames[0];

		// Clear items from that state
		pa.getContentPane().removeAll();
		pa.getContentPane().repaint();

		pa.setLayout(gbLayout);

		// Add components to mainPanel, add mainPanel

		bottomPanel.setLayout(new BorderLayout());
		
		welcomeLabel.setText("Welcome to PhotoAlbum34");
		welcomeLabel.setFont(new Font("Serif", Font.ITALIC, 50));
		topPanel.add(welcomeLabel, BorderLayout.CENTER);

		// Set up bottomPanel with labels and buttons

		usernameField.setPreferredSize(new Dimension(130, 20));
		submitButton.setPreferredSize(new Dimension(200, 40));
		submitButton.setEnabled(false);
		submitButton.setVisible(true);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		buttonPanel.setBorder(new EmptyBorder(10, 0, 30, 0));
		buttonPanel.setVisible(true);
		
		usernameLabel.setText("Username:");
		usernameLabel.setVisible(true);
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setVisible(true);
		usernamePanel.add(usernameLabel, BorderLayout.EAST);
		usernamePanel.add(usernameField, BorderLayout.WEST);
		usernamePanel.setBorder(new EmptyBorder(30, 0, 0, 0));

		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(usernamePanel, BorderLayout.NORTH);
		bottomPanel.add(buttonPanel, BorderLayout.CENTER);

		final JLabel errorLabel = new JLabel("Error: User ID not found in database.");
		errorLabel.setForeground(Color.red);
		errorLabel.setVisible(false);

		JPanel errorPanel = new JPanel();
		errorPanel.add(errorLabel);
		errorPanel.setPreferredSize(new Dimension(100, 20));

		bottomPanel.add(errorPanel, BorderLayout.SOUTH);
		bottomPanel.setBorder(new EmptyBorder(0, 330, 0, 330));

		// add top panel to main panel

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.weightx = 1;
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 1;
		gbConstraints.anchor = GridBagConstraints.NORTHWEST;
		pa.add(topPanel, gbConstraints);

		// add bottom panel to main panel
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.anchor = GridBagConstraints.SOUTHWEST;
		gbConstraints.weightx = 1;
		gbConstraints.gridy = 2;
		gbConstraints.gridx = 1;

		bottomPanel.setVisible(true);
		pa.add(bottomPanel, gbConstraints);
		
		pa.getContentPane().repaint();
		
		// add event listener for when text is entered into textarea
		usernameField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {
				if (!usernameField.getText().equals("")) {
					submitButton.setEnabled(true);
				} else {
					submitButton.setEnabled(false);
				}
			}

			@Override
			public void keyReleased(KeyEvent event) {
				if (!usernameField.getText().equals("")) {
					submitButton.setEnabled(true);
				} else {
					submitButton.setEnabled(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent event) {
				if (!usernameField.getText().equals("")) {
					submitButton.setEnabled(true);
				} else {
					submitButton.setEnabled(false);
				}
			}
		});

		// add event listener for when submit button is pressed
		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Go to new state if necessary
				if (PhotoAlbum.backend.containsUser(usernameField.getText())) {

					errorLabel.setVisible(false);
					user = usernameField.getText();
					
					if (usernameField.getText().equalsIgnoreCase("admin")) {
						// go to state 2, admin view
						processEvent();
					} else {
						processEvent();
					}

					// Entered name not valid, tell user the error
				} else {
					errorLabel.setVisible(true);
					// refresh jframe
					pa.revalidate();
					pa.repaint();
				}

			}
		});

	}

	// Processes events to move to other states
	public PhotoAlbumState processEvent() {

		// JButton b = (JButton) lastEvent.getSource();

		if (usernameLabel.getText().equalsIgnoreCase("admin")) {
			// go to admin view, state 2
		} else {
			// go to standard view, state 3
			PhotoAlbumStore.newAlbum3State.enter();
			return PhotoAlbumStore.newAlbum3State;
		}

		return null;
	}

	public static Login1State getInstance() {
		if (instance == null) {
			instance = new Login1State();
		}
		return instance;
	}

}
