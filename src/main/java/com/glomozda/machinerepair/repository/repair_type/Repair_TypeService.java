package com.glomozda.machinerepair.repository.repair_type;

import java.util.List;

import com.glomozda.machinerepair.domain.order.Order;
import com.glomozda.machinerepair.domain.repair_type.Repair_Type;
import com.glomozda.machinerepair.domain.user.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Repair_TypeService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PasswordEncoder encoder;

	
	@Transactional
   public  List <Repair_Type> fetchAllRepair_Type(){
		return em.createQuery("SELECT u FROM Repair_Type u", Repair_Type.class).getResultList();
	}
	
   @Transactional
   public Repair_Type fetchAllRepair_TypeById(int type){
		return em.createQuery("SELECT u FROM Repair_Type u where type_id="+type, Repair_Type.class).getSingleResult();
	}
   

	@Transactional
	public void add(Repair_Type u) {
		em.persist(u);
	}	

}
