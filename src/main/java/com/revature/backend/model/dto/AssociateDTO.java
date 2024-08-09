package com.revature.backend.model.dto;

import com.revature.backend.model.Associate;

public class AssociateDTO {

	private int id;

	private String salesforceId;

	private String email;

	private String firstName;

	private String lastName;

	private int managerId;

	private int batchId;

	private String status;

	public AssociateDTO() {
	}

	public AssociateDTO(int id, String salesforceId, String email, String firstName, String lastName, int manager,
			int batch, String status) {
		super();
		this.id = id;
		this.salesforceId = salesforceId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.managerId = manager;
		this.batchId = batch;
		this.status = status;
	}

	public AssociateDTO(String salesforceId, String email, String firstName, String lastName, int manager, int batch,
			String status) {
		super();
		this.salesforceId = salesforceId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.managerId = manager;
		this.batchId = batch;
		this.status = status;
	}

	public AssociateDTO(Associate associate) {
		super();
		this.id = associate.getId();
		this.salesforceId = associate.getSalesforceId();
		this.email = associate.getEmail();
		this.firstName = associate.getFirstName();
		this.lastName = associate.getLastName();
		this.managerId = associate.getManager().getId();
		this.batchId = associate.getBatch().getId();
		this.status = associate.getStatus().toString();
	}

	@Override
	public String toString() {
		return "AssociateDTO [id=" + id + ", salesforceId=" + salesforceId + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", manageIdr=" + managerId + ", batchId=" + batchId
				+ ", status=" + status + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSalesforceId() {
		return salesforceId;
	}

	public void setSalesforceId(String salesforceId) {
		this.salesforceId = salesforceId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getManager() {
		return managerId;
	}

	public void setManager(int manager) {
		this.managerId = manager;
	}

	public int getBatch() {
		return batchId;
	}

	public void setBatch(int batch) {
		this.batchId = batch;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batchId;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + managerId;
		result = prime * result + ((salesforceId == null) ? 0 : salesforceId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		AssociateDTO other = (AssociateDTO) obj;
		if (batchId != other.batchId)
			return false;
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
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (managerId != other.managerId)
			return false;
		if (salesforceId == null) {
			if (other.salesforceId != null)
				return false;
		} else if (!salesforceId.equals(other.salesforceId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}