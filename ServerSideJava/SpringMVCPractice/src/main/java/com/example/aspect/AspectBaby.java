package com.example.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect	
@Component("advice")
public class AspectBaby {

	private Logger logSon = Logger.getLogger(AspectBaby.class);
	@Before("execution(* login(*))")
	public void inserted(JoinPoint jp) {
		System.out.println("User logged in");
		logSon.info("User logged in");
	}
	
	@After("execution(* likePost(*))")
	public void oneFound(JoinPoint jp) {
		System.out.println("One post liked.");
		logSon.info("One post liked.");
	}
	
	@After("execution(* com.example.controller.ProfileController.insert(*))")
	public void oneAdded(JoinPoint jp) {
		logSon.info("One profile added");
	}
	
	@After("execution(* updateAndReturn(..))")
	public void oneUpdated(JoinPoint jp) {
		logSon.info("One profile updated");
	}
	
	@After("execution(* resetPassword(..))")
	public void resetPW(JoinPoint jp) {
		logSon.info("One password reset.");
	}
	
	@After("execution(* com.example.controller.PostController.insert(*))")
	public void postAdded(JoinPoint jp) {
		logSon.info("One post added.");
	}
	
	@After("execution(* com.example.controller.PostController.list())")
	public void postsFetched(JoinPoint jp) {
		logSon.info("All posts fetched for the feed.");
	}
	
//	@After("execution(* select*(*))")
//	public void selected(JoinPoint jp) {
//		logSon.info("Profiles searched.");
//	}
//	
	@After("execution(* com.example.controller.PostController.getPostsByUserId(*))")
	public void getPostsByUser(JoinPoint jp) {
		logSon.info("Posts for user retrieved.");
	}
//	
//	@Before("execution(* allMovies())")
//	public void allFound(JoinPoint jp) {
//		logSon.info("All movies found (/allMovies.app");
//	}
}
