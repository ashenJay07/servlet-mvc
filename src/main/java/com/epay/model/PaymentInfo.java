package com.epay.model;

public class PaymentInfo {
	private int id;
	private String packageName;
	private float transAmount;
	private String cardHolder;
	private String email;
	private String cardNumber;
	private int cardExpMonth;
	private int cardExpYear;
	private String transactionDate;
	private String cvv;
	private String userId;
	private int packageId;
	private int packageDuration;

	public PaymentInfo() {}

	public PaymentInfo(int id, String userId, int packageId, int packageDuration) {
		this.id = id;
		this.userId = userId;
		this.packageId = packageId;
		this.packageDuration = packageDuration;
	}

	public PaymentInfo(int id, float transAmount, String cardHolder, String email, String transactionDate, String userId) {
		this.id = id;
		this.transAmount = transAmount;
		this.cardHolder = cardHolder;
		this.email = email;
		this.transactionDate = transactionDate;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public String getPackageName() {
		return packageName;
	}

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

	public String getTransactionDate() {
		return transactionDate;
	}

	public String getCvv() {
		return cvv;
	}

	public String getUserId() {
		return userId;
	}

	public int getPackageId() {
		return packageId;
	}

	public int getPackageDuration() {
		return packageDuration;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
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

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public void setPackageDuration(int packageDuration) {
		this.packageDuration = packageDuration;
	}

	
}
