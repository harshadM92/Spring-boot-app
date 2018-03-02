package com.facebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facebook.DAO.UserDetail;

@Repository
public interface TokenRepository extends JpaRepository<UserDetail,String> {

	
}
