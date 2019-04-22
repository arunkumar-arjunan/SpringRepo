package com.pro.customer.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pro.customer.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session curSession = sessionFactory.getCurrentSession();
		// create a query
		Query<Customer> theQuery = curSession.createQuery("from Customer order by lastName",Customer.class);
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get the current hibernate session
			Session curSession = sessionFactory.getCurrentSession();
		// save the customer
			curSession.saveOrUpdate(theCustomer);		
	}

	@Override
	public Customer getCustomer(int theId) {
		// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
				
		// now retrieve/read from database using the primary key
			Customer theCustomer = currentSession.get(Customer.class, theId);
			
			return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query theQuery = currentSession.createQuery("delete from Customer where id=:custId");
		theQuery.setParameter("custId",theId);
		theQuery.executeUpdate();
	}



}
