package com.revature.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "batch")
public class Batch {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// IDENTITY was preferred to AUTO.
	private int id;

	@Column(name = "salesforce_id")
	private String salesforceID;

	@Column(name = "name")
	private String name;

	@Column(name = "skill")
	private String skill;

	@Column(name = "location")
	private String location;

	public Batch() {
	}

	public Batch(int id, String salesforceID, String name, String skill, String location) {
		super();
		this.id = id;
		this.salesforceID = salesforceID;
		this.name = name;
		this.skill = skill;
		this.location = location;
	}

	public Batch(String salesforceID, String name, String skill, String location) {
		super();
		this.salesforceID = salesforceID;
		this.name = name;
		this.skill = skill;
		this.location = location;
	}

	@Override
	public String toString() {
		return "batch [id=" + id + ", salesforce_id=" + salesforceID + ", name=" + name + ", skill=" + skill
				+ ", location=" + location + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSalesforceID() {
		return salesforceID;
	}

	public void setSalesforceID(String salesforce_id) {
		this.salesforceID = salesforce_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Batch))
			return false;
		Batch batch = (Batch) o;
		return getId() == batch.getId() && Objects.equals(getSalesforceID(), batch.getSalesforceID())
				&& Objects.equals(getName(), batch.getName()) && Objects.equals(getSkill(), batch.getSkill())
				&& Objects.equals(getLocation(), batch.getLocation());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getSalesforceID(), getName(), getSkill(), getLocation());
	}
}
