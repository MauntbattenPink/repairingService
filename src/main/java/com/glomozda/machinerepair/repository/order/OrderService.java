package com.glomozda.machinerepair.repository.order;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.*;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.util.List;

import com.glomozda.machinerepair.domain.client.*;
import com.glomozda.machinerepair.domain.machine.Machine;
import com.glomozda.machinerepair.domain.order.Order;
import com.glomozda.machinerepair.domain.user.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	@Autowired
	private PasswordEncoder encoder;

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
		
	}
	

	@Transactional
	   public  List<Order> fetchAllOrders(){
			return em.createQuery("SELECT u FROM Order u", Order.class).getResultList();
		}
	
	@Transactional
	   public  Order fetchOrderById(int id){
			return em.createQuery("SELECT u FROM Order u where orderid="+id, Order.class).getSingleResult();
		}
	
	@Transactional
	   public  List<Order> fetchAllOrdersByClientId(int id){
			return em.createQuery("SELECT u FROM Order u where clientsid="+id, Order.class).getResultList();
		}
	
	@Transactional
	   public  List<Order> fetchAllDoneOrders(){
			return em.createQuery("SELECT u FROM Order u where status='done'", Order.class).getResultList();
		}
	

	@Transactional
	   public  List<Order> fetchAllUndoneOrders(){
			return em.createQuery("SELECT u FROM Order u where status='accepted'", Order.class).getResultList();
		}
	
	@Transactional
	   public  List<Order> fetchAllDeclinedOrders(){
			return em.createQuery("SELECT u FROM Order u where status='declined'", Order.class).getResultList();
		}
	

	@Transactional
	   public  List<Order> fetchAllPendedOrders(){
			return em.createQuery("SELECT u FROM Order u where status='undone'", Order.class).getResultList();
		}
	

	@Transactional
	   public  List<Order> fetchAllUnpendedOrders(){
			return em.createQuery("SELECT u FROM Order u where status='done' OR status='accepted' OR status='declined'", Order.class).getResultList();
		}

	
	@Transactional
	   public  List<Order> fetchAllNewOrderById(int id){
			return em.createQuery("SELECT u FROM Order u where (status='undone' OR status='accepted') AND clientsid="+id, Order.class).getResultList();
		}


	
	
	@Transactional
	   public  List<Order> fetchAllDoneOrderById(int id){
			return em.createQuery("SELECT u FROM Order u where status='done' AND clientsid="+id, Order.class).getResultList();
		}
	
	@Transactional
	   public  List<Order> fetchAllDoneOrDeclinedOrderById(int id){
			return em.createQuery("SELECT u FROM Order u where (status='done' OR status='declined') AND clientsid="+id, Order.class).getResultList();
		}

	@Transactional
	   public  List<Order> fetchAllUndoneOrderById(int id){
			return em.createQuery("SELECT u FROM Order u where status='accepted'AND clientsid="+id, Order.class).getResultList();
		}
	
	@Transactional(noRollbackFor = Exception.class)
	   public void PayById(int i) throws SQLException{
	
		  dataSource.setDriver(new com.mysql.jdbc.Driver());
	        dataSource.setUrl("jdbc:mysql://localhost/machinerepair?user=root&amp;password=44morning44");
	        dataSource.setUsername("root");
	        dataSource.setPassword("44morning44");
	         
	        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	        String sqlUpdate = "UPDATE orders set status=? where orderid=?";
	        jdbcTemplate.update(sqlUpdate, "done", i);
	      
	   // return em.merge(ord);
		}
	
	
	@Transactional(noRollbackFor = Exception.class)
	   public void acceptId(int i) throws SQLException{
	
		  dataSource.setDriver(new com.mysql.jdbc.Driver());
	        dataSource.setUrl("jdbc:mysql://localhost/machinerepair?user=root&amp;password=44morning44");
	        dataSource.setUsername("root");
	        dataSource.setPassword("44morning44");
	         
	        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	        String sqlUpdate = "UPDATE orders set status=? where orderid=?";
	        jdbcTemplate.update(sqlUpdate, "accepted", i);
	      
	   // return em.merge(ord);
		}
	
	
	
	@Transactional(noRollbackFor = Exception.class)
	   public void declineId(int i) throws SQLException{
	
		  dataSource.setDriver(new com.mysql.jdbc.Driver());
	        dataSource.setUrl("jdbc:mysql://localhost/machinerepair?user=root&amp;password=44morning44");
	        dataSource.setUsername("root");
	        dataSource.setPassword("44morning44");
	         
	        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	        String sqlUpdate = "UPDATE orders set status=? where orderid=?";
	        jdbcTemplate.update(sqlUpdate, "declined", i);
	      
	   // return em.merge(ord);
		}
	
	
	
	@Transactional
	public void add(Order u) {
		em.persist(u);
	}	

	
	
	SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
	 
	// code to set driver class name, database URL, username and password
	 
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
}
