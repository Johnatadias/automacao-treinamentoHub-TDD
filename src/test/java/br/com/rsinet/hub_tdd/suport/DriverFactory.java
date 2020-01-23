package br.com.rsinet.hub_tdd.suport;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	/*setando configurações nescessarias para selenium webdriver*/
	public static WebDriver createChromer() {
		System.setProperty("webdriver.chrome.driver", "C:/Drivers-Libs-Servidor/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.advantageonlineshopping.com/#/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		return driver;
	}
	
	/*fechando driver do chrome*/
	public static void quitChrome(WebDriver driver) {
		if(driver != null)
			driver.quit();
	}
}
