package com.glomozda.machinerepair;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Locale;

import javax.swing.text.rtf.RTFEditorKit;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.glomozda.machinerepair.domain.client.Client;
import com.glomozda.machinerepair.domain.machine.Machine;
import com.glomozda.machinerepair.domain.machines_serviceable.Machines_serviceable;
import com.glomozda.machinerepair.domain.machines_serviceable.Machines_serviceableService;
import com.glomozda.machinerepair.domain.order.Order;
import com.glomozda.machinerepair.domain.repair_type.Repair_Type;
import com.glomozda.machinerepair.domain.user.User;
import com.glomozda.machinerepair.domain.userauthorization.UserAuthorization;
import com.glomozda.machinerepair.repository.client.ClientService;
import com.glomozda.machinerepair.repository.machine.MachineService;
import com.glomozda.machinerepair.repository.order.OrderService;
import com.glomozda.machinerepair.repository.repair_type.Repair_TypeService;
import com.glomozda.machinerepair.repository.user.UserService;
import com.glomozda.machinerepair.repository.userauthorization.UserAuthorizationService;

@Controller
public class AdminPageController implements MessageSourceAware {
	
	static Logger log = Logger.getLogger(AdminPageController.class.getName());

	@Autowired
	private UserService userSvc;
	
	@Autowired
	private UserAuthorizationService userAuthorizationSvc;

	@Autowired
	private MachineService machineSvc;

	@Autowired
	private Machines_serviceableService machineServiceableSvc;


	@Autowired
	private OrderService orderSvc;


	@Autowired
	private Repair_TypeService repairTypeSvc;

	
	@Autowired
	private ClientService clientSvc;	
	
	@Autowired
	private PasswordEncoder encoder;
	
	private User myUser;
	private String rtmessagename = "";
	private String rtmessageprice = "";
	private String rtmessageduration = "";
	
	private MessageSource messageSource;
	
	private String messageUserAuthorizationUserId = "";
	private Integer selectedUserAuthorizationUserId = 0;

	private String messageClientUserId = "";
	private Integer selectedClientUserId = 0;	
	 
	public void setMessageSource(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@RequestMapping(value = "/adminpage", method = RequestMethod.GET)
	public String activate(final Principal principal, final Model model) {
		model.addAttribute("rtmessagename", rtmessagename);
		model.addAttribute("rtmessageprice", rtmessageprice);
		model.addAttribute("rtmessageduration", rtmessageduration);
		model.addAttribute("omessagestart", omessagestart);
		model.addAttribute("omessagestatus", omessagestatus);
		model.addAttribute("omessagemachine", omessagemachine);
		model.addAttribute("omessageclient", omessageclient);
		model.addAttribute("omessagetype", omessagetype);
		model.addAttribute("msmessagecountry", msmessagecountry);
		model.addAttribute("msmessagename", msmessagename);
		model.addAttribute("msmessagetrademark", msmessagetrademark);
		model.addAttribute("msmessageclient", msmessageclient);
		model.addAttribute("mmessageserialnumber", mmessageserialnumber);
		model.addAttribute("mmessageyear", mmessageyear);
		model.addAttribute("mmessagems", mmessagems);
				log.info("Activating Admin Page for " + principal.getName() + "...");
		
		myUser = userSvc.getUserByLogin(principal.getName());
		if (null == myUser) {
			return "redirect:/index";
		}
		
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new User());
		}
		if (!model.containsAttribute("userAuthorization")) {
			model.addAttribute("userAuthorization", new UserAuthorization());
		}
		if (!model.containsAttribute("client")) {
			model.addAttribute("client", new Client());
		}
		if (!model.containsAttribute("machine")) {
			model.addAttribute("machine", new Machine());
		}
		if (!model.containsAttribute("machinesServiceable")) {
			model.addAttribute("machines_serviceable", new Machines_serviceable());
		}
		if (!model.containsAttribute("order")) {
			model.addAttribute("order", new Order());
		}
		if (!model.containsAttribute("repairType")) {
			model.addAttribute("repairType", new Repair_Type());
		}
		model.addAttribute("users", userSvc.getAll());
		model.addAttribute("user_authorizations", userAuthorizationSvc.getAllWithFetching());
		model.addAttribute("user_roles", userAuthorizationSvc.getAllRoles());
		model.addAttribute("clients", clientSvc.getAllWithFetching());
		
		model.addAttribute("machines", machineSvc.fetchAllMachines());
		model.addAttribute("machinesServiceables", machineServiceableSvc.fetchAllAtAllMachines_serviceable());
		model.addAttribute("orders", orderSvc.fetchAllOrders());
		model.addAttribute("repairTypes", repairTypeSvc.fetchAllRepair_Type());
		
		
		
		
		
		
		model.addAttribute("message_user_authorization_user_id", messageUserAuthorizationUserId);
		messageUserAuthorizationUserId = "";		
		model.addAttribute("selected_user_authorization_user_id", selectedUserAuthorizationUserId);
		selectedUserAuthorizationUserId = 0;
		
		model.addAttribute("message_client_user_id", messageClientUserId);
		messageClientUserId = "";		
		model.addAttribute("selected_client_user_id", selectedClientUserId);
		selectedClientUserId = 0;	
				
		return "adminpage";
	}	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(
			@ModelAttribute("user") @Valid final User user,
			final BindingResult bindingResult,			
			final RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute
			("org.springframework.validation.BindingResult.user", bindingResult);
			redirectAttributes.addFlashAttribute("user", user);
			return "redirect:/adminpage#users";
		}
		
		userSvc.add(new	User(user.getLogin(), user.getPasswordText(),
				encoder.encode(user.getPasswordText())));
		return "redirect:/adminpage#users";
	}
	
	@RequestMapping(value = "/addUserAuthorization", method = RequestMethod.POST)
	public String addUserAuthorization
		(@ModelAttribute("userAuthorization") @Valid final UserAuthorization userAuthorization,
			final BindingResult bindingResult,			
			final RedirectAttributes redirectAttributes,
			@RequestParam("userId") final Integer userId) {
		
		if (userId == 0 || bindingResult.hasErrors()) {
			if (userId == 0) {
				messageUserAuthorizationUserId = 
						messageSource.getMessage("error.adminpage.userId", null,
								Locale.getDefault());			
			}

			if (bindingResult.hasErrors()) {
				redirectAttributes.addFlashAttribute
				("org.springframework.validation.BindingResult.userAuthorization", bindingResult);
				redirectAttributes.addFlashAttribute("userAuthorization", userAuthorization);				
			}
			
			selectedUserAuthorizationUserId = userId;
			return "redirect:/adminpage#user_auths";
		}
		
		userAuthorizationSvc.add(new UserAuthorization(userAuthorization.getRole()),
				userId);
		return "redirect:/adminpage#user_auths";
	}

	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public String addClient(@ModelAttribute("client") @Valid final Client client,
			final BindingResult bindingResult,			
			final RedirectAttributes redirectAttributes,
			@RequestParam("userId") final Integer userId) {
		
		if (userId == 0 || bindingResult.hasErrors()) {
			if (userId == 0) {
				messageClientUserId = 
						messageSource.getMessage("error.adminpage.userId", null,
								Locale.getDefault());			
			}

			if (bindingResult.hasErrors()) {
				redirectAttributes.addFlashAttribute
				("org.springframework.validation.BindingResult.client", bindingResult);
				redirectAttributes.addFlashAttribute("client", client);				
			}
			
			selectedClientUserId = userId;
			return "redirect:/adminpage#clients";
		}
		
		clientSvc.add(client, userId);
		return "redirect:/adminpage#clients";
	}
	
	 
	 @RequestMapping(value = "/adminpage/submittype", method = RequestMethod.GET)
	 public String submitmachine(@RequestParam("rtname") String name, 
			 @RequestParam("rtprice") String price, 
			 @RequestParam("rtduration") String duration, Model model) throws InterruptedException
	 {
		 if (name.isEmpty()) {
			 rtmessagename = "Name can't be empty!";
				model.addAttribute("rtmessagename", rtmessagename);
				log.info("empty");
				return "redirect:/adminpage#repair_types"; 	
		 };
		 if (price.isEmpty()) {
			 rtmessageprice = "Price can't be empty!";
				model.addAttribute("rtmessageprice", rtmessageprice);
				log.info("empty");
				return "redirect:/adminpage#repair_types"; 	
		 };
		 if (duration.isEmpty()) {
			 rtmessageduration = "Duration can't be empty!";
				model.addAttribute("rtmessageduration", rtmessageduration);
				log.info("empty");
				return "redirect:/adminpage#repair_types"; 	
		 };
		 if (!duration.matches("^-?\\d+$")) {
			 rtmessageduration = "Duration must be a number!";
				model.addAttribute("rtmessageduration", rtmessageduration);
				log.info("nan");
				return "redirect:/adminpage#repair_types"; 	
		 };
		 if (!price.matches("^-?\\d+$")) {
			 rtmessageprice = "Price must be a number!";
				model.addAttribute("rtmessageprice", rtmessageprice);
				log.info("nan");
				return "redirect:/adminpage#repair_types"; 	
		 };
		// machineServiceableSvc.addMachines_serviceable(new Machines_serviceable("machine", "trademark", "country", 11));
		int priceint =Integer.parseInt(price);
		int durationint=Integer.parseInt(duration);
		 repairTypeSvc.add(new Repair_Type(name, priceint,durationint));
		log.info("rt-yes");
		rtmessageprice="";rtmessageduration="";rtmessagename="";
	return "redirect:/adminpage#repair_types";}
	
	
	 
	 
	 
	 public String omessagestart="";
	 public String omessagestatus="";
	 public String omessagemachine="";
	 public String omessageclient="";
	 public String omessagetype="";
	 
	 
	 
	 @RequestMapping(value = "/adminpage/submitorder", method = RequestMethod.GET)
	 public String submitorder( 
			 @RequestParam("ostatus") String status, 
			 @RequestParam("omachine") Integer machineid,
			 @RequestParam("oclient") Integer clientid,
			 @RequestParam("otype") Integer typeid, Model model) throws InterruptedException
	 {
		 
		 if (status.isEmpty()) {
			 omessagestatus = "Status can't be empty!";
				model.addAttribute("omessagestatus", omessagestatus);
				log.info("empty");
				return "redirect:/adminpage#orders"; 	
		 };
		
		 if (machineid<1) {
			 omessagemachine = "Select machine!";
				model.addAttribute("omessagemachine", omessagemachine);
				log.info("empty");
				return "redirect:/adminpage#orders"; 	
		 };
		

		 if (clientid<1) {
			 omessageclient = "Select client!";
				model.addAttribute("omessageclient", omessageclient);
				log.info("empty");
				return "redirect:/adminpage#orders"; 	
		 };


		 if (typeid<1) {
			 omessagetype = "Select type!";
				model.addAttribute("omessagetype", omessagetype);
				log.info("empty");
				return "redirect:/adminpage#orders"; 	
		 };
		 // machineServiceableSvc.addMachines_serviceable(new Machines_serviceable("machine", "trademark", "country", 11));
		// repairTypeSvc.add(new Repair_Type(name, priceint,durationint));
		 orderSvc.add(new Order(Date.valueOf(LocalDate.now()),status, clientid, typeid, machineid));
		log.info("o-yes");
		  omessagestart="";
		  omessagestatus="";
		  omessagemachine="";
		  omessageclient="";
		  omessagetype="";
		//	rtmessageprice="";rtmessageduration="";rtmessagename="";
	return "redirect:/adminpage#orders";}
	
	 
	 
	 
	 
	 public String msmessagecountry="";
	 public String msmessagename="";
	 public String msmessagetrademark="";
	 public String msmessageclient="";
		 
	 @RequestMapping(value = "/adminpage/submitms", method = RequestMethod.GET)
	 public String submitms( 
			 @RequestParam("mscountry") String country, 
			 @RequestParam("msname") String name,
			 @RequestParam("mstrademark") String trademark,
			 @RequestParam("msclient") Integer clientid, Model model) throws InterruptedException
	 {
		 
		 if (country.isEmpty()) {
			 msmessagecountry = "Country can't be empty!";
				model.addAttribute("msmessagecountry", msmessagecountry);
				log.info("empty");
				return "redirect:/adminpage#machinesServiceables"; 	
		 };
		
		 if (name.isEmpty()) {
			 msmessagename = "Name can't be empty!";
				model.addAttribute("msmessagename", msmessagename);
				log.info("empty");
				return "redirect:/adminpage#machinesServiceables"; 	
		 };
		
		 if (trademark.isEmpty()) {
			 msmessagetrademark = "Trademark can't be empty!";
				model.addAttribute("msmessagetrademark", msmessagetrademark);
				log.info("empty");
				return "redirect:/adminpage#machinesServiceables"; 	
		 };
		 

		 if (clientid<1) {
			 msmessageclient = "Select client!";
				model.addAttribute("msmessageclient", msmessageclient);
				log.info("empty");
				return "redirect:/adminpage#machinesServiceables"; 	
		 };

		 // machineServiceableSvc.addMachines_serviceable(new Machines_serviceable("machine", "trademark", "country", 11));
		// repairTypeSvc.add(new Repair_Type(name, priceint,durationint));
		machineServiceableSvc.addMachines_serviceable(new Machines_serviceable(name, trademark, country, clientid));
		 log.info("ms-yes");
		  omessagestart="";
		  omessagestatus="";
		  omessagemachine="";
		  omessageclient="";
		  omessagetype="";
		//	rtmessageprice="";rtmessageduration="";rtmessagename="";
	return "redirect:/adminpage#machinesServiceables";}	 
	 

	 public String mmessageserialnumber="";
	 public String mmessageyear="";
	 public String mmessagems="";
	 
	 @RequestMapping(value = "/adminpage/submitm", method = RequestMethod.GET)
	 public String submitm( 
			 @RequestParam("mserial_number") String serial_number, 
			 @RequestParam("myear") String year,
			 @RequestParam("mms") Integer ms_id, Model model) throws InterruptedException
	 {
		 
		 if (serial_number.isEmpty()) {
			 mmessageserialnumber = "Serial Number can't be empty!";
				model.addAttribute("mmessageserialnumber", mmessageserialnumber);
				log.info("empty");
				return "redirect:/adminpage#machines"; 	
		 };
		
		 if (year.isEmpty()) {
			 mmessageyear = "Year can't be empty!";
				model.addAttribute("mmessageyear", mmessageyear);
				log.info("empty");
				return "redirect:/adminpage#machines"; 	
		 };
		
		 if (!year.matches("^-?\\d+$")) {
			 mmessageyear = "Year must be a number!";
				model.addAttribute("mmessageyear", mmessageyear);
				log.info("empty");
				return "redirect:/adminpage#machines"; 	
		 };
		
		 
		 if (ms_id<1) {
			 mmessagems = "Select machine serviceable!";
				model.addAttribute("mmessagems", mmessagems);
				log.info("empty");
				return "redirect:/adminpage#machines"; 	
		 };

		 // machineServiceableSvc.addMachines_serviceable(new Machines_serviceable("machine", "trademark", "country", 11));
		// repairTypeSvc.add(new Repair_Type(name, priceint,durationint));
		machineSvc.add(new Machine(ms_id, serial_number, Integer.parseInt(year)));
		 //machineServiceableSvc.addMachines_serviceable(new Machines_serviceable(name, trademark, country, clientid));
		 log.info("ms-yes");
		  omessagestart="";
		  omessagestatus="";
		  omessagemachine="";
		  omessageclient="";
		  omessagetype="";
		//	rtmessageprice="";rtmessageduration="";rtmessagename="";
	return "redirect:/adminpage#machinesServiceables";}	 
	 

	 
	 
	 
}