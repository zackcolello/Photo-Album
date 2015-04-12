package cs213.photoAlbum.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import cs213.photoAlbum.model.Ialbum;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.model.album;
import cs213.photoAlbum.model.photo;
import cs213.photoAlbum.model.tag;

/**
 * Controller allows for communication between the model and the view. Controller does all manipulation, searching and validity checks of data.
 * @author Zack Colello and Anna Genke
 *
 */
public interface IController {

	//this will return backend's readDatabase method
	/**
	 * Calls controller's read database method
	 * @return HashMap with User in database for session persistence 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @author Zack Colello
	 */
	public abstract HashMap<String, User> readDatabase() throws IOException,
			ClassNotFoundException;

	/**
	 * Stores the contents of the database for session persistence
	 * @param database instance of hashMap that stores all Users 
	 * @throws IOException
	 * @author Zack Colello
	 */
	public abstract void writeDatabase(HashMap<String, User> database)
			throws IOException;

	/**
	 * returns an ArrayList of Users 
	 * @return An ArrayList containing the Users from the database in backend
	 * @author Zack Colello
	 */
	public abstract ArrayList<User> listUsers();

	/**
	 * Creates and adds a new instance of user to the database in backend, if the User does not already exist
	 * @param id String that represents the id of a User
	 * @param fullName String that represents the fullname of a user
	 * @return True of the User was successfully added to the database, and false otherwise
	 * @author Zack Colello
	 */
	public abstract boolean addUser(String id, String fullName);

	/**
	 * Removes a User from the database by id if present
	 * @param id String representing the id of a user
	 * @return True if the User was successfully removed and false otherwise
	 * @author Zack Colello
	 */
	public abstract boolean removeUser(String id);

	/**
	 * Creates a new instance of a photo album with given name for the given User 
	 * @param id String that is the id of the user
	 * @param albumName String that is the name of the Album to be created 
	 * @return True if album was successfully added, false otherwise
	 * @author Zack Colello
	 */
	public abstract boolean createAlbum(String id, String albumName);

	/**
	 * Deletes an Album from a User
	 * @param id Sting that is a User's id
	 * @param albumName String that is the name of the Album to be removed
	 * @return True if the Album was successfully removed, false on failure
	 * @author Zack Colello
	 */
	public abstract boolean deleteAlbum(String id, String albumName);


	/**
	 * Renames an album from a user from album to the given new name
	 * @param id String of User's id
	 * @param album Album that needs to be renamed
	 * @param newName New name for the given album
	 * @return True if album was successfully renamed, false on failure
	 * @author Zack Colello
	 */
	public abstract boolean renameAlbum(String id, Ialbum album, String newName);
	
	
	/**
	 * Gets the list of albums specific to the given user
	 * @param id String that is the id of the current User
	 * @return An arrayList of the Albums belonging to the given user
	 * @author Zack Colello
	 */
	public abstract ArrayList<album> listAlbums(String id);

	/**
	 * Returns a list of photos from the given album
	 * @param id String that is the User's id
	 * @param albumName string that is the album's name
	 * @return ArrayList of photos in the given album
	 * @author Zack Colello
	 */
	public abstract ArrayList<photo> listPhotos(String id, String albumName);

	/**
	 * Creates and adds a new instance of photo to the given album
	 * @param id String that is the id of the user
	 * @param fileName String that is the path to the photo
	 * @param caption String that is the caption of the photo
	 * @param albumName String that is the name of the album
	 * @return True if photo is successfully added, false othwise
	 * @author Zack Colello
	 */
	public abstract boolean addPhoto(String id, String fileName,
			String caption, String albumName);

	/**
	 * Moves a photo from one album to another
	 * @param id String that is the id of the User
	 * @param fileName String that is the path to the photo
	 * @param oldAlbum String that is the name of the old album
	 * @param newAlbum String that is the name of the new album
	 * @return 1 on successful move, -1 if the photo did not exist in old album, -2 otherwise
	 * @author Zack Colello
	 */
	public abstract int movePhoto(String id, String fileName, String oldAlbum,
			String newAlbum);

	/**
	 * Removes a photo from a given id's album
	 * @param id String with the id for the user for photo to be removed
	 * @param fileName String with the name of the photo to be removed
	 * @param AlbumName String with the name of the album for the photo to be removed
	 * @return True on successful photo removal, false on failure
	 * @author Zack Colello
	 */
	public abstract boolean removePhoto(String id, String fileName,
			String AlbumName);

	/**
	 * Adds a tag to a given photo
	 * @param id String with user ID
	 * @param fileName String with name of photo to be given the tag
	 * @param newtag String with name of tag to be created
	 * @return True on successful tag addition, false on failure
	 * @author Zack Colello
	 */
	public abstract boolean addTag(String id, String fileName, tag newtag);

	/**
	 * Deletes a tag from a given photo
	 * @param id String with user ID
	 * @param fileName String with name of photo for the tag to be removed from
	 * @param type String with name of tag type to be removed
	 * @param value String with name of tag value to be removed
	 * @return True on successful tag removal, false on failure
	 * @author Zack Colello
	 */
	public abstract boolean deleteTag(String id, String fileName, String type, String value);

	/**
	 * Returns an ArrayList of Strings with information about a given photo
	 * @param id String with user ID
	 * @param fileName String with name of photo for information to be listed
	 * @return An ArrayList of Strings with photo information
	 * @author Zack Colello
	 */
	public abstract ArrayList<String> listPhotoInfo(String id, String fileName);

	/**
	 * Returns an arrayList of photos in a given time period
	 * @param id String with user ID
	 * @param startDate String with the starting date of photos to be returned
	 * @param endDate String with the ending date of photos to be returned
	 * @return An ArrayList of photos that have dates between the startDate and endDate
	 * @author Zack Colello
	 */
	public abstract ArrayList<photo> getPhotosByDate(String id, String startDate,
			String endDate);

	/**
	 * Returns an ArrayList of photos that contain a given list of tags
	 * @param id String with user ID
	 * @param tagList ArrayList of strings that contain the tags for the photos to be returned
	 * @return An ArrayList of the photos with the given tag(s)
	 * @author Zack Colello
	 */
	public abstract ArrayList<photo> getPhotosByTag(String id, ArrayList<tag> tagList);
	
	
	public void printUserPhotoList(String id);


}