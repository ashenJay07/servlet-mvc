package com.epay.utils;

public class PaymentInfo {
	private float transAmount;
	private String cardHolder;
	private String email;
	private String cardNumber;
	private int cardExpMonth;
	private int cardExpYear;
	private String cvv;
	
	public PaymentInfo() {}

	public float getTransAmount() {
		return transAmount;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public String getEmail() {
		return email;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public int getCardExpMonth() {
		return cardExpMonth;
	}

	public int getCardExpYear() {
		return cardExpYear;
	}

	public String getCvv() {
		return cvv;
	}

	public void setTransAmount(float transAmount) {
		this.transAmount = transAmount;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setCardExpMonth(int cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}

	public void setCardExpYear(int cardExpYear) {
		this.cardExpYear = cardExpYear;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	};
	
	
}
