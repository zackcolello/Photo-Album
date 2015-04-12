package cs213.photoAlbum.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import cs213.photoAlbum.model.IUser;
import cs213.photoAlbum.model.Ialbum;
import cs213.photoAlbum.model.Ibackend;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.model.album;
import cs213.photoAlbum.model.photo;
import cs213.photoAlbum.model.tag;

/**
 * Controller allows for communication between the model and the view.
 * Controller does all manipulation, searching and validity checks of data.
 * 
 * @author Zack Colello and Anna Genke
 *
 */
public class Controller implements IController {

	/**
	 * Holds an instance of the backend class
	 */
	private Ibackend backend;
	/**
	 * Holds an instance of the Cmdview class
	 */
	private cs213.photoAlbum.simpleview.CmdView cmdview;

	public static final String storeDir = "data";
	public static final String storeFile = "data.dat";

	/**
	 * This is the constructor of the Controller class
	 * 
	 * @param backend
	 *            instance of class backend, this is the Model that stores data
	 * @param cmdview
	 *            instance of Cmdview, that acts as a simple user interface
	 * @author Zack Colello
	 */
	public Controller(Ibackend backend,
			cs213.photoAlbum.simpleview.CmdView cmdview) {
		this.backend = backend;
		this.cmdview = cmdview;
	}

	// this will return backend's readDatabase method
	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#readDatabase()
	 */
	public HashMap<String, User> readDatabase() throws IOException,
			ClassNotFoundException {

		ObjectInputStream Fin = new ObjectInputStream(new FileInputStream(
				storeDir + File.separator + storeFile));
		HashMap<String, User> output = (HashMap<String, User>) Fin.readObject();
		return output;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs213.photoAlbum.control.IController#writeDatabase(java.util.HashMap)
	 */
	public void writeDatabase(HashMap<String, User> database)
			throws IOException {

		try{
			FileOutputStream data = new FileOutputStream("data/data.dat");
		}catch(Exception e){
			File theDir = new File("data/");
			
			if(!theDir.exists()){
				theDir.mkdir();
			}
			
			File f = new File("data" + File.separator + "data.dat");
			//add file to data/
			
		}
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				"data" + File.separator + "data.dat"));
		out.writeObject(database);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#listUsers()
	 */
	public ArrayList<User> listUsers() {
		return backend.returnList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#addUser(java.lang.String,
	 * java.lang.String)
	 */
	public boolean addUser(String id, String fullName) {

		ArrayList<User> list = backend.returnList();

		for (IUser s : list) {

			if (s.getId().equals(id)) {
				return false;
			}

		}

		User newUser = new User(id, fullName);
		backend.addUser(newUser);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#removeUser(java.lang.String)
	 */
	public boolean removeUser(String id) {

		return backend.removeUser(id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#createAlbum(java.lang.String,
	 * java.lang.String)
	 */
	public boolean createAlbum(String id, String albumName) {

		if (backend.getUser(id).albumExists(albumName)) {
			return false;
		}

		return backend.getUser(id).addAlbum(albumName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#deleteAlbum(java.lang.String,
	 * java.lang.String)
	 */
	public boolean deleteAlbum(String id, String albumName) {

		return backend.getUser(id).removeAlbum(albumName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#renameAlbum(java.lang.String,
	 * cs213.photoAlbum.model.Ialbum, java.lang.String)
	 */
	public boolean renameAlbum(String id, Ialbum album, String newName) {

		return backend.getUser(id).renameAlbum(album, newName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#listAlbums(java.lang.String)
	 */
	public ArrayList<album> listAlbums(String id) {

		return backend.getUser(id).getAlbums();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#listPhotos(java.lang.String,
	 * java.lang.String)
	 */
	public ArrayList<photo> listPhotos(String id, String albumName) {

		Ialbum al = backend.getUser(id).getAlbum(albumName);

		return al.getPhotos();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#addPhoto(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean addPhoto(String id, String fileName, String caption,
			String albumName) {

		boolean success = false;

		// First, check if photo already exists

		if (backend.getUser(id).photoExists(fileName)) {

			// If photo exists, make sure it is not already in album
			if (!backend.getUser(id).getAlbum(albumName).photoExists(fileName)) {

				// update counter for userPhotos

				backend.getUser(id)
						.getPhoto(fileName)
						.setCount(
								backend.getUser(id).getPhoto(fileName)
										.getCount() + 1);

				// add photo to album
				return backend.getUser(id).getAlbum(albumName)
						.addPhoto(backend.getUser(id).getPhoto(fileName));
			} else {
				return false;
			}
		}

		photo photo = new photo(fileName, caption);

		// If photo is not in other album, attempt to place photo into
		// underlying array of userPhotos
		try {
			success = backend.getUser(id).addUserPhoto(fileName, caption);
		} catch (NullPointerException e) {
			return false;
		}

		try {
			success = backend.getUser(id).getAlbum(albumName).addPhoto(photo);
		} catch (NullPointerException e) {
			return false;
		}

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#movePhoto(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public int movePhoto(String id, String fileName, String oldAlbum,
			String newAlbum) {

		// make sure both albums exist
		if (backend.getUser(id).albumExists(oldAlbum)
				&& backend.getUser(id).albumExists(newAlbum)) {

			if (backend.getUser(id).getAlbum(oldAlbum).getPhoto(fileName) != null) {

				backend.getUser(id)
						.getAlbum(newAlbum)
						.addPhoto(
								backend.getUser(id).getAlbum(oldAlbum)
										.getPhoto(fileName));

				backend.getUser(id)
						.getAlbum(oldAlbum)
						.removePhoto(
								backend.getUser(id).getAlbum(oldAlbum)
										.getPhoto(fileName));
				return 1;

			} else {
				// photo did not exist
				return -1;
			}

		} else {
			// albums don't exist
			return -2;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#removePhoto(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public boolean removePhoto(String id, String fileName, String AlbumName) {

		// decrement count in userPhotos
		backend.getUser(id)
				.getPhoto(fileName)
				.setCount(backend.getUser(id).getPhoto(fileName).getCount() - 1);

		if (backend.getUser(id).getPhoto(fileName).getCount() == 0) {
			backend.getUser(id).getUserPhotos()
					.remove(backend.getUser(id).getPhoto(fileName));
		}

		backend.getUser(id)
				.getAlbum(AlbumName)
				.removePhoto(
						backend.getUser(id).getAlbum(AlbumName)
								.getPhoto(fileName));

		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#addTag(java.lang.String,
	 * java.lang.String)
	 */
	public boolean addTag(String id, String fileName, tag tag) {

		if (backend.getUser(id).photoExists(fileName)) {

			for (tag t : backend.getUser(id).getPhoto(fileName).getTag()) {

				if (tag.getType().equalsIgnoreCase("location")
						&& t.getType().equalsIgnoreCase("location")) {
					return false;
				}

				if (t.getValue().equals(tag.getValue())
						&& t.getType().equals(tag.getType())) {
					return false;
				}

			}

			return backend.getUser(id).getPhoto(fileName).addTag(tag);

		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#deleteTag(java.lang.String,
	 * java.lang.String)
	 */
	public boolean deleteTag(String id, String fileName, String type,
			String value) {

		if (!backend.getUser(id).photoExists(fileName)) {
			return false;
		}

		return backend.getUser(id).getPhoto(fileName).removeTag(type, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs213.photoAlbum.control.IController#listPhotoInfo(java.lang.String)
	 */
	public ArrayList<String> listPhotoInfo(String id, String fileName) {

		ArrayList<String> photoInfo = new ArrayList<String>();

		if (!backend.getUser(id).photoExists(fileName)) {
			return null;
		}

		photoInfo.add(fileName);

		for (int i = 0; i < backend.getUser(id).getUserPhotos().size(); i++) {

			if (backend.getUser(id).getUserPhotos().get(i).getFileName()
					.equals(fileName)) {

				// add date
				photoInfo.add(backend.getUser(id).getUserPhotos().get(i)
						.getCalendar());

				// add caption
				photoInfo.add(backend.getUser(id).getUserPhotos().get(i)
						.getCaption());

				// cycle through tags, add to list

				for (tag t : backend.getUser(id).getUserPhotos().get(i)
						.getTag()) {
					photoInfo.add(t.getType() + ":" + t.getValue());
				}

			}
		}

		return photoInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs213.photoAlbum.control.IController#getPhotosByDate(java.lang.String,
	 * java.lang.String)
	 */
	public ArrayList<photo> getPhotosByDate(String id, String startDate,
			String endDate) {

		ArrayList<photo> photoList = new ArrayList<photo>();

		// Put startDate and endDate in order year/month/day/hour/minute/second
		// for comparison

		String month, day, year;

		try {
			month = startDate.substring(0, startDate.indexOf('/'));
			startDate = startDate.substring(startDate.indexOf('/') + 1);
			day = startDate.substring(0, startDate.indexOf('/'));
			startDate = startDate.substring(startDate.indexOf('/') + 1);
			year = startDate.substring(0, startDate.indexOf('-'));
			startDate = startDate.substring(startDate.indexOf('-') + 1);
			startDate = year + month + day + startDate;
		} catch (Exception e) {
			return null;
		}
		// Do same for endDate
		try {
			month = endDate.substring(0, endDate.indexOf('/'));
			endDate = endDate.substring(endDate.indexOf('/') + 1);
			day = endDate.substring(0, endDate.indexOf('/'));
			endDate = endDate.substring(endDate.indexOf('/') + 1);
			year = endDate.substring(0, endDate.indexOf('-'));
			endDate = endDate.substring(endDate.indexOf('-') + 1);
			endDate = year + month + day + endDate;
		} catch (Exception e) {
			return null;
		}
		// Remove non integer numbers to be used in comparisons for dates

		startDate = startDate.replace("/", "");
		startDate = startDate.replace(":", "");
		startDate = startDate.replace("-", "");
		Long startDateToLong = Long.parseLong(startDate);

		endDate = endDate.replace("/", "");
		endDate = endDate.replace(":", "");
		endDate = endDate.replace("-", "");
		Long endDateToLong = Long.parseLong(endDate);

		for (photo p : backend.getUser(id).getUserPhotos()) {

			// for each photo, convert to year/month/day as above for
			// comparisons

			String formattedDate = p.getCalendar();

			month = formattedDate.substring(0, formattedDate.indexOf('/'));
			formattedDate = formattedDate
					.substring(formattedDate.indexOf('/') + 1);
			day = formattedDate.substring(0, formattedDate.indexOf('/'));
			formattedDate = formattedDate
					.substring(formattedDate.indexOf('/') + 1);
			year = formattedDate.substring(0, formattedDate.indexOf('-'));
			formattedDate = formattedDate
					.substring(formattedDate.indexOf('-') + 1);
			formattedDate = year + month + day + formattedDate;

			// Get string date, remove non-integers
			formattedDate = formattedDate.replace("/", "");
			formattedDate = formattedDate.replace(":", "");
			formattedDate = formattedDate.replace("-", "");

			Long dateToLong = Long.parseLong(formattedDate);
			// System.out.println(dateToLong);

			if (dateToLong < endDateToLong && dateToLong > startDateToLong) {
				photoList.add(p);
			}

			// Collections.sort(photoList);

		}

		return photoList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs213.photoAlbum.control.IController#getPhotosByTag(java.util.ArrayList)
	 */
	public ArrayList<photo> getPhotosByTag(String id, ArrayList<tag> tagList) {

		ArrayList<photo> photoList = new ArrayList<photo>();
		ArrayList<photo> userPhotos = backend.getUser(id).getUserPhotos();

		boolean success = false;
		boolean singleSuccess = false;

		for (photo p : userPhotos) {

			for (tag t : tagList) {

				singleSuccess = false;

				for (int i = 0; i < p.getTag().size(); i++) {

					if (p.getTag().get(i).getValue().equals(t.getValue())
							&& (t.getType().equals(p.getTag().get(i).getType()) || (t
									.getType().equals("")))) {
						singleSuccess = true;
					}

				}

				if (singleSuccess == false) {
					break;
				}

			}

			if (singleSuccess == true) {
				photoList.add(p);
			}

		}

		return photoList;

	}

	// used for debugging purposes
	public void printUserPhotoList(String id) {

		for (photo p : backend.getUser(id).getUserPhotos()) {
			System.out.println(p.getFileName() + " - " + p.getCaption() + " - "
					+ p.getCount());
		}
	}

}
