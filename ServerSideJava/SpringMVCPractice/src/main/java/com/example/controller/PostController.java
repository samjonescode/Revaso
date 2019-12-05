package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Post;
import com.example.repository.PostDao;

/**
 * PostController is responsible for connection between front end and back end. 
 * 
 * @author Sam, Lucnel, Niroj, Jimmy
 * It is passing the request parameters to Dao Class methods and returning the data to front end.
 * @CrossOrigin is used to handle the request from different origins. 
 *
 */
@RestController
@CrossOrigin
@SessionAttributes("profile")
public class PostController {
	
	@Autowired
	private PostDao postDao;
	
	/**
	 * Mapping with the retrieve all posts request method.
	 * @return
	 */
	@GetMapping("/allPosts.do")
	public ResponseEntity<List<Post>> list(){
		List<Post> posts = postDao.selectAll();
		return ResponseEntity.ok().body(posts);
	}
	
	/**
	 * Mapping with the post update request method
	 * @param post
	 * @return
	 */
	@PostMapping("/updatePosts.do")
	public ResponseEntity<String> update(Post post){
		
		return ResponseEntity.ok().body("Post updated: " + post.toString());
	}
	
	/**
	 * Mapping with the get all posts by post Id.
	 * @param id
	 * @return
	 */
	@GetMapping(value="/allPosts/{id}")
	public Post getPostById(@PathVariable("id") int id) {
		return postDao.selectPostById(id);
	}
	
	/**
	 * Mapping with the get all posts by user ID
	 * @param userId
	 * @return
	 */
	@GetMapping(value="/postsByUser/{userId}")
	public List<Post> getPostsByUserId(@PathVariable("userId") int userId){
		return postDao.selectPostByUserId(userId);
	}

	@PutMapping(value="/likePost/{id}.do")
	public Post likePost(@PathVariable int id) {
		Post postToLike = postDao.selectPostById(id);
		postToLike.setNumLikes(postToLike.getNumLikes()+1);
		postDao.update(postToLike);
		return postToLike;
	}
	@PostMapping(value="/addPost.do")
	public ResponseEntity<String> insert(@RequestBody Post post){
		System.out.println("Post added... " + post.toString());
		postDao.insert(post);
		return ResponseEntity.ok().body("Post added: " + post.toString());
	}
	
	@PostMapping(value="/deletePost")
	public ResponseEntity<String> delete(Post post){
		return ResponseEntity.ok().body("Post deleted: " + post.toString());
	}
}
