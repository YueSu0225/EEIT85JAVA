package tw.rc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.rc.model.Cust;
import tw.rc.model.Customers;
import tw.rc.model.QueryDate;
import tw.rc.repository.CustRepo;

@Service
public class CustServiceImpl implements CustomersService{

	@Autowired
	private CustRepo custRepo;
	@Override
	public List<Customers> getAll() {
		return custRepo.findAll();
	}
	
	@Override
	public String getCompanyById(String customerId) {
		return custRepo.findCustomerID(customerId);
	}

	@Override
	public Cust getCompanyByIdV2(String customerId) {
		return custRepo.findCustomerIDV2(customerId);
	}
	@Override
	public List<Customers> getByCompanyName(String companyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customers> getByCompanyNameKey(String companyNameKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customers> getByCompanyNameLike(String companyNameKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customers> getByCompanyNameLikeSQL(String companyNameKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customers> getByCityOrCountry(String city, String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customers getByDate(String customerId, QueryDate queryDate) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Customers getById(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}



}
