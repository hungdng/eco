/**
 * ****************************************************
 * * Description :
 * * File        : CategoryService.java
 * * Author      : hung.tran
 * * Date        : Nov 03, 2020
 * ****************************************************
 **/
package com.hung.eco.data.service;

import com.hung.eco.data.entity.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    ProductCategory findById(UUID id);

    List<ProductCategory> findAll();

    ProductCategory save(ProductCategory productCategory);

    boolean isExists(UUID id);
}
