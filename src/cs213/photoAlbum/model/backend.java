package cs213.photoAlbum.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The backend class allows for the creation of a backend instance. 
 * A backend has a hashMap called database that contains all Users.
 * @author Zack Colello and Anna Genke
 *
 */
public class backend implements java.io.Serializable, Ibackend{

	
	/**
	 * Instance of a hashMap that holds all instances of users 
	 */
	private HashMap<String, User> database;

	/**
	 * Constructor for the database of all users 
	 */
	public backend() {
		database = new HashMap<String, User>();
	}

	
	//read in serial file here
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ibackend#readDatabase()
	 */
	public final void readDatabase() throws IOException, ClassNotFoundException{
		
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ibackend#writeDatabase(java.util.HashMap)
	 */
	public final void writeDatabase(HashMap<String, User> database) throws IOException{
	}
	
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ibackend#addUser(cs213.photoAlbum.model.User)
	 */
	public void addUser(User newUser){
		database.put(newUser.getId(), newUser);
	}

	public void addUser(String ID, String Name){
		
		User newUser = new User(ID, Name);
		database.put(newUser.getId(), newUser);
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ibackend#removeUser(java.lang.String)
	 */
	public boolean removeUser(String id) {
		
		return database.remove(id) != null;

	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ibackend#returnList()
	 */
	public ArrayList<User> returnList() {

		ArrayList<User> list = new ArrayList<User>();
		
		for (String key : database.keySet()) {
			list.add(database.get(key));
		}

		
		return list;

	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ibackend#containsUser(java.lang.String)
	 */
	public boolean containsUser(String id){
		
		return database.containsKey(id);

	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ibackend#getUser(java.lang.String)
	 */
	public IUser getUser(String id){
		
		return database.get(id);
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ibackend#getDatabase()
	 */
	public HashMap<String, User> getDatabase(){
		
		return database;
		
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ibackend#setDatabase(java.util.HashMap)
	 */
	public void setDatabase(HashMap<String, User> dataMap){
		
		database = dataMap;
		
	}

}
