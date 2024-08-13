package com.test.productmanager.common;

public final class Constants {

    public static final String SUCCESS_MSG_PRODUCT_ADDED = "Product added successfully";
    public static final String SUCCESS_MSG_CATEGORY_ADDED = "Product Category added successfully";
    public static final String SUCCESS_MSG_PRODUCT_DELETED = "Product deleted successfully for productId: [%s]";
    public static final String SUCCESS_MSG_CATEGORY_DELETED = "Category deleted successfully for categoryId: [%s]";
    public static final String SUCCESS_MSG_PRODUCT_EDITED = "Product details updated successfully";
    public static final String SUCCESS_MSG_PRODUCTS_FETCHED_SUCCESSFULLY = "Products fetched successfully";
    public static final String SUCCESS_MSG_PRODUCT_FETCHED_SUCCESSFULLY = "Product fetched succesfully";
    public static final String SUCCESS_MSG_CATEGORIES_FETCHED_SUCCESSFULLY = "categories fetched successfully";
    public static final String SUCCESS_MSG_CATEGORY_FETCHED_SUCCESSFULLY = "categories fetched succesfully";
    public static final String ERROR_MSG_INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String ERROR_MSG_CATEGORY_NOT_FOUND_BY_CATEGORY_ID = "Category not found for CategoryId: [%s]";
    public static final String ERROR_MSG_CATEGORY_NOT_FOUND_BY_CATEGORY_NAME = "Category not found for CategoryName: [%s]";
    public static final String ERROR_MSG_PRODUCT_NOT_FOUND_BY_PRODUCT_ID = "Product not found for ProductId: [%s]";
    public static final String ERROR_MSG_PRODUCT_NOT_FOUND_BY_PRODUCT_UUID = "Product not found for ProductUUID: [%s]";
    public static final String MANDATORY_PARAMETERS_MISSING_FROM_ADD_PRODUCT_REQUEST = "Mandatory Paramters missing: {productName, basePrice, category} missing";
    private Constants() {
        throw new IllegalStateException("Utility Class");
    }

}
