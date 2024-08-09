package com.revature.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "manager")
public class Manager {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// IDENTITY was preferred to AUTO.
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	public Manager() {
	}

	public Manager(int id, String email, String firstName, String lastName) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Manager(String email, String firstName, String lastName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Manager))
			return false;
		Manager manager = (Manager) o;
		return getId() == manager.getId() && Objects.equals(getEmail(), manager.getEmail())
				&& Objects.equals(getFirstName(), manager.getFirstName())
				&& Objects.equals(getLastName(), manager.getLastName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getEmail(), getFirstName(), getLastName());
	}

	public String toString() {
		return "Manager(id=" + this.getId() + ", email=" + this.getEmail() + ", first_name=" + this.getFirstName()
				+ ", last_name=" + this.getLastName() + ")";
	}
}
