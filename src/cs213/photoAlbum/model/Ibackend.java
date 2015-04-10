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
public interface Ibackend {

	//read in serial file here
	/**
	 * Allows database to be serialized 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @author Anna Genke
	 */
	public abstract void readDatabase() throws IOException,
			ClassNotFoundException;

	/**
	 * Allows database to be read after being serialized 
	 * @param database A hashmap of all users
	 * @throws IOException
	 * @author Anna Genke
	 */
	public abstract void writeDatabase(HashMap<String, User> database)
			throws IOException;

	/**
	 * Adds a instance of User into the database hashMap
	 * @param newUser instance of user as defined in User.java
	 * @author Anna Genke
	 */
	public abstract void addUser(User newUser);
	
	
	public abstract void addUser(String ID, String Name);

	/**
	 * Removes an instance of User with given id from the hashMap
	 * @param id String that represents a User's id
	 * @return True if the user was successfully removed, and false on failure
	 * @author Anna Genke
	 */
	public abstract boolean removeUser(String id);

	/**
	 * Returns an ArrayList of all users from the hashMap
	 * @return an ArryList of type User containing all users
	 * @author Anna Genke
	 */
	public abstract ArrayList<User> returnList();

	/**
	 * Checks to see if the hashMap contains an instance of a User with given id
	 * @param id a String representing a User's id
	 * @return true if the user associated with the id is present in the list, and false otherwise
	 * @author Anna Genke
	 */
	public abstract boolean containsUser(String id);

	/**
	 * Gets instance of a User with the given id, from the hashMap 
	 * @param id: a String representing a User's id
	 * @return an instance of the User associated with the given is
	 * @author Anna Genke
	 */
	public abstract IUser getUser(String id);
	
	/**
	 * Gets database of a backend object
	 * @return HashMap of database from the backend
	 */
	public abstract HashMap<String, User> getDatabase();
	
	
	/**
	 * Sets the database for a backend
	 * @param dataMap HashMap of String, User to build database from
	 */
	public void setDatabase(HashMap<String, User> dataMap);

}