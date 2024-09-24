package tw.rc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customers")
public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CustomerID")
	private String customerId;
	
	@Column(name = "CompanyName", nullable = false)
	private String companyName;
	
	@Column(name = "City", nullable = true)
	private String city;
	
	@Column(name = "Country", nullable = true)
	private String country;
	

	public String getCustomerId() {
		return customerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
//--------------------------------------------------------------	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true) 
	@JsonManagedReference
	private List<Orders> orders;


	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	

	
	
	
}
