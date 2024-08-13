package com.test.productmanager.service;

import com.test.productmanager.dto.CategoryResponse;
import com.test.productmanager.entity.Category;
import com.test.productmanager.exception.ProductManagerException;

import java.util.List;

public interface CategoryService {
    Category addCategory(String catgoryName);

    CategoryResponse findCategory(Long categoryId) throws ProductManagerException;

    List<CategoryResponse> findAllCategories();

    void deleteCategory(Long categoryId) throws ProductManagerException;
}
