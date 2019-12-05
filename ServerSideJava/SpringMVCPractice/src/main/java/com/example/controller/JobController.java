package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Job;
import com.example.repository.JobDao;

@RestController
public class JobController {
	
	@Autowired
	private JobDao jobDao;
	
	public JobController() {
	}
	
	@RequestMapping(value="/allJobs.app", method=RequestMethod.GET)
	public List<Job> getAllJobs(){
		return jobDao.selectAll();
	}
	
	@RequestMapping(value="/addJob.app", method=RequestMethod.POST)
	public void addJob(@RequestParam int jobId, @RequestParam String jobName, @RequestParam String jobRole) {
		Job j = new Job(jobId, jobName, jobRole);
		jobDao.insert(j);
		
	}
	
//	@GetMapping(value="/index")
//	public String index() {
//		return "/index.html";
//	}
	
}
