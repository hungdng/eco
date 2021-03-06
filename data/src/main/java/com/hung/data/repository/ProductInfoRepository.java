/**
 * ****************************************************
 * * Description :
 * * File        : ProductInfoRepository.java
 * * Author      : hung.tran
 * * Date        : Nov 03, 2020
 * ****************************************************
 **/
package com.hung.data.repository;

import com.hung.data.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, UUID> {
}
