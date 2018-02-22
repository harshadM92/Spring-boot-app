package com.springboot.starterApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.starterApp.DAO.FirstEntity;

public interface FirstRepository extends JpaRepository<FirstEntity, String>{

}
