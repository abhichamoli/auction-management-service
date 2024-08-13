package com.test.bidservice.common;

public final class Constants {

    public final static String SUCCESS_MSG_PLACED_BET = "Bid is placed successfully";
    public final static String SUCCESS_MSG_BETS_FETCHED_SUCCESFULLY = "Bids fetched successfully";
    public static final String ERROR_MSG_INTERNAL_SERVER_ERROR = "Internal Server Error";

    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}


