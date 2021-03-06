/**
 * ****************************************************
 * * Description :
 * * File        : CategoryService.java
 * * Author      : hung.tran
 * * Date        : Nov 10, 2020
 * ****************************************************
 **/
package com.hung.api.service;

import com.hung.data.entity.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    ProductCategory findById(UUID id);

    List<ProductCategory> findAll();

    ProductCategory save(ProductCategory productCategory);

    boolean isExists(UUID id);
}
