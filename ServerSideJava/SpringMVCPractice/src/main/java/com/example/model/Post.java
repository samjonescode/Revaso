package com.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Sam, Lucnel, Niroj, Jimmy
 * 
 * Post class is the model class responsible for creating table named POST which contains five fields.Such as,
 * Post_ID, User_ID, Post_Title,Post_Body and Num_Likes.
 * Post_ID is the primary key in this table and it is generating automatically.
 * 
 * This class has ManyToOne relationship and The using  @JoinColumn, 
 * annotation helps us specify the column we'll use for joining an entity association or element collection.
 * 
 *Setters and Getters have been used for update and retrieve values .
 */

@Entity
@Table(name = "POST")
public class Post {

	@Id 
	@Column(name = "POST_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int postId;
	
	@Column(name = "USER_ID", nullable = false)
	private int userId;
	
	@Column(name = "POST_TITLE", nullable = false)
	private String postTitle;
	
	@Column(name = "POST_BODY", nullable = false)
	private String postBody;
	
	@Column(name = "NUM_LIKES")
	private int numLikes;
	
	@Column(name = "postImage")
	private String postImage;
	//private List<Ulike> whoseLiked;
	
	@ManyToOne(targetEntity= Post.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="USERNAME")
	private List<Profile> profiles;

	public Post() {
		
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostBody() {
		return postBody;
	}

	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}

	public int getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	/**
	 *  toString method to return the retrieved data.
	 */
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", userId=" + userId + ", postTitle=" + postTitle + ", postBody=" + postBody
				+ ", numLikes=" + numLikes + ", profiles=" + profiles + "]";
	}

	/**
	 * Constructs and initializes a post.
	 * @param postId  Id of the post.
	 * @param userId  Id of the post creator.
	 * @param postTitle  Title of the post.
	 * @param postBody   Actual post written by user.
	 * @param numLikes   The number of likes received by other users.
	 * @param profiles   The other users profile who liked the post.
	 */
	public Post(int postId, int userId, String postTitle, String postBody, int numLikes, List<Profile> profiles) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.postTitle = postTitle;
		this.postBody = postBody;
		this.numLikes = numLikes;
		this.profiles = profiles;
	}

	/**
	 * Constructs and initializes a post.
	 * @param postId   Id of the post.
	 * @param userId   Id of the post creator.
	 * @param postTitle  Title of the post.
	 * @param postBody   Actual post written by user.
	 * @param numLikes   The number of likes received by other users.
	 */
	public Post(int postId, int userId, String postTitle, String postBody, int numLikes) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.postTitle = postTitle;
		this.postBody = postBody;
		this.numLikes = numLikes;
	}


	

}
