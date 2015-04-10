package cs213.photoAlbum.simpleview;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs213.photoAlbum.control.Controller;
import cs213.photoAlbum.control.IController;
import cs213.photoAlbum.model.IUser;
import cs213.photoAlbum.model.Ialbum;
import cs213.photoAlbum.model.Ibackend;
import cs213.photoAlbum.model.Iphoto;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.model.album;
import cs213.photoAlbum.model.backend;
import cs213.photoAlbum.model.photo;
import cs213.photoAlbum.model.tag;

/**
 * CmdView contains the main method for the Photo Album program and creates a
 * backend, cmdview, and controller objects. CmdView handles reading in previous
 * data from the controller for session persistence. This class accepts input
 * from the user for creating Users for a database, and allows for login to
 * program. If login is successfully, CmdView starts interactiveMode, which
 * allows for various commands to add photos, albums, and more interacting with
 * the controller object.
 * 
 * @author Anna Genke and Zack Colello
 *
 */
public class CmdView {

	/**
	 * Stores the backend for the model portion of the application
	 */
	public static Ibackend backend = new backend();
	/**
	 * Created CmdView object to be given to the controller
	 */
	public static CmdView cmdview = new CmdView();
	/**
	 * Creates Controller object to pass information from view to model
	 */
	public static IController controller = new Controller(backend, cmdview);

	/**
	 * The main function accepts input from keyboard to create User, delete
	 * User, list User, or log in. Once a user is logged in, the interactiveMode
	 * method is called.
	 * 
	 * @param args
	 *            A command to either list Users, add User, remove User, or log
	 *            in.
	 * @throws IOException
	 * @author Anna Genke
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {

		// Try reading in serialized database
		try {
			HashMap<String, User> dataMap = controller.readDatabase();
			backend.setDatabase(dataMap);

		} catch (Exception e) {
			// Database file does not exist yet, create file

			File f = new File("data" + File.separator + "data.dat");

		}

		if(args.length == 0){
			System.out.println("Error: Must Enter a command.");
			return;
		}
		
		
		if (args[0].equalsIgnoreCase("listusers")) {

			if (args.length != 1) {
				System.out
						.println("Error: Too many command line arguments for listusers command.");
				return;
			}

			ArrayList<User> list = controller.listUsers();

			if (list.isEmpty()) {
				System.out.println("no users exist.");
			}

			for (IUser s : list) {
				System.out.println(s.getId());
			}

		}

		if (args[0].equalsIgnoreCase("adduser")) {

			if (args.length != 3) {
				System.out
						.println("Error: Wrong number of command line arguments for adduser command.");
				return;
			}

			if (controller.addUser(args[1], args[2])) {
				System.out.println("Created user " + args[1] + " with name "
						+ args[2]);
			} else {
				System.out.println("User " + args[1]
						+ " already exists with name " + args[2]);
			}

		}

		if (args[0].equalsIgnoreCase("deleteuser")) {

			if (args.length != 2) {
				System.out
						.println("Error: Wrong number of command line arguments for deleteuser command.");
				return;
			}

			if (controller.removeUser(args[1])) {
				System.out.println("Deleted user " + args[1]);
			} else {
				System.out.println("User " + args[1] + " does not exist");
			}

		}

		if (args[0].equalsIgnoreCase("login")) {

			if (args.length != 2) {
				System.out
						.println("Wrong number of command line arguments for login command.");
				return;
			}

			if (backend.containsUser(args[1])) {
				interactiveMode(args[1]);
			} else {
				System.out.println("User " + args[1] + " doesn't exist.");
				return;
			}
		}

		controller.writeDatabase(backend.getDatabase());

	}

	/**
	 * InteractiveMode is activated when a user has successfully logged in from
	 * main. InteractiveMode is where a user can use the program to add photos,
	 * albums, as well as other commands.
	 * 
	 * @param id
	 *            String of the id of User that is logged in
	 * @throws IOException
	 * @author Anna Genke
	 */
	public static void interactiveMode(String id) throws IOException {

		boolean interactiveMode = true;

		Scanner sc = new Scanner(System.in);
		String line = "";

		while (interactiveMode) {

			line = sc.nextLine();

			ArrayList<String> stringList = new ArrayList<String>();

			String regex = "\"([^\"]*)\"|(\\S+)";

			Matcher m = Pattern.compile(regex).matcher(line);
			while (m.find()) {
				if (m.group(1) != null) {
					stringList.add(m.group(1));
				} else {
					stringList.add(m.group(2));
				}
			}

			// for debugging purposes
			if (stringList.get(0).equalsIgnoreCase("ls")) {

				controller.printUserPhotoList(id);
				continue;
			}

			if (stringList.get(0).equalsIgnoreCase("createAlbum")) {

				if (stringList.size() != 2) {
					System.out
							.println("Error: Wrong number of arguments for createAlbum command.");
					continue;
				}

				if (controller.createAlbum(id, stringList.get(1))) {
					System.out.println("Created album for user " + id + ":");
					System.out.println(stringList.get(1));
				} else {
					System.out.println("Album exists for user " + id + ":\n" + stringList.get(1));
				}
				continue;
			}

			if (stringList.get(0).equalsIgnoreCase("deleteAlbum")) {

				if (stringList.size() != 2) {
					System.out
							.println("Error: Wrong number of arguments for deleteAlbum command.");
					continue;
				}

				if (controller.deleteAlbum(id, stringList.get(1))) {
					System.out.println("Deleted album from user " + id + ":\n" + stringList.get(1));
				} else {
					System.out
							.println("Album does not exist for user " + id + ":\n" + stringList.get(1));
				}
				continue;

			}

			if (stringList.get(0).equalsIgnoreCase("renameAlbum")) {

				if (stringList.size() != 3) {
					System.out
							.println("Error: Wrong number of arguments for renameAlbum command.");
					continue;
				}

				if (controller.renameAlbum(id,
						backend.getUser(id).getAlbum(stringList.get(1)),
						stringList.get(2))) {
					System.out.println("Renamed album " + stringList.get(1)
							+ " to " + stringList.get(2));
				} else {
					System.out.println("Album " + stringList.get(1)
							+ " does not exist for this user.");
				}

				continue;

			}

			if (stringList.get(0).equalsIgnoreCase("listAlbums")) {

				if (stringList.size() != 1) {
					System.out
							.println("Error: Wrong number of arguments for listAlbums command.");
					continue;
				}

				ArrayList<album> albumList = controller.listAlbums(id);

				if (!albumList.isEmpty()) {
					System.out.println("Albums for user " + id + ":");

					for (Ialbum a : albumList) {

						if (a.getPhotos().isEmpty()) {
							System.out.println(a.getName()
									+ " number of photos: 0");
						} else {
							System.out.println(a.getName()
									+ " number of photos: "
									+ a.getPhotos().size()
									+ ", "
									+ a.getPhotos().get(0).getCalendar()
									+ " - "
									+ a.getPhotos()
											.get(a.getPhotos().size() - 1)
											.getCalendar());
						}
					}

				} else {
					System.out.println("No albums exist for user " + id);
				}
				continue;

			}

			if (stringList.get(0).equalsIgnoreCase("listPhotos")) {

				if (stringList.size() != 2) {
					System.out
							.println("Error: Wrong number of arguments for listPhotos command.");
					continue;
				}

				ArrayList<photo> photoList = controller.listPhotos(id,
						stringList.get(1));

				if (!photoList.isEmpty()) {
					System.out.println("Photos for album " + stringList.get(1)
							+ ":");

					for (Iphoto p : photoList) {
						System.out.println(p.getFileName() + " - "
								+ p.getCalendar());

					}
				} else {
					System.out.println("No photos exist in album "
							+ stringList.get(1));
				}
				continue;
			}

			// Need to deal with leaving caption blank to get caption from other
			// album
			if (stringList.get(0).equalsIgnoreCase("addPhoto")) {

				if (stringList.size() != 4) {
					System.out
							.println("Error: Wrong number of arguments for addPhoto command.");
					continue;
				}

				// check if album is valid
				if (!backend.getUser(id).albumExists(stringList.get(3))) {
					System.out.println("Album " + stringList.get(3)
							+ " does not exist for this user.");
					continue;
				}

				File f = new File(stringList.get(1));
				
				
				if(f.exists() && !f.isDirectory()){
					
					if (controller.addPhoto(id, f.getCanonicalPath(),
							stringList.get(2), stringList.get(3))) {
						System.out.println("Added photo: " + stringList.get(1));
						System.out.println(stringList.get(2) + " - " + "Album: "
								+ stringList.get(3));

					} else {
						System.out.println("Photo " + stringList.get(1)
								+ " already exists in album " + stringList.get(3));

					}
					
				}else{
					System.out.println("File " + stringList.get(1) + " does not exist");
				}
	

				continue;

			}

			if (stringList.get(0).equalsIgnoreCase("movePhoto")) {

				if (stringList.size() != 4) {
					System.out
							.println("Error: Wrong number of arguments for movePhoto command.");
					continue;
				}

				// check if photo already exists in other album, return quietly
				// if so
				try {
					if (backend.getUser(id).getAlbum(stringList.get(3))
							.photoExists(stringList.get(1))) {

						if (backend.getUser(id).getAlbum(stringList.get(2))
								.photoExists(stringList.get(1))) {

							continue;
						}
					}
				} catch (Exception e) {
					// carry on
				}

				int flag = controller.movePhoto(id, stringList.get(1),
						stringList.get(2), stringList.get(3));

				if (flag == 1) {
					System.out.println("Moved photo " + stringList.get(1));
					System.out.println(stringList.get(1) + " - "
							+ "From album " + stringList.get(2) + " to album "
							+ stringList.get(3));
				} else if (flag == -1) {
					System.out.println("Photo " + stringList.get(1)
							+ " does not exist in " + stringList.get(2));
				} else {
					System.out
							.println("Error: One of the albums/photo specified does not exist.");
				}

				continue;
			}

			if (stringList.get(0).equalsIgnoreCase("removePhoto")) {

				if (stringList.size() != 3) {
					System.out
							.println("Error: Wrong number of arguments for removePhoto command.");
					continue;
				}

				// check if album exists

				if (!backend.getUser(id).albumExists(stringList.get(2))) {
					System.out.println("Album " + stringList.get(2)
							+ " does not exist");
					continue;
				}

				// check if photo exists

				if (!backend.getUser(id).getAlbum(stringList.get(2))
						.photoExists(stringList.get(1))) {
					System.out.println("Photo " + stringList.get(1)
							+ " is not in album " + stringList.get(2));
					continue;
				}

				if (controller.removePhoto(id, stringList.get(1),
						stringList.get(2))) {
					System.out.println("Removed photo:\n" + stringList.get(1)
							+ "- From album " + stringList.get(2));

				} else {
					System.out.println("");
				}
				continue;
			}

			if (stringList.get(0).equalsIgnoreCase("addTag")) {

				if (stringList.size() < 3) {
					System.out
							.println("Error: Wrong number of arguments for addTag command.");
					continue;
				}

				if (stringList.get(2).indexOf(":") == -1) {
					System.out
							.println("Error: Invalid use of tag type for addTag command.");
					continue;
				}

				// Rebuild string of tags from argument list

				String type = stringList.get(2).substring(0,
						stringList.get(2).indexOf(":"));
				String value = stringList.get(2).substring(
						stringList.get(2).indexOf(":") + 1);

				for (int i = 3; i < stringList.size(); i++) {

					value += " " + stringList.get(i);
				}

				value = value.replace("\"", "");

				tag newtag = new tag(type, value);

				if (controller.addTag(id, stringList.get(1), newtag)) {
					System.out.println("Added tag:");
					System.out.println(stringList.get(0) + " " + type + ":"
							+ value);
				} else {
					System.out.println("Tag already exists for "
							+ stringList.get(1) + " " + type + ":" + value);

				}
				continue;
			}

			if (stringList.get(0).equalsIgnoreCase("deleteTag")) {

				if (stringList.size() < 3) {
					System.out
							.println("Error: Wrong number of arguments for deleteTag command.");
					continue;
				}

				if (stringList.get(2).indexOf(":") == -1) {
					System.out
							.println("Error: Invalid use of tag type for deleteTag command.");
					continue;
				}

				String type = stringList.get(2).substring(0,
						stringList.get(2).indexOf(":"));
				String value = stringList.get(2).substring(
						stringList.get(2).indexOf(":") + 1);

				for (int i = 3; i < stringList.size(); i++) {

					value += " " + stringList.get(i);
				}

				value = value.replace("\"", "");

				if (controller.deleteTag(id, stringList.get(1), type, value)) {
					System.out.println("Deleted tag:");
					System.out.println(stringList.get(1) + " " + type + ":"
							+ value);
				} else {
					System.out.println("Tag does not exist for "
							+ stringList.get(1) + " " + type + ":" + value);
				}

				continue;
			}

			if (stringList.get(0).equalsIgnoreCase("listPhotoInfo")) {

				if (stringList.size() != 2) {
					System.out
							.println("Error: Wrong number of arguments for listPhotoInfo command.");
					continue;
				}

				ArrayList<String> photoInfo = controller.listPhotoInfo(id,
						stringList.get(1));

				if (photoInfo == null) {
					System.out
							.println("Photo does not exist in any album for this user.");
					continue;
				}

				System.out.println("Photo file name: " + photoInfo.get(0));

				boolean moreThanOneAlbum = false;
				System.out.print("Album: ");
				for (album a : backend.getUser(id).getAlbums()) {
					if (a.getPhoto(photoInfo.get(0)) != null) {

						if (moreThanOneAlbum == false) {
							System.out.print(a.getName());
							moreThanOneAlbum = true;
						} else {
							System.out.print(", " + a.getName());
						}

					}
				}

				System.out.println("");

				// list date

				System.out.println("Date: " + photoInfo.get(1));

				System.out.println("Caption: " + photoInfo.get(2));

				System.out.println("Tags:");

				// print location first
				for (tag t : backend.getUser(id).getPhoto(photoInfo.get(0))
						.getTag()) {
					if (t.getType().equalsIgnoreCase("location")) {
						System.out.println(t.getType() + ":" + t.getValue());
					}
				}

				// print people list next

				ArrayList<String> peopleList = new ArrayList<String>();

				for (tag t : backend.getUser(id).getPhoto(photoInfo.get(0))
						.getTag()) {
					if (t.getType().equalsIgnoreCase("person")) {
						peopleList.add(t.getType() + ":" + t.getValue());
					}
				}

				Collections.sort(peopleList);

				for (String s : peopleList) {
					System.out.println(s);
				}

				// print rest of tags that are not person or location

				// gather types together

				ArrayList<String> tags = new ArrayList<String>();

				for (tag t : backend.getUser(id).getPhoto(photoInfo.get(0))
						.getTag()) {

					if (!t.getType().equalsIgnoreCase("location")
							&& !t.getType().equalsIgnoreCase("person")) {
						tags.add(t.getType() + ":" + t.getValue());
					}
				}

				Collections.sort(tags);

				for (String s : tags) {
					System.out.println(s);
				}

				continue;
			}

			if (stringList.get(0).equalsIgnoreCase("getPhotosByDate")) {

				if (stringList.size() != 3) {
					System.out
							.println("Error: Wrong number of arguments for getPhotosByDate command.");
					continue;
				}
				
				int invalid=0;
				for(int i=1;i<3;i++){
					StringTokenizer st = new StringTokenizer(stringList.get(i), "//-:");
					int mo=Integer.parseInt(st.nextToken());
					int d=Integer.parseInt(st.nextToken());
					int y=Integer.parseInt(st.nextToken());
					int h=Integer.parseInt(st.nextToken());
					int mi=Integer.parseInt(st.nextToken());
					int sec=Integer.parseInt(st.nextToken());



					if(mo<1||mo>12){
						invalid=-1;
					}

					if((mo==1||mo==3||mo==5||mo==7||mo==8||mo==10||mo==12)&&(d<1||d>31)){
						invalid=-1;
					}
					if((mo==4||mo==6||mo==9||mo==11)&&(d<1||d>30)){
						invalid=-1;
					}
					if((mo==2)&&(d<1||d>=29)){

						if((y%4!=0)&&(d>=29)){
							invalid=-1;
						}else if((y%100==0)&&(y%400!=0)){
							invalid=-1;
						}
					}
					if((h<0||h>23)){
						invalid=-1;
					}
					if((mi<0||mi>59)){
						invalid=-1;
					}
					if((sec<0||sec>59)){
						invalid=-1;
					}
				}
				if (invalid==-1){
					System.out.println("Invalid date");
					continue;
				}
				
				ArrayList<photo> photoList = controller.getPhotosByDate(id,
						stringList.get(1), stringList.get(2));

				for (photo p : photoList) {
					System.out.print(p.getCaption() + " - ");

					boolean moreThanOneAlbum = false;
					System.out.print("Album: ");
					for (album a : backend.getUser(id).getAlbums()) {
						if (a.getPhoto(p.getFileName()) != null) {

							if (moreThanOneAlbum == false) {
								System.out.print(a.getName());
								moreThanOneAlbum = true;
							} else {
								System.out.print(", " + a.getName());
							}

						}
					}
					System.out.println(" - Date: " + p.getCalendar());

				}
				continue;
			}

			if (stringList.get(0).equalsIgnoreCase("getPhotosByTag")) {

				// removing the "getPhotosByTag" string

				if (stringList.size() < 2) {
					System.out
							.println("Error: Wrong number of arguments for getPhotosByTag command.");
					continue;
				}

				stringList.remove(0);

				// rebuild tag list
				ArrayList<tag> tagList = new ArrayList<tag>();

				String type = "";
				String value = "";
				String token = "";

				for (int i = 0; i < stringList.size(); i++) {

					token = stringList.get(i);

					// remove comma at end if it's there
					if (token.charAt(token.length() - 1) == ',') {
						token = token.substring(0, token.length() - 1);
					}

					// This means there is a colon, and we are dealing with a
					// type
					if (token.indexOf(':') != -1) {

						type = "";
						value = "";
						type = token.substring(0, token.indexOf(':'));

					} else { // No colon, dealing with a segment of a value or
								// value with no type

						// if no type, that means this is a value with no type
						// being searched for
						if (type.equals("")) {

							// if token is in quotes, we can create tag and give
							// with blank type
							if (token.charAt(token.length() - 1) == '"'
									&& token.charAt(0) == '"') {

								value = token.substring(1, token.length() - 1);
								tag newTag = new tag(type, token);
								tagList.add(newTag);
								type = "";
								value = "";
								
								continue;

								// If there is no beginning and no end quote,
								// dealing with either a standalone
								// value or part of a longer value
							} else if (token.charAt(token.length() - 1) != '"'
									&& token.charAt(0) != '"') {

								if (value.equals("")) {
									tag newTag = new tag(type, token);
									tagList.add(newTag);
									
									type = "";
									value = "";
									continue;
								} else { // value is being built, add it on
									value += " ";
									value += token.substring(0, token.length());
									continue;
								}

								// if there is an end quote and no beginning,
								// end of a search
							} else if (token.charAt(token.length() - 1) == '"'
									&& token.charAt(0) != '"') {
								value += " ";
								value += token.substring(0, token.length() - 1);

								tag newTag = new tag(type, value);
								tagList.add(newTag);
								type = "";
								value = "";
								continue;
							}
						}

						// If the last value is a quotation, add and move on
						if (token.charAt(token.length() - 1) == '"') {
							value += " ";
							value += token.substring(0, token.length() - 1);

							tag newTag = new tag(type, value);
							tagList.add(newTag);
							type = "";
							value = "";
							continue;
						} else { // not done with value, add and move on to next

							value += " ";
							value += token.substring(0, token.length());
							continue;
						}

					}

					// If there is a quotation mark right after the colon, we
					// must rebuild value
					if (token.charAt(token.indexOf(':') + 1) == '"') {

						// If there is a quotation at the end of the string, add
						// value and move on
						if (token.charAt(token.length() - 1) == '"') {
							value += token.substring(token.indexOf(':') + 2,
									token.length() - 1);

							tag newTag = new tag(type, value);
							tagList.add(newTag);
							type = "";
							value = "";
							continue;
						} else { // This means no quotation at end of string,
									// need to build value still

							value += token.substring(token.indexOf(':') + 2);

							continue;

						}

					} else { // No beginning quotation mark in value, can just
								// add it

						value = token.substring(token.indexOf(':') + 1);

						// remove comma at end if it's there
						if (value.charAt(value.length() - 1) == ',') {
							value = value.substring(0, value.length() - 1);
						}

						tag newTag = new tag(type, value);
						tagList.add(newTag);
						type = "";
						value = "";
						continue;

					}

				}

				System.out.print("Photos for user " + id + " with tags ");

				for (String s : stringList) {
					System.out.print(s + " ");
				}
				System.out.println("");

				ArrayList<photo> photoList = controller.getPhotosByTag(id, tagList);
				
				//Finally we can print the photos
				for (photo p : photoList) {
					System.out.print(p.getCaption() + " - ");

					boolean moreThanOneAlbum = false;
					System.out.print("Album: ");
					for (album a : backend.getUser(id).getAlbums()) {
						if (a.getPhoto(p.getFileName()) != null) {

							if (moreThanOneAlbum == false) {
								System.out.print(a.getName());
								moreThanOneAlbum = true;
							} else {
								System.out.print(", " + a.getName());
							}

						}
					}
					System.out.println(" - Date: " + p.getCalendar());

				}

				continue;
			}

			if (stringList.get(0).equalsIgnoreCase("logout")) {
				return;
			}

			System.out.println("Error: Invalid command.");

			
			
		}

		sc.close();

	}

}
