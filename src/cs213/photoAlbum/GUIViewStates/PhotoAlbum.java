package cs213.photoAlbum.GUIViewStates;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

import cs213.photoAlbum.control.Controller;
import cs213.photoAlbum.control.IController;
import cs213.photoAlbum.model.Ibackend;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.model.album;
import cs213.photoAlbum.model.backend;
import cs213.photoAlbum.model.photo;
import cs213.photoAlbum.model.tag;

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

		
		//Create test user for debugging
		User z = new User("z", "z");
		z.addAlbum("Things");
		photo photo = new photo("docs/puppy.jpg", "A cute puppy");
		Album5Store.currentPhoto = photo;
		Album5Store.currentAlbum = (album) z.getAlbum("Things");
		photo photo2 = new photo("docs/Carlton.png", "This is Carlton");
		
		//Add tags for test data
		tag ct1 = new tag("Name", "Carlton");
		tag ct2 = new tag("Location", "Bel-Air");
		tag ct3 = new tag("Cousin", "Will Smith");
		tag pt1 = new tag("Name", "Fluffy");
		tag pt2 = new tag("Location", "New Brunswick");
		tag pt3 = new tag("Fur Color", "Brown");
		photo2.addTag(ct1); photo2.addTag(ct2); photo2.addTag(ct3);
		photo.addTag(pt1); photo.addTag(pt2); photo.addTag(pt3);
		
		z.getAlbum("Things").addPhoto(photo);
		z.getAlbum("Things").addPhoto(photo2);
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
