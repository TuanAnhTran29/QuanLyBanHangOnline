package com.quanlybanhangonline.repo;

import com.quanlybanhangonline.model.Cart;
import com.quanlybanhangonline.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepo extends JpaRepository<Cart,Long> {

    @Query(value = "select * from Cart where user= ?1",nativeQuery = true)
    Iterable<Cart> findAllCartByUser(Long userId);
}
