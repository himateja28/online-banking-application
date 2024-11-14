package klu.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.repo.FundRepo;
import klu.repo.UserRepository;
import klu.model.Fund;
import klu.model.User;

@Service
public class UserManager {
	
	@Autowired
	UserRepository UR;
	
	@Autowired
	FundRepo FR;
	
	public String signup(User U)
	{
		try {
			if(UR.validateUser(U.getUsername())>0)
					throw new Exception("Username already exists..");
			UR.save(U);
			return "New User has been added..";
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	public String login(User U)
	{
		try {
			
			if(UR.validateLogin(U.getUsername(), U.getPassword())==1)
			{
				return "Login successfull";
			}
			else
			{
				throw new Exception("Login failed");
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String deposit(String username,double amount)
	{
		try {
			 Optional<User> userOptional = UR.findByUsername(username);

		        if (userOptional.isPresent()) {
		            User user = userOptional.get();
		            Double currentAmount = user.getBalance();
		            user.setBalance(amount+currentAmount);
		             UR.save(user);
		             return "deposited successfully amount :"+amount;
		}
		        return "user not present";
		        } catch (Exception e) {
			return e.getMessage();
		}
	}
	
	
	public String withdraw(String username,double amount)
	{
		try {
			
			 Optional<User> userOptional = UR.findByUsername(username);

		        if (userOptional.isPresent()) {
		            User user = userOptional.get();
		            Double currentAmount = user.getBalance();
		            if(currentAmount<=0)
		            {
		            	return "insufficent balance";
		            }
		            user.setBalance(currentAmount-amount);
		             UR.save(user);
		             return "withdraw successfully amount :"+amount;
		}
		        return "user not present";
		        } catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String donate(String username,Long id, double amount)
	{
		try {
			Optional<User> userOptional = UR.findByUsername(username);

	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            Double currentAmount = user.getBalance();
	            if(currentAmount<=0)
	            {
	            	return "insufficent funds in account";
	            }
	            user.setBalance(currentAmount-amount);
	             UR.save(user);
	            
	             Optional<Fund> fundoptional = FR.findById(id);
	             
	             if(fundoptional.isPresent())
	             {
	            	 Fund fund = fundoptional.get();
	            	 Double currentFund = fund.getAmountraised();
	            	 fund.setAmountraised(amount+currentFund);
	            	 FR.save(fund);
	            	 return "Fund donated successfully";
	             }
	             return "Fund donation errror";
		}
	        return "user error";
		}catch (Exception e) {
			return e.getMessage();
		}
	}

	public double getBalance(String username) {
		try {
			return UR.findBalanceByUsername(username);
		} catch (Exception e) {
			return 0;
		}
		
	}
}
