package tw.rc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.rc.model.Cust;
import tw.rc.model.Orders;
import tw.rc.repository.CustRepository;

@Service
public class CustServiceImpl {

	@Autowired
	private CustRepository custRepository;
	
	public Cust addCust(Cust cust) {
		return custRepository.save(cust);
	}
	
	public void delCust(Long id) {
		custRepository.deleteById(id);
	}
	
	public List<Orders> getAllOrders(Long id){
		Cust cust = custRepository.findById(id).orElse(null);
		return cust.getOrders();
	}
}
