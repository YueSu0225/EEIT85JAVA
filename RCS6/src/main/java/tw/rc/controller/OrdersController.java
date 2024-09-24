package tw.rc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.rc.model.Orders;
import tw.rc.service.OrdersService;

@RequestMapping("/orders")
@RestController
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
	@GetMapping("/get/{orderId}")
	public Orders geyById(@PathVariable Long orderId) {
		return ordersService.getOrderById(orderId);
	}
}
