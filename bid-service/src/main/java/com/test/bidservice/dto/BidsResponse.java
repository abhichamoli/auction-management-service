package com.test.bidservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BidsResponse extends BaseResponse {
    List<BidResponse> bidResponses;
}
