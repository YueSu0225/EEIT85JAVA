package tw.rc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tw.rc.model.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>{
	public List<User> findByAccount(String account);
	
	public List<User> findByAccountContaining(String account);
	//SQL 針對model裡面的 類別名稱
	@Query("SELECT u FROM User u WHERE u.account LIKE %:account% OR u.name LIKE %:name%")
	public List<User> findByAccountOrNameLike(@Param("account")String account,
											@Param("name")String name);
}
