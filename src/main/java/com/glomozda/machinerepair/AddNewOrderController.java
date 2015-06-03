package com.glomozda.machinerepair;
import java.security.Principal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.sql.Date;
import java.util.ArrayList;
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
import com.glomozda.machinerepair.domain.order.Order;
import com.glomozda.machinerepair.domain.user.User;
import com.glomozda.machinerepair.repository.client.ClientService;
import com.glomozda.machinerepair.repository.machine.MachineService;
import com.glomozda.machinerepair.repository.order.OrderService;
import com.glomozda.machinerepair.repository.repair_type.Repair_TypeService;
import com.glomozda.machinerepair.repository.user.UserService;

import org.apache.log4j.Logger;

@Controller
public class AddNewOrderController implements MessageSourceAware {
	@Autowired
	private Machines_serviceableService mss;

	@Autowired
	private OrderService os;

	
	@Autowired
	private MachineService machs;

	@Autowired
	private UserService userSvc;

	@Autowired
	private Repair_TypeService rtSvc;

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
	
public AddNewOrderController() {
	// TODO Auto-generated constructor stub
}

	 @RequestMapping(value = "/addneworder")
	    public String addmachine(Locale locale, Model model,final Principal principal) {
		 	ArrayList <MachineMachineServiceable> ord=new ArrayList<MachineMachineServiceable>();
			model.addAttribute("message", message);
			// = "";
			
			for (Machines_serviceable machser:mss.fetchAllMachines_serviceable(userSvc.getUserByLogin(principal.getName()).getClient().getClientId())){
			for (Machine m: machs.fetchAllMachinesByMSId(machser.getMs_id()))
				ord.add(new MachineMachineServiceable(m, machser));	
			}
		      model.addAttribute("machines_serviceable", ord);
			model.addAttribute("repair_type",rtSvc.fetchAllRepair_Type());
	        log.info("Here you can add new order: "+ locale.toString());
	      //  os.add(new Order(11, "undone", Date.valueOf(LocalDate.now()), 2, 1)); 
	      //  Order ord=new Order(Date.valueOf(LocalDate.now()), "undone", 11, 1, 1);
	   //     os.add(ord);
//	        model.addAttribute("newMachine", new Machines_serviceable("ASHA", "Nokia", "Finland",userSvc.getUserByLogin(principal.getName()).getClient().getClientId()));
//	       mss.addMachines_serviceable(new Machines_serviceable("ASHA", "Nokia", "Finland",userSvc.getUserByLogin(principal.getName()).getClient().getClientId()));
//	        log.info("New List: "+mss.fetchAllMachines_serviceable(userSvc.getUserByLogin(principal.getName()).getClient().getClientId()));	         
	        return "addneworder";
	    }
	 	
	 @RequestMapping(value = "/addneworder/submitorder", method = RequestMethod.GET)
	 public String submitmachine(final Principal principal, @RequestParam("selectmachine") Integer machine, 
			 @RequestParam("selectrepairtype") Integer type, Model model) throws InterruptedException
	 {
		// LocalDate dateToConvert = LocalDate.now();
		// Date convertToDate =Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

		os.add(
			//	log.info(type+" "+machine
	new Order(Date.valueOf(LocalDate.now()), "undone", 
			userSvc.getUserByLogin(principal.getName()).getClient().getClientId(),
				type, machine)
				); 
		 log.info("added?");
		 return "redirect:/viewdoneorders";
	 }
		 
	 

}
