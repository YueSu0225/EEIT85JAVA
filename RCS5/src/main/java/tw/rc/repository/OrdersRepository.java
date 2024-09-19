package tw.rc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.rc.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
