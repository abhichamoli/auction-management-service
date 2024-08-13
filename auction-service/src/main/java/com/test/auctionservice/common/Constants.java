package com.test.auctionservice.common;

public final class Constants {
    public static final String SUCCESS_MSG_AUCTION_SLOT_ADDED = "Auction Slot added successfully";
    public static final String SUCCESS_MSG_PRODUCT_MAPPED_FOR_A_AUCTION_SLOT = "Product with UUID: [%s] is successfully added for a auction slot: [%s]";
    public static final String SUCCESS_MSG_PRODUCT_DELETED_SUCCESSFULLY_FROM_AUCTION_SLOT = "Product with auctionId: [%s] is successfully deteled from auction slot";
    public static final String SUCCESS_MSG_AUCTION_SLOT_SUCCESSFULLY_DELETED = "Auction slot with slotId [%s] is succesfully deleted";
    public static final String SUCCESS_MSG_PRODDUCT_AUCTION_FETCHED_SUCCESFULLY = "Product Auction fetched succesfully";
    public static final String SUCCESS_MSG_PRODDUCT_AUCTION_LIST_FETCHED_SUCCESFULLY = "Product Auctions List fetched succesfully";
    public static final String SUCCESS_MSG_AUCTION_SLOT_EDIT_SUCCESSFULLY_FOR_SLOT_ID = "Auction slot edit successfully for slot Id: [%s]";
    public static final String AUCTION_SLOT_FETCHETED_SUCCESSFULLY = "Auction Slot fetched succesfully";
    public static final String AUCTION_SLOTS_FETCHETED_SUCCESSFULLY = "Auction Slots fetched succesfully";
    public static final String ERROR_MSG_INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String AUCTION_SLOT_NOT_FOUND_FOR_SLOT_ID = "Auction Slot not found for slotId: [%s]";
    public static final String START_TIME_CANNOT_BE_GREATER_THAN_END_TIME = "startTime cannot be greater than endTime";
    public static final String MANDATORY_PARAMETERS_MISSING_FROM_ADD_SLOT_REQUEST = "Mandatory parameters: {startTime, Endtime} missing";
    public static final String PRODUCT_AUCTION_NOT_FOUND_FOR_AUCTION_UUID = "Product Auction not found for AuctionUUID: [%s]";
    public static final String PRODUCT_AUCTION_NOT_FOUND_FOR_AUCTION_ID = "Product Auction not found for auctionId: [%s]";
    public static final String SUCCESSFULLY_PLACED_BID_FOR_AUCTION_UUID = "Succesfully placed bid for auctionUUID: [%s]";
    public static final String ERR_MSG_BID_AMOUNT_LOWER_THAN_OR_EQUALS_TO_HIGHEST_BID = "bidAmount: [%s] should be more than current higest bid: [%s]";
    public static final String ERR_MSG_BID_AMOUNT_IS_LOWER_THAN_BASE_PRICE = "bidAmount: [%s] is lower than basePrice: [%s]";
    public static final String ERR_MSG_AUCTION_WITH_UUID_IS_NOT_ACTIVE = "auction with auctionUUID: [%s] is not active";

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

}
