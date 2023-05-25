package com.quanlybanhangonline.service.cartservice;

import com.quanlybanhangonline.model.Cart;
import com.quanlybanhangonline.service.IGeneralService;

public interface ICartService extends IGeneralService<Cart> {
    Iterable<Cart> findAllCartByUser(Long userId);
}
