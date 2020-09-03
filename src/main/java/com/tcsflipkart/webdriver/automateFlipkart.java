package com.tcsflipkart.webdriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.tcsflipkart.common.Constants;

public class automateFlipkart {

	public static WebDriver driver;

	/**
	 * @param URL
	 * @throws InterruptedException
	 */
	public static void initWebDriver(String URL) throws InterruptedException {
		System.setProperty(Constants.CHROME_DRIVER_NAME, Constants.CHROME_DRIVER);
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
	}

	public static void main(String[] args) throws InterruptedException {
		initWebDriver(Constants.WEBSITE_URL);
		flipkartLogin();
		driver.findElement(By.name(Constants.SEARCH_BOX)).sendKeys(Constants.KEY_WORD);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.className("Zhf2z-")).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.ADD_TO_CHART))).click();
		getElement(By.xpath(Constants.VIEW_CHART)).click();
		getElement(By.cssSelector("form[id='view-cart-form'] button")).click();
		Thread.sleep(1000);
		endSession();
	}

	public static WebElement getElement(final By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver arg0) {
				return arg0.findElement(locator);
			}
		});

		return element;
	}

	public static void flipkartLogin() {
		driver.findElement(By.className("_1dBPDZ")).sendKeys(Constants.USER_NAME);
		driver.findElement(By.xpath("//form//input[@type='password']")).sendKeys(Constants.PASSWORD);
		driver.findElement(By.className("_1LctnI")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void endSession() {
		driver.close();
		driver.quit();
	}
}
