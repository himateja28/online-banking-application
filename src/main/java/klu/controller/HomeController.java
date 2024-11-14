package klu.controller;

import java.security.Principal;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.annotation.security.RolesAllowed;
import klu.model.UserManager;

@Controller
public class HomeController {

	@Autowired
	UserManager UM;
	
    @GetMapping("/login")
    public String login() {
        return "login";  // returns login.html
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        // Replace these with your actual methods to get user details
        String username = principal.getName(); // Method to fetch username
        double availableBalance = UM.getBalance(username); // Method to fetch balance
        
        model.addAttribute("username", username);
        model.addAttribute("availableBalance", availableBalance);
        return "home"; // Name of the Thymeleaf template
    }
    
    @GetMapping("/signup")
    public String signup() {
        return "signup";  
    }
    
    @GetMapping("/deposit")
    public String deposit(Model model, Principal principal) {
    	String username = principal.getName(); // Get the username of the authenticated user
        model.addAttribute("username", username);
        return "deposite";  
    }
    
    @GetMapping("/withdraw")
    public String withdraw(Model model, Principal principal) {
        String username = principal.getName(); // Get the username of the authenticated user
        model.addAttribute("username", username);
        return "withdraw"; // Return the Thymeleaf template name
    }
    
    @GetMapping("/donate")
    public String donate(Model model, Principal principal) {
    	String username = principal.getName(); // Get the username of the authenticated user
        model.addAttribute("username", username);
        return "donate";  
    }
    @GetMapping("/*")
    public String error()
    {
    	return "error";
    }
    
    @GetMapping("/createfund")
    public String createFund()
    {
    	return "fund";
    }
    
    @GetMapping("/about")
    public String about()
    {
    	return "about";
    }
    @GetMapping("/contact")
    public String contact()
    {
    	return "contact";
    }
}
