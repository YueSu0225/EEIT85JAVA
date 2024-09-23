package tw.rc.service;

import java.util.List;

import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tw.rc.model.Customers;
import tw.rc.repository.CustomersRepository;

@Service
public class CustomersServiceImpl implements CustomersService{
	@Autowired
	private CustomersRepository customersRepository;
	@Override
	public Customers getById(String customerId) {
		return customersRepository.findById(customerId).orElse(null);
	}

	@Override
	public List<Customers> getByCompanyName(String companyName) {
		return customersRepository.findByCompanyName(companyName);
	}

	@Override
	public List<Customers> getByCompanyNameKey(String companyNameKey) {
		return customersRepository.findAllByCompanyNameContaining(companyNameKey);
	}

	@Override
	public List<Customers> getByCompanyNameLike(String companyNameKey) {
		return customersRepository.findByCompanyNameLike(companyNameKey);
	}
	
	@Override
	public List<Customers> getByCompanyNameLikeSQL(String Key) {
		String like = "%" + Key + "%";
		return customersRepository.findAllBySQL(like);
	}

	@Override
	public List<Customers> getByCityOrCountry(String city, String country) { //Sort.Direction.DESC = 排序反向, Sort.Direction.DESC = 排序正向
		return customersRepository.findByCityOrCountry(city, country, Sort.by(Sort.Direction.DESC, "customerId"));
	}

}
