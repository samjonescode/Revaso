package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOB")
public class Job {
	
	@Id
	@Column(name="job_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int jobId;
	
	
	@Column(name="job_name",unique=true, nullable=false)
	private String jobName;
	
	@Column(name="job_role")
	private String jobRole;
	
	public Job() {
	}
	public Job(int jobId, String jobName, String jobRole) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.jobRole = jobRole;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}



	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobName=" + jobName + ", jobRole=" + jobRole + "]";
	}

	public Job(String jobName, String jobRole) {
		super();
		this.jobName = jobName;
		this.jobRole = jobRole;
	}
	
	
	
}
