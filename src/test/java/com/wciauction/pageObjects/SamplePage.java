package com.wciauction.pageObjects;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SamplePage {

	public static void main(String[] args) {
        // Define the Eastern Time zone
        ZoneId easternTimeZone = ZoneId.of("America/New_York");

        // Get current date in Eastern Time
        ZonedDateTime zonedDateTime = ZonedDateTime.now(easternTimeZone);

        // Get local date
        LocalDate myDateObj = zonedDateTime.toLocalDate();

        // Format current date in MM/dd/yyyy format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedCurrentDate = myDateObj.format(dateFormatter);

        System.out.println("Current date: " + formattedCurrentDate);
    }
}
