package com.revature.backend.model.api;

public class ApiAssociateAssignment {

	String trainingStatus;
	ApiAssociateTemplate associate;
	String startDate;
	String endDate;
	boolean active;
	public ApiAssociateAssignment() {
		super();
	}
	public ApiAssociateAssignment(String trainingStatus, ApiAssociateTemplate associate, String startDate,
			String endDate, boolean active) {
		super();
		this.trainingStatus = trainingStatus;
		this.associate = associate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
	}
	public String getTrainingStatus() {
		return trainingStatus;
	}
	public void setTrainingStatus(String trainingStatus) {
		this.trainingStatus = trainingStatus;
	}
	public ApiAssociateTemplate getAssociate() {
		return associate;
	}
	public void setAssociate(ApiAssociateTemplate associate) {
		this.associate = associate;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((associate == null) ? 0 : associate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((trainingStatus == null) ? 0 : trainingStatus.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiAssociateAssignment other = (ApiAssociateAssignment) obj;
		if (active != other.active)
			return false;
		if (associate == null) {
			if (other.associate != null)
				return false;
		} else if (!associate.equals(other.associate))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (trainingStatus == null) {
			if (other.trainingStatus != null)
				return false;
		} else if (!trainingStatus.equals(other.trainingStatus))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ApiAssociateAssignment [trainingStatus=" + trainingStatus + ", associate=" + associate + ", startDate="
				+ startDate + ", endDate=" + endDate + ", active=" + active + "]";
	}
	
	
	
	
}
