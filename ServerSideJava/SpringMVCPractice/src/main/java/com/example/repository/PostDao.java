package com.example.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Post;

/**
 * 
 * PostDao class is responsible for connecting with database and has different methods to perform various transactions.
 * 
 * @author Sam, Luke, Niroj, Jimmy
 * 
 *
 */
@Repository("postRepo")
@Transactional
public class PostDao {

	@Autowired
	private SessionFactory sesFact;
	public PostDao() {
		super();
		
	}
	
	/**
	 * This method is used for storing new post in the post table.
	 * @param post   Passing post class as a parameter.
	 */
	public void insert(Post post) {
		sesFact.getCurrentSession().save(post);
	}
	
	/**
	 * This method is used for modify the already created post in the post table.
	 * @param post Passing post class as a parameter.
	 */
	public void update(Post post) {
		sesFact.getCurrentSession().update(post);
	}
	
	/**
	 * This method is used for retrieving a post from post table associated with a given post ID.
	 * @param id  Passing the Post ID as parameter
	 * @return  This method returns the post.
	 */
	public Post selectPostById(int id) {
		return sesFact.getCurrentSession().get(Post.class, id);
	}
	
	/**
	 * This method is used for retrieving all posts from post table associated with a given User ID.
	 * @param userId    Passing the User ID as parameter
	 * @return  This methods returns a List which contains all the posts created by a specific user.
	 */
	public List<Post> selectPostByUserId(int userId){
		Query q = sesFact.getCurrentSession().createQuery("from Post where user_id=:userId", Post.class);
		q.setInteger("userId", userId);
		return q.getResultList();
		
	}
	
	/**
	 * This method is used for retrieving all posts from post table.
	 * @return  This methods returns a List which contains all the posts
	 */
	public List<Post> selectAll(){
		return sesFact.getCurrentSession().createQuery("from Post", Post.class).list();
	}
	

}
