package com.cg.cucumber;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.cg.project.beans.LoginPage;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestCucumber {
	static WebDriver driver;
	LoginPage loginPage;

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Given("^Driver is open$")
	public void driver_is_open() throws Throwable {
	   
	    throw new PendingException();
	}

	@Given("^loginPage is there$")
	public void loginpage_is_there() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Username is blank$")
	public void username_is_blank() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^Password is blank$")
	public void password_is_blank() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^we get error message$")
	public void we_get_error_message() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	
	
	/*@Given("^there is a toothpaste on brush$")
	public void there_is_a_toothpaste_on_brush() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^the mouth is open$")
	public void the_mouth_is_open() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^the back teeth are brushed$")
	public void the_back_teeth_are_brushed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^the front teeth are brushed$")
	public void the_front_teeth_are_brushed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the teeth looks clean$")
	public void the_teeth_looks_clean() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the mouth feels fresh$")
	public void the_mouth_feels_fresh() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the braces aren't damaged$")
	public void the_braces_aren_t_damaged() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
*/

}
