package com.wciauction.pageObjects.events;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class RandomEventNameGenerator {
    private static final String PROPERTIES_FILE_PATH = "src/main/resources/config.properties";
    private static final String EVENT_NAME_KEY = "event.name";
    
    
    /**
     * Generates a random event name and updates it in the properties file
     * @return The newly generated event name
     * @throws IOException If there's an error reading or writing to the properties file
     */
    static String generateAndUpdateEventName() throws IOException {
        String newEventName = generateRandomEventName();
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Properties file not found or couldn't be loaded. Creating a new one.");
        }
        if (properties.containsKey(EVENT_NAME_KEY)) {
            String previousEventName = properties.getProperty(EVENT_NAME_KEY);
            properties.setProperty("previous." + EVENT_NAME_KEY, previousEventName);
        }
        properties.setProperty(EVENT_NAME_KEY, newEventName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(PROPERTIES_FILE_PATH)) {
            properties.store(fileOutputStream, "Updated Event Name");
        }
        return newEventName;
    }
    
    /**
     * Generates a random event name
     * @return A randomly generated event name
     */
    private static String generateRandomEventName() {
        Random random = new Random();
        
        // Generate a random number between 1000 and 9999
        int randomNumber = 1000 + random.nextInt(9000);
        
        // Generate current year + random offset (0-4)
        int year = 2023 + random.nextInt(5); // Events between 2023-2027
        
        // Format: JointAuctionAutomation-[random 4 digit number]-[year]
        return "JointAuctionAutomation-" + randomNumber + "-" + year;
    }
}