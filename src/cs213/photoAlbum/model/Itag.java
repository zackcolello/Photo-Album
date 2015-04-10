package cs213.photoAlbum.model;

/**
 * The tag class allows for a creation of a tag object, with methods to return the type of the tag, the value of the tag, as well as methods setType to change the type of tag and setValue to change the value of the tag.
 * @author Zack Colello and Anna Genke
 *
 */
public interface Itag {

	/**
	 * Gets type of tag
	 * @return String that represents the type of a tag 
	 * @author Zack Colello
	 */
	public abstract String getType();

	/**
	 * Gets tag
	 * @return String that represents the tag
	 * @author Zack Colello 
	 */
	public abstract String getValue();

	/**
	 * Sets the type of tag to be changed
	 * @param type String with new type
	 * @author Zack Colello
	 */
	public abstract void setType(String type);

	/**
	 * Sets the value of tag to be changed
	 * @param value  String with new value
	 * @author Zack Colello
	 */
	public abstract void setValue(String value);

}