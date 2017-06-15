package io.rogue.Base;

import io.rogue.Config.Config;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionNotFoundException;

public class TestBase {
	public static WebDriver driver = null;
	
	private static boolean healthCheck(){
		try{
			driver.getTitle();
		
		}catch(Exception ex){
			System.out.println("\nBrowser was closed. Reopening the browser");
			return false;
		}
		
		return true;
	}

	public static WebDriver getDriver() {
		/*if (driver != null){
			if(healthCheck()){
				return driver;
			}
			driver = null;
		}*/

		if (driver == null) {						
			if (Config.getProperty("browser").equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();				
			
			} else if (Config.getProperty("browser").equalsIgnoreCase("IE")) {
				File fileIE = new File("src//test/java/iedriver.exe");
				System.setProperty("webdriver.ie.driver", fileIE.getAbsolutePath());				
				
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				
				try {										
					driver = new InternetExplorerDriver(ieCapabilities);										
				} catch (Exception e) {					
					String msg = "Unable to open IE browser!";
					System.out.println(msg);
				}
				
			} else if (Config.getProperty("browser").equalsIgnoreCase("Chrome")) {

				File fileChrome = new File("src//test/java/chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", fileChrome.getAbsolutePath());

				DesiredCapabilities chrome = DesiredCapabilities.chrome();

				try {
					driver = new ChromeDriver(chrome);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			} else {
				System.out.println("Can't get any browser");
			}
			
			Runtime.getRuntime().addShutdownHook(new Thread() {
			    public void run() { 
				       try {
				    	   TestBase.getDriver();
				    	   TestBase.getDriver().close();
					       TestBase.getDriver().quit();
				       } catch (SessionNotFoundException e) {
				    	   System.out.println("Session has already closed");
				       }				       			       
			       }
			 });
		}
			return driver;
	}
}
