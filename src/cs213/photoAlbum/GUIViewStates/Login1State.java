package cs213.photoAlbum.GUIViewStates;

import javax.swing.JButton;

public class Login1State extends PhotoAlbumState {

	/**
	 * Singleton instance 
	 */
	private static Login1State instance = null;	
	
	//Constructor
	public Login1State(){}
	
	//Called when state is entered, build state
	public void enter(){
		
	}
	
	//Processes events to move to other states
	public PhotoAlbumState processEvent(){
		
		JButton b = (JButton)lastEvent.getSource();
		
		return null;
	}
	
	public static Login1State getInstance() {
		if (instance == null) {
			instance = new Login1State();
		}
		return instance;
	}
	
}
