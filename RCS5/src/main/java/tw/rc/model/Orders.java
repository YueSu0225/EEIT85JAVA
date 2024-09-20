package tw.rc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "oders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "date")
	private String date;
	

	
	@ManyToOne
	@JoinColumn(name = "cust_id", nullable = false)
	@JsonBackReference
	private Cust cust;

	public Long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public Cust getCust() {
		return cust;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setCust(Cust cust) {
		this.cust = cust;
	}
	
	
}
