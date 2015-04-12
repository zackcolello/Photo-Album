package cs213.photoAlbum.model;

import java.util.ArrayList;

/**
 * The album class allows for the creation of an album instance.
 * An album has a name and an ArrayList of photos.
 * This class has methods getName to return the name of an album, setAlbumName to set the name of an album, getPhoto to return one given photo, getPhotos to return an arrayList of all photos, addPhoto to add a photo to an album, removePhoto to remove an album from an album, and editCaption to edit the caption of a photo.
 * @author Zack Colello and Anna Genke
 *
 */
public interface Ialbum {

	/**
	 * Gets the name of an album
	 * @return String with the name of the album
	 * @author Zack Colello
	 */
	public abstract String getName();

	/**
	 * Sets the name of an album
	 * @param name String with the name of the album to be set
	 * @author Zack Colello
	 */
	public abstract void setAlbumName(String name);

	/**
	 * Gets the photo with the given file name
	 * @param fileName File name of photo to be returned
	 * @return Photo instance with the given file name
	 * @author Zack Colello
	 */
	public abstract photo getPhoto(String fileName);

	/**
	 * Gets an arrayList of all photos in an album
	 * @return An arrayList of all photos in a given album
	 * @author Zack Colello
	 */
	public abstract ArrayList<photo> getPhotos();

	/**
	 * Adds a photo instance to a given album
	 * @param photo Photo instance to be added to an album
	 * @return True if photo was successfully added, false on failure
	 * @author Zack Colello
	 */
	public abstract boolean addPhoto(photo photo);

	/**
	 * Removes a photo instance from a given album
	 * @param photo Photo instance to be removed from album
	 * @return True if photo was successfully removed, false on failure
	 * @author Zack Colello
	 */
	public abstract boolean removePhoto(Iphoto photo);

	/**
	 * Edits the caption of a photo in an album
	 * @param photo Photo instance to be changed
	 * @param caption String with new caption for the given photo object
	 * @author Zack Colello
	 */
	public abstract void editCaption(Iphoto photo, String caption);
	
	/**
	 * Checks to see if a photo exists in an album
	 * @param photoName Name of photo to be searched
	 * @return true if photo exists in album, false if not
	 */
	public boolean photoExists(String photoName);

}