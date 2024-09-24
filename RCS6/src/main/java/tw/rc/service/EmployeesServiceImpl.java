package tw.rc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.rc.model.Customers;
import tw.rc.model.Employees;
import tw.rc.model.Orders;
import tw.rc.repository.EmployeesRepository;

@Service
public class EmployeesServiceImpl implements EmployeesService{

	@Autowired
	private EmployeesRepository employeesRepository;
	
	@Autowired
	private OrdersService ordersService;
	@Override
	public Employees getById(Long employeeId) {
		Employees employee = employeesRepository.findById(employeeId).orElse(null);
		
		List<Orders> orders = employee.getOrders();		
		for (Orders order : orders) {
			order = ordersService.getOrderById(order.getOrderId());
			
		}		
		return employee;
	}
		
		
		
	

}
