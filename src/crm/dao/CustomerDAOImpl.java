package crm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crm.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName",
				                                               Customer.class);
			
		// get the result list
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void addCustomer(Customer theCustomer) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
        
		// save/update the customer ... finally 
		currentSession.saveOrUpdate(theCustomer);;
	}

	@Override
	public Customer getCustomers(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		// do the work
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomer(Customer theCustomer) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		String firstName = theCustomer.getFirstName();
		String lastName = theCustomer.getLastName();
		String email = theCustomer.getEmail();
		System.out.println(firstName);
		
		Query<Customer> theQuery = null;
		String hql = "";
		
		    // search customers by firstName
			hql = "from Customer where firstName=:firstName";
			theQuery = currentSession.createQuery(hql);
			theQuery.setParameter("firstName", firstName);
			List<Customer> customers = theQuery.getResultList();
			
			// search customers by lastName
			hql = "from Customer where lastName=:lastName";
			theQuery = currentSession.createQuery(hql);
			theQuery.setParameter("lastName", lastName);
			List<Customer> customers1 = theQuery.getResultList();
			customers.addAll(customers1);
		    
			// search customers by email
			hql = "from Customer where email=:email";
			theQuery = currentSession.createQuery(hql);
			theQuery.setParameter("email", email);
			List<Customer> customers2 = theQuery.getResultList();
			customers.addAll(customers2);	
	
		//System.out.println(customers);
		
		return customers;
	}


}
