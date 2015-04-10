package cs213.photoAlbum.model;

import java.util.ArrayList;

/**
 * The User class allows for the creation of a User object.
 * A User has an id, fullName, and an ArrayList of albums.
 * This class includes methods getId to get the id of a User, getFullName to get the full name of a User, getAlbums to get the ArrayList of albums for a User, setId to set the id of a User, setFullName to set the name of a User, addAlbum to add an album to a User's albumList, removeAlbum to remove an album fro a User's albumList, renameAlbum to rename an album in a User's albumList, getAlbum to get an album from a User's albumList, and albumExists to check if an album exists in a User's albumList.
 * @author Zack Colello and Anna Genke
 *
 */
public interface IUser {

	/**
	 * Gets the id of a User
	 * @return String of the id for a User
	 * @author Zack Colello
	 */
	public abstract String getId();

	/**
	 * Gets the full name of a User
	 * @return String of the full name for a User
	 * @author Zack Colello
	 */
	public abstract String getfullName();

	/**
	 * Returns an ArrayList of albums for the User
	 * @return ArrayList of albums for a User
	 * @author Zack Colello
	 */
	public abstract ArrayList<album> getAlbums();

	/**
	 * Sets the id of a User
	 * @param id The id to be set for a User
	 */
	public abstract void setId(String id);

	/**
	 * Sets the full name of a User
	 * @param fullName String of a full name to be set for that User
	 * @author Zack Colello
	 */
	public abstract void setfullName(String fullName);

	/**
	 * Adds an album to a User's albumList
	 * @param name String of album to be added
	 * @return True if album was successfully added, false if otherwise
	 * @author Zack Colello
	 */
	public abstract boolean addAlbum(String name);

	/**
	 * Removes an album from a User's albumList
	 * @param albumName Album name to be removed from a User's albumList
	 * @return True if album was successfully removed, false if otherwise
	 * @author Zack Colello
	 */
	public abstract boolean removeAlbum(String albumName);

	/**
	 * Renames an album for a given User
	 * @param album Album to be renamed
	 * @param name String of new name for the album
	 * @return True on successfuly rename, false on failure
	 * @author Zack Colello
	 */
	public abstract boolean renameAlbum(Ialbum album, String name);

	/**
	 * Gets the album for a given User
	 * @param albumName String for name of album to be returned
	 * @return Album of User from their albumList, or null if User does not have that album
	 * @author Zack Colello
	 */
	public abstract Ialbum getAlbum(String albumName);

	/**
	 * Checks if an album exists for a User
	 * @param albumName String that is the name of an album
	 * @return True if album exists for that User, false if otherwise
	 * @author Zack Colello
	 */
	public abstract boolean albumExists(String albumName);
	
	/**
	 * Returns an array list of photos that belong to the user's albums.
	 * @return ArrayList of photos that belong to all albums of a user
	 */
	public ArrayList<photo> getUserPhotos();

	/**
	 * 
	 * @param fileName String of photo to be added to a User's underlying array of photos
	 * @param caption String with caption to be added
	 * @return True on success, false on failure
	 */
	public boolean addUserPhoto(String fileName, String caption);
	
	/**
	 * Detects whether a photo exists in a User's photo list
	 * @param photoName Name of photo to be found
	 * @return True if photo exists in User's photos, false otherwise
	 */
	public boolean photoExists(String photoName);
	
	/**
	 * Gets the index of a photo for a User's array list of photos
	 * @param photoName Name of photo for index to be retrieved
	 * @return Index of photo, -1 if not in array list
	 */
	public int getPhotoIndex(String photoName);
	
	/**
	 * Gets a photo from a User's ArrayList of photos
	 * @param photoName String with name of photo to be gotten
	 * @return A photo object with the given String photoName
	 */
	public photo getPhoto(String photoName);
	
	public void renameAlbum(String oldAlbumName, String newAlbumName);

}