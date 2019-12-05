package com.example.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Profile;

/**
 *  ProfileDao class is responsible for connecting with database and has different methods to perform various transactions.
 * @author Sam, Lucnel, Niroj, Jimmy
 *
 */
@Repository("profileRepo")
@Transactional
public class ProfileDao {
	
	
	@Autowired
	private SessionFactory sesFact;
	public ProfileDao() {
		
	}
	
	/**
	 * This method used to register a new user in the application database.
	 * @param profile   passing profile class as a parameter.
	 */
	public void insert(Profile profile) {
		sesFact.getCurrentSession().save(profile);
	}
	

	/**
	 * This method is used to search a user by the user Id.
	 * @param id    It will require the user Id for method parameter.
	 * @return      It will return the user profile as a result.
	 */

	public Profile selectById(int id) {
		return sesFact.getCurrentSession().get(Profile.class, id);
	}
	
	/**
	 *  This method is used to search a user by the user name.
	 * @param userName     It will require the user name for method parameter.
	 * @return			   It will return the user profile as a result.
	 */
	public Profile selectByUserName(String userName) {
		Query q = sesFact.getCurrentSession().createQuery("from Profile where username like :username");
		q.setParameter("username", userName + "%");
		return (Profile) q.uniqueResult();
	}
	
	/**
	 * This method is used to search a user by the user name.It will be useful when someone only knows other person's email id.
	 * @param email       It will require the email Id for method parameter.
	 * @return			  It will return the user profile as a result.
	 */
	public List<Profile> selectByEmail(String email) {
		Query q = sesFact.getCurrentSession().createQuery("from Profile where email like :email");
		q.setParameter("email", email + "%");
		return (List<Profile>) q.getResultList();
	}
	
	/**
	 * This method is used to search a user by the first name.
	 * @param firstName   It will require the first name for method parameter.
	 * @return			  It will return a list of profiles which have same first name. 
	 */
	public List<Profile> selectByFirstName(String firstName) {
		Query q = sesFact.getCurrentSession().createQuery("from Profile where fName like :fName");
		q.setParameter("fName", firstName + "%");
		return (List<Profile>) q.getResultList();
	}
	
	/**
	 * This method is used to retrieve all users.
	 * @return    It will return a list of all profiles.
	 */
	public List<Profile> selectAll(){
		List<Profile> profileList = sesFact.getCurrentSession().createQuery("from Profile", Profile.class).list();
		return profileList;
	}
	
	/**
	 * This method is used to update a user's profile.
	 * @param id   Passing user Id to update the specific user's profile.
	 * @param profile     passing the profile class as a parameter for updating which contains user's information.
	 */
	public Profile update(int id, Profile profile) {
		Session sess = sesFact.getCurrentSession();
		Profile newProfile = sess.byId(Profile.class).load(id);
		
		newProfile.setfName(profile.getfName());
		newProfile.setEmail(profile.getEmail());
		newProfile.setCity(profile.getCity());
		newProfile.setDob(profile.getDob());
		newProfile.setlName(profile.getlName());
		newProfile.setGender(profile.getGender());
		newProfile.setUserName(profile.getUserName());
		newProfile.setUserPassword(profile.getUserPassword());
		System.out.println("New profile: " + newProfile.toString());
		sess.saveOrUpdate(newProfile);
		System.out.println("Updated.");
		
		return newProfile;
	}
	
}
