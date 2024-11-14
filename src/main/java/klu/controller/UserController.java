package klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import klu.model.*;
//import oqa.service.UserManager;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserManager UM;
	
	@PostMapping("/signup")
	public String signup(@RequestParam("username")String u ,@RequestParam("password")String pwd)
	{
		User U=new User();
		U.setUsername(u);
		U.setPassword(pwd);
		U.setBalance(0);
		U.setRole("user");
		return UM.signup(U);
	}
	
	@GetMapping("/login")
	public String login(@RequestBody User U)
	{
		return UM.login(U);
	}
	
	@PostMapping("/deposit/{un}")
	@ResponseBody
	public String userDeposit(@PathVariable("un")String username,@RequestParam("amt") double amount)
	{
		return UM.deposit(username, amount);
	}
	
	@PostMapping("/withdraw/{un}")
	public String userWithdraw(@PathVariable("un")String username,@RequestParam("amt") double amount)
	{
		return UM.withdraw(username, amount);
	}
	
	@PostMapping("/donate/{un}")
	public String userDonate(@PathVariable("un")String username,@RequestParam("id")Long fid ,@RequestParam("amt")double amount)
	{
		return UM.donate(username,fid, amount);
	}
}
