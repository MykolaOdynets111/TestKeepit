package bonus.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	protected static WebDriver webDriver;

	@Test(enabled = true)
	public void setup() throws Exception {
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		webDriver.get("https://ws-test.keepit.com"); // go to URL
		Thread.sleep(2000);
		webDriver.manage().window().maximize();
		// login :
		webDriver.findElement(By.xpath("//INPUT[@id='login-form-login']")).sendKeys("automation@keepitqa.com");
		webDriver.findElement(By.xpath("//INPUT[@id='login-form-password']")).sendKeys("E#*b2wGIbFHz");
		webDriver.findElement(By.xpath("//INPUT[@id='login-form-login-button']")).click();
		Thread.sleep(5000);

		webDriver.findElement(By.xpath("//A[@id='cloud-devices-button']")).click(); // find “Add” under “Cloud Services”

		Thread.sleep(2000);
		// Make sure that it is possible to create a Office 365 Admin:
		Assert.assertTrue(
				webDriver.findElement(By.xpath("//SPAN[@class='o365-device-menu-item'][text()='Office 365 Admin']"))
						.isEnabled());
		System.out.println("It as possible to create a Office 365 Admin ");

		webDriver.quit();

	}
}
