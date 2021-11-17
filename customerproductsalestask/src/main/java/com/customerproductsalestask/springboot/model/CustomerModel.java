package com.customerproductsalestask.springboot.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "customer")
public class CustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	
	@NotEmpty(message="Full name field can not be empty")
	@Size(max=20, min=3, message="Length of full name should be between 20 and 3")
	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Email(message="Should be in email format") 
	@NotEmpty(message="Email field can not be empty")
	@Column(name = "email_address", unique = true)
	private String emailAddress;

	@Size(max=10, min=7, message="Length of mobile number should be between 10 and 7")
	@Column(name = "mobile_number", unique = true, length = 10)
	private String mobileNumber;

	@Column(name = "is_regular")
	private boolean isRegular;
  
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Date of birth field is mandatory")
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@NotEmpty(message="Address field can not be empty") 
	@Column(name = "address", nullable = false)
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isRegular() {
		return isRegular;
	}

	public void setRegular(boolean isRegular) {
		this.isRegular = isRegular;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
