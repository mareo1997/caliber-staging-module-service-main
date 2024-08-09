package com.revature.backend.model.api;

public class ApiEmployeeAssignment {
	String role;
	ApiEmployeeTemplate employee;
	String deletedAt;

	public ApiEmployeeAssignment() {
		super();
	}

	public ApiEmployeeAssignment(String role, ApiEmployeeTemplate employee, String deletedAt) {
		super();
		this.role = role;
		this.employee = employee;
		this.deletedAt = deletedAt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ApiEmployeeTemplate getEmployee() {
		return employee;
	}

	public void setEmployee(ApiEmployeeTemplate employee) {
		this.employee = employee;
	}

	public String getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		ApiEmployeeAssignment other = (ApiEmployeeAssignment) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ApiEmployeeAssignment [role=" + role + ", employee=" + employee + ", deletedAt=" + deletedAt + "]";
	}

}
