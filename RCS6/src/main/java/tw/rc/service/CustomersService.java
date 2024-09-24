package tw.rc.service;

import java.util.List;

import tw.rc.model.Cust;
import tw.rc.model.Customers;
import tw.rc.model.QueryDate;

public interface CustomersService {
	public String getCompanyById(String customerId);
	public List<Customers> getAll();
	public Cust getCompanyByIdV2(String customerId);
	
	public Customers getById(String customerId);
	public List<Customers> getByCompanyName(String companyName);
	public List<Customers> getByCompanyNameKey(String companyNameKey);
	public List<Customers> getByCompanyNameLike(String companyNameKey);
	public List<Customers> getByCompanyNameLikeSQL(String companyNameKey);

	
	public List<Customers> getByCityOrCountry(String city, String country);
	
	public Customers getByDate(String customerId, QueryDate queryDate);
	
	
	
	
}
