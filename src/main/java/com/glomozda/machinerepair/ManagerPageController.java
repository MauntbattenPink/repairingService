package com.glomozda.machinerepair;

import java.security.Principal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glomozda.machinerepair.domain.machine.Machine;
import com.glomozda.machinerepair.domain.user.User;
import com.glomozda.machinerepair.repository.client.ClientService;
import com.glomozda.machinerepair.repository.machine.MachineService;
import com.glomozda.machinerepair.repository.user.UserService;

import org.apache.log4j.Logger;

@Controller
public class ManagerPageController {
	
	static Logger log = Logger.getLogger(ManagerPageController.class.getName());
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ClientService clientSvc;
	
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private MachineService ms;
	
	private User myUser;		
	
	@RequestMapping(value = "/managerpage", method = RequestMethod.GET)
	public String activate(final Principal principal, final Model model) {
		
		log.info("Activating Manager Page for " + principal.getName() + "...");
		
		myUser = userSvc.getUserByLogin(principal.getName());
		if (null == myUser) {
			return "redirect:/index";
		}
		
		UsernamePasswordAuthenticationToken userToken =
				(UsernamePasswordAuthenticationToken)principal;
		
		model.addAttribute("user_token_authorities",
				userToken.getAuthorities().toString());
		log.info(ms.fetchAllMachines());
		for (Machine m:ms.fetchAllMachines())log.info(m.getMs_id());
		model.addAttribute("machines", ms.fetchAllMachines());
		return "managerpage";
	}	
}
