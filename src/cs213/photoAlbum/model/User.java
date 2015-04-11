package cs213.photoAlbum.model;

import java.util.ArrayList;

/**
 * The User class allows for the creation of a User object.
 * A User has an id, fullName, and an ArrayList of albums.
 * This class includes methods getId to get the id of a User, getFullName to get the full name of a User, getAlbums to get the ArrayList of albums for a User, setId to set the id of a User, setFullName to set the name of a User, addAlbum to add an album to a User's albumList, removeAlbum to remove an album from a User's albumList, renameAlbum to rename an album in a User's albumList, getAlbum to get an album from a User's albumList, and albumExists to check if an album exists in a User's albumList.
 * @author Zack Colello and Anna Genke
 *
 */
public class User implements java.io.Serializable, IUser {

	/**
	 * Holds the id of a User
	 */
	private String id;
	
	/**
	 * Holds the full name of a User
	 */
	private String fullName;
	
	/**
	 * Holds the arrayList of albums for a User 
	 */
	private ArrayList<album> albums;
	
	/**
	 * Holds an underlying arrayList of all photos for a User
	 */
	private ArrayList<photo> userPhotos;
	
	/**
	 * Constructor to create a User instance
	 * @param id the id of a User to be created
	 * @param fullName the full name of a User to be created
	 */
	public User(String id, String fullName) {

		this.id = id;
		this.fullName = fullName;
		albums = new ArrayList<album>();
		userPhotos = new ArrayList<photo>();
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#getId()
	 */
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#getfullName()
	 */
	public String getfullName() {
		return fullName;
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#getAlbums()
	 */
	public ArrayList<album> getAlbums() {
		return albums;
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#setId(java.lang.String)
	 */
	public void setId(String id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#setfullName(java.lang.String)
	 */
	public void setfullName(String fullName) {
		this.fullName = fullName;
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#addAlbum(java.lang.String)
	 */
	public boolean addAlbum(String name){
		album newAlbum = new album(name);
		
		return albums.add(newAlbum);
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#removeAlbum(java.lang.String)
	 */
	public boolean removeAlbum(String albumName){

		for(Ialbum a:albums){
			if(albumName.equalsIgnoreCase(a.getName())){
				return albums.remove(a);
			}
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#renameAlbum(cs213.photoAlbum.model.Ialbum, java.lang.String)
	 */
	public boolean renameAlbum(Ialbum album, String name){
		try{
			album.setAlbumName(name);
		}catch(Exception e){
			return false;
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#getAlbum(java.lang.String)
	 */
	public Ialbum getAlbum(String albumName){
		
		for(Ialbum s: albums){
			if(s.getName().equalsIgnoreCase(albumName)){
				return s;
			}
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#albumExists(java.lang.String)
	 */
	public boolean albumExists(String albumName){
		
		for(Ialbum a:albums){
			if(albumName.equalsIgnoreCase(a.getName())){
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#getUserPhotos
	 */
	public ArrayList<photo> getUserPhotos() {
		return userPhotos;
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#addUserPhoto(java.lang.String, java.lang.String)
	 */
	public boolean addUserPhoto(String fileName, String caption) {
		
		photo newPhoto = new photo(fileName, caption);
		
		return userPhotos.add(newPhoto);
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#photoExists(java.lang.String)
	 */
	public boolean photoExists(String photoName){
		
		for(photo p: userPhotos){
			if(p.getFileName().equals(photoName)){
				return true;
			}
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#getPhotoIndex(java.lang.String)
	 */
	public int getPhotoIndex(String photoName){
		
		for(photo p: userPhotos){
			if(p.getFileName().equals(photoName)){
				return userPhotos.indexOf(p);
			}
		}
		
		return -1;
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.IUser#getPhoto(java.lang.String)
	 */
	public photo getPhoto(String photoName){
		
		for(photo p: userPhotos){
			if(p.getFileName().equals(photoName)){
				return p;
			}
		}
		
		return null;
		
	}


	@Override
	public void renameAlbum(String oldAlbumName, String newAlbumName) {

			for(album a: albums){
				if(a.getName().equals(oldAlbumName)){
					a.renameAlbum(newAlbumName);
				}
			}
		
	}
	
	public String toString(){
		
		return "ID: "+id+"  -  User: "+fullName;
	}

}
