package com.cg.project.test;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cg.project.utility.BrowserCapabilitiesUtility;

public class UnitTestForGoogleChrome {
	public static RemoteWebDriver driver;
	public static String webURL = "http://www.google.com";
	public static String hubURL="http://10.102.52.199:4444/wd/hub";
	@BeforeClass
	@Parameters({ "browser" })
	public void setUpGoogleChromeTestEnv(String browser) throws MalformedURLException {
		driver = BrowserCapabilitiesUtility.getDriver(hubURL,browser);
		driver.manage().window().maximize();
	}
	
	@Test
	public void testWebPage() {
		driver.get(webURL);
		WebElement searchField = driver.findElement(By.id("lst-ib"));
		 searchField.sendKeys("pluralsight"); 
		 searchField.submit();
		
		  WebElement imagesLink = driver.findElements(By.linkText("Images")).get(0);
		  imagesLink.click();
		 
		 WebElement imageElement =driver.findElements(By.cssSelector("a[class = rg_l]")).get(0);
		 
		 WebElement imageLink = imageElement.findElements(By.tagName("img")).get(0);
		 imageLink.click();
	}
	@AfterClass 
	public void tearDownGoogleChromeTestEnv() throws MalformedURLException {
		driver.close();
		driver=null;
	}
}
