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
import com.glomozda.machinerepair.domain.machines_serviceable.Machines_serviceable;
import com.glomozda.machinerepair.domain.machines_serviceable.Machines_serviceableService;
import com.glomozda.machinerepair.domain.user.User;
import com.glomozda.machinerepair.repository.client.ClientService;
import com.glomozda.machinerepair.repository.user.UserService;
import com.glomozda.machinerepair.repository.userauthorization.UserAuthorizationService;

import org.apache.log4j.Logger;

@Controller
public class AddMachineController implements MessageSourceAware {
	@Autowired
	private Machines_serviceableService mss;

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
	
public AddMachineController() {
	// TODO Auto-generated constructor stub
}

	 @RequestMapping(value = "/addmachine")
	    public String addmachine(Locale locale, Model model,final Principal principal) {

			model.addAttribute("message", message);
			// = "";
			
			
	        log.info("Here you can add new machine: "+ locale.toString());
	         
//	        model.addAttribute("newMachine", new Machines_serviceable("ASHA", "Nokia", "Finland",userSvc.getUserByLogin(principal.getName()).getClient().getClientId()));
//	       mss.addMachines_serviceable(new Machines_serviceable("ASHA", "Nokia", "Finland",userSvc.getUserByLogin(principal.getName()).getClient().getClientId()));
//	        log.info("New List: "+mss.fetchAllMachines_serviceable(userSvc.getUserByLogin(principal.getName()).getClient().getClientId()));	         
	        return "addmachine";
	    }
	 	
	 @RequestMapping(value = "/addmachine/submitmachine", method = RequestMethod.GET)
	 public String submitmachine(final Principal principal, @RequestParam("name") String name, @RequestParam("trademark") String trademark,
			 @RequestParam("country") String country, Model model) throws InterruptedException
	 {
		 if (name.isEmpty()) {
				message = "Name can't be empty!";
				model.addAttribute("message", message);
				
				log.info("empty");
				return "redirect:/addmachine"; 	
		 }
		 if (trademark.isEmpty()) {
				message = "Trademark can't be empty!";
				model.addAttribute("message", message);
				
				log.info("empty");
				return "redirect:/addmachine"; 
			}
		 if (country.isEmpty()) {
				message = "Country can't be empty!";			
				model.addAttribute("message", message);
				log.info("empty");
				return "redirect:/addmachine"; 
			}
		
		 for (Machines_serviceable ms:mss.fetchAllMachines_serviceable(userSvc.getUserByLogin(principal.getName()).getClient().getClientId())) 
		 { 
			 log.info(ms);
			 if ((ms.getTrademark().equals(trademark))&&(ms.getCountry().equals(country))&&(ms.getName().equals(name)))
				 { log.info("true");
					
				 message = "Machine is in use already!";
			// model.addAttribute("message", message);
			 return "redirect:/addmachine";}
		//	 log.info("if");
			
		//		return "addmachine";
		 }
		 
		 mss.addMachines_serviceable(new Machines_serviceable(name, trademark, country, userSvc.getUserByLogin(principal.getName()).getClient().getClientId()));
		 log.info("added?");
		 return "redirect:/clientpage";
	 }
		 
	 

}
