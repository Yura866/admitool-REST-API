package com.pagantis.admintool.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

	@Entity
	@Table(name = "users")
	public class User implements Serializable {
		
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
		@GenericGenerator(name="native",strategy="native")
		private Long id;		
		
		
		@Column(name = "first_name", nullable = false)
		@NotBlank(message = "Enter the user's first name")
		private String firstName;	
		
		
		@Column(name = "last_name")
		@NotBlank(message = "Enter the user's last name")
		private String lastName;
		
		@Column(unique = true) 
		private String email; 
		
		@Column
		private String company;
		
		@Column 
		private String address;
		
		@Column
		private String city;
		
		@Column
		private String country;
		
		@Column
		private String zipCode;
		
		@Column(unique = true) 
		@NotBlank(message = "Enter the username")
		private String userName;			
		
		
		@OneToMany(mappedBy="user" , fetch = FetchType.LAZY )
		@OnDelete(action = OnDeleteAction.CASCADE)
		@JsonBackReference
		private List<Wallet> wallets = new ArrayList<>();
		
		
		public User() {}
		
		
		public User(Long id, @NotBlank(message = "Enter the user's first name") String firstName,
				@NotBlank(message = "Enter the user's last name") String lastName, String email, String company,
				String address, String city, String country, String zipCode,
				@NotBlank(message = "Enter the username") String userName, List<Wallet> wallets) {
			super();	
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.company = company;
			this.address = address;
			this.city = city;
			this.country = country;
			this.zipCode = zipCode;
			this.userName = userName;
			this.wallets = wallets;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
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


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public String getCompany() {
			return company;
		}


		public void setCompany(String company) {
			this.company = company;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public String getCity() {
			return city;
		}


		public void setCity(String city) {
			this.city = city;
		}


		public String getCountry() {
			return country;
		}


		public void setCountry(String country) {
			this.country = country;
		}


		public String getZipCode() {
			return zipCode;
		}


		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}


		public List<Wallet> getWallets() {
			return wallets;
		}


		public void setWallets(List<Wallet> wallets) {
			this.wallets = wallets;
		}
		
	
}
