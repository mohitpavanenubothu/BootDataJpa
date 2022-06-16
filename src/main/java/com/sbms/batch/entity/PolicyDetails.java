package com.sbms.batch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PolicyDetails {

	@Id
	private String personId;

	private String policyId;

	private String amount;

	public String getPersonId() {
		return personId;
	}

	public String getPolicyId() {
		return policyId;
	}

	public String getAmount() {
		return amount;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public PolicyDetails(String personId, String policyId, String amount) {
		super();
		this.personId = personId;
		this.policyId = policyId;
		this.amount = amount;
	}

	public PolicyDetails() {
		super();
	}

	@Override
	public String toString() {
		return "PolicyDetails [personId=" + personId + ", policyId=" + policyId + ", amount=" + amount + "]";
	}

}
