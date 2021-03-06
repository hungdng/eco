/**
 * ****************************************************
 * * Description :
 * * File        : ProductService.java
 * * Author      : hung.tran
 * * Date        : Nov 03, 2020
 * ****************************************************
 **/
package com.hung.api.service;

import com.hung.data.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {
    ProductInfo findByid(UUID id);

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo update(ProductInfo productInfo);

    ProductInfo create(ProductInfo productInfo);

    void delete(UUID id);
}
