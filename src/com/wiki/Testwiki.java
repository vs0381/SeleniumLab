package com.wiki;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testwiki {
 
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
		           "/Users/vandana/Documents/workspace/exec/chromedriver");
		
		
		WebDriver driver = new ChromeDriver();
		

		driver.get("http://www.wikipedia.org");
		WebElement link;
		link = driver.findElement(By.id("js-link-box-en"));
		link.click();
		Thread.sleep(5000);
		WebElement searchBox;
		searchBox = driver.findElement(By.id("searchInput"));
		searchBox.sendKeys("Software");
		searchBox.submit();
		Thread.sleep(5000);
		driver.quit();
}
}