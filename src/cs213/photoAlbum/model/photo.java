package cs213.photoAlbum.model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;

import cs213.photoAlbum.GUIViewStates.Album5Store;

/**
 * The photo class allows for the creation of a photo object. A photo object has
 * a file name, caption, calendar instance, and an array of tags. This class
 * includes methods getFileName to return the name of the photo, getCaption to
 * return the caption, getCalendar to return the calendar instance, getTag to
 * get the array of tags for that photo, as well as methods setCaption to set
 * the caption of a photo, addTag to add the tag, and removeTag to remove the
 * tag.
 * 
 * @author Zack Colello and Anna Genke
 *
 */
public class photo implements java.io.Serializable, Iphoto {

	/**
	 * Holds the file name of the photo
	 */
	private String fileName;

	/**
	 * Holds the local name of the photo
	 */
	private String localName;
	/**
	 * Holds the caption of the photo
	 */
	private String caption;
	/**
	 * Holds the calendar instance of the photo
	 */
	private String date;
	/**
	 * Holds the arrayList of tags for the photo
	 */
	private ArrayList<tag> tags;

	/**
	 * Holds the count of how many albums the photo belongs to
	 */
	private int count;

	// Holds actual ImageIcon of photo
	private ImageIcon photo;

	/**
	 * Creates an instance of the photo class, storing its file name, caption,
	 * array list of tags, and calendar instance
	 * 
	 * @param fileName
	 *            String, representing the name of a photo file
	 * @param caption
	 *            String, representing the caption of a photo
	 */
	public photo(String fileName, String caption) {
		this.fileName = fileName;
		this.caption = caption;

		// Add actual photo
		photo = new ImageIcon(fileName);
		
		tags = new ArrayList<tag>();

		Calendar calendar = null;
		calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);

		// convert date to specified format as MM/DD/YYYY-HH:MM:SS
		date = "";

		if (calendar.get(Calendar.MONTH) < 10) {
			date += "0";
		}

		date += Integer.toString(calendar.get(Calendar.MONTH) + 1);
		date += "/";

		if (calendar.get(Calendar.DATE) < 10) {
			date += "0";
		}
		date += Integer.toString(calendar.get(Calendar.DATE));
		date += "/";
		date += Integer.toString(calendar.get(Calendar.YEAR));
		date += "-";

		if (calendar.get(Calendar.HOUR_OF_DAY) < 10) {
			date += "0";
		}
		date += Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		date += ":";

		if (calendar.get(Calendar.MINUTE) < 10) {
			date += "0";
		}
		date += Integer.toString(calendar.get(Calendar.MINUTE));
		date += ":";

		if (calendar.get(Calendar.SECOND) < 10) {
			date += "0";
		}
		date += Integer.toString(calendar.get(Calendar.SECOND));

		int index = -1;
		localName = fileName;
		while (localName.indexOf('/') != -1) {
			index = localName.indexOf('/');
			localName = localName.substring(index + 1);
		}

		// Add actual photo object

		count = 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.model.Iphoto#getFileName()
	 */
	public String getFileName() {
		return fileName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.model.Iphoto#getCaption()
	 */
	public String getCaption() {
		return caption;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.model.Iphoto#getCalendar()
	 */
	public String getCalendar() {
		return date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.model.Iphoto#getTag()
	 */
	public ArrayList<tag> getTag() {
		return tags;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.model.Iphoto#setCaption(java.lang.String)
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.model.Iphoto#addTag(cs213.photoAlbum.model.tag)
	 */
	public boolean addTag(tag tag) {

		return tags.add(tag);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.model.Iphoto#removeTag(cs213.photoAlbum.model.tag)
	 */
	public boolean removeTag(String type, String value) {

		for (tag t : tags) {
			if (t.getType().equals(type) && t.getValue().equals(value)) {
				return tags.remove(t);
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.model.Iphoto#setCount(Java.lang.String)
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.model.Iphoto#getCount()
	 */
	public int getCount() {
		return count;
	}

	public ImageIcon getPhoto(){
		return photo;
	}
	
	public void setPhoto(ImageIcon newP){
		photo = newP;
	}
}
