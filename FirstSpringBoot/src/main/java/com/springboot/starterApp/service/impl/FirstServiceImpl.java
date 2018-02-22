package com.springboot.starterApp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.starterApp.DAO.FirstEntity;
import com.springboot.starterApp.repository.FirstRepository;
import com.springboot.starterApp.service.FirstService;

@Service
@Transactional
public class FirstServiceImpl implements FirstService {

	@Autowired
	private FirstRepository firstRepository;
	
	@Override
	public FirstEntity getMyString(String id) {
		// TODO Auto-generated method stub
		List<FirstEntity> firstEntity = firstRepository.findAll();
		if(firstEntity !=null && firstEntity.size() > 0) {
			return firstEntity.get(0);	
		}
		return null;
	}

	
}
