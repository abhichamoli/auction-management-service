package com.test.productmanager.controller;

import com.test.productmanager.common.ProductMangerHelper;
import com.test.productmanager.dto.AddCategoryRequest;
import com.test.productmanager.dto.BaseResponse;
import com.test.productmanager.dto.CategoriesResponse;
import com.test.productmanager.dto.CategoryResponse;
import com.test.productmanager.exception.ProductManagerException;
import com.test.productmanager.service.CategoryService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.test.productmanager.common.Constants.SUCCESS_MSG_CATEGORIES_FETCHED_SUCCESSFULLY;
import static com.test.productmanager.common.Constants.SUCCESS_MSG_CATEGORY_ADDED;
import static com.test.productmanager.common.Constants.SUCCESS_MSG_CATEGORY_DELETED;
import static com.test.productmanager.common.Constants.SUCCESS_MSG_CATEGORY_FETCHED_SUCCESSFULLY;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Add a new Category")
    public ResponseEntity<BaseResponse> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
        categoryService.addCategory(addCategoryRequest.getCategoryName());
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse(HttpStatus.CREATED.value(), SUCCESS_MSG_CATEGORY_ADDED));
    }

    @GetMapping
    @Operation(summary = "Get all categories")
    public ResponseEntity<BaseResponse> findAllCategories(){
        List<CategoryResponse> categoryResponses = categoryService.findAllCategories();
        CategoriesResponse categoriesResponse = new CategoriesResponse(categoryResponses);
        ProductMangerHelper.addSuccessMessageAndResponseCode(categoriesResponse, SUCCESS_MSG_CATEGORIES_FETCHED_SUCCESSFULLY);
        return ResponseEntity.ok().body(categoriesResponse);
    }

    @Hidden
    @GetMapping("/{categoryId}")
    public ResponseEntity<BaseResponse> findCategory(@PathVariable Long categoryId) throws ProductManagerException {
        CategoryResponse categoryResponse = categoryService.findCategory(categoryId);
        ProductMangerHelper.addSuccessMessageAndResponseCode(categoryResponse, SUCCESS_MSG_CATEGORY_FETCHED_SUCCESSFULLY);
        return ResponseEntity.ok().body(categoryResponse);
    }

    @Hidden
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<BaseResponse> deleteCategory(@PathVariable Long categoryId) throws ProductManagerException {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().body(new BaseResponse(HttpStatus.OK.value(), String.format(SUCCESS_MSG_CATEGORY_DELETED, categoryId)));
    }

}
