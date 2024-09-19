package tw.rc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.rc.model.Cust;
import tw.rc.model.Orders;
import tw.rc.service.CustServiceImpl;

@RequestMapping("/cust")
@RestController
public class CustController {
	
	@Autowired
	private CustServiceImpl custServiceImpl;
	
	@PostMapping("/add")
	public Cust addCust(@RequestBody Cust cust) {
		return custServiceImpl.addCust(cust);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delCust(@PathVariable Long id) {
		custServiceImpl.delCust(id);
	}
	
	@GetMapping("/getOrders/{id}")
	public List<Orders> getOrders(@PathVariable Long id) {
		return custServiceImpl.getAllOrders(id);
	}
	
	
	
}
