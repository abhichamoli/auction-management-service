package com.test.notificationservice.common;

public class Constants {
    public static final String WIN_EMAIL_SUBJECT = "Congratulations on Winning the Auction!";
    public static final String WIN_EMAIL_TEXT = "Congratulations!\n\nUser with ID %s has won the bid for the product.\n\nProduct Details:\nProduct Name: %s\n\nBest regards,\nYour Auction Team";

    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}
