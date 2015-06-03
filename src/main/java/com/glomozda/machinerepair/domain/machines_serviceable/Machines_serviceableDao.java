package com.glomozda.machinerepair.domain.machines_serviceable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.glomozda.machinerepair.domain.user.User;
 
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class Machines_serviceableDao  {

private static final String SELECT_QUERY = "select p from machines_serviceable p";
 
@PersistenceContext
private EntityManager entityManager;
 
public EntityManager getEntityManager() {
	return entityManager;
}
 
public void setEntityManager(EntityManager entityManager) {
	this.entityManager = entityManager;
}
 
public void insert(Machines_serviceable ms) {
	entityManager.persist(ms);
}
 
public List<Machines_serviceable> selectAll(int clientId) {
	List<Machines_serviceable> result = entityManager.createQuery("SELECT u FROM Machines_serviceable u where client_id="+clientId, Machines_serviceable.class).getResultList();
	return result;
	//return 0;//new Machines_serviceable();
}

public List<Machines_serviceable> selectAllAtAll() {
	List<Machines_serviceable> result = entityManager.createQuery("SELECT u FROM Machines_serviceable u", Machines_serviceable.class).getResultList();
	return result;
	//return 0;//new Machines_serviceable();
}

	

	

}
