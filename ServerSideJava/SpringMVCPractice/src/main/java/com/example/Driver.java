package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.model.Profile;
import com.example.repository.ProfileDao;

public class Driver {
	public static ApplicationContext appC = new ClassPathXmlApplicationContext("applicationContext.xml");
	public static ProfileDao proDao = appC.getBean("profileRepo", ProfileDao.class);
	public static void main(String[] args) {
		
		Profile p1 = new Profile("Jabroni", "passw4rd", "Ja", "Broni", "male","jb@jb.com");
		Profile p2 = new Profile("Jabroni1", "passw4rd", "Ja", "Broni", "male","jb1@jb.com");
		Profile p3 = new Profile("Jabroni2", "passw4rd", "Ja", "Broni", "male","jb2@jb.com");
				
		proDao.insert(p1);
		proDao.insert(p2);
		proDao.insert(p3);
	}

}
