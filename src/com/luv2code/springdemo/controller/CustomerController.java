package com.luv2code.springdemo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//need to inject the customer dao;
	@Autowired
	//public CustomerDAO customerDAO; -->> now service will handle this part
	public CustomerService customerService;
		
	
	//@RequestMapping("/list")
	@GetMapping("/list")
	public String listCustomer(Model theModel) {
		
		//get customers from the dao
		List<Customer> theCustomers=customerService.getCustomers();
		
		//add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to build form data
		Customer theCustomer=new Customer();
		
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		//save the customer using our service
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		//get the customer from the service
		Customer theCustomer=customerService.getCustomers(theId);
		
		//set customer as a model attribute to pre populate the form
		theModel.addAttribute("customer",theCustomer);
		
		//send over the form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		
		//delete the customer
		customerService.deleteCustomer(theId);
		
		//send over the form
		return "redirect:/customer/list";
	}
	
	

}
