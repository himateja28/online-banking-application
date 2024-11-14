package klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import klu.model.*;
//import oqa.service.FundManager;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	FundManager FM;
	
	@PostMapping("/create")
	public String createFund(@RequestBody Fund F)
	{
		return FM.createFund(F);
	}
}
