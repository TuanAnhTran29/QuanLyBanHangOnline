package com.quanlybanhangonline.controller;

import com.quanlybanhangonline.dto.response.ResponseMessage;
import com.quanlybanhangonline.model.Product;
import com.quanlybanhangonline.service.productservice.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/any/api/product/all_product")
    public ResponseEntity<Iterable<Product>> findAll(){
        return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);
    }

    @PostMapping("/admin/api/product/add_product")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product){
        if (product.getInventory() != 0){
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResponseMessage("Inventory must be more than 1"), HttpStatus.OK);
        }

    }

    @GetMapping("/any/api/product/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id){
        return new ResponseEntity<>(productService.findById(id),HttpStatus.OK);
    }

    @PutMapping("/admin/api/product/edit_product/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product ,@PathVariable Long id){
        Optional<Product> productOptional= productService.findById(id);
        if (!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            product.setId(productOptional.get().getId());
            productService.save(product);
            return new ResponseEntity<>(new ResponseMessage("Updated product successfully!"),HttpStatus.OK);
        }
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
//        productService.remove(id);
//        cartSerivce.deleteCartByProductId(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
