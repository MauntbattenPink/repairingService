package com.glomozda.machinerepair.domain.machines_serviceable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
@Component
public class Machines_serviceableService {
	@Autowired
	private Machines_serviceableDao msDao;
	 
	public Machines_serviceableDao getMachines_serviceableDao() {
	return msDao;
	}
	
	//@Autowired
	//public void setMachines_serviceableDao(Machines_serviceableDao msDao) {
	//.msDao = msDao;
	//}
	@Transactional
	public void addMachines_serviceable(Machines_serviceable ms) {
	getMachines_serviceableDao().insert(ms);
	}
	 
	@Transactional
	public List<Machines_serviceable> fetchAllMachines_serviceable(int clientId) {
	return getMachines_serviceableDao().selectAll(clientId);
	}
	

	@Transactional
	public List<Machines_serviceable> fetchAllAtAllMachines_serviceable() {
	return getMachines_serviceableDao().selectAllAtAll();
	}
}
