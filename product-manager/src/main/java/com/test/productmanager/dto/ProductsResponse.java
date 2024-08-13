package com.test.productmanager.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonPropertyOrder({"responseCode", "responseMessage", "productResponses"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsResponse extends BaseResponse{
    List<ProductResponse> productResponses;
}
