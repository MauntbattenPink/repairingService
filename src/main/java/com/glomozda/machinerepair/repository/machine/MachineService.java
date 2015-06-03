package com.glomozda.machinerepair.repository.machine;
import java.sql.SQLException;
import java.util.List;

import com.glomozda.machinerepair.domain.machine.Machine;
import com.glomozda.machinerepair.domain.repair_type.Repair_Type;
import com.glomozda.machinerepair.domain.user.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MachineService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PasswordEncoder encoder;


	@Transactional
   public  List <Machine> fetchAllMachines(){
		return em.createQuery("SELECT u FROM Machine u", Machine.class).getResultList();
	}

	@Transactional
	   public  Machine fetchMachineById(int id){
			return em.createQuery("SELECT u FROM Machine u where id="+id, Machine.class).getSingleResult();
		}


	@Transactional
   public  List <Machine> fetchAllMachinesByMSId(int msId){
		return em.createQuery("SELECT u FROM Machine u where ms_id="+msId, Machine.class).getResultList();
	}
	
	@Transactional
	public void add(Machine u) {
		em.persist(u);
	}
	
	
	
	@Transactional(noRollbackFor = Exception.class)
	   public void repair(int mid, int newVal) throws SQLException{
	
		  dataSource.setDriver(new com.mysql.jdbc.Driver());
	        dataSource.setUrl("jdbc:mysql://localhost/machinerepair?user=root&amp;password=44morning44");
	        dataSource.setUsername("root");
	        dataSource.setPassword("44morning44");
	         
	        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	        String sqlUpdate = "UPDATE machine set times_repaired=? where id=?";
	        jdbcTemplate.update(sqlUpdate, newVal, mid);
	      
	   // return em.merge(ord);
		}
	SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
	 
	// code to set driver class name, database URL, username and password
	 
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


	
	
}
