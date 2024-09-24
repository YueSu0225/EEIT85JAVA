package tw.rc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.rc.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long>{

}
