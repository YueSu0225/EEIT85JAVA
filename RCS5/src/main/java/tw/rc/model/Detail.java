package tw.rc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detail")
public class Detail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nickname;
	
	@OneToOne(mappedBy = "detail")
	@JsonBackReference
	private User user;

	public Long getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public User getUser() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
