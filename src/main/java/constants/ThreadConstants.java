package constants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ThreadConstants {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static ThreadLocal<String> testCaseId = new ThreadLocal<>();
	public static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
}
 