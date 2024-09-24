package tw.rc.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.rc.model.Cust;
import tw.rc.model.Customers;
import tw.rc.service.CustomersServiceImpl;

@Controller
public class ViewController {
	ModelAndView modelRc01 = new ModelAndView("rc01");
	ModelAndView modelRc02 = new ModelAndView("rc02");
	
	@Autowired
	private CustomersServiceImpl customersServiceImpl;
	
	public ViewController() {
		modelRc01.addObject("mesg", "Hello, RC1");
		modelRc02.addObject("mesg", "Hello, RC2");
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("companyName","RC Bog Company");
		return "index";
	}
	
	@RequestMapping("/rc01")
	public ModelAndView rc01() {
		return modelRc01;
	}
	
	@RequestMapping("/rc02")
	public ModelAndView rc02() {
		return modelRc02;
	}
	
	@RequestMapping("/rc03/{id}")
	public String rc03(Model model, @PathVariable String id) {
		 Customers customer = customersServiceImpl.getById(id);
		Cust cust = new Cust(customer.getCompanyName(),customer.getCity(),customer.getCountry());
		model.addAttribute("companyName","RC Bog Company");	
		model.addAttribute("cust",cust);
		
		return "rc03";
	}
	
	@RequestMapping("/rc003")
	public String rc003(Model model) {
		model.addAttribute("companyName","RC Bog Company");	
		//List<Customers> customers = customersService.getAll();
		List<String> names = new LinkedList<String>();
		names.add("Test1");
		names.add("Test2");
		names.add("Test3");
		
		model.addAttribute("names", names);		
		return "rc003";
	}
	
}
