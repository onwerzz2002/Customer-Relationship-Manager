package crm.service;

import java.util.List;

import crm.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void addCustomer(Customer theCustomer);

	public Customer getCustomers(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomer(Customer theCustomer);
	
}
