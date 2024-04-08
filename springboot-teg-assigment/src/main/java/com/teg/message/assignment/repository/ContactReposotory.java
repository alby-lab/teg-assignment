package com.teg.message.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teg.message.assignment.entity.Contacts;

@Repository
public interface ContactReposotory extends JpaRepository<Contacts, Integer>{

}
