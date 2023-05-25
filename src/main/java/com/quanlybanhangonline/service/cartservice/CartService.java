package com.quanlybanhangonline.service.cartservice;

import com.quanlybanhangonline.model.Cart;
import com.quanlybanhangonline.repo.ICartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService implements ICartService{
    @Autowired
    private ICartRepo cartRepo;

    @Override
    public Iterable<Cart> findAll() {
        return cartRepo.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepo.findById(id);
    }

    @Override
    public void save(Cart cart) {
        cartRepo.save(cart);
    }

    @Override
    public void remove(Long id) {
        cartRepo.deleteById(id);
    }

    @Override
    public Iterable<Cart> findAllCartByUser(Long userId) {
        return cartRepo.findAllCartByUser(userId);
    }
}
