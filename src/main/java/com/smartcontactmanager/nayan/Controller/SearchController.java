package com.smartcontactmanager.nayan.Controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smartcontactmanager.nayan.Dao.ContactRepository;
import com.smartcontactmanager.nayan.Dao.UserRepository;
import com.smartcontactmanager.nayan.Entity.Contact;
import com.smartcontactmanager.nayan.Entity.User;
import com.smartcontactmanager.nayan.Service.WriteToCSV;


@RestController
public class SearchController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ContactRepository contactRepo;
	
	//search handler
	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query,Principal principal){
		
		
		User user = userRepo.getUserByUserName(principal.getName());
		
		List<Contact> contacts = contactRepo.findByNameContainingAndUser(query, user);
		
		return ResponseEntity.ok(contacts);
		
	}
	
	
	
	
	
	
	
	
	
	@GetMapping("/user/download")
	public void downloaCsvFile(Principal principal,HttpServletResponse response) throws IOException {
		 
		User user = userRepo.getUserByUserName(principal.getName());
		
		
		response.setContentType("text/csv");
		response.setHeader("Content-Display","attachment; file = contact.csv");
		List<Contact> contacts = user.getContacts();
		  
		WriteToCSV.writeToCsv(response.getWriter(), contacts);
		 
		
		  
		
	}
	
	
	
}
