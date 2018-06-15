package com.cg.githublogintest.utility;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserCapabilitiesUtility {
	public static RemoteWebDriver getDriver(String hubURL,String browser) throws MalformedURLException {
		return new RemoteWebDriver(new URL("http://10.102.52.199:4444/wd/hub"), getBrowserCapabilities(browser)); 
	}

	private static DesiredCapabilities getBrowserCapabilities(String browserType) {
		if(browserType.equalsIgnoreCase("chrome")) {
			
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.WINDOWS);
			return capability;
		}
		else if(browserType.equalsIgnoreCase("firefox")) {
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.WINDOWS);
			return capability;
		}
		else return null;
	}
}
