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
@Table(name = "cust")
public class Cust {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "cust", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Orders> orders;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
	
}
