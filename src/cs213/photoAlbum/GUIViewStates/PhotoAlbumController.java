package cs213.photoAlbum.GUIViewStates;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhotoAlbumController implements ActionListener{

	PhotoAlbumState currentState;
	
	public void start(){
		//currentState = PhotoAlbumState.loginState;
		currentState.enter();
	}
	
	public void actionPerformed(ActionEvent e) {
		PhotoAlbumState.lastEvent = e; 
		currentState = currentState.processEvent();
	}
	
}
