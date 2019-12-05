package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.encryption.Encrypter;
import com.example.helpers.LoginCredentials;
import com.example.model.Profile;
import com.example.repository.ProfileDao;

@RestController
@CrossOrigin
@Component
@SessionAttributes("loggedInUser")
public class LoginController {

	@Autowired
	ProfileDao proDao;

	@Autowired
	Encrypter encrypter;

	@PostMapping(value = "/login.do")
	public Profile login(@RequestBody LoginCredentials creds) {
		if (creds == null) {
			return null;
		}
		Profile found = returnLoggedInUser(creds.getUsername());
		if (creds.getPassword().equals(found.getUserPassword())) {
			return found;
		} else {
			return null;
		}
	}

	@ModelAttribute("loggedInUser")
	public Profile returnLoggedInUser(String userName) {
		Profile p = proDao.selectByUserName(userName);
		return p;
	}
}
