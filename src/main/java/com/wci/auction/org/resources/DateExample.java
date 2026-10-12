package com.wci.auction.org.resources;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateExample {
	public static String formattedDate;
	
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formattedDate = today.format(formatter);
        System.out.println("Today's date: " + formattedDate);
    }
}
