package tw.rc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.rc.model.OrderDetails;
import tw.rc.model.Orders;
import tw.rc.repository.OrdersRepository;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Override
	public Orders getOrderById(Long id) {
		Orders orders = ordersRepository.findById(id).orElse(null);
		List<OrderDetails> details = orders.getOrderDetails();
		for (OrderDetails detail : details) {
			detail.setProductName(detail.getProduct().getProductName());
		}
		
		return orders;
	}

}
