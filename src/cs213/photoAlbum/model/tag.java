package cs213.photoAlbum.model;

/**
 * The tag class allows for a creation of a tag object, with methods to return the type of the tag, the value of the tag, as well as methods setType to change the type of tag and setValue to change the value of the tag.
 * @author Zack Colello and Anna Genke
 *
 */
public class tag implements java.io.Serializable, Itag {

	/**
	 * Holds the type type of the tag instance
	 * @author Zack Colello
	 */
	private String type;
	
	/**
	 * Holds the value of the tag instance
	 * @author Zack Colello
	 */
	private String value;

	/**
	 * The constructor of tag class creates a tag instance
	 * @param type string that represents the type of a tag 
	 * @param value string that represents the tag
	 * @author Zack Colello
	 */
	public tag(String type, String value) {
		this.type = type;
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Itag#getType()
	 */
	public String getType(){
		return type;
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Itag#getValue()
	 */
	public String getValue(){
		return value;
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Itag#setType(java.lang.String)
	 */
	public void setType(String type){
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see cs213.photoAlbum.model.Itag#setValue(java.lang.String)
	 */
	public void setValue(String value){
		this.value = value;
	}
	
}
