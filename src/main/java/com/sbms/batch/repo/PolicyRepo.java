package com.sbms.batch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbms.batch.entity.PolicyDetails;

public interface PolicyRepo extends JpaRepository<PolicyDetails, String> {

}
