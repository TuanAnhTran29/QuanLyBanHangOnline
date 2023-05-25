package com.quanlybanhangonline.service.productservice;

import com.quanlybanhangonline.model.Product;
import com.quanlybanhangonline.service.IGeneralService;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllProduct();
}
