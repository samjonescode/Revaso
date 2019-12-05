package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Profile;
import com.example.repository.ProfileDao;

@Service
public class ProfileServ {
	
	@Autowired
	ProfileDao profiledao;
	
	public List<Profile> selectAll(){
		return profiledao.selectAll();
	}
	
	public void insert(Profile profile) {
		profiledao.insert(profile);
	}
	
	public Profile selectById(int id) {
		return profiledao.selectById(id);
	}
	
//	public void update(int id, Profile proflie) {
//		profiledao.update(id , profile);
//	}

}