package com.cg.project;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.cg.project.beans.LoginPage;

public class LoginPageTest {
	
	static WebDriver driver;
	LoginPage loginPage;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\Deepak\\corejava\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}	

	@Before
	public void setUp() throws Exception {
		driver.get("https://github.com/login");
		loginPage=new LoginPage();
		PageFactory.initElements(driver, loginPage);
		
	}

	@After
	public void tearDown() throws Exception {
		loginPage=null;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
		driver=null;
	}
	
	@Test
	public void testForBlankUserNameAndPassword() {
		loginPage.setUsername("");
		loginPage.setPassword("");
		loginPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//div[@class='container']")).getText();
		System.out.println("Error Message :-"+actualErrorMessage);
		String expectedErrorMessage="Incorrect username or password.";
		Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
	}
	
	
	@Test
	public void testForWrongUserNameAndPassword() {
		loginPage.setUsername(getInvalidUsername());
		loginPage.setPassword(getInvalidpassword());
		loginPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//div[@class='container']")).getText();
		System.out.println("Error Message :-"+actualErrorMessage);
		String expectedErrorMessage="Incorrect username or password.";
		Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
	}
	@Test
	
	public void testForCorrectUserNameAndPassword() {
		loginPage.setUsername(getCorrectUsername());
		loginPage.setPassword(getCorrectPassword());
		loginPage.clickSubmitButton();
		String actualMessage=driver.findElement(By.name("user-login")).getAttribute("content");
		System.out.println(actualMessage);
		String expectedMessage="crossdeepak";
		System.out.println(expectedMessage);
		Assert.assertEquals(expectedMessage, actualMessage);
	}
	
	public String getInvalidUsername(){
		return "abcd";
	}
	public String getInvalidpassword(){
		return "abyg";
	}
	public String getCorrectUsername(){
		return "crossdeepak";
	}
	public String getCorrectPassword(){
		return "5234sd";
	}

}
