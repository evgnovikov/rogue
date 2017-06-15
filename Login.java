package io.rogue.Pages;

import java.util.concurrent.TimeUnit;

import io.rogue.Base.PageBase;
import io.rogue.Config.Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends PageBase {

	public Login (WebDriver driver) {
		super(driver);
	}
	
	public void starting () {
		System.out.println("\n========== New test ==========");
		driver.get(Config.getProperty("base_Prod"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
