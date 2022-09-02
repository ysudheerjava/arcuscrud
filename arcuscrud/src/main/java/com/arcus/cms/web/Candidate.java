package com.arcus.cms.web;

public class Candidate {
	protected int id;
	protected String name;
	protected String gender;
	protected String email;
	protected String qualification;
	protected String state;

	/**
	 * @param id
	 * @param name
	 * @param gender
	 * @param email
	 * @param qualification
	 * @param state
	 */
	public Candidate(int id, String name, String gender, String email, String qualification, String state) {

		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.qualification = qualification;
		this.state = state;

	}

	public Candidate(String name, String gender, String email, String qualification, String state) {

		this.name = name;
		this.gender = gender;
		this.email = email;
		this.qualification = qualification;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}