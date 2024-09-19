package tw.rc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.rc.model.Cust;
import tw.rc.model.Orders;
import tw.rc.repository.CustRepository;
import tw.rc.repository.OrdersRepository;

@Service
public class OrdersServiceImpl {

	@Autowired
	private OrdersRepository orderRepository;
	
	@Autowired
	private CustRepository custRespository;
	
	public Orders addOrder(Orders orders, Long userId) {
		Cust cust = custRespository.findById(userId).orElse(null);
		if (cust != null) {
			orders.setCust(cust);
			return orderRepository.save(orders);
		}else {
			return null;
		}
		
	}
	
	public void delOrder(Long id) {
		orderRepository.deleteById(id);
	}
	

}
