package cs213.photoAlbum.model;

import java.util.ArrayList;

/**
 * The album class allows for the creation of an album instance.
 * An album has a name and an ArrayList of photos.
 * This class has methods getName to return the name of an album, setAlbumName to set the name of an album, getPhoto to return one given photo, getPhotos to return an arrayList of all photos, addPhoto to add a photo to an album, removePhoto to remove an album from an album, and editCaption to edit the caption of a photo.
 * @author Zack Colello and Anna Genke
 *
 */
public class album implements Ialbum, java.io.Serializable {

	/**
	 * Stores the name of an album for a User
	 */
	private String name;
	/**
	 * Stores an ArrayList of all photos in an album
	 */
	private ArrayList<photo> photos;

	/**
	 * Creates an instance of the album class
	 * @param name String with the name of album to be created
	 * @author Zack Colello
	 */
	public album(String name) {
		this.name = name;
		photos = new ArrayList<photo>();
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ialbum#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ialbum#setAlbumName(java.lang.String)
	 */
	public void setAlbumName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ialbum#getPhoto(java.lang.String)
	 */
	public photo getPhoto(String fileName){
		
		for(photo p:photos){
			if(fileName.equalsIgnoreCase(p.getFileName())){
				return p;
			}
		}
		
		return null;
		
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ialbum#getPhotos()
	 */
	public ArrayList<photo> getPhotos() {
		return photos;
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ialbum#addPhoto(cs213.photoAlbum.model.photo)
	 */
	public boolean addPhoto(photo photo) {
		return photos.add(photo);
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ialbum#removePhoto(cs213.photoAlbum.model.photo)
	 */
	public boolean removePhoto(Iphoto photo) {
		return photos.remove(photo);
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ialbum#editCaption(cs213.photoAlbum.model.photo, java.lang.String)
	 */
	public void editCaption(Iphoto photo, String caption) {
		photo.setCaption(caption);
	}
	
	/*
	 * (non-Javadoc)
	 * @see cs213.photoAlbum.model.Ialbum#photoExists(java.lang.String)
	 */
	public boolean photoExists(String photoName){
		
		for(photo p: photos){
			if(p.getFileName().equals(photoName)){
				return true;
			}
		}
		
		return false;
	}

}
