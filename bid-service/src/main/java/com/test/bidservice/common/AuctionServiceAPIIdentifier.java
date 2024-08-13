package com.test.bidservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuctionServiceAPIIdentifier {

    PLACE_BID("/api/auctions/auctionUUID/{auctionUUID}/bid");

    private String url;
}
