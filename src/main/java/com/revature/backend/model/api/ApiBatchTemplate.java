package com.revature.backend.model.api;

import java.util.Arrays;

//Used for getting data from the caliber API and putting it into an object.
public class ApiBatchTemplate {
	int id;
	String batchId;
	String name;
	String startDate;
	String endDate;
	String skill;
	String location;
	String type;
	double goodGrade;
	double passingGrade;
	ApiEmployeeAssignment[] employeeAssignments;
	ApiAssociateAssignment[] associateAssignments;
	int currentWeek;
	public ApiBatchTemplate() {
		super();
	}
	public ApiBatchTemplate(int id, String batchId, String name, String startDate, String endDate, String skill,
			String location, String type, double goodGrade, double passingGrade,
			ApiEmployeeAssignment[] employeeAssignments, ApiAssociateAssignment[] associateAssignments,
			int currentWeek) {
		super();
		this.id = id;
		this.batchId = batchId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.skill = skill;
		this.location = location;
		this.type = type;
		this.goodGrade = goodGrade;
		this.passingGrade = passingGrade;
		this.employeeAssignments = employeeAssignments;
		this.associateAssignments = associateAssignments;
		this.currentWeek = currentWeek;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getGoodGrade() {
		return goodGrade;
	}
	public void setGoodGrade(double goodGrade) {
		this.goodGrade = goodGrade;
	}
	public double getPassingGrade() {
		return passingGrade;
	}
	public void setPassingGrade(double passingGrade) {
		this.passingGrade = passingGrade;
	}
	public ApiEmployeeAssignment[] getEmployeeAssignments() {
		return employeeAssignments;
	}
	public void setEmployeeAssignments(ApiEmployeeAssignment[] employeeAssignments) {
		this.employeeAssignments = employeeAssignments;
	}
	public ApiAssociateAssignment[] getAssociateAssignments() {
		return associateAssignments;
	}
	public void setAssociateAssignments(ApiAssociateAssignment[] associateAssignments) {
		this.associateAssignments = associateAssignments;
	}
	public int getCurrentWeek() {
		return currentWeek;
	}
	public void setCurrentWeek(int currentWeek) {
		this.currentWeek = currentWeek;
	}
	@Override
	public String toString() {
		return "ApiBatchTemplate [id=" + id + ", batchId=" + batchId + ", name=" + name + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", skill=" + skill + ", location=" + location + ", type=" + type
				+ ", goodGrade=" + goodGrade + ", passingGrade=" + passingGrade + ", employeeAssignments="
				+ Arrays.toString(employeeAssignments) + ", associateAssignments="
				+ Arrays.toString(associateAssignments) + ", currentWeek=" + currentWeek + "]";
	}
	
	
	
	
	
	
}
