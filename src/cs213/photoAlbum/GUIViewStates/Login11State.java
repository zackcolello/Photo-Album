package cs213.photoAlbum.GUIViewStates;

	import java.awt.BorderLayout;
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
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JTextArea;
	import javax.swing.JTextField;
	import javax.swing.border.EmptyBorder;

	public class Login11State extends PhotoAlbumState {

		/**
		 * Singleton instance
		 */
		private static Login1State instance = null;

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
		public Login11State() {
		}

		// Called when state is entered, build state
		public void enter() {

			// Grab current JFrame
			Frame[] frames = Frame.getFrames();
			PhotoAlbum pa = (PhotoAlbum) frames[0];

			pa.setLayout(gbLayout);

			// Add components to mainPanel, add mainPanel

			bottomPanel.setLayout(new BorderLayout());
			welcomeLabel.setText("Welcome to PhotoAlbum34");
			welcomeLabel.setFont(new Font("Serif", Font.ITALIC, 50));
			topPanel.add(welcomeLabel, BorderLayout.CENTER);

			// Set up bottomPanel with labels and buttons

			usernameField.setPreferredSize(new Dimension(130, 20));
			submitButton.setPreferredSize(new Dimension(200, 40));
			submitButton.setEnabled(true);

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
			pa.add(bottomPanel, gbConstraints);


		}

		// Processes events to move to other states
		public PhotoAlbumState processEvent(String button) {

			JButton b = (JButton) lastEvent.getSource();


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

			// add event listener for when button is pressed
			submitButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// Execute when button is pressed
					if (!PhotoAlbum.backend.containsUser(usernameField.getText())) {
						
						JTextField errorMessage = new JTextField("Error: User not found");
				
						bottomPanel.add(errorMessage, BorderLayout.SOUTH);
						
					}

				}
			});
			
			return null;
		}

		public static Login1State getInstance() {
			if (instance == null) {
				instance = new Login1State();
			}
			return instance;
		}

	}

	

