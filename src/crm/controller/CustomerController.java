package crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import crm.dao.CustomerDAO;
import crm.entity.Customer;
import crm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// injecting customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// getting customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
				
		// adding customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// creating model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/addCustomer")
	public String addCustomer(@ModelAttribute("customer") Customer theCustomer) {

		customerService.addCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		// getting the customer from the service
		Customer theCustomer = customerService.getCustomers(theId);
		
		// setting customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// sending over to our form
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {		
		
		// deleting the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";		
	}
	
	@GetMapping("/showFormForSearchResult")
	public String showFormForSearchResult(Model theModel) {
		
		// creating model attribute to bind form data
		Customer theCustomer = new Customer();
	
		// setting customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// sending over to our form
		return "search-customer-form";
		
	}
	
	@GetMapping("/SearchCustomer")
	public String SearchCustomer(@ModelAttribute("customer") Customer theCustomer, Model theModel) {

		List<Customer> theCustomers = customerService.searchCustomer(theCustomer);
		
		// adding the customers to the model
		theModel.addAttribute("customers", theCustomers);
		System.out.println("controller " + theCustomers);		
		
		return "list-search-customers";
	}
}


