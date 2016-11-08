package crm.dao;

import java.util.List;

import crm.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void addCustomer(Customer theCustomer);

	public Customer getCustomers(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomer(Customer theCustomer);
	
}
