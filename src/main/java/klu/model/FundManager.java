package klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.repo.FundRepo;

@Service
public class FundManager 
{

	@Autowired
	FundRepo FR;
	
	public String createFund(Fund F)
	{
		try {
			FR.save(F);
			return "Fund created successfully";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
