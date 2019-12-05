package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.encryption.Encrypter;
import com.example.helpers.SendEmail;
import com.example.model.Profile;
import com.example.repository.ProfileDao;


/**
 * ProfileController is responsible for connection between front end and back end. 
 *
 * @author IBM
 * It is passing the request parameters to Dao Class methods and returning the data to front end.
 * @CrossOrigin is used to handle the request from different origins. 
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileController {
	
	@Autowired
	private ProfileDao proDao;
	
	@Autowired
	private Encrypter encrypter;
	
	/**
	 * Mapping with getting all profiles method.
	 * @return  Return a list of profiles.
	 */

	@GetMapping("/getAllProfiles.do")
	public ResponseEntity<List<Profile>> list(){
		List<Profile>Plist = proDao.selectAll();
		return ResponseEntity.ok().body(Plist);
	}
	

	/**
	 * Mapping with select by user name method.
	 * @param userName    Passing user name as a parameter.
	 * @return     Returning profile of the specific user.
	 */		
	@GetMapping("/selectProfileByUsername/{userName}.do")
	public ResponseEntity<Profile> selectProfileByUserName(@PathVariable("userName") String userName){
		Profile profile = proDao.selectByUserName(userName);
		return ResponseEntity.ok().body(profile);
	}
	
	/**
	 * 
	 * Mapping with select by email method.
	 * @param email    Passing email id as a parameter.
	 * @return     Returning profile of the specific user.
	 */
	@GetMapping("/selectProfileByEmail/{email}.do")
	public ResponseEntity<List<Profile>> selectProfileByEmail(@PathVariable("email") String email){
		List<Profile> profiles = proDao.selectByEmail(email);
		return ResponseEntity.ok().body(profiles);
	}
	
	/**
	 * Mapping with the select by first name method.
	 * @param firstName   Passing first name as a parameter.
	 * @return    Return a list of profiles.
	 */
	@GetMapping("/selectProfileByFirstName/{firstName}.do")
	public ResponseEntity<List<Profile>> selectProfileByFirstName(@PathVariable("firstName") String firstName){
		List<Profile> profiles = proDao.selectByFirstName(firstName);
		return ResponseEntity.ok().body(profiles);
	}
	//insert a profile
	@CrossOrigin("http://localhost:4200")
	@PostMapping(value="/addProfile.do")
	@ResponseBody
	public HttpStatus insert(@RequestBody Profile profile){
		
		proDao.insert(profile);
		return HttpStatus.ACCEPTED;
	}
	 
	/**
	 * Mapping with select by user Id method.
	 * @param id    Passing user Id as a parameter.
	 * @return      Return the specific user profile.
	 */
	//select profile by ID
	@GetMapping("/selectProfileByID/{id}.do")
	public ResponseEntity<Profile> selectById(@PathVariable("id") int id){
		Profile profile = proDao.selectById(id);
		return ResponseEntity.ok().body(profile);
	}
	
	@PutMapping("/updateProfile/{id}")
	public Profile updateAndReturn(@PathVariable int id, @RequestBody Profile profile) {
		System.out.println("Updating profile: " + profile.toString());
		Profile newProfile = proDao.update(id, profile);
		System.out.println("New profile in controller: " + newProfile.toString());
		return newProfile;
//		return ResponseEntity.ok().body("Profile updated: " + newProfile.toString());
	}
	
	@PutMapping("/resetPassword/{username}")
	public HttpStatus resetPassword(@PathVariable String username, @RequestBody Profile profile) {
		
		Profile foundProfile = proDao.selectByUserName(username); // find profile of the user
		String tempPass = SendEmail.sendMail(foundProfile.getEmail());
		if(foundProfile.getUserPassword().equals("false")) {
			System.out.println("SMTP failed. Resetting password to 'false' for user: " + foundProfile.getUserName());	
			return HttpStatus.NOT_IMPLEMENTED;
		}
//		String tempPass = "false";
		foundProfile.setUserPassword(tempPass);
		proDao.update(foundProfile.getUserId(), foundProfile); //update the atabase
		System.out.println("Password reset and sent to: " + foundProfile.getEmail());
		return HttpStatus.OK;  //send a message to front end
		
	}
	
}




