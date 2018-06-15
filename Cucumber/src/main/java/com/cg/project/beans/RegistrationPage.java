package com.cg.project.beans;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage {
	@FindBy(id="user[login]")
	WebElement username;
	
	@FindBy(id="user[password]")
	WebElement password;
	
	@FindBy(id="user[email]")
	WebElement email;
	
	@FindBy(className="btn")
	WebElement button;

	public WebElement getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username.sendKeys(username);
	}

	public WebElement getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password.sendKeys(password);
	}

	public WebElement getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email.sendKeys(email);
	}

	public void clickSubmitButton() {
		button.submit();
	}
	
}
