package com.cg.githublogintest.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	
	public LoginPage() {}

@FindBy(id="login_field")
WebElement username;

@FindBy(id="password")
WebElement password;

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

public void clickSubmitButton() {
	button.submit();
}


}
