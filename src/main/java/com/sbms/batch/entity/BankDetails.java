package com.sbms.batch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankDetails {

	@Id
	private String personId;

	private String accountType;

	private String bankName;

	private String accountNo;

	public String getPersonId() {
		return personId;
	}

	public String getAccountType() {
		return accountType;
	}

	public String getBankName() {
		return bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public BankDetails(String personId, String accountType, String bankName, String accountNo) {
		super();
		this.personId = personId;
		this.accountType = accountType;
		this.bankName = bankName;
		this.accountNo = accountNo;
	}

	public BankDetails() {
		super();
	}

	@Override
	public String toString() {
		return "BankDetails [personId=" + personId + ", accountType=" + accountType + ", bankName=" + bankName
				+ ", accountNo=" + accountNo + "]";
	}

	
}
