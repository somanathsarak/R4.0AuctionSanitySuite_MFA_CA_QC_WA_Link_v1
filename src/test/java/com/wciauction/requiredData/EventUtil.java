package com.wciauction.requiredData;

import java.util.Random;

public class EventUtil {

    private static String getRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String createEventName(String baseName) {
        if (baseName == null || baseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Base name cannot be null or empty");
        }
        return baseName.trim() + "Event" + getRandomString(6); // 6-char random suffix
    }

    public static void main(String[] args) {
        String event1 = createEventName("ReserveSaleEvent");
        String event2 = createEventName("UserRegistration");

        System.out.println(event1); // e.g., AuctionApprovalCAEventX9D4KQ
        System.out.println(event2); // e.g., UserRegistrationEvent7B2LNP
    }
}