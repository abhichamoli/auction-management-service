package com.test.productmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CategoriesResponse extends BaseResponse {
    List<CategoryResponse> categoryResponses;
}
