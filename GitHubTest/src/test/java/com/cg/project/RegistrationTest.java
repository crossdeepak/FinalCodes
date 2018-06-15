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

import com.cg.project.beans.RegistrationPage;

public class RegistrationTest {

	static WebDriver driver;
	RegistrationPage registrationPage;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\Deepak\\corejava\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Before
	public void setUp() throws Exception {
		driver.get("https://github.com/");
		registrationPage=new RegistrationPage();
		PageFactory.initElements(driver, registrationPage);
	}

	@After
	public void tearDown() throws Exception {
		registrationPage=null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
		driver=null;
	}

	@Test
	public void testForBlankInput() {
		registrationPage.setUsername("");
		registrationPage.setPassword("");
		registrationPage.setEmail("");
		registrationPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//*[@id='signup-form']/div")).getText();
		String actualErrorMessage1=driver.findElement(By.xpath("//*[@id='signup-form']/auto-check[1]/dl/dd[2]")).getText();
		String actualErrorMessage2=driver.findElement(By.xpath("//*[@id='signup-form']/auto-check[2]/dl/dd[2]")).getText();
		String actualErrorMessage3=driver.findElement(By.xpath("//*[@id='signup-form']/auto-check[3]/dl/dd[2]")).getText();
		System.out.println("Error Message :-"+actualErrorMessage);
		System.out.println("Error Message :-"+actualErrorMessage1);
		System.out.println("Error Message :-"+actualErrorMessage2);
		System.out.println("Error Message :-"+actualErrorMessage3);
		String expectedErrorMessage="There were problems creating your account.";
		Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
		String expectedErrorMessage1="Login can't be blank";
		Assert.assertEquals(expectedErrorMessage1, actualErrorMessage1);
		String expectedErrorMessage2="Email can't be blank";
		Assert.assertEquals(expectedErrorMessage2, actualErrorMessage2);
		String expectedErrorMessage3="Password can't be blank and is too short (minimum is 7 characters)";
		Assert.assertEquals(expectedErrorMessage3, actualErrorMessage3);
	}
	
	@Test
	public void testForInvalidUsername() {
		registrationPage.setUsername(getInvalidUsername());
		registrationPage.setPassword(getValidPassword());
		registrationPage.setEmail(getValidEmail());
		registrationPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//*[@id='signup-form']/div")).getText();
		String actualErrorMessage1=driver.findElement(By.xpath("//*[@id='signup-form']/auto-check[1]/dl/dd[2]")).getText();
		System.out.println("Error Message :-"+actualErrorMessage);
		System.out.println("Error Message :-"+actualErrorMessage1);
		String expectedErrorMessage="There were problems creating your account.";
		Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
		String expectedErrorMessage1="Login is already taken";
		Assert.assertEquals(expectedErrorMessage1, actualErrorMessage1);
	}
	
	@Test
	public void testForInvalidPassword() {
		registrationPage.setUsername(getValidUsername());
		registrationPage.setPassword(getInvalidPassword());
		registrationPage.setEmail(getValidEmail());
		registrationPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//*[@id='signup-form']/div")).getText();
		String actualErrorMessage1=driver.findElement(By.xpath("//*[@id='signup-form']/auto-check[3]/dl/dd[2]")).getText();
		System.out.println("Error Message :-"+actualErrorMessage);
		System.out.println("Error Message :-"+actualErrorMessage1);
		String expectedErrorMessage="There were problems creating your account.";
		Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
		String expectedErrorMessage1="Password is too short (minimum is 7 characters) and needs at least one number";
		Assert.assertEquals(expectedErrorMessage1, actualErrorMessage1);
	}
	
	@Test
	public void testForInvalidEmail() {
		registrationPage.setUsername(getValidUsername());
		registrationPage.setPassword(getValidPassword());
		registrationPage.setEmail(getInvalidEmail());
		registrationPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//*[@id='signup-form']/div")).getText();
		String actualErrorMessage1=driver.findElement(By.xpath("//*[@id='signup-form']/auto-check[2]/dl/dd[2]")).getText();
		System.out.println("Error Message :-"+actualErrorMessage);
		System.out.println("Error Message :-"+actualErrorMessage1);
		String expectedErrorMessage="There were problems creating your account.";
		Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
		String expectedErrorMessage1="Email is invalid or already taken";
		Assert.assertEquals(expectedErrorMessage1, actualErrorMessage1);
	}
	
	@Test
	public void testForInvalidEmailAndUsername() {
		registrationPage.setUsername(getInvalidUsername());
		registrationPage.setPassword(getValidPassword());
		registrationPage.setEmail(getInvalidEmail());
		registrationPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//*[@id='signup-form']/div")).getText();
		String actualErrorMessage1=driver.findElement(By.xpath("//*[@id='signup-form']/auto-check[2]/dl/dd[2]")).getText();
		String actualErrorMessage2=driver.findElement(By.xpath("//*[@id='signup-form']/auto-check[1]/dl/dd[2]")).getText();
		System.out.println("Error Message :-"+actualErrorMessage);
		System.out.println("Error Message :-"+actualErrorMessage1);
		System.out.println("Error Message :-"+actualErrorMessage2);
		String expectedErrorMessage="There were problems creating your account.";
		Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
		String expectedErrorMessage1="Email is invalid or already taken";
		Assert.assertEquals(expectedErrorMessage1, actualErrorMessage1);
		String expectedErrorMessage2="Login is already taken";
		Assert.assertEquals(expectedErrorMessage2, actualErrorMessage2);
	}
	
	@Test
	public void testForInvalidEmailAndPassword() {
		registrationPage.setUsername(getValidUsername());
		registrationPage.setPassword(getInvalidPassword());
		registrationPage.setEmail(getInvalidEmail());
		registrationPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//*[@id='signup-form']/div")).getText();
		String actualErrorMessage1=driver.findElement(By.xpath("//*[@id='signup-form']/auto-check[2]/dl/dd[2]")).getText();
		String actualErrorMessage2=driver.findElement(By.xpath("//*[@id='signup-form']/auto-check[3]/dl/dd[2]")).getText();
		System.out.println("Error Message :-"+actualErrorMessage);
		System.out.println("Error Message :-"+actualErrorMessage1);
		System.out.println("Error Message :-"+actualErrorMessage2);
		String expectedErrorMessage="There were problems creating your account.";
		Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
		String expectedErrorMessage1="Email is invalid or already taken";
		Assert.assertEquals(expectedErrorMessage1, actualErrorMessage1);
		String expectedErrorMessage2="Password is too short (minimum is 7 characters) and needs at least one number";
		Assert.assertEquals(expectedErrorMessage2, actualErrorMessage2);
	}
	
	@Test
	public void testForInvalidUsernameAndPassword() {
		registrationPage.setUsername(getInvalidUsername());
		registrationPage.setPassword(getInvalidPassword());
		registrationPage.setEmail(getValidEmail());
		registrationPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//*[@id='signup-form']/div")).getText();
		String actualErrorMessage1=driver.findElement(By.xpath("//*[@id='signup-form']/auto-check[1]/dl/dd[2]")).getText();
		String actualErrorMessage2=driver.findElement(By.xpath("//*[@id='signup-form']/auto-check[3]/dl/dd[2]")).getText();
		System.out.println("Error Message :-"+actualErrorMessage);
		System.out.println("Error Message :-"+actualErrorMessage1);
		System.out.println("Error Message :-"+actualErrorMessage2);
		String expectedErrorMessage="There were problems creating your account.";
		Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
		String expectedErrorMessage1="Login is already taken";
		Assert.assertEquals(expectedErrorMessage1, actualErrorMessage1);
		String expectedErrorMessage2="Password is too short (minimum is 7 characters) and needs at least one number";
		Assert.assertEquals(expectedErrorMessage2, actualErrorMessage2);
	}
	
	public String getValidUsername(){
		return "frostygg";
	}
	public String getInvalidUsername(){
		return "xyz";
	}
	public String getValidPassword(){
		return "qwerty1";
	}
	public String getInvalidPassword(){
		return "abcd";
	}
	public String getValidEmail(){
		return "deepakproo@gmail.com";
	}
	public String getInvalidEmail(){
		return "abc@gmail.com";
	}
}
