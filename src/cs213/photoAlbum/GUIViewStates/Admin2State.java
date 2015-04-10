package cs213.photoAlbum.GUIViewStates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs213.photoAlbum.model.User;

public class Admin2State extends PhotoAlbumState {

	private static Admin2State instance = null;

	public Admin2State(){

	}

	public void enter(){
		System.out.println("Admin state");

		// Grab current JFrame
		Frame[] frames = Frame.getFrames();
		Admin2Store.pa = (PhotoAlbum) frames[0];

		// Clear items from that state
		Admin2Store.pa.getContentPane().removeAll();
		Admin2Store.pa.getContentPane().repaint();
		Admin2Store.pa.getContentPane().revalidate();


		Admin2Store.gbl = new GridBagLayout();
		Admin2Store.gbc = new GridBagConstraints();
		Admin2Store.MainPanel = new JPanel();
		Admin2Store.MainPanel.setLayout(Admin2Store.gbl);

		//Top BAR
		JPanel AdminBar = new AdminBarPanel();
		AdminBar= (JPanel)((AdminBarPanel)AdminBar).createAdminBar();

		Admin2Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		Admin2Store.gbc.anchor= GridBagConstraints.NORTH;
		Admin2Store.gbc.gridx = 0;
		Admin2Store.gbc.gridy = 0;
		Admin2Store.gbc.weightx = .5;
		Admin2Store.gbc.gridwidth = 2;
		Admin2Store.MainPanel.add(AdminBar, Admin2Store.gbc);

		//Label for Users
		Admin2Store.UserLabelPanel = new JPanel();
		Admin2Store.UserLabelPanel.setBorder(new EtchedBorder());


		Admin2Store.UserLabel = new JLabel();
		Admin2Store.UserLabel.setText("Users");
		//Admin2Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		Admin2Store.gbc.gridx = 0;
		Admin2Store.gbc.gridy = 1;
		Admin2Store.gbc.weightx = 1;
		Admin2Store.gbc.gridwidth = 2;

		Admin2Store.UserLabelPanel
		.add(Admin2Store.UserLabel);




		Admin2Store.gbc.gridx = 0;
		Admin2Store.gbc.gridy = 1;
		Admin2Store.gbc.weightx = 1;
		Admin2Store.gbc.gridwidth = 1;

		Admin2Store.MainPanel.add(Admin2Store.UserLabelPanel, Admin2Store.gbc);


		Admin2Store.UserPanel = new JPanel();
		//Admin2Store.innerPanel = new JPanel();

		Admin2Store.listModel = new DefaultListModel<User>();

		Admin2Store.database = PhotoAlbum.backend.returnList();

		for(User u: Admin2Store.database){
			if(!u.getId().equalsIgnoreCase("admin")){
				Admin2Store.listModel.addElement(u);
			}
		}


		Admin2Store.UserList = new JList<User>(Admin2Store.listModel); 

		Admin2Store.UserList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		Admin2Store.UserList.setLayoutOrientation(JList.VERTICAL);
		Admin2Store.UserList.setVisibleRowCount(-1);


		//Admin2Store.innerPanel.setBorder(new EtchedBorder());;
		//Admin2Store.innerPanel.setLayout(Admin2Store.gbl);

		Admin2Store.UserScroll = new JScrollPane(Admin2Store.UserList);
		Admin2Store.UserScroll.setVisible(true);
		Admin2Store.UserScroll
		.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Admin2Store.UserScroll.setLayout(new ScrollPaneLayout());
		Admin2Store.UserScroll.setVisible(true);

		Admin2Store.UserPanel.setLayout(new BorderLayout());
		Admin2Store.UserPanel.setPreferredSize(new Dimension(100, 510));

		Admin2Store.gbc.gridheight = GridBagConstraints.REMAINDER;
		Admin2Store.gbc.gridx = 0;
		Admin2Store.gbc.gridy = 2;
		Admin2Store.gbc.weightx = 1;
		Admin2Store.gbc.gridwidth = 2;

		Admin2Store.UserPanel
		.add(Admin2Store.UserScroll, BorderLayout.CENTER);



		Admin2Store.gbc.gridx = 0;
		Admin2Store.gbc.gridy = 2;
		Admin2Store.gbc.weightx = .8;
		Admin2Store.gbc.gridwidth = 1;
		Admin2Store.UserPanel.setBorder(new EtchedBorder());
		Admin2Store.UserPanel.setVisible(true);

		Admin2Store.MainPanel.add(Admin2Store.UserPanel, Admin2Store.gbc);


		Admin2Store.ButtonsPanel = new JPanel();
		Admin2Store.ButtonsPanel.setLayout(Admin2Store.gbl);
		Admin2Store.ButtonsPanel.setPreferredSize(new Dimension(100, 540));
		Admin2Store.bgbc = new GridBagConstraints();
		Admin2Store.bgbl = new GridBagLayout();
		Admin2Store.ButtonsPanel.setLayout(Admin2Store.bgbl);

		Admin2Store.AddUserButton = new JButton("Add User");
		Admin2Store.bgbc.gridx = 0;
		Admin2Store.bgbc.gridy = 0;
		Admin2Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		Admin2Store.gbc.fill = GridBagConstraints.VERTICAL;
		Admin2Store.ButtonsPanel.add(Admin2Store.AddUserButton, Admin2Store.bgbc);

		Admin2Store.RemoveUserButton = new JButton("Remove User");
		Admin2Store.RemoveUserButton.setEnabled(false);
		Admin2Store.bgbc.gridx = 1;
		Admin2Store.bgbc.gridy = 0;
		Admin2Store.bgbc.weightx=.05;
		Admin2Store.gbc.fill = GridBagConstraints.HORIZONTAL;
		Admin2Store.ButtonsPanel.add(Admin2Store.RemoveUserButton, Admin2Store.bgbc);

		Admin2Store.fillerPanel = new JPanel();
		Admin2Store.bgbc.gridx = 0;
		Admin2Store.bgbc.gridy = 1;
		Admin2Store.bgbc.weighty = 1;
		Admin2Store.bgbc.weightx = 1;
		Admin2Store.bgbc.fill = GridBagConstraints.BOTH;
		Admin2Store.bgbc.gridwidth = 3;
		Admin2Store.ButtonsPanel.add(Admin2Store.fillerPanel, Admin2Store.bgbc);

		Admin2Store.gbc.gridx = 1;
		Admin2Store.gbc.gridy = 1;
		Admin2Store.gbc.weightx = .2;
		Admin2Store.gbc.weighty = 1;
		Admin2Store.ButtonsPanel.setBorder(new EtchedBorder());
		Admin2Store.ButtonsPanel.setVisible(true);

		Admin2Store.MainPanel.add(Admin2Store.ButtonsPanel, Admin2Store.gbc);

		Admin2Store.pa.add(Admin2Store.MainPanel, Admin2Store.gbc);

		//fillUserList;

		AddListeners();

	}


	public void fillUserList(){



	}












	public void AddListeners() {

		Admin2Store.Add = new JButton("Add");
		Admin2Store.Cancel = new JButton("Cancel");
		
		
		//Even listener for list selection
		
		
		Admin2Store.UserList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				if(Admin2Store.UserList.getSelectedIndex()!=-1){
				Admin2Store.RemoveUserButton.setEnabled(true);
				}else{
				Admin2Store.RemoveUserButton.setEnabled(false);
			}
			}

		});
		
		

		// Event listener for Add Album Button
		Admin2Store.AddUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin2Store.AddUserButton.setEnabled(false);
				Admin2Store.RemoveUserButton.setEnabled(false);
				// Create GBL & GBC for filler panel
				Admin2Store.filgbl = new GridBagLayout();
				Admin2Store.filgbc = new GridBagConstraints();
				Admin2Store.fillerPanel.setLayout(Admin2Store.filgbl);
				Admin2Store.filgbc.insets = new Insets(10, 10, 10, 10);

				// Create Name field
				Admin2Store.UserName = new JLabel("User Name:");
				Admin2Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Admin2Store.filgbc.gridx = 0;
				Admin2Store.filgbc.gridy = 0;
				Admin2Store.fillerPanel.add(Admin2Store.UserName,
						Admin2Store.filgbc);

				// Create NAme field
				Admin2Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Admin2Store.filgbc.weightx = .5;
				Admin2Store.filgbc.gridx = 1;
				Admin2Store.filgbc.gridy = 0;
				Admin2Store.NameField = new JTextField();
				Admin2Store.NameField.setSize(new Dimension(50, 20));
				Admin2Store.fillerPanel.add(Admin2Store.NameField,
						Admin2Store.filgbc);

				// Create ID field
				Admin2Store.UserID = new JLabel("User ID:");
				Admin2Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Admin2Store.filgbc.gridx = 0;
				Admin2Store.filgbc.gridy = 1;
				Admin2Store.fillerPanel.add(Admin2Store.UserID,
						Admin2Store.filgbc);

				// Create ID field
				Admin2Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Admin2Store.filgbc.weightx = .5;
				Admin2Store.filgbc.gridx = 1;
				Admin2Store.filgbc.gridy = 1;
				Admin2Store.IDField = new JTextField();
				Admin2Store.IDField.setSize(new Dimension(50, 20));
				Admin2Store.fillerPanel.add(Admin2Store.IDField,
						Admin2Store.filgbc);

				// Create 'ADD' Button
				Admin2Store.filgbc.fill = GridBagConstraints.HORIZONTAL;
				Admin2Store.filgbc.weightx = .5;
				Admin2Store.filgbc.gridwidth = 1;
				Admin2Store.filgbc.gridx = 0;
				Admin2Store.filgbc.gridy = 2;
				Admin2Store.Add.setEnabled(false);
				Admin2Store.Add.setVisible(true);
				Admin2Store.fillerPanel.add(Admin2Store.Add,
						Admin2Store.filgbc);

				// Create 'Cancel' Button
				Admin2Store.filgbc.weighty = 1;
				Admin2Store.filgbc.weightx = .5;
				Admin2Store.filgbc.gridx = 1;
				Admin2Store.filgbc.gridwidth = 1;
				Admin2Store.filgbc.gridy = 2;
				Admin2Store.Cancel.setVisible(true);
				Admin2Store.fillerPanel.add(Admin2Store.Cancel,
						Admin2Store.filgbc);

				// Create whitespace filler panel
				Admin2Store.fillerbottom = new JPanel();
				Admin2Store.filgbc.gridy = 2;
				Admin2Store.filgbc.gridx = 0;
				Admin2Store.filgbc.weightx = 1;
				Admin2Store.filgbc.weighty = 1;
				Admin2Store.filgbc.gridwidth = 2;
				Admin2Store.fillerbottom.setPreferredSize(new Dimension(100,
						350));
				Admin2Store.filgbc.fill = GridBagConstraints.BOTH;
				Admin2Store.fillerPanel.add(Admin2Store.fillerbottom,
						Admin2Store.filgbc);
				
				Admin2Store.errLabel = new JLabel("Error, User already Exists with that ID");


				// Event listener for when text is entered into NameField
				Admin2Store.NameField.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent event) {
						if ((!Admin2Store.NameField.getText().equals(""))&&(!Admin2Store.IDField.getText().equals(""))) {
							Admin2Store.Add.setEnabled(true);
						} else {
							Admin2Store.Add.setEnabled(false);
						}
					}

					@Override
					public void keyReleased(KeyEvent event) {
						if ((!Admin2Store.NameField.getText().equals(""))&&(!Admin2Store.IDField.getText().equals(""))) {
							Admin2Store.Add.setEnabled(true);
						} else {
							Admin2Store.Add.setEnabled(false);
						}
					}

					@Override
					public void keyPressed(KeyEvent event) {
						if ((!Admin2Store.NameField.getText().equals(""))&&(!Admin2Store.IDField.getText().equals(""))) {
							Admin2Store.Add.setEnabled(true);
						} else {
							Admin2Store.Add.setEnabled(false);
						}
					}
				});

				// Event listener for when text is entered into IDField
				Admin2Store.IDField.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent event) {
						if ((!Admin2Store.NameField.getText().equals(""))&&(!Admin2Store.IDField.getText().equals(""))) {
							Admin2Store.Add.setEnabled(true);
						} else {
							Admin2Store.Add.setEnabled(false);
						}
					}

					@Override
					public void keyReleased(KeyEvent event) {
						if ((!Admin2Store.NameField.getText().equals(""))&&(!Admin2Store.IDField.getText().equals(""))) {
							Admin2Store.Add.setEnabled(true);
						} else {
							Admin2Store.Add.setEnabled(false);
						}
					}

					@Override
					public void keyPressed(KeyEvent event) {
						if ((!Admin2Store.NameField.getText().equals(""))&&(!Admin2Store.IDField.getText().equals(""))) {
							Admin2Store.Add.setEnabled(true);
						} else {
							Admin2Store.Add.setEnabled(false);
						}
					}
				});


				Admin2Store.pa.revalidate();
				Admin2Store.pa.repaint();

			}
		});

		// Event listener for Cancel Album Button
		Admin2Store.Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Admin2Store.NameField.setVisible(false);
				Admin2Store.UserName.setVisible(false);
				Admin2Store.IDField.setVisible(false);
				Admin2Store.UserID.setVisible(false);
				Admin2Store.Cancel.setVisible(false);
				Admin2Store.Add.setVisible(false);
				Admin2Store.AddUserButton.setEnabled(true);
				Admin2Store.fillerbottom.setVisible(false);
				Admin2Store.errLabel.setVisible(false);
				
				if(Admin2Store.UserList.getSelectedIndex()!=-1){
					Admin2Store.RemoveUserButton.setEnabled(true);
				}else{
					Admin2Store.RemoveUserButton.setEnabled(false);
				}
			}
		});

		// Event listener for Create Album Button
		Admin2Store.Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(PhotoAlbum.backend.containsUser(Admin2Store.IDField.getText())){
					addDubError();

				}else{

					PhotoAlbum.backend.addUser(Admin2Store.IDField.getText(),Admin2Store.NameField.getText());
					Admin2State.instance = null;
					PhotoAlbumStore.admin2State.enter();
				}

			/*	Admin2Store.NameField.setVisible(false);
				Admin2Store.UserName.setVisible(false);
				Admin2Store.IDField.setVisible(false);
				Admin2Store.UserID.setVisible(false);
				Admin2Store.Cancel.setVisible(false);
				Admin2Store.Add.setVisible(false);
				Admin2Store.AddUserButton.setEnabled(true);
				Admin2Store.fillerbottom.setVisible(false);
    */

			}
		});

		// Event listener for Delete Album Button
		Admin2Store.RemoveUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhotoAlbum.backend.removeUser(((User)Admin2Store.UserList.getSelectedValue()).getId());
				PhotoAlbumStore.admin2State.enter();
			}
		});


	}





	public void addDubError() {

		
		Admin2Store.errLabel.setVisible(true);
		Admin2Store.errLabel.setForeground(Color.red);
		Admin2Store.bgbc.gridy = 2;
		Admin2Store.bgbc.gridx = 0;
		Admin2Store.bgbc.weighty = 1;
		Admin2Store.bgbc.weightx = 1;
		Admin2Store.bgbc.fill = GridBagConstraints.BOTH;
		Admin2Store.bgbc.gridwidth = 3;
		Admin2Store.ButtonsPanel.add(Admin2Store.errLabel, Admin2Store.bgbc);

		Admin2Store.pa.revalidate();
		Admin2Store.pa.repaint();
	}








	@Override
	PhotoAlbumState processEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Admin2State getInstance() {
		if (instance == null) {
			instance = new Admin2State();
		}
		return instance;
	}

}
