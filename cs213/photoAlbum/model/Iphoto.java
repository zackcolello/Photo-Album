package cs213.photoAlbum.model;

import java.util.ArrayList;

/**
 * The photo class allows for the creation of a photo object.
 * A photo object has a file name, caption, calendar instance, and an array of tags. 
 * This class includes methods getFileName to return the name of the photo, getCaption to return the caption, getCalendar to return the calendar instance, getTag to get the array of tags for that photo, as well as methods setCaption to set the caption of a photo, addTag to add the tag, and removeTag to remove the tag.
 * @author Zack Colello and Anna Genke
 *
 */
public interface Iphoto {

	/**
	 * Gets the name of a file 
	 * @return String representing the name of a  photo file
	 * @author Anna Genke
	 */
	public abstract String getFileName();

	/**
	 * Gets the caption of a photo
	 * @return String representing the caption of a photo
	 * @author Anna Genke
	 */
	public abstract String getCaption();

	/**
	 * Gets the instance of the Calendar of the photo
	 * @return The string form of the calendar instance
	 * @author Anna Genke
	 */
	public abstract String getCalendar();

	/**
	 * Gets the tag(s) of the photo
	 * @return An arrayList of type tag containing the tag(s) of the photo
	 * @author Anna Genke
	 */
	public abstract ArrayList<tag> getTag();

	/**
	 * Edits the caption of the photo instance 
	 * @param caption A string representing the caption of the photo
	 * @author Anna Genke
	 */
	public abstract void setCaption(String caption);

	/**
	 * Adds a tag to a given photo
	 * @param tag An instance of tag as defined in tag.java
	 * @return True if the tag was successfully added, false if otherwise
	 * @author Anna Genke
	 */
	public abstract boolean addTag(tag tag);

	/**
	 * Removes a tag from a given photo
	 * @param type String with type of tag to be removed
	 * @param value A value of tag as defined in tag.java
	 * @return True if the tag was successfully removed, false if otherwise
	 * @author Anna Genke
	 */
	public abstract boolean removeTag(String type, String value);
	
	/**
	 * Sets the album count of a photo
	 * @param count Sets the count of albums a photo belongs to
	 */
	public void setCount(int count);
	
	/**
	 * Gets the count of how many albums a photo belongs to
	 * @return Returns an int that represents the number of albums the photo is in
	 * @author Zack Colello
	 */
	public int getCount();

}