package com.glomozda.machinerepair;
import java.awt.List;
import java.security.Principal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
 
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glomozda.machinerepair.domain.client.Client;
import com.glomozda.machinerepair.domain.machine.Machine;
import com.glomozda.machinerepair.domain.machines_serviceable.Machines_serviceable;
import com.glomozda.machinerepair.repository.client.ClientService;
import com.glomozda.machinerepair.repository.machine.MachineService;
import com.glomozda.machinerepair.repository.repair_type.Repair_TypeService;
import com.glomozda.machinerepair.repository.user.UserService;
import com.glomozda.machinerepair.repository.userauthorization.UserAuthorizationService;
import com.glomozda.machinerepair.domain.machines_serviceable.Machines_serviceableService;
import com.glomozda.machinerepair.domain.repair_type.Repair_Type;
import com.glomozda.machinerepair.domain.user.User;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
@Controller
public class ViewMyMachinesController implements MessageSourceAware  {
	@Autowired
	private Machines_serviceableService mss;


	@Autowired
	private Repair_TypeService rt;

	
	@Autowired
	private UserService userSvc;

	@Autowired
	private UserAuthorizationService userAuthorizationSvc;

	@Autowired
	private ClientService clientSvc;	
	
	@Autowired
	private MachineService machineSvc;	
	
	
	@Autowired
	private PasswordEncoder encoder;

	static Logger log = Logger.getLogger(AddMachineController.class.getName());
	private MessageSource messageSource;	
	private Map tramps;
	private User myUser;
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	 public ViewMyMachinesController() {
		// tramps = new HashMap();
		//     populate(tramps);
		   }
	
	 @RequestMapping(value = "/viewmymachines")
	    public String viewmymachines(Locale locale, Model model,final Principal principal) {
		
	        log.info("Here you can view your machines: "+ locale.toString());
	     //   log.info(rt.fetchAllRepair_TypeById(1));
	      //  log.info(mss.fetchAllMachines_serviceable(userSvc.getUserByLogin(principal.getName()).getUserId()));
	//.info(list);
	    //    for ()
	          //  model.addAttribute("machines_serviceable",
	        ArrayList <MachineMachineServiceable> mmslist=new ArrayList<MachineMachineServiceable>();
	        for (Machines_serviceable machs: mss.fetchAllMachines_serviceable(userSvc.getUserByLogin(principal.getName()).getClient().getClientId())){
	        	for (Machine m: machineSvc.fetchAllMachinesByMSId(machs.getMs_id()))
	        	{
	        		//log.info(m);
	        		//log.info(machs);
	        		mmslist.add(new MachineMachineServiceable(m, machs));
	        	}
	        }
	        
	        model.addAttribute("machinetypes", mss.fetchAllMachines_serviceable(userSvc.getUserByLogin(principal.getName()).getClient().getClientId()));
	        
	        
	        
	        model.addAttribute("machines_serviceable", mmslist);
	        	return "viewmymachines";
	    }
//	 private void populate(Map tramps) {
//	    tramps.put(1L, "Lake Waikaremoana Great Walk");
//		    tramps.put(2L, "Tongariro Northern Circuit");
//		    tramps.put(3L, "Whanganui Journey");
//		    tramps.put(4L, "Abel Tasman Coast Track");
//		    tramps.put(5L, "Heaphy Track");
//		    tramps.put(6L, "Kepler Track");
//		    tramps.put(7L, "Milford Track");
//		    tramps.put(8L, "Routeburn Track");
//		    tramps.put(9L, "Rakiura Track");
//		  }
}
