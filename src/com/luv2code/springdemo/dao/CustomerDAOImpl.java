package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject session facrtory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	//@Transactional ->> because we are moving this functionality to service layer
	public List<Customer> getCustomers() {
		
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//create a query...sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by last_name",
																Customer.class);
		
		//execute query and get result set
		List<Customer> customers=theQuery.getResultList();
		
		//return the result
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//save the customer
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomers(int theId) {
		//get the hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//retrieve from database using primary key
		Customer theCustomer=currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		//get the hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//delete object with primary key
		Query theQuery=currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
	}

}
