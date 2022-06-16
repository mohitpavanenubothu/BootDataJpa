package com.sbms.batch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbms.batch.entity.BankDetails;

public interface BankDetailsRepo extends JpaRepository<BankDetails, String> {

}
