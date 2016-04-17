package MultipleWindow;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * Note---->>>
 * There is only one way you can get multiple windows via Selenium web driver, 
 * that is by clicking on a link which opens the page in a new browser window. 
 * Selenium web driver keeps a track of how many windows it opened during a session. 
 * Which means that it will not keep track of any browser window 
 * which is - Opened manually - Opened by a previous session of Selenium Webdriver 
 * By session of selenium WebDriver we mean the duration from the time we instantiate 
 * a WebDriver instance to the time we kill it via WebDriver.Quit or by manually killing 
 * the process. With this understanding lets first open some windows using Selenium WebDriver. 
 */
public class BrowserWaite {
	
	WebDriver  driver;
	
	@BeforeTest
	public void setup(){
		driver = new FirefoxDriver();
		
		/**
		 * PageLoadTimeout Command:
		 *Purpose-->
		 *Sets the amount of time to wait for a page load to complete before throwing an error.
		 *If the timeout is negative, page loads can be indefinite.
		 */
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		
		/**
		 * SetScriptTimeout Command:
		 * Purpose-->> 
		 * Sets the amount of time to wait for an asynchronous script to finish execution before 
		 * throwing an error. If the timeout is negative, then the script will be allowed to run 
		 * indefinitely
		 */
		 driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS);
	}
	
//	public WebDriver driver(String driverName){
//		WebDriver d;
//		
//		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
//		d = new ChromeDriver();
//	}
//	
	/**
	 * Purpose--->>
	 * Selenium WebDriver has borrowed the idea of implicit waits from Watir. 
	 * This means that we can tell Selenium that we would like it to wait for a 
	 * certain amount of time before throwing an exception that it cannot find 
	 * the element on the page. We should note that implicit waits will be in place 
	 * for the entire time the browser is open. This means that any search for elements
	 * on the page could take the time the implicit wait is set for.
	 */
	@Test
	public void implicityWait(){
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
		 driver.get("http://localhost/ac");
		 
//		 WebElement myDynamicElement = driver.findElement(By.id("myDynamicElement"));
	}
	
	/**
	 * Purpose--->>
	 *  Each FluentWait instance defines the maximum amount of time to wait for a condition, 
	 *  as well as the frequency with which to check the condition. Furthermore, the user may 
	 *  configure the wait to ignore specific types of exceptions whilst waiting, such as 
	 *  NoSuchElementExceptions when searching for an element on the page.
	 */
	@Test
	public void fluientWait(){
		 
	}
	
	/**
	 * Purpose-->>
	 * Models a condition that might reasonably be expected to eventually evaluate to 
	 * something that is neither null nor false.
	 */
	@Test
	public void ExpecteddCondition(){
		driver.get("http://localhost/ac/wp-admin");
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name("wp-submit")));
		element.click();
	}
	
	
	
}
