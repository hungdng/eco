/**
 * ****************************************************
 * * Description :
 * * File        : CategoryController.java
 * * Author      : hung.tran
 * * Date        : Nov 04, 2020
 * ****************************************************
 **/
package com.hung.api.contrller;

import com.hung.api.component.support.ResponseSupport;
import com.hung.api.dto.request.category.RequestCaregoryCreate;
import com.hung.api.dto.response.ApiResponse;
import com.hung.api.dto.response.category.ResponseCategoryList;
import com.hung.common.enums.ResponseStatus;
import com.hung.data.entity.ProductCategory;
import com.hung.data.enums.MessageCode;
import com.hung.data.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseSupport responseSupport;

    @GetMapping
    public ApiResponse<?> findAll(){
        final List<ProductCategory> categories = categoryService.findAll();

        final Type categoryListType = new TypeToken<List<ResponseCategoryList>>() {
        }.getType();

        final List<ResponseCategoryList> categoryList = modelMapper.map(categories, categoryListType);
        return responseSupport.fetchSuccess(categoryList, MessageCode.SUCCESS_GET_DATA);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody final RequestCaregoryCreate requestCaregoryCreate){
        final ProductCategory category = modelMapper.map(requestCaregoryCreate, ProductCategory.class);
        return ResponseEntity.ok(categoryService.save(category));
    }
}
