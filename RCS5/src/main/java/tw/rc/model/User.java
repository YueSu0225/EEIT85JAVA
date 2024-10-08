package tw.rc.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Long id;
	
	@Column(name ="account")
	private String account;
	
	@Column(name ="passwd")
	private String passwd;
	
	@Column(name ="name")
	private String name;
	
	@Column(name ="icon")
	private byte[] icon;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "detail_id", referencedColumnName = "id")
	@JsonManagedReference
	private Detail detail;
	
	

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	public Long getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getName() {
		return name;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
	
	
	
}
