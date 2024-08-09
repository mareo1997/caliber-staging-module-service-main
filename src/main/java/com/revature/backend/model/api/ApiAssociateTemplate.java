package com.revature.backend.model.api;

public class ApiAssociateTemplate {
	String email;
	String salesforceId;
	String firstName;
	String lastName;
	ApiFlagTemplate flag;
	public ApiAssociateTemplate() {
		super();
	}
	public ApiAssociateTemplate(String email, String salesforceId, String firstName, String lastName, ApiFlagTemplate flag) {
		super();
		this.email = email;
		this.salesforceId = salesforceId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.flag = flag;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSalesforceId() {
		return salesforceId;
	}
	public void setSalesforceId(String salesforceId) {
		this.salesforceId = salesforceId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public ApiFlagTemplate getFlag() {
		return flag;
	}
	public void setFlag(ApiFlagTemplate flag) {
		this.flag = flag;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((flag == null) ? 0 : flag.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((salesforceId == null) ? 0 : salesforceId.hashCode());
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
		ApiAssociateTemplate other = (ApiAssociateTemplate) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (flag == null) {
			if (other.flag != null)
				return false;
		} else if (!flag.equals(other.flag))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (salesforceId == null) {
			if (other.salesforceId != null)
				return false;
		} else if (!salesforceId.equals(other.salesforceId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ApiAssociateTemplate [email=" + email + ", salesforceId=" + salesforceId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", flag=" + flag + "]";
	}
	
	
	
}
