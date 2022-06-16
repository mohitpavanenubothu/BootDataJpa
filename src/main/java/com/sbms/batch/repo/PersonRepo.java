package com.sbms.batch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbms.batch.entity.Person;

public interface PersonRepo extends JpaRepository<Person, String> {

}
