package com.quanlybanhangonline.controller;

import com.quanlybanhangonline.dto.response.ResponseMessage;
import com.quanlybanhangonline.model.Cart;
import com.quanlybanhangonline.model.Product;
import com.quanlybanhangonline.model.User;
import com.quanlybanhangonline.service.cartservice.ICartService;
import com.quanlybanhangonline.service.productservice.IProductService;
import com.quanlybanhangonline.service.userservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/api")
public class CartController {
    @Autowired
    private ICartService cartService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @PostMapping("/addToCart/{productId}/{quantity}/{userId}")
    public ResponseEntity<?> addToCart(@PathVariable Long productId,@PathVariable int quantity,@PathVariable Long userId){
        if (quantity<= 0){
            return new ResponseEntity<>(new ResponseMessage("Quantity must be more than 0!"),HttpStatus.OK);
        }
        if (quantity > productService.findById(productId).get().getInventory()){
            return new ResponseEntity<>(new ResponseMessage("Quantity exceeds stock!"),HttpStatus.OK);
        }
        Product product= productService.findById(productId).get();
        User user= userService.findById(userId).get();
        cartService.save(new Cart(user,product,quantity));
        return new ResponseEntity<>(new ResponseMessage("Add to cart success!"), HttpStatus.OK);
    }



    @GetMapping("/viewAllProductInCart/{userId}")
    public ResponseEntity<?> viewAllProductInCart(@PathVariable Long userId){
        Iterable<Cart> carts= cartService.findAllCartByUser(userId);
        List<Product> productList= new ArrayList<>();

        carts.forEach(c->{
            productList.add(c.getProduct());
        });
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }
}
