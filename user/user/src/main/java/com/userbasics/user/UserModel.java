package com.userbasics.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// This Json ignore at class level also ignores the fields to be not sent in response
// @JsonIgnoreProperties(value= {"field1, field2"})

// Define Dynamic filter here
//@JsonFilter("UserModelFilter")
@ApiModel(description = "Details about UserModel for Swagger")
@Entity
public class UserModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	// Added valdiation for size
	// java Validation API 
	@Size(min=2, message="Name should have atleast 2 characters")
	@ApiModelProperty(notes = "Name should have atleast 2 Characters")
	private String name;
	
	//@Past
	//@ApiModelProperty(notes = "Birth Date should be in past")
	//private Date birthDate;
	
	// This field would be filtered and wont come in CALL made
	// @JsonIgnore
	//private String userFieldToBeFiltered;
	
	public UserModel() {}
	public UserModel(Integer id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	/*
	public UserModel(Integer id, String name, Date birthDate, String userFieldToBeFiltered) {
		super();
		this.id = id;
		this.name = name;
		//this.birthDate = birthDate;
		//this.userFieldToBeFiltered = userFieldToBeFiltered;
	}
	*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getUserFieldToBeFiltered() {
		return userFieldToBeFiltered;
	}

	public void setUserFieldToBeFiltered(String userFieldToBeFiltered) {
		this.userFieldToBeFiltered = userFieldToBeFiltered;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", userFieldToBeFiltered="
				+ userFieldToBeFiltered + "]";
	}
*/
	
	
	
	
	
	

}
