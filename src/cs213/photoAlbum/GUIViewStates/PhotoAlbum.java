package cs213.photoAlbum.GUIViewStates;

import javax.swing.JFrame;

public class PhotoAlbum extends JFrame {

	
	public void buildLayout(){
		
		
	}
	
	public PhotoAlbum(){
		
		
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
