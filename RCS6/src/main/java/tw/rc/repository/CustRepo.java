package tw.rc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.rc.model.Cust;
import tw.rc.model.Customers;
/*
 * JPQL => 是在使用java物件
 */

public interface CustRepo extends JpaRepository<Customers, String> {
	@Query("SELECT c FROM Customers c")
	List<Customers> findAllCustomers();
	
	@Query("SELECT c.companyName FROM Customers c WHERE c.customerId = :id")
	String findCustomerID(@Param("id")String customerID);

	@Query("SELECT new tw.rc.model.Cust(c.companyName, c.city, c.country) FROM Customers c WHERE c.customerId = :id")
	Cust findCustomerIDV2(@Param("id")String customerID);


}
