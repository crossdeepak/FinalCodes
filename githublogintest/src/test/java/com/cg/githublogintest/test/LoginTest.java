package com.cg.githublogintest.test;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cg.githublogintest.model.LoginPage;
import com.cg.githublogintest.model.RegistrationPage;
import com.cg.githublogintest.utility.BrowserCapabilitiesUtility;

public class LoginTest {
	public static String webURL = "http://www.github.com/";
	public static String hubURL="http://10.102.52.199:4444/wd/hub";
	public static RemoteWebDriver driver;
	private RegistrationPage registrationPage;

	@BeforeMethod
	@Parameters({ "browser" })
	public void setUptestEnv(@Optional("browser") String browser) throws MalformedURLException {
		driver = BrowserCapabilitiesUtility.getDriver(hubURL,browser);
		driver.manage().window().maximize();
		driver.get("http://www.github.com/");
		registrationPage=new RegistrationPage();
		PageFactory.initElements(driver, registrationPage);
	}
	
	@AfterMethod
	public void tearDowntestEnv() {
		registrationPage=null;
	}

	@AfterClass
	public static void tearDownDriverEnv() {
		driver.close();
		driver=null;
	}
	
	@Test(priority=1)
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
	
	@Test(priority=2)
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
	
	@Test(priority=3)
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
	
	@Test(priority=4)
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
	
	@Test(priority=5)
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
	
	/*@Test(priority=6)
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
	
	@Test(priority=7)
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
	}*/
	
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

	
	/*public static String webURL = "http://www.github.com/login";
	public static String hubURL="http://10.102.52.199:4444/wd/hub";
	public static RemoteWebDriver driver;
	private LoginPage loginPage;

	@BeforeMethod
	@Parameters({ "browser" })
	public void setUptestEnv(@Optional("browser") String browser) throws MalformedURLException {
		driver = BrowserCapabilitiesUtility.getDriver(hubURL,browser);
		driver.manage().window().maximize();
		driver.get("http://www.github.com/login");
		loginPage=new LoginPage();
		PageFactory.initElements(driver, loginPage);
	}
	
	@Test(priority=1)
	public void testForBlankUserNameAndPassword() {
		loginPage.setUsername("");
		loginPage.setPassword("");
		loginPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//div[@class='container']")).getText();
		System.out.println("error Message:-"+actualErrorMessage);
		String message="Incorrect username or password.";
		assertEquals(actualErrorMessage, message);
	}

	@Test(priority=2)
	public void testForInValidUserNameAndValidPassword() {
		loginPage.setUsername(getInvalidUserName());
		loginPage.setPassword(getValidPassword());
		loginPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//div[@class='container']")).getText();
		System.out.println("error Message:-"+actualErrorMessage);
		String message="Incorrect username or password.";
		assertEquals(actualErrorMessage, message);
	}

	private String getValidPassword() {	
		return "45wefs";
	}

	private String getInvalidUserName() {	
		return "xrt";
	}

	@Test(priority=3)
	public void testForValidUserNameAndInValidPassword() {
		loginPage.setUsername(getValidUserName());
		loginPage.setPassword(getInvalidPassword());
		loginPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//div[@class='container']")).getText();
		System.out.println("error Message:-"+actualErrorMessage);
		String message="Incorrect username or password.";
		assertEquals(actualErrorMessage, message);
	}

	private String getInvalidPassword() {	
		return "xyz";
	}

	private String getValidUserName() {
		return "crossdeepak";
	}

	@Test(priority=4)
	public void testForInValidUserNameAndPassword() {
		loginPage.setUsername(getInvalidUserName());
		loginPage.setPassword(getInvalidPassword());
		loginPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//div[@class='container']")).getText();
		System.out.println("error Message:-"+actualErrorMessage);
		String message="Incorrect username or password.";
		assertEquals(actualErrorMessage, message);
	}

	@Test(priority=5)
	public void testForValidUserNameAndPassword() {
		loginPage.setUsername(getValidUserName());
		loginPage.setPassword(getValidPassword());
		loginPage.clickSubmitButton();
		String actualErrorMessage=driver.findElement(By.xpath("//meta[@name='user-login']" )).getAttribute("content");
		System.out.println("User:-"+actualErrorMessage);
		String message="crossdeepak";
		assertEquals(actualErrorMessage, message);
	}
	
	@AfterMethod
	public void tearDowntestEnv() {
		loginPage=null;
	}

	@AfterClass
	public static void tearDownDriverEnv() {
		driver.close();
		driver=null;
	}*/
}
