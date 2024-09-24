package tw.rc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.rc.model.Cust;
import tw.rc.model.Customers;
import tw.rc.model.QueryDate;
import tw.rc.service.CustServiceImpl;
import tw.rc.service.CustomersService;
import tw.rc.service.CustomersServiceImpl;

@RequestMapping("/customers")
@RestController
public class CustomersController {

	@Autowired
	private CustomersServiceImpl customersService;
	
	@Autowired
	private CustServiceImpl custService;

	@GetMapping("/get")
	public List<Customers> getByAll() {
		return custService.getAll();
	}
	
	@GetMapping("/getv2/{customerId}")
	public String getByIdV2(@PathVariable String customerId) {
		return custService.getCompanyById(customerId);
	}
	
	@GetMapping("/getv3/{customerId}")
	public Cust getByIdV3(@PathVariable String customerId) {
		return custService.getCompanyByIdV2(customerId);
	}
	
	@GetMapping("/get/{customerId}")
	public Customers getById(@PathVariable String customerId) {
		return customersService.getById(customerId);
	}
	
	@PostMapping("/getByDate/{customerId}")
	public Customers getByDate(@PathVariable String customerId, @RequestBody QueryDate queryDate) {
		return null;
	}
	
	
	@GetMapping("/getByCompanyName/{companyName}")
	public List<Customers> getByCompanyName(@PathVariable String companyName) {
		return customersService.getByCompanyName(companyName);
	}
	
	@GetMapping("/getByCompanyNameKey/{key}")
	public List<Customers> getByCompanyNameKey(@PathVariable String key) {
		return customersService.getByCompanyNameKey(key);
	}
	
	@GetMapping("/getByCompanyNameLike/{key}")
	public List<Customers> getByCompanyNameLike(@PathVariable String key) {
		return customersService.getByCompanyNameLike(key);
	}
	
	@GetMapping("/getByCompanyNameLikeSQL/{key}")
	public List<Customers> getByCompanyNameLikeSQL(@PathVariable String key) {
		return customersService.getByCompanyNameLikeSQL(key);
	}
	
	@GetMapping("/getByCityOrCountry/{city}/{country}")
	public List<Customers> getByCityOrCountry(@PathVariable String city, String country) {
		return customersService.getByCityOrCountry(city, country);
	}
}
