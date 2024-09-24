package tw.rc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.rc.model.Employees;
import tw.rc.service.EmployeesService;

@RequestMapping("/employees")
@RestController
public class EmployeesController {
	
	@Autowired
	private EmployeesService employeesService;
	
	@GetMapping("/get/{employeeId}")
	public Employees getById(@PathVariable Long employeeId) {
		return employeesService.getById(employeeId);
	}
}
