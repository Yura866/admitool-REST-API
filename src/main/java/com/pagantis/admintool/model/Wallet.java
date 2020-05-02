package com.pagantis.admintool.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "wallets")
public class Wallet implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
    @Column(name="balance")
	private double balance;
    
    @Column(name="name")
    @NotBlank(message = "Enter the wallet's name")
	private String name; 
    
    @Column(name="address")
	private String address;
    
    @Column(name="private_key")
	private String privateKey;

   
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)	
    @JsonManagedReference
    @JsonIgnore
	private User user;	    
       
	
	public Wallet() {	}

	public Wallet(Long id, double balance, @NotBlank(message = "Enter the wallet's name") String name, String address,
			String privateKey, User user) {
		super();
		this.id = id;
		this.balance = balance;
		this.name = name;
		this.address = address;
		this.privateKey = privateKey;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	public Long getUser_Id() {		
		return this.user.getId();
	}
	
	public String getUserName() {		
		return this.user.getUserName();
	}
}
