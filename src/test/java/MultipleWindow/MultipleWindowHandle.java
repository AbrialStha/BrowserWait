package MultipleWindow;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class MultipleWindowHandle {
	/**
	 * Basics of Multiple Window:
	 * There is only one way you can get multiple windows via Selenium web driver, that is
	 * by clicking on a link which opens the page in a new browser window. Selenium web driver
	 * keeps a track of how many windows it opened during a session. Which means that it 
	 * will not keep track of any browser window which is 
	 * - Opened manually 
	 * - Opened by a previous session of Selenium Webdriver 
	 * By session of selenium WebDriver we mean the duration from the time we instantiate a 
	 * WebDriver instance to the time we kill it via WebDriver.Quit or by manually killing 
	 * the process. With this understanding lets first open some windows using Selenium 
	 * WebDriver. To achieve this all we have to do is to click on the button on our practice page. 
	 * Here is the code for that
	 * @throws InterruptedException 
	 */
	
	@Test
	public void newWindow() throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");
		WebElement clickElement = driver.findElement(By.id("button1")); 
 
		for(int i = 0; i < 3; i++)
		{
			clickElement.click();
			Thread.sleep(3000);
		}
	}
	
	/**
	 * Handle Windows in Selenium Webdriver:
	 * To uniquely identify an opened browser Selenium WebDriver keeps a map of Opened 
	 * windows VS Window Handle. Window handle is a unique string value that uniquely 
	 * identifies a Browser window on desktop. It is guaranteed that each browser will have 
	 * a unique window handle. To get Window handle WebDriver interface provides two methods 
	 * - getWindowHandle() 
	 * - getWindowHandles()
	 * getWindowHandle() method return a string value and it returns the Window handle of current 
	 * focused browser window. 
	 * getWindowHandles() method returns a set of all Window handles of all the browsers that were 
	 * opened in the session. In this case it will return 4 windows handles because we have 4 windows open. 
	 * Here is the code to print out window handles on console of eclipse.   
	 * @throws InterruptedException
	 */
	@Test
	public void newWindowHandle() throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");
		String parentWindowHandle = driver.getWindowHandle();
		System.out.println("Parent window's handle -> " + parentWindowHandle);
		WebElement clickElement = driver.findElement(By.id("button1")); 
 
		for(int i = 0; i < 3; i++)
		{
			clickElement.click();
			Thread.sleep(3000);
		}
 
		Set<String> allWindowHandles = driver.getWindowHandles();
 
		for(String handle : allWindowHandles)
		{
			System.out.println("Window handle - > " + handle);
		}

	}
	
	/**
	 * Handling Multiple Windows in Selenium WebDriver:
	 * There is a concept of current focused window which means that all selenium webdriver commands will go to the focused window. 
	 * By default the focus is always on the Parent window, please see the screenshot above. In order to shift focus from Parent 
	 * Window to any child window we have to use the following command on WebDriver 
	 * – WebDriver.SwitchTo().window(String windowHandle); 
	 * This command takes in a window handle and switches the driver context on that window. 
	 * Once the Switch happens all the driver commands will go to the newly focused window. 
	 * This is very important to understand, without switching to the desired window we wil not be able to perform any action on that 
	 * window. Now lets see some code which iteratively moves across all the open windows and navigates to a particular page in all the 
	 * open windows one by one.
	 * @throws InterruptedException 
	 */
	@Test
	public void newWindowSwitch() throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");
		String parentWindowHandle = driver.getWindowHandle();
		System.out.println("Parent window's handle -> " + parentWindowHandle);
		WebElement clickElement = driver.findElement(By.id("button1")); 
 
		for(int i = 0; i < 3; i++)
		{
			clickElement.click();
			Thread.sleep(3000);
		}
 
		Set<String> allWindowHandles = driver.getWindowHandles();
 
		for(String handle : allWindowHandles)
		{
			System.out.println("Switching to window - > " + handle);
			System.out.println("Navigating to google.com");
			driver.switchTo().window(handle); //Switch to the desired window first and then execute commands using driver
			driver.get("http://google.com");
		}

	}
	
	/**
	 * Closing all the Windows:
	 * There are basically two commands that we can use to close the opened browser windows. 
	 * - WebDriver.close() 
	 * - WebDriver.quit() 
	 * WebDriver.Close() command will close the current window on which the focus is present. 
	 * This can be used to close windows selectively. Just switch to the window that you want 
	 * to close by using the correct Window handle and the call the WebDriver.close command. 
	 * That will close the current browser window. 
	 * Note-->> 
	 * After closing a window you have to explicity switch to another valid window before sending in 
	 * any WebDriver commands. if you fail to do this you wil get following exception. 
	 * org.openqa.selenium.NoSuchWindowException: Window not found. 
	 * The browser window may have been closed.   In the code below we will close the parent window and 
	 * then explicitly move focus to the last window in the list.
	 * @throws InterruptedException 
	 */
	@Test
	public void newWindowClose() throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");
		String parentWindowHandle = driver.getWindowHandle();
		System.out.println("Parent window's handle -> " + parentWindowHandle);
		WebElement clickElement = driver.findElement(By.id("button1")); 
 
		for(int i = 0; i < 3; i++)
		{
			clickElement.click();
			Thread.sleep(3000);
		}
 
		Set<String> allWindowHandles = driver.getWindowHandles();
		String lastWindowHandle = "";
		for(String handle : allWindowHandles)
		{
			System.out.println("Switching to window - > " + handle);
			System.out.println("Navigating to google.com");
			driver.switchTo().window(handle); //Switch to the desired window first and then execute commands using driver
			driver.get("http://google.com");
			lastWindowHandle = handle;
		}
 
		//Switch to the parent window
		driver.switchTo().window(parentWindowHandle);
		//close the parent window
		driver.close();
		//at this point there is no focused window, we have to explicitly switch back to some window.
		driver.switchTo().window(lastWindowHandle);
		driver.get("http://toolsqa.com");

	}
	/*
	 * WebDriver.quit() will close all the windows opened in the session. This command basically shuts down the driver 
	 * instance and any further commands to WebDriver results in exception.  
	 */
}
