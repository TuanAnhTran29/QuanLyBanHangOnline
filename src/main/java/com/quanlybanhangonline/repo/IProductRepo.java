package com.quanlybanhangonline.repo;

import com.quanlybanhangonline.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends JpaRepository<Product,Long> {
    @Query(value = "select * from Product where status= true ",nativeQuery = true)
    Iterable<Product> findAllProduct();
}
