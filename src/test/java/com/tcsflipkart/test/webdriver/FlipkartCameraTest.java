package com.tcsflipkart.test.webdriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.tcsflipkart.common.Constants;

public class FlipkartCameraTest {
	public WebDriver driver;

	@Before
	public void launchBrowser() {
		System.setProperty(Constants.CHROME_DRIVER_NAME, Constants.CHROME_DRIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(Constants.WEBSITE_URL);
	}

	@Test
	public void searchCamera() throws InterruptedException {
		driver.findElement(By.className("_1dBPDZ")).sendKeys(Constants.USER_NAME);
		driver.findElement(By.xpath("//form//input[@type='password']")).sendKeys(Constants.PASSWORD);
		driver.findElement(By.className("_1LctnI")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.name(Constants.SEARCH_BOX)).sendKeys(Constants.KEY_WORD);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.className("Zhf2z-")).click();
		Thread.sleep(1000);
		String title = driver.getTitle();
		Assert.assertEquals(title,
				"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!",
				"Title assertion is failed!");
	}

	@After
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
		driver.quit();
	}

}
