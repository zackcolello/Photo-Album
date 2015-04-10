package cs213.photoAlbum.GUIViewStates;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cs213.photoAlbum.control.Controller;
import cs213.photoAlbum.control.IController;
import cs213.photoAlbum.model.Ibackend;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.model.backend;
import cs213.photoAlbum.simpleview.CmdView;

public class PhotoAlbum extends JFrame {

	/**
	 * Stores the backend for the model portion of the application
	 */
	public static Ibackend backend = new backend();

	/**
	 * Creates Controller object to pass information from view to model
	 */
	public static IController controller = new Controller(backend);

	public ActionListener al = new PhotoAlbumController();

	public void buildLayout() {

		((PhotoAlbumController) al).start();

	}

	public PhotoAlbum() {
		// give title to JFrame
		super("Photo Album");
		this.setPreferredSize(new Dimension(900, 600));
		buildLayout();
		((PhotoAlbumController) al).start();

		
		User admin = new User("admin", "admin");
		backend.addUser(admin);

		User z = new User("z", "z");
		backend.addUser(z);

	}

	public static void main(String[] args) {

		PhotoAlbum photoAlbumGUI = new PhotoAlbum();
		// photoAlbumGUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
		photoAlbumGUI.setResizable(false);
		photoAlbumGUI.pack();
		photoAlbumGUI.setLocationRelativeTo(null);
		photoAlbumGUI.setVisible(true);

		try {
			HashMap<String, User> dataMap = controller.readDatabase();
			backend.setDatabase(dataMap);

		} catch (Exception e) {
			// Database file does not exist yet, create file
			File f = new File("data" + File.separator + "data.dat");

		}

		photoAlbumGUI.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					controller.writeDatabase(backend.getDatabase());
				} catch (IOException e) {
					e.printStackTrace();
				}

				System.exit(0);
			}
		});

	}

}
