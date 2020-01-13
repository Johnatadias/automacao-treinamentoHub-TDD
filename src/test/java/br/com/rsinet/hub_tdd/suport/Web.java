package br.com.rsinet.hub_tdd.suport;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Web {

	public static WebDriver createChromer() {
		System.setProperty("webdriver.chrome.driver", "c:/Drivers-Libs-Servidor/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.advantageonlineshopping.com/#/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		return driver;
	}
	
	public static void killChromer(WebDriver driver) {
		driver.quit();
	}
}
