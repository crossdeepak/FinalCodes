import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WEbDriverDemo {

	public static void main(String[] args) {
		try{
			System.setProperty("webdriver.chrome.driver", "D:\\Deepak\\corejava\\chromedriver.exe");
			WebDriver driver=new ChromeDriver();
			driver.get("http://www.google.com");
			driver.manage().window().maximize();
			
			WebElement searchField=driver.findElement(By.id("lst-ib"));
			searchField.sendKeys("royal enfield 350");
			searchField.submit();
			
			WebElement imagesLink=driver.findElements(By.linkText("Images")).get(0);
			imagesLink.click();
			
			WebElement imagesLink1=driver.findElements(By.linkText("red colour")).get(0);
			imagesLink1.click();
			
			WebElement imageElement=driver.findElements(By.cssSelector("a[class=rg_l]")).get(1);
			imageElement.click();
			
			/*WebElement imageLink=imageElement.findElements(By.tagName("img")).get(0);
			imageLink.click();*/
			
			
			
			
			
			/*WebElement searchField=driver.findElement(By.id("lst-ib"));
			searchField.sendKeys("pluralsight");
			searchField.submit();
			
			WebElement imagesLink=driver.findElements(By.linkText("Images")).get(0);
			imagesLink.click();
			
			WebElement imageElement=driver.findElements(By.cssSelector("a[class=rg_l]")).get(0);
			
			WebElement imageLink=imageElement.findElements(By.tagName("img")).get(0);
			imageLink.click();*/
			
			
		}finally{
			
		}

	}

}
