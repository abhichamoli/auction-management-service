package com.test.productmanager.service;

import com.test.productmanager.dto.CategoryResponse;
import com.test.productmanager.entity.Category;
import com.test.productmanager.exception.ProductManagerException;
import com.test.productmanager.exception.ResourceNotFoundException;
import com.test.productmanager.repository.CategoryRepository;
import com.test.productmanager.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category addCategory(String categoryName) {
        Category category = new Category(categoryName);
        return categoryRepository.save(category);
    }

    @Override
    public CategoryResponse findCategory(Long categoryId) throws ProductManagerException {
        Optional<Category> category = categoryRepository.findById(categoryId);
        Category categoryValue = category.orElseThrow(() -> {
            log.error("Category not found for categoryId: [{}]", categoryId);
            return new ResourceNotFoundException(String.format(Constants.ERROR_MSG_CATEGORY_NOT_FOUND_BY_CATEGORY_ID, categoryId));
        });
        return new CategoryResponse(categoryValue.getCategoryName());
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> new CategoryResponse(category.getCategoryName())).toList();
    }

    @Override
    public void deleteCategory(Long categoryId) throws ProductManagerException{
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            log.error("Category not found for CategoryId: {}", categoryId);
            throw new ResourceNotFoundException(String.format(Constants.ERROR_MSG_CATEGORY_NOT_FOUND_BY_CATEGORY_ID, categoryId));
        }
        categoryRepository.deleteById(categoryId);
    }

}
