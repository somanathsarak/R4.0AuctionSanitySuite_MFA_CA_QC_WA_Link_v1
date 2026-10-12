package constants;

import java.util.UUID;
import java.util.Random;

public class EventNameConstants {


	public static String ReserveSaleEventName;
	public static String ReserveSaleEventNameR70;
	public static String ReserveSaleEventName_WA;
	public static String eventNameEng;
	public static String eventNameFR;
	public static String eventNameWS;
	public static String eventNameCA;
	public static String eventNameQC;
	public static String eventName_PE1;
	public static String eventName_PE2;
	public static String eventName_PE3;
	public static String eventName_PE4;
	public static String eventName_PE5;
	public static String eventName_PE6;
	public static String eventName_PE7;
	public static String eventName_PE8;
	public static String eventName_PE9;
	public static String eventName_PE10;
	public static String browserName;
	
	public static String eventName_PE11;
	public static String eventName_PEQc1;
	public static String eventName_PE12;
	public static String eventName_PEQc2;
	public static String eventName_PEWa2;
	public static String eventName_PEWa;
	public static String eventName_REWA1;
	public static String eventName_PECa;
	public static String eventName_PEQc3;
	public static String eventName_PEQc6;
	public static String eventName_PEQc4;
	public static String eventName_REWA2;
	public static String eventName_PEQc5;
	
	
	 private static final String[] EVENT_PREFIXES = {"Sale", "Promo", "Discount", "Special", "Deal", "Offer", "Launch", "Exclusive"};
	    private static final String[] EVENT_SUFFIXES = {"Event", "Campaign", "Promotion", "Drive", "Days", "Season", "Period", "Showcase"};
	    private static final String[] BROWSER_NAMES = {"Chrome", "Firefox", "Safari", "Edge", "Opera", "Brave", "Vivaldi"};
	    
	    // Static initialization block to initialize all constants
	    static {
	        Random random = new Random();
	        
	        ReserveSaleEventName = generateRandomEventName(random);
	        ReserveSaleEventNameR70 = generateRandomEventName(random) + "-R70";
	        ReserveSaleEventName_WA = generateRandomEventName(random) + "-WA";
	        eventNameEng = generateRandomEventName(random) + "-ENG";
	        eventNameFR = generateRandomEventName(random) + "-FR";
	        eventNameWS = generateRandomEventName(random) + "-WS";
	        eventNameCA = generateRandomEventName(random) + "-CA";
	        eventNameQC = generateRandomEventName(random) + "-QC";
	        
	        // Initialize PE event names with sequential numbers for easier tracking
	        eventName_PE1 = generateRandomEventName(random) + "-PE1";
	        eventName_PE2 = generateRandomEventName(random) + "-PE2";
	        eventName_PE3 = generateRandomEventName(random) + "-PE3";
	        eventName_PE4 = generateRandomEventName(random) + "-PE4";
	        eventName_PE5 = generateRandomEventName(random) + "-PE5";
	        eventName_PE6 = generateRandomEventName(random) + "-PE6";
	        eventName_PE7 = generateRandomEventName(random) + "-PE7";
	        eventName_PE8 = generateRandomEventName(random) + "-PE8";
	        eventName_PE9 = generateRandomEventName(random) + "-PE9";
	        eventName_PE10 = generateRandomEventName(random) + "-PE10";
	        
	        // Set random browser name
	        browserName = BROWSER_NAMES[random.nextInt(BROWSER_NAMES.length)];
	    }
	    
	    // Helper method to generate random event names
	    private static String generateRandomEventName(Random random) {
	        String prefix = EVENT_PREFIXES[random.nextInt(EVENT_PREFIXES.length)];
	        String suffix = EVENT_SUFFIXES[random.nextInt(EVENT_SUFFIXES.length)];
	        // Add a short UUID section to ensure uniqueness
	        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
	        
	        return prefix + "_" + suffix + "_" + uniqueId;
	    }
	
}
