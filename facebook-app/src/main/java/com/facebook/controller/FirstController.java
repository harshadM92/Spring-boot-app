package com.facebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import com.facebook.DAO.FirstEntity;
import com.facebook.service.FirstService;

@RestController
@RequestMapping("FirstController")
public class FirstController {

	@Autowired
	private FirstService firstService;
	
	@RequestMapping("/hello/{id}")
	public FirstEntity getMyString(@PathVariable String id) {
		return firstService.getMyString(id);
	}
	
	// for 403 access denied page
	@RequestMapping(value = "/403")
	public String accesssDenied(Principal user) {
		return "unauthorized";
	}
}
