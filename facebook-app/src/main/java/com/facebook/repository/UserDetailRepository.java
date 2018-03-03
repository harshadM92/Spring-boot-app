package com.facebook.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.facebook.DAO.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail,String> {

	@Query("SELECT ud from UserDetail ud where userName=:userName")
	public UserDetail findUserDetailByUserName(@Param("userName") String userName);
	
}
