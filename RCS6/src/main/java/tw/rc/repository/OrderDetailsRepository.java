package tw.rc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.rc.model.OrderDetails;
import tw.rc.model.OrderDetailsKey;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsKey>{

}
