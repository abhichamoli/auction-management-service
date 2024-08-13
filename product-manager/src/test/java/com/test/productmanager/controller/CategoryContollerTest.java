package com.test.productmanager.controller;

import com.test.productmanager.dto.AddCategoryRequest;
import com.test.productmanager.dto.BaseResponse;
import com.test.productmanager.dto.CategoriesResponse;
import com.test.productmanager.dto.CategoryResponse;
import com.test.productmanager.entity.Category;
import com.test.productmanager.service.CategoryService;
import com.test.productmanager.common.TestConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static com.test.productmanager.common.Constants.SUCCESS_MSG_CATEGORIES_FETCHED_SUCCESSFULLY;
import static com.test.productmanager.common.Constants.SUCCESS_MSG_CATEGORY_ADDED;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CategoryContollerTest {

    @InjectMocks
    CategoryController categoryController;

    @Mock
    CategoryService categoryService;

    @Test
    public void testAddCategory(){
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest(TestConstants.CATEGORY);
        Mockito.when(categoryService.addCategory(TestConstants.CATEGORY)).thenReturn(new Category(TestConstants.CATEGORY));

        ResponseEntity<BaseResponse> responseEntity  = categoryController.addCategory(addCategoryRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(SUCCESS_MSG_CATEGORY_ADDED, Objects.requireNonNull(responseEntity.getBody()).getResponseMessage());
    }

    @Test
    public void testGetAllCategories(){
        List<CategoryResponse> categoryResponseList = List.of(new CategoryResponse(TestConstants.CATEGORY));
        CategoriesResponse categoriesResponse = new CategoriesResponse(categoryResponseList);
        Mockito.when(categoryService.findAllCategories()).thenReturn(categoryResponseList);

        ResponseEntity<BaseResponse> responseEntity = categoryController.findAllCategories();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(SUCCESS_MSG_CATEGORIES_FETCHED_SUCCESSFULLY, Objects.requireNonNull(responseEntity.getBody()).getResponseMessage());
    }

}


