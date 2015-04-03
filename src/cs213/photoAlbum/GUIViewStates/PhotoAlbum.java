package cs213.photoAlbum.GUIViewStates;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class PhotoAlbum extends JFrame {

	private ActionListener al = new PhotoAlbumController();

	public void buildLayout() {

		((PhotoAlbumController)al).start();
		
	}

	public PhotoAlbum() {
		// give title to JFrame
		super("Photo Album");
		this.setPreferredSize(new Dimension(900, 600));
		buildLayout();
		((PhotoAlbumController)al).start();
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
