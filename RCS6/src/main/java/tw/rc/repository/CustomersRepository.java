package tw.rc.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.rc.model.Customers;

public interface CustomersRepository extends JpaRepository<Customers, String>{
	
	//原生SQL語法 => SELECT * FROM Customers WHERE CompanyName = ?
	public List<Customers> findByCompanyName(String companyName);
	
	public List<Customers> findAllByCompanyName(String companyName);

	public List<Customers> findAllByCompanyNameContaining(String companyNameKey);
	
	//原生SQL語法 => SELECT * FROM Customers WHERE City = ? OR Country = ? 
	public List<Customers> findByCityOrCountry(String city, String country, Sort sort);//Sort是排序
	
	//SQL  => SELECT * FROM Customers WHERE CompanyName LIKE %key%
	//下面Query => 寫的是model裡面的屬性名稱  不是資料庫的欄位
	@Query("SELECT c FROM Customers c WHERE c.companyName LIKE %:key%")
	public List<Customers> findByCompanyNameLike(@Param("key") String key);
	
	//加value是寫原生SQL
	@Query(value="SELECT CustomerID,CompanyName,City,Country FROM customers WHERE CompanyName LIKE ?1", nativeQuery = true)
	public List<Customers> findAllBySQL(String key);
}
