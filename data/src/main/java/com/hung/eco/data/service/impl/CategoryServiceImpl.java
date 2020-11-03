/**
 * ****************************************************
 * * Description :
 * * File        : CategoryServiceImpl.java
 * * Author      : hung.tran
 * * Date        : Nov 03, 2020
 * ****************************************************
 **/
package com.hung.eco.data.service.impl;

import com.hung.eco.data.entity.ProductCategory;
import com.hung.eco.data.repository.ProductCategoryRepository;
import com.hung.eco.data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findById(UUID id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public boolean isExists(UUID id) {
        return productCategoryRepository.existsById(id);
    }
}
