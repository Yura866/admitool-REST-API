package com.pagantis.admintool.dto;

import com.pagantis.admintool.model.User;

public class WalletDTO {
	
	private Long id;

	private double balance;
	private String name;	
    private String address;
	private String privateKey;	
	private Long userId;
	private User user;
	
	//fields for send coins
	private String transferFrom;
	private String transferTo;
	private double amount;
	private String message;

	
	public WalletDTO() {}	
	
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

	public String getTransferFrom() {
		return transferFrom;
	}

	public void setTransferFrom(String transferFrom) {
		this.transferFrom = transferFrom;
	}

	public String getTransferTo() {
		return transferTo;
	}

	public void setTransferTo(String transferTo) {
		this.transferTo = transferTo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}		
	
	@Override
	public String toString() {
		return "WalletDTO [id=" + id + ", balance=" + balance + ", name=" + name + ", address=" + address + ", privateKey=" + privateKey + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
