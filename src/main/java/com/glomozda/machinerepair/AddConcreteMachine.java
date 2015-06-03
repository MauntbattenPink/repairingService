package com.glomozda.machinerepair;
import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.glomozda.machinerepair.domain.client.Client;
import com.glomozda.machinerepair.domain.machine.Machine;
import com.glomozda.machinerepair.domain.machines_serviceable.Machines_serviceable;
import com.glomozda.machinerepair.domain.machines_serviceable.Machines_serviceableService;
import com.glomozda.machinerepair.domain.user.User;
import com.glomozda.machinerepair.repository.client.ClientService;
import com.glomozda.machinerepair.repository.machine.MachineService;
import com.glomozda.machinerepair.repository.user.UserService;
import com.glomozda.machinerepair.repository.userauthorization.UserAuthorizationService;

import org.apache.log4j.Logger;

@Controller
public class AddConcreteMachine implements MessageSourceAware {
	@Autowired
	private Machines_serviceableService mss;

	@Autowired
	private MachineService machs;

	@Autowired
	private UserService userSvc;

	@Autowired
	private ClientService clientSvc;	
	
	@Autowired
	private PasswordEncoder encoder;

	private String message = "";
	
	static Logger log = Logger.getLogger(AddMachineController.class.getName());
	private MessageSource messageSource;	
	private Map tramps;
	private User myUser;
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
public AddConcreteMachine() {
	// TODO Auto-generated constructor stub
}

	 @RequestMapping(value = "/addconcretemachine")
	    public String addmachine(Locale locale, Model model,final Principal principal) {

			model.addAttribute("message", message);
			// = "";
		      model.addAttribute("machines_serviceable",mss.fetchAllMachines_serviceable(userSvc.getUserByLogin(principal.getName()).getClient().getClientId()));
			
	        log.info("Here you can add new concrete machine: "+ locale.toString());
	         
//	        model.addAttribute("newMachine", new Machines_serviceable("ASHA", "Nokia", "Finland",userSvc.getUserByLogin(principal.getName()).getClient().getClientId()));
//	       mss.addMachines_serviceable(new Machines_serviceable("ASHA", "Nokia", "Finland",userSvc.getUserByLogin(principal.getName()).getClient().getClientId()));
//	        log.info("New List: "+mss.fetchAllMachines_serviceable(userSvc.getUserByLogin(principal.getName()).getClient().getClientId()));	         
	        return "addconcretemachine";
	    }
	 	
	 @RequestMapping(value = "/addconcretemachine/submitmachine", method = RequestMethod.GET)
	 public String submitmachine(final Principal principal, @RequestParam("serial_number") String serialNumber, @RequestParam("year") String year,
			 @RequestParam("selectmachine") String machine, Model model) throws InterruptedException
	 {
		 if (serialNumber.isEmpty()) {
				message = "Serial Number can't be empty!";
				model.addAttribute("message", message);
				
				log.info("empty");
				return "redirect:/addconcretemachine"; 	
		 }
		 if (year.isEmpty()) {
				message = "Year can't be empty!";
				model.addAttribute("message", message);
				
				log.info("empty");
				return "redirect:/addconcretemachine"; 
			}
		 if (!year.matches("^-?\\d+$")) {
				message = "Year must be number!";
				model.addAttribute("message", message);
				log.info("empty");
				return "redirect:/addconcretemachine"; 
			}
		 //||(!year.matches("^-?\\d+$"))
		 if (machine.isEmpty()) {
				message = "Select machine!";			
				model.addAttribute("message", message);
				log.info("empty");
				return "redirect:/addconcretemachine"; 
			}
		
		 for (Machine ms:machs.fetchAllMachinesByMSId(Integer.parseInt(machine)))//(userSvc.getUserByLogin(principal.getName()).getClient().getClientId())) 
		 { 
			 log.info(ms);
			 if ((ms.getSerial_number().equals(serialNumber))&&(ms.getMs_id()==Integer.parseInt(machine)))
				 { log.info("true");
					
				 message = "Machine already already exists!";
			// model.addAttribute("message", message);
			 return "redirect:/addconcretemachine";}
		//	 log.info("if");
			
		//		return "addmachine";
		 }
		 
		 machs.add(new Machine(Integer.parseInt(machine),serialNumber,Integer.parseInt(year)));
		 log.info("added?");
		 return "redirect:/viewmymachines";
	 }
		 
	 

}
